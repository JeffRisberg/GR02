package com.incra

import java.util.List 

/**
 * The <i>ProjectController</i> was based on generated code, but has been changed greatly to
 * use the ProjectService for business logic on projects and recalculations.
 *
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class ProjectController {
	def pageFrameworkService
	def tagUsageService
	def commentService
	def resourceService
	def projectService
	def accountService
	def sessionFactory
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static loggingInstructions = [
		list: [key: "Project List", severity: LogEntrySeverity.MEDIUM],
	]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	/**
	 * Show a list of Projects for the current user
	 */
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		EntityType etProject = EntityType.findByName("Project")
		
		List<Tag> tags = tagUsageService.getDistinctTags(etProject);
		
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'projectType', label: 'Project Type:', type: 'Select',
		values: ProjectType.list())
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'tags', label: 'Tags:', type: 'Select',
		values: tags)
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.name = params.name
		flash.projectType = params.projectType
		flash.tags = params.tags
		
		List<Long> rowIds2 = new ArrayList<Long>();
		
		if (params.tags) {
			// Prefetch to get the project ids that match the tag
			Long tagId = params.tags as Long
			
			def session = sessionFactory.currentSession
			
			def query = session.createSQLQuery("select project.id from project, tag_usage " +
			"where project.id = tag_usage.entity_Id " +
			"  and tag_usage.tag_Id = :tagId" +
			"  and tag_usage.entity_Type_Id = :etId");
			query.setParameter("tagId", tagId)
			query.setParameter("etId", etProject.id);
			List<Object> rowIds = query.list()
			
			for (Object o : rowIds) {
				rowIds2.add(o as Long)
			}
		}
		
		if (params.tags && rowIds2.size() == 0) {
			[filters: filters,
				projectInstanceList: [], projectInstanceTotal: 0]
		}
		else {
			def criteria = Project.createCriteria()
			def query = {
				and {
					if (params.name && params.name.trim()) {
						like("name", '%' + params.name + '%')
					}
					if (params.projectType) {
						def selectedProjectType = ProjectType.get(Integer.parseInt(params.projectType))
						if (selectedProjectType) {
							eq('type', selectedProjectType)
						}
					}
					if (currentUser != null) {
						or {
							isNull('user')						
							eq('user', currentUser)
						}
					}
					if (currentUser == null) {
						isNull('user')
					}
					if (params.tags) {
						'in'("id", rowIds2)
					}
				}
			}
			
			def results = criteria.list(params, query)
			
			[filters: filters, userIsLoggedIn: currentUser != null,
				projectInstanceList: results, projectInstanceTotal: results.totalCount]
		}
	}
	
	/**
	 * Show all Projects and their user (admin function)
	 */
	def listAll = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'projectType', label: 'Project Type:', type: 'Select',
		values: ProjectType.list())
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.name = params.name
		flash.projectType = params.projectType
		
		def criteria = Project.createCriteria()
		def query = {
			and {
				if (params.name && params.name.trim()) {
					like("name", '%' + params.name + '%')
				}
				if (params.projectType) {
					def selectedProjectType = ProjectType.get(Integer.parseInt(params.projectType))
					if (selectedProjectType) {
						eq('type', selectedProjectType)
					}
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, projectInstanceList: results, projectInstanceTotal: results.totalCount]
	}
	
	/**
	 * Show a summary of one project in a pop-up
	 */
	def summary = {
		Long projectId = params.id as Long
		Project projectInstance = Project.get(projectId)
		ROIProject roiProjectInstance = ROIProject.get(params.id)
		
		if (projectInstance) {
			ProjectCalculationDataPojo pcd = new ProjectCalculationDataPojo()
			
			projectService.recalc(projectInstance, roiProjectInstance, pcd)
			
			[projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
				pcd: pcd]
		}
		else {
			[]
		}
	}
	
	/**
	 * Show the information about one project
	 */
	def show = {		
		Long projectId = params.id as Long
		
		def projectInstance = Project.get(projectId)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			ROIProject roiProjectInstance = ROIProject.get(projectId)
			EntityType etProject = EntityType.findByName("Project")			
			
			List<Tag> tags = tagUsageService.getDistinctTags(etProject, projectId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 60)
			List<Comment> comments = commentService.getComments(etProject, projectId)
			
			List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
			ProjectPanePojo pane
			
			pane = new ProjectPanePojo("General Data Entry", "/projectShowGeneral", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectShowRecycledWater1", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectShowRecycledWater2", projectInstance.type)
			panes.add(pane)
			
			[panes: panes, projectInstance: projectInstance, roiProjectInstance: roiProjectInstance, 
				comments: comments, formattedTags: formattedTags]
		}
	}
	
	/** 
	 * Begin creating a new Project 
	 */
	def create = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		def projectInstance = new Project()
		def roiProjectInstance = new ROIProject()
		projectInstance.properties = params
		roiProjectInstance.properties = params
		
		def projectTypes = []
		projectTypes.add(ProjectType.findByName("Recycled Water"))
		
		def criteria = ProjectStatus.createCriteria()
		def query = {
			order("sequence", "asc")
		}
		
		def projectStatuses = criteria.list(params, query)
		
		List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
		ProjectPanePojo pane
		
		pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
		panes.add(pane)
		
		pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
		panes.add(pane)
		
		pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
		panes.add(pane)
		
		[panes: panes, projectInstance: projectInstance, roiProjectInstance: roiProjectInstance, 
			accounts: accountService.getValidAccounts(currentUser),
			projectTypes: projectTypes, projectStatuses: projectStatuses]
	}
	
	/**
	 * Finish creating a new project
	 */
	def save = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		def projectInstance = new Project(params)
		def roiProjectInstance = new ROIProject(params)
		def formattedTags = params.tags
		
		roiProjectInstance.project = projectInstance
		projectInstance.user = currentUser
		
		projectService.recalc(projectInstance, roiProjectInstance, null)
		
		if (getProject(params.name, currentUser)) {
			flash.message = "Project named '${params.name}' already exists"
			
			def projectTypes = []
			projectTypes.add(ProjectType.findByName("Recycled Water"))
			
			def criteria = ProjectStatus.createCriteria()
			def query = {
				order("sequence", "asc")
			}
			
			def projectStatuses = criteria.list(params, query)
			
			List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
			ProjectPanePojo pane
			
			pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
			panes.add(pane)
			
			render(view: "create", model: [panes: panes,
				projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
				accounts: accountService.getValidAccounts(currentUser),
				projectTypes: projectTypes, projectStatuses: projectStatuses])
		}
		else if (projectInstance.validate() && projectInstance.save(flush: true) && roiProjectInstance.save(flush: true)) {
			if (formattedTags && formattedTags.trim()) {
				EntityType etProject = EntityType.findByName("Project")
				Long projectId = projectInstance.id as Long
				tagUsageService.saveTags(etProject, projectId, formattedTags)
			}
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
			redirect(action: "show", id: projectInstance.id)
		}
		else {			
			def projectTypes = []
			projectTypes.add(ProjectType.findByName("Recycled Water"))
			
			def criteria = ProjectStatus.createCriteria()
			def query = {
				order("sequence", "asc")
			}
			
			def projectStatuses = criteria.list(params, query)
			
			List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
			ProjectPanePojo pane
			
			pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
			panes.add(pane)
			
			render(view: "create", model: [panes: panes,
				projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
				accounts: accountService.getValidAccounts(currentUser),
				projectTypes: projectTypes, projectStatuses: projectStatuses])
		}
	}
	
	/**
	 * Begin editing an existing project
	 */
	def edit = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		def projectInstance = Project.get(params.id)
		def roiProjectInstance = ROIProject.get(params.id)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			EntityType etProject = EntityType.findByName("Project")
			Long projectId = params.id as Long
			
			def projectTypes = []
			projectTypes.add(ProjectType.findByName("Recycled Water"))
			
			def criteria = ProjectStatus.createCriteria()
			def query = {
				order("sequence", "asc")
			}
			
			def projectStatuses = criteria.list(params, query)	
			
			List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
			ProjectPanePojo pane
			
			pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
			panes.add(pane)
			
			pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
			panes.add(pane)
			
			List<Tag> tags = tagUsageService.getDistinctTags(etProject, projectId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 999)
			
			[panes: panes, projectInstance: projectInstance, roiProjectInstance: roiProjectInstance, 
				formattedTags: formattedTags, accounts: accountService.getValidAccounts(currentUser), 
				projectTypes: projectTypes, projectStatuses: projectStatuses]
		}
	}
	
	/**
	 * Finish editing an existing project
	 */
	def update = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		def projectInstance = Project.get(params.id)		
		if (projectInstance) {
			def roiProjectInstance = ROIProject.findByProject(projectInstance)
			
			if (params.version) {
				def version = params.version.toLong()
				if (projectInstance.version > version) {
					
					projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this Project while you were editing")
					
					def projectTypes = []
					projectTypes.add(ProjectType.findByName("Recycled Water"))
					
					def criteria = ProjectStatus.createCriteria()
					def query = {
						order("sequence", "asc")
					}
					
					def projectStatuses = criteria.list(params, query)
					
					List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
					ProjectPanePojo pane
					
					pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
					panes.add(pane)
					
					pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
					panes.add(pane)
					
					pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
					panes.add(pane)
					
					render(view: "edit", model: [panes: panes,
						projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
						accounts: accountService.getValidAccounts(currentUser),
						projectTypes: projectTypes, projectStatuses: projectStatuses])
					return
				}
			}
			
			EntityType etProject = EntityType.findByName("Project")
			Long projectId = params.id as Long
			
			projectInstance.properties = params
			roiProjectInstance?.properties = params
			
			projectService.recalc(projectInstance, roiProjectInstance, null)
			
			Project possibleDuplicate = getProject(params.name, currentUser)
			if (possibleDuplicate != null && possibleDuplicate.id != projectId) {
				flash.message = "Project named '${params.name}' already exists"			
				
				def projectTypes = []
				projectTypes.add(ProjectType.findByName("Recycled Water"))
				
				def criteria = ProjectStatus.createCriteria()
				def query = {
					order("sequence", "asc")
				}
				
				def projectStatuses = criteria.list(params, query)
				
				List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
				ProjectPanePojo pane
				
				pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
				panes.add(pane)
				
				pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
				panes.add(pane)
				
				pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
				panes.add(pane)
				
				render(view: "edit", model: [panes: panes,
					projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
					accounts: accountService.getValidAccounts(currentUser),
					projectTypes: projectTypes, projectStatuses: projectStatuses])
			}
			else if (projectInstance.validate() && !projectInstance.hasErrors() && 
			roiProjectInstance.validate() && !roiProjectInstance.hasErrors() && 
			projectInstance.save(flush: true)) {
				if (params.tags && params.tags.trim()) {
					tagUsageService.saveTags(etProject, projectId, params.tags)
				}
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
				redirect(action: "show", id: projectInstance.id)
			}
			else {
				def projectTypes = []
				projectTypes.add(ProjectType.findByName("Recycled Water"))
				
				def criteria = ProjectStatus.createCriteria()
				def query = {
					order("sequence", "asc")
				}
				
				def projectStatuses = criteria.list(params, query)
				
				List<ProjectPanePojo> panes = new ArrayList<ProjectPanePojo>()
				ProjectPanePojo pane
				
				pane = new ProjectPanePojo("General Data Entry", "/projectEditGeneral", projectInstance.type)
				panes.add(pane)
				
				pane = new ProjectPanePojo("Recycled Water Data Entry", "/projectEditRecycledWater1", projectInstance.type)
				panes.add(pane)
				
				pane = new ProjectPanePojo("Capital Costs Data Entry", "/projectEditRecycledWater2", projectInstance.type)
				panes.add(pane)
				
				render(view: "edit", model: [panes: panes,
					projectInstance: projectInstance, roiProjectInstance: roiProjectInstance,
					accounts: accountService.getValidAccounts(currentUser),
					projectTypes: projectTypes, projectStatuses: projectStatuses])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Delete an existing project
	 */
	def delete = {
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			def roiProjectInstance = ROIProject.findByProject(projectInstance)
			
			try {
				roiProjectInstance?.delete(flush: true)
				projectInstance?.delete(flush: true)
				
				EntityType etProject = EntityType.findByName("Project")
				Long projectId = params.id as Long
				tagUsageService.deleteTags(etProject, projectId)
				
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Recalc all projects
	 */
	def recalcAll = {
		List<Project> projects = Project.findAll()
		
		for (Project project : projects) {
			ROIProject roiProject = ROIProject.get(project.id)
			
			projectService.recalc(project, roiProject, null)
			project.save()
			roiProject.save()
		}
		
		redirect(action: "list", params: params)
	}
	
	/**
	 * Support routine to find visible Project with specific name.  This is used in duplicate name
	 * checking.
	 */
	private Project getProject(String name, User user) {
		def criteria = Project.createCriteria()
		def query = {
			and {
				eq('name', name)
				if (user != null) {
					or {
						isNull('user')
						eq('user', user)
					}
				}
				if (user == null) {
					isNull('user')
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		if (results.size() > 0)
			return results[0]
		else
			return null
	}
}

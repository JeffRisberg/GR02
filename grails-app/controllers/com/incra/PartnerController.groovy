package com.incra

/**
 * The <i>PartnerController</i> class is based on generated code, but has 
 * been updated to handle filtering and tagging.
 *
 * @author Spoorthy Ananthaiah
 * @since 11/17/10
 */
class PartnerController {
	def pageFrameworkService
	def tagUsageService
	def commentService
	def sessionFactory
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def roList = {
		params.max = Math.min(params.max ? params.int('max') : 5, 50)
		[partnerInstanceList: Partner.list(params), partnerInstanceTotal: Partner.count()]
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 50)
		
		EntityType etPartner = EntityType.findByName("Partner")
		
		List<Tag> tags = tagUsageService.getDistinctTags(etPartner);
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'feature', label: 'Featured Only:', type: 'CheckBox')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'tags', label: 'Tags:', type: 'Select',
		values: tags)
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.name = params.name
		flash.feature = params.feature
		flash.tags = params.tags
		
		List<Long> rowIds2 = new ArrayList<Long>();
		
		if (params.tags) {
			// Prefetch to get the partner ids that match the tag
			Long tagId = params.tags as Long
			
			def session = sessionFactory.currentSession
			
			def query = session.createSQLQuery("select partner.id from partner, tag_usage " +
			"where partner.id = tag_usage.entity_Id " +
			"  and tag_usage.tag_Id = :tagId" +
			"  and tag_usage.entity_Type_Id = :etId");
			query.setParameter("tagId", tagId)
			query.setParameter("etId", etPartner.id);
			List<Object> rowIds = query.list()
			
			for (Object o : rowIds) {
				rowIds2.add(o as Long)
			}
		}
		
		def userIsAdministrator = pageFrameworkService.userIsAdministrator(session)
		
		if (params.tags && rowIds2.size() == 0) {
			[filters: filters, userIsAdministrator: userIsAdministrator,
				partnerInstanceList: [], partnerInstanceTotal: 0]
		}
		else {
			def criteria = Partner.createCriteria()
			def query = {
				and {
					if (params.name && params.name.trim()) {
						like("name", '%' + params.name + '%')
					}
					if (params.feature && params.feature.trim()) {
						eq("featured", true)
					}
					if (params.tags) {
						'in'("id", rowIds2)
					}
				}
			}
			
			def results = criteria.list(params, query)
			
			[filters: filters, userIsAdministrator: userIsAdministrator,
				partnerInstanceList: results, partnerInstanceTotal: results.totalCount]
		}
	}
	
	// tag-based
	def query = {
		params.max = Math.min(params.max ? params.int('max') : 5, 50)
		
		Tag tag = Tag.findByName(params.tag)
		List<Long> rowIds2 = new ArrayList<Long>();
		
		if (tag) {
			EntityType etPartner = EntityType.findByName("Partner")
			// native query for ids
			def session = sessionFactory.currentSession
			
			def query = session.createSQLQuery("select partner.id from partner, tag_usage " +
			"where partner.id = tag_usage.entity_Id " +
			"  and tag_usage.tag_Id = :tagId" +
			"  and tag_usage.entity_Type_Id = :etId");
			query.setParameter("tagId", tag.id)
			query.setParameter("etId", etPartner.id);
			List<Object> rowIds = query.list()
			
			for (Object o : rowIds) {
				rowIds2.add(o as Long)
			}
		}
		
		if (rowIds2.size() > 0) {
			// regular query for records
			def criteria = Partner.createCriteria()
			def query2 = {
				'in'("id", rowIds2)
			}
			
			def results = criteria.list(params, query2)
			
			render(view: "roList", model: [partnerInstanceList: results, partnerInstanceTotal: results.totalCount])
			return
		}
		else {
			render(view: "roList", model: [partnerInstanceList: [], partnerInstanceTotal: 0])
		}
	}
	
	def show = {
		def partnerInstance = Partner.get(params.id)
		if (!partnerInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
			redirect(action: "list")
		}
		else {
			EntityType etPartner = EntityType.findByName("Partner")
			Long partnerId = params.id as Long
			
			List<Tag> tags = tagUsageService.getDistinctTags(etPartner, partnerId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 60)
			List<Comment> comments = commentService.getComments(etPartner, partnerId)
			
			def userIsAdministrator = pageFrameworkService.userIsAdministrator(session)
			
			[userIsAdministrator: userIsAdministrator, partnerInstance: partnerInstance, 
				comments: comments, formattedTags: formattedTags]
		}
	}
	
	def create = {
		def partnerInstance = new Partner()
		partnerInstance.properties = params
		[partnerInstance: partnerInstance]
	}
	
	def save = {
		def partnerInstance = new Partner(params)
		def addressInstance = new Address(params)
		def contactInstance = new Contact(params)
		def formattedTags = params.tags
		
		partnerInstance.address = addressInstance
		partnerInstance.contact = contactInstance
		
		if (addressInstance.save(flush: true) && contactInstance.save(flush:true)) {
			
			if (partnerInstance.save(flush: true)) {
				if (formattedTags && formattedTags.trim()) {
					EntityType etPartner = EntityType.findByName("Partner")
					Long partnerId = partnerInstance.id as Long
					tagUsageService.saveTags(etPartner, partnerId, formattedTags)
				}
				flash.message = "${message(code: 'default.created.message', args: [message(code: 'partner.label', default: 'Partner'), partnerInstance.id])}"
				redirect(action: "show", id: partnerInstance.id)
			}
			else {
				render(view: "create", model: [partnerInstance: partnerInstance, addressInstance: addressInstance, contactInstance: contactInstance, formattedTags: formattedTags])
			}
		}
		else {
			render(view: "create", model: [partnerInstance: partnerInstance, addressInstance: addressInstance, contactInstance: contactInstance, formattedTags: formattedTags])
		}
	}
	
	def edit = {
		def partnerInstance = Partner.get(params.id)
		if (!partnerInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
			redirect(action: "list")
		}
		else {
			EntityType etPartner = EntityType.findByName("Partner")
			Long partnerId = params.id as Long
			
			List<Tag> tags = tagUsageService.getDistinctTags(etPartner, partnerId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 999)
			
			[partnerInstance: partnerInstance, addressInstance: partnerInstance?.address,
				contactInstance: partnerInstance?.contact, formattedTags: formattedTags]
		}
	}
	
	def update = {
		def partnerInstance = Partner.get(params.id)
		
		if (partnerInstance) {
			EntityType etPartner = EntityType.findByName("Partner")
			Long partnerId = params.id as Long
			
			def addressInstance = partnerInstance.address
			def contactInstance = partnerInstance.contact
			
			List<Tag> tags = tagUsageService.getDistinctTags(etPartner, partnerId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 60)
			
			if (params.version) {
				def version = params.version.toLong()
				if (partnerInstance.version > version) {
					
					partnerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'partner.label', default: 'partner')] as Object[], "Another user has updated this partner while you were editing")
					render(view: "edit", model: [partnerInstance: partnerInstance, addressInstance: addressInstance, contactInstance: contactInstance, formattedTags: formattedTags])
					return
				}
			}
			
			partnerInstance.properties = params
			addressInstance?.properties = params
			contactInstance?.properties = params
			
			if (!partnerInstance.hasErrors() && partnerInstance.save(flush: true)) {
				if (params.tags && params.tags.trim()) {
					tagUsageService.saveTags(etPartner, partnerId, params.tags)
				}
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'partner.label', default: 'Partner'), partnerInstance.id])}"
				redirect(action: "show", id: partnerInstance.id)
			}
			else {
				render(view: "edit", model: [partnerInstance: partnerInstance, addressInstance: addressInstance, contactInstance: contactInstance, formattedTags: formattedTags])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def partnerInstance = Partner.get(params.id)
		def addressInstance = partnerInstance.address
		def contactInstance = partnerInstance.contact
		
		if (partnerInstance) {
			try {
				if (addressInstance && contactInstance){
					
					partnerInstance.delete(flush: true)
					addressInstance.delete(flush: true)
					contactInstance.delete(flush: true)
					
					EntityType etPartner = EntityType.findByName("Partner")
					Long partnerId = params.id as Long
					tagUsageService.deleteTags(etPartner, partnerId)
					
					flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
					redirect(action: "list")
				}
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'partner.label', default: 'Partner'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def toggleFeatured = {
		def partnerInstance = Partner.get(params.id)
		if (partnerInstance) {
			if (params.featured == 'true') {
				partnerInstance.featured = true
			} else {
				partnerInstance.featured = false
			}
			partnerInstance.save()
		}
		render ''
	}
}

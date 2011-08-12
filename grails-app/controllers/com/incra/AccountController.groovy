package com.incra

/**
 * The <i>AccountController</i> is based on generated code, but has changed
 * greatly since to use ownership information about Accounts.
 *
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class AccountController {
	def pageFrameworkService
	def tagUsageService
	def commentService
	def accountService
	def sessionFactory	
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static loggingInstructions = [
		list: [key: "Account List", severity: LogEntrySeverity.MEDIUM],
	]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	/**
	 * Show a list of Accounts for the current user
	 */
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		EntityType etAccount = EntityType.findByName("Account")
		
		List<Tag> tags = tagUsageService.getDistinctTags(etAccount);
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'accountType', label: 'Facility Type:', type: 'Select',
		values: AccountType.list())
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'tags', label: 'Tags:', type: 'Select',
		values: tags)
		filters.add(dfp)		
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.name = params.name
		flash.accountType = params.accountType
		flash.tags = params.tags
		
		List<Long> rowIds2 = new ArrayList<Long>();
		
		if (params.tags) {
			// Prefetch to get the account ids that match the tag
			Long tagId = params.tags as Long
			
			def session = sessionFactory.currentSession
			
			def query = session.createSQLQuery("select account.id from account, tag_usage " +
			"where account.id = tag_usage.entity_Id " +
			"  and tag_usage.tag_Id = :tagId" +
			"  and tag_usage.entity_Type_Id = :etId");
			query.setParameter("tagId", tagId)
			query.setParameter("etId", etAccount.id);
			List<Object> rowIds = query.list()
			
			for (Object o : rowIds) {
				rowIds2.add(o as Long)
			}
		}
		
		AccountType atCustomer = AccountType.findByName("Customer")
		
		if (params.tags && rowIds2.size() == 0) {
			[filters: filters,
				accountInstanceList: [], accountInstanceTotal: 0]
		}
		else {
			def criteria = Account.createCriteria()
			def query = {
				and {
					eq('geoScale', prefs.geoScale)
					if (params.name && params.name.trim()) {
						like("name", '%' + params.name + '%')
					}
					if (params.accountType) {
						def selectedAccountType = AccountType.get(Integer.parseInt(params.accountType))
						if (selectedAccountType) {
							eq('type', selectedAccountType)
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
					ne('type', atCustomer)
					if (params.tags) {
						'in'("id", rowIds2)
					}
				}
			}
			
			def results = criteria.list(params, query)
			
			[filters: filters, geoScale: prefs.geoScale, userIsLoggedIn: currentUser != null,
				accountInstanceList: results, accountInstanceTotal: results.totalCount]
		}
	}
	
	def showChildren = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		// find parent object using id (db query)
		def accountInstance = Account.get(params.id)
		if (!accountInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
			redirect(action: "list")
		}
		else {
			Preferences prefs = Preferences.get(1)
			List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
			DisplayFilterPojo dfp;
			
			// find the children of parent (db query)
			def criteria = AccountLink.createCriteria()
			def query = {
				// parent must match the specified object
				eq('parent', accountInstance)
			}
			def links = criteria.list(params, query)
			
			List<Account> results = new ArrayList<Account>()
			for (AccountLink link : links) {
				results.add(link.getChild())
			}
			
			render(view: "list", model: [filters: filters, geoScale: prefs.geoScale, userIsLoggedIn: currentUser != null,
				accountInstanceList: results, accountInstanceTotal: links.totalCount])
		}
	}
	
	/**
	 * Show all Accounts and their user (admin function)
	 */
	def listAll = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'accountType', label: 'Facility Type:', type: 'Select',
		values: AccountType.list())
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.name = params.name
		flash.accountType = params.accountType
		
		def criteria = Account.createCriteria()
		def query = {
			and {
				if (params.name && params.name.trim()) {
					like("name", '%' + params.name + '%')
				}
				if (params.accountType) {
					def selectedAccountType = AccountType.get(Integer.parseInt(params.accountType))
					if (selectedAccountType) {
						eq('type', selectedAccountType)
					}
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, accountInstanceList: results, accountInstanceTotal: results.totalCount]
	}
	
	/**
	 * Populate the read-only display of accounts on the iframe of the front page
	 */
	def roList = {
		params.max = Math.min(params.max ? params.int('max') : 5, 50)
		
		String userName = params.userName
		String userId = params.profile
		def results
		
		if (userId) {
			// Look up the user with this userId.  If this user doesn't exist, create one as member.
			User user = User.findByUserId(userId)
			
			if (user == null) {
				if (userName != null) {
					Profile profile = new Profile(fullName: userName);
					profile.save();
					
					user = new User(userId: userId, profile: profile, password: "dummy1234");
					user.save();
					Role roleMember = Role.get(2);
					UserRole userRole = new UserRole(user: user, role: roleMember, effectiveStart: new Date());
					userRole.save();
					user.addToUserRoles(userRole);
					user.save();
				}
			}
			
			def criteria = Account.createCriteria()
			def query = {
				and {
					if (user != null) {
						eq('user', user)
					}
					if (user == null) {
						isNull('user')
					}
				}
			}
			
			results = criteria.list(query)
		}
		[accountInstanceList: results]
	}
	
	/**
	 * Show one account
	 */
	def show = {
		def accountInstance = Account.get(params.id)
		if (!accountInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
			redirect(action: "list")
		}
		else {
			def addressInstance = accountInstance.address
			EntityType etAccount = EntityType.findByName("Account")
			Long accountId = params.id as Long
			
			List<Tag> tags = tagUsageService.getDistinctTags(etAccount, accountId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 60)
			List<Comment> comments = commentService.getComments(etAccount, accountId)
			
			[accountInstance: accountInstance, addressInstance: addressInstance, comments: comments, formattedTags: formattedTags]
		}
	}
	
	/**
	 * Begin creating a new account
	 */
	def create = {
		def accountInstance = new Account()
		accountInstance.type = AccountType.findByName("Building")
		def addressInstance = new Address();
		
		accountInstance.properties = params
		[accountInstance: accountInstance, addressInstance: addressInstance,
			accountTypes: accountService.getValidAccountTypes()]
	}
	
	/** 
	 * Complete creating a new Account 
	 */
	def save = {
		User currentUser = pageFrameworkService.getCurrentUser(session)	
		
		def accountInstance = new Account(params)
		def addressInstance = new Address(params)
		def formattedTags = params.tags
		GeoScale geoScale = GeoScale.findByScale(2)
		
		accountInstance.address = addressInstance			
		accountInstance.geoScale = geoScale
		accountInstance.user = currentUser
		
		if (getAccount(params.name, currentUser)) {
			flash.message = "Facility named '${params.name}' already exists"
			
			render(view: "create", model: [accountInstance: accountInstance, addressInstance: addressInstance,
				accountTypes: accountService.getValidAccountTypes()])
		}
		else if (addressInstance.save(flush:true) && accountInstance.save(flush: true)) {
			if (formattedTags && formattedTags.trim()) {
				EntityType etAccount = EntityType.findByName("Account")
				Long accountId = accountInstance.id as Long
				tagUsageService.saveTags(etAccount, accountId, formattedTags)
			}
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'account.label', default: 'Facility'), accountInstance.id])}"
			redirect(action: "show", id: accountInstance.id)
		}
		else {
			render(view: "create", model: [accountInstance: accountInstance, addressInstance: addressInstance,
				accountTypes: accountService.getValidAccountTypes()])
		}
	}
	
	/**
	 * Begin editing an existing account
	 */
	def edit = {
		def accountInstance = Account.get(params.id)
		if (!accountInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Facility'), params.id])}"
			redirect(action: "list")
		}
		else {
			EntityType etAccount = EntityType.findByName("Account")
			Long accountId = params.id as Long
			
			List<Tag> tags = tagUsageService.getDistinctTags(etAccount, accountId)
			String formattedTags = tagUsageService.getFormattedTags(tags, 999)
			
			[accountInstance: accountInstance, addressInstance: accountInstance?.address, 
				formattedTags: formattedTags, accountTypes: accountService.getValidAccountTypes()]
		}
	}
	
	/**
	 * Finish editing an existing account
	 */
	def update = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		
		def accountInstance = Account.get(params.id)
		if (accountInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (accountInstance.version > version) {
					def addressInstance = accountInstance.address
					
					accountInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'account.label', default: 'Facility')] as Object[], "Another user has updated this Account while you were editing")					
					
					render(view: "edit", model: [accountInstance: accountInstance, addressInstance: addressInstance, 
						accountTypes: accountService.getValidAccountTypes()])
					return
				}
			}
			
			EntityType etAccount = EntityType.findByName("Account")
			Long accountId = params.id as Long
			
			def addressInstance = accountInstance.address
			
			accountInstance.properties = params
			addressInstance.properties = params
			
			Account possibleDuplicate = getAccount(params.name, currentUser)
			if (possibleDuplicate != null && possibleDuplicate.id != accountId) {
				flash.message = "Facility named '${params.name}' already exists"
				
				render(view: "edit", model: [accountInstance: accountInstance, addressInstance: addressInstance,
					accountTypes: accountService.getValidAccountTypes()])
			} 
			else if (!accountInstance.hasErrors() && accountInstance.save(flush: true)) {
				if (params.tags && params.tags.trim()) {
					tagUsageService.saveTags(etAccount, accountId, params.tags)
				}
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'account.label', default: 'Facility'), accountInstance.id])}"
				redirect(action: "show", id: accountInstance.id)
			}
			else {			
				render(view: "edit", model: [accountInstance: accountInstance, addressInstance: addressInstance, 
					accountTypes: accountService.getValidAccountTypes()])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Facility'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Delete an existing Account
	 */
	def delete = {
		def accountInstance = Account.get(params.id)
		if (accountInstance) {
			try {
				accountInstance.delete(flush: true)
				
				EntityType etAccount = EntityType.findByName("Account")
				Long accountId = params.id as Long
				tagUsageService.deleteTags(etAccount, accountId)
				
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'account.label', default: 'Facility'), params.id])}"				
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'account.label', default: 'Facility'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Facility'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Support routine to find visible Account with specific name.  This is used in duplicate name 
	 * checking.
	 */
	private Account getAccount(String name, User user) {
		AccountType atCustomer = AccountType.findByName("Customer")
		
		def criteria = Account.createCriteria()
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
				ne('type', atCustomer)
			}
		}
		
		def results = criteria.list(params, query)
		
		if (results.size() > 0)
			return results[0]
		else
			return null
	}
	
	def renderImage = {
		def account = Account.findById(params.id)
		
		if (account && account?.photo) {
			response.setContentLength(account.photo.length)
			response.getOutputStream().write(account.photo)
		} else {
			response.sendError(404)
		}
	}
}


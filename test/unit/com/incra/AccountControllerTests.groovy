package com.incra

import grails.test.*

class AccountControllerTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testShow() {
		EntityType et1 = new EntityType(name: "Account")
		AccountType at1 = new AccountType(name: "Building")
		AccountType at2 = new AccountType(name: "Data Center")
		GeoScale gsCluster = new GeoScale(name: "Cluster")
		
		Account account1 = new Account(name: "A", type: at1, geoScale: gsCluster);
		Account account2 = new Account(name: "B", type: at2, geoScale: gsCluster);
		
		mockDomain(EntityType, [et1])
		mockDomain(AccountType, [at1, at2])
		mockDomain(GeoScale, [gsCluster])
		mockDomain(Account, [account1, account2])
		mockDomain(Tag)
		mockDomain(TagUsage)
		
		this.controller.tagUsageService = new TagUsageService()
		this.controller.commentService = new MockCommentService()
		
		this.controller.params.id = 1
		def model1 = this.controller.show()
		assertEquals "A", model1["accountInstance"]?.name
		assertEquals '', model1["formattedTags"]
		
		this.controller.params.id = 2
		def model2 = this.controller.show()
		assertEquals "B", model2["accountInstance"]?.name
		assertEquals '', model2["formattedTags"]
		
		this.controller.tagUsageService.saveTags(et1, 1, "alpha, beta")
		this.controller.params.id = 1
		def model3 = this.controller.show()
		assertEquals "alpha, beta", model3["formattedTags"]
	}
}

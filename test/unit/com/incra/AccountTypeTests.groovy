package com.incra

import grails.test.*

class AccountTypeTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new AccountType(name: "Alpha")
		mockForConstraintsTests(AccountType, [alpha])
		
		def testAccountType = new AccountType()
		assertFalse testAccountType.validate()
		assertEquals "nullable", testAccountType.errors['name']
		
		testAccountType = new AccountType(name: "beta", description: "description")
		assertTrue testAccountType.validate()
	}
}

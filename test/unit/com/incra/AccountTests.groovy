package com.incra

import grails.test.*

class AccountTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Account(name: "Alpha")
		mockForConstraintsTests(Account, [alpha])
		
		def testAccount = new Account()
		assertFalse testAccount.validate()
		assertEquals "nullable", testAccount.errors['name']
		assertEquals "nullable", testAccount.errors['geoScale']
		assertEquals "nullable", testAccount.errors['type']
		
		AccountType at1 = new AccountType(name: "at1")
		GeoScale gs1 = new GeoScale(name: "gs1")
		Address address1 = new Address()
		
		testAccount = new Account(name: "beta", description: "description", geoScale: gs1, type: at1, address: address1)
		assertTrue testAccount.validate()
	}
}

package com.incra

import grails.test.*

class PartnerTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Partner(name: "Alpha")
		mockForConstraintsTests(Partner, [alpha])
		
		def testPartner = new Partner()
		assertFalse testPartner.validate()
		assertEquals "nullable", testPartner.errors['name']
		assertEquals "nullable", testPartner.errors['address']
		
		Address address1 = new Address();
		
		testPartner = new Partner(name: "beta", description: "description", address: address1)
		assertFalse testPartner.validate()
	}
}

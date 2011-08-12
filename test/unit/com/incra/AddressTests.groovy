package com.incra

import grails.test.*

class AddressTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Address(city: "Palo Alto")
		mockForConstraintsTests(Address, [alpha])
		
		def testAddress = new Address(stateCode: "zz")
		assertFalse testAddress.validate()
		assertEquals "inList", testAddress.errors['stateCode']
		
		testAddress = new Address(stateCode: 'CA' )
		assertTrue testAddress.validate()
	}
}

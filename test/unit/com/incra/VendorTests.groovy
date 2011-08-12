package com.incra

import grails.test.*

class VendorTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Vendor(name: "Alpha")
		mockForConstraintsTests(Vendor, [alpha])
		
		def testVendor = new Vendor()
		assertFalse testVendor.validate()
		assertEquals "nullable", testVendor.errors['name']
		assertEquals "nullable", testVendor.errors['address']
		
		Address address1 = new Address();
		
		testVendor = new Vendor(name: "beta", description: "description", address: address1)
		assertFalse testVendor.validate()
	}
}

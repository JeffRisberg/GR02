package com.incra

import grails.test.*

class ResourceTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Resource(name: "Alpha")
		mockForConstraintsTests(Resource, [alpha])
		
		def testResource = new Resource()
		assertFalse testResource.validate()
		assertEquals "nullable", testResource.errors['name']
		assertEquals "nullable", testResource.errors['type']
		
		ResourceType rt1 = new ResourceType(name: "rt1")
		
		testResource = new Resource(name: "beta", description: "description", type: rt1 )
		assertFalse testResource.validate()
	}
}

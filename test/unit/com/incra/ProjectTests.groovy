package com.incra

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Project(name: "Alpha")
		mockForConstraintsTests(Project, [alpha])
		
		def testProject = new Project()
		assertFalse testProject.validate()
		assertEquals "nullable", testProject.errors['name']
		assertEquals "nullable", testProject.errors['type']
		
		ProjectType pt1 = new ProjectType(name: "pt1")
		
		testProject = new Project(name: "beta", description: "description", type: pt1 )
		assertFalse testProject.validate()
	}
}

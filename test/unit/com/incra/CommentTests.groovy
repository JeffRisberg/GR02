package com.incra

import grails.test.*

class CommentTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testConstraints() {
		def alpha = new Comment()
		mockForConstraintsTests(Comment, [alpha])
		
		def testComment = new Comment()
		assertFalse testComment.validate()
		assertEquals "nullable", testComment.errors['entityType']
		
		EntityType etAccount = new EntityType(name: "Account")
		testComment = new Comment(entityType: etAccount, entityId: 5, text: "hello" )
		assertFalse testComment.validate()
	}
}

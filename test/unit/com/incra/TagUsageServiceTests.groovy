package com.incra

import grails.test.*

class TagUsageServiceTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testSomethingA() {
		mockLogging(TagUsageService)
		EntityType etAccount = new EntityType(name: "Account")
		EntityType etProject = new EntityType(name: "Project")
		Tag tagA = new Tag(name: "A")
		Tag tagB = new Tag(name: "B")
		Tag tagC = new Tag(name: "C")
		Tag tagD = new Tag(name: "D")
		TagUsage tagUsage1 = new TagUsage(entityType: etAccount, entityId: 1, tag: tagA)
		TagUsage tagUsage2 = new TagUsage(entityType: etAccount, entityId: 2, tag: tagA)
		TagUsage tagUsage3 = new TagUsage(entityType: etAccount, entityId: 2, tag: tagB)
		TagUsage tagUsage4 = new TagUsage(entityType: etProject, entityId: 1, tag: tagC)
		
		mockDomain(EntityType, [etAccount, etProject])
		mockDomain(Tag, [tagA, tagB, tagC, tagD])
		mockDomain(TagUsage, [
			tagUsage1,
			tagUsage2,
			tagUsage3,
			tagUsage4
		])
		
		TagUsageService tuService = new TagUsageService()
		def result1 = tuService.getTagUsages(etAccount)
		
		assertEquals(result1.size(), 3)
		assertEquals(result1[0], tagUsage1)
		
		def result2 = tuService.getTagUsages(etAccount, 2)
		
		assertEquals(result2.size(), 2)
		assertEquals(result2[1], tagUsage3)
		assertEquals(result2[1].tag, tagB)
		
		def result3 = tuService.getDistinctTags(etAccount)
		
		assertEquals(result3.size(), 2)
		assertEquals(result3[0], tagA)
	}
	
	void testSomethingB() {
		mockLogging(TagUsageService)
		EntityType etAccount = new EntityType(name: "Account")
		EntityType etProject = new EntityType(name: "Project")
		
		mockDomain(EntityType, [etAccount, etProject])
		mockDomain(Tag)
		mockDomain(TagUsage)
		
		TagUsageService tuService = new TagUsageService()
		tuService.saveTags(etAccount, 1, "alpha, beta, gamma")
		
		def result1 = tuService.getDistinctTags(etAccount)
		assertEquals(result1.size(), 3)
		
		tuService.saveTags(etAccount, 2, "alpha, beta, delta")
		def result2 = tuService.getDistinctTags(etAccount)
		assertEquals(result2.size(), 4)
		
		tuService.saveTags(etAccount, 2, "alpha, beta")
		def result3 = tuService.getDistinctTags(etAccount)
		assertEquals(result3.size(), 3)
	}
}

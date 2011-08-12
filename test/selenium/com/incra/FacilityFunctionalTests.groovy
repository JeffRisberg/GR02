package com.incra

import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*

/**
 * @author Jeffrey Risberg
 * @since 12/08/10
 */
@Mixin(SeleniumAware)
class FacilityFunctionalTests extends GroovyTestCase {
	
	@Test void facilitySearch() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		FacilityListPage facilityListPage = dashboardPage.clickTab("Facilities")
		int priorRowCount = facilityListPage.getRowCount()
		
		assertTrue(priorRowCount > 0)
		
		facilityListPage.setName("ENG")
		facilityListPage.clickSearch()
		
		int rowCount = facilityListPage.getRowCount()
		
		//assertTrue(rowCount > 0)
		//assertTrue(rowCount < priorRowCount)
		
		facilityListPage.setName("Zigamorf")
		facilityListPage.clickSearch()
		
		rowCount = facilityListPage.getRowCount()
		
		//assertEquals(0, rowCount)
	}
	
	@Test void facilityCreate() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		FacilityListPage facilityListPage = dashboardPage.clickTab("Facilities")
		int priorRowCount = facilityListPage.getRowCount()
		
		assertTrue priorRowCount > 0
		FacilityCreatePage facilityCreatePage = FacilityCreatePage.open()
		facilityCreatePage.setName("eBay Data Center")
		facilityCreatePage.setAddressLine1("2311 North First Street")
		facilityCreatePage.setCity("San Jose")
		facilityCreatePage.setStateCode("CA")
		FacilityShowPage facilityShowPage = facilityCreatePage.clickCreate()
		
		dashboardPage = facilityCreatePage.goHome()
		
		facilityListPage = dashboardPage.clickTab("Facilities")
		int posteriorRowCount = facilityListPage.getRowCount(); 
		
		assertTrue posteriorRowCount > 0
		
		facilityListPage.goHome()
	}
}

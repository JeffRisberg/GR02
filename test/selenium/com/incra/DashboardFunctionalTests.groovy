package com.incra

import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*

/**
 * @author Jeffrey Risberg
 * @since 12/08/10
 */
@Mixin(SeleniumAware)
class DashboardFunctionalTests extends GroovyTestCase {
	
	@Test void openDashboard() {
		DashboardPage dashboardPage = DashboardPage.open()
	}
	
	@Test void openFacilities() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		FacilityListPage facilityListPage = dashboardPage.clickTab("Facilities")
		
		facilityListPage.goHome()
	}
	
	@Test void openProjects() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		ProjectListPage projectListPage = dashboardPage.clickTab("Projects")
		
		projectListPage.goHome()
	}
	
	@Test void openFacility() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		FacilityListPage facilityListPage = dashboardPage.clickTab("Facilities")
		FacilityShowPage facilityShowPage = FacilityShowPage.open(1)
		
		facilityShowPage.goHome()
	}
	
	@Test void openProject() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		ProjectListPage projectListPage = dashboardPage.clickTab("Projects")
		ProjectShowPage projectShowPage = ProjectShowPage.open(1)
		
		projectShowPage.goHome()
	}
	
	@Test void openAllFacilities() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		FacilityListPage facilityListPage = dashboardPage.clickTab("Facilities")
		
		int rows = facilityListPage.getRowCount()
		
		for (int i = 0; i < rows; i++) {
			FacilityShowPage facilityShowPage = facilityListPage.openRow(i)			
			facilityShowPage.goToList()
		}
		
		facilityListPage.goHome()
	}
	
	@Test void openAllProjects() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		ProjectListPage projectListPage = dashboardPage.clickTab("Projects")
		
		int rows = projectListPage.getRowCount()	
		
		for (int i = 0; i < rows; i++) {
			ProjectShowPage projectShowPage = projectListPage.openRow(1)
			projectShowPage.goToList()
		}
		
		projectListPage.goHome()
	}
}

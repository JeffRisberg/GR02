package com.incra

import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*

/**
 * @author Jeffrey Risberg
 * @since 12/08/10
 */
@Mixin(SeleniumAware)
class ProjectFunctionalTests extends GroovyTestCase {
	
	@Test void projectSearch() {
		DashboardPage dashboardPage = DashboardPage.open()
		
		ProjectListPage projectListPage = dashboardPage.clickTab("Projects")
		int priorRowCount = projectListPage.getRowCount()
		
		//assertEquals(2, priorRowCount)
		
		projectListPage.setName("Tower")
		projectListPage.clickSearch()
		
		int rowCount = projectListPage.getRowCount()
		
		//assertEquals(1, rowCount)
		
		projectListPage.setName("Castle")
		projectListPage.clickSearch()
		
		rowCount = projectListPage.getRowCount()
		
		assertEquals(0, rowCount)
	}
}

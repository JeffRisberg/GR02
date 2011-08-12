package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/10/10
 */
@Mixin(SeleniumAware)
class DashboardPage extends Page {
	private static final URL = "/" 
	
	static def tabDefinitions = [
		'Dashboard': DashboardPage,
		'Facilities': FacilityListPage,
		'Projects': ProjectListPage ]
	
	// Factory method that opens the page
	static DashboardPage open() {
		new DashboardPage(URL)
	}
	
	// Constructor called by navigation methods in other page objects
	DashboardPage() {
		super()
	}
	
	// Constructor called by the factory method
	private DashboardPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		for (String key : tabDefinitions.keySet()) {
			if (!selenium.isElementPresent("link=" + key)) {
				throw new UnexpectedPageException("Invalid dashboard page - missing tab " + key)
			}
		}
		
		// Look for elements that are specific to the dashboard		
		if (!selenium.isTextPresent("Vendors") || !selenium.isTextPresent("Facilities")) {
			throw new UnexpectedPageException("Invalid Dashboard page")
		}
	}
	
	public Object clickTab(String tabName) {
		String theClass = tabDefinitions.get(tabName)
		if (theClass) {
			selenium.clickAndWait("link="+tabName)
			return tabDefinitions.get(tabName).newInstance()
		}
		else {
			throw new UnexpectedPageException("Invalid tab " + tabName)
		}
	}
}

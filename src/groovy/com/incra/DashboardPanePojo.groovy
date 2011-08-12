package com.incra

/**
 * The <i>DashboardPanePojo</i> holds information used to drive one pane of the main dashboard.
 * 
 * @author Jeffrey Risberg
 * @since 11/29/10
 */
class DashboardPanePojo {
	static int maxRecordCount = 4
	
	String label
	String manageLabel
	String plural
	String imgSrc
	String controllerName
	String templateName
	EntityType entityType
	List<Object> records
	boolean showManageLink;
	boolean showCreateLink;
	boolean showMoreLink;
	
	/** Constructor */
	DashboardPanePojo(String label, String plural, String imgSrc, String controllerName, String templateName, EntityType entityType) {
		this.label = label
		this.manageLabel = label
		this.plural = plural
		this.imgSrc = imgSrc
		this.controllerName = controllerName
		this.templateName = templateName
		this.entityType = entityType;
		this.records = new ArrayList<Object>()
		this.showManageLink = true
		this.showCreateLink = false
		this.showMoreLink = false
	}
	
	/** Constructor */
	DashboardPanePojo(String label, String manageLabel, String plural, String imgSrc, String controllerName, String templateName, EntityType entityType) {
		this.label = label
		this.manageLabel = manageLabel
		this.plural = plural
		this.imgSrc = imgSrc
		this.controllerName = controllerName
		this.templateName = templateName
		this.entityType = entityType;
		this.records = new ArrayList<Object>()
		this.showManageLink = true
		this.showCreateLink = false
		this.showMoreLink = false
	}
	
	/**
	 * Add one more record to the pane, cutting off at the maxRecordCount and instead setting the more flag.
	 */
	void addRecord(Object record) {
		if (records.size() >= maxRecordCount) {
			showMoreLink = true;
		}
		else {
			records.add(record);
		}
	}
	
	/** 
	 * Add a list of records to the pane
	 */
	void addRecords(List<Object> records) {
		for (Object record : records) {
			addRecord(record);
		}
	}
}

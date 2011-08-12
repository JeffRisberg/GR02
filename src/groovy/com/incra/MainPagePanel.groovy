package com.incra

/**
 * The <i>MainPagePanel</i> has a name and a list of MainPageItems.  It also maintains the scroll
 * position of the panel.
 * 
 * @author Jeff Risberg
 * @since 03/02/11
 */
class MainPagePanel {
	
	String label
	int firstItemIndex
	List<MainPageItem> items
	
	/** Constructor */
	MainPagePanel(String label) {
		this.label = label
		this.firstItemIndex = 0;
		this.items = new ArrayList<MainPageItem>()
	}
	
	/**
	 * Add one item to the panel
	 */
	void addItem(MainPageItem item) {
		items.add(item);
	}
}

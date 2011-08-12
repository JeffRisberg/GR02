package com.incra

/**
 * The <i>MainPagePanel</i> has a name and a list of MainPageItems.  It also maintains the scroll
 * position of the panel.
 * 
 * @author Jeff Risberg
 * @since 08/02/11
 */
class MarketplacePanel {

  String label
  List<MarketplaceItem> items

  /** Constructor */
  MarketplacePanel(String label) {
    this.label = label
    this.items = new ArrayList<MarketplaceItem>()
  }

  /**
   * Add one item to the panel
   */
  void addItem(MarketplaceItem item) {
    items.add(item);
  }
}

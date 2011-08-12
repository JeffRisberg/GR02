package com.incra

/**
 * The <i>MarketplaceItem</i> holds...
 * 
 * @author Jeff Risberg
 * @since 08/03/11
 */
class MarketplaceItem {

  Long id
  String label
  String imgSrc
  String cssStyleName
  String url

  /** Constructor */
  MarketplaceItem(Long id, String label, String imgSrc, String cssStyleName, String url) {
    this.id = id
    this.label = label
    this.imgSrc = imgSrc
    this.cssStyleName = cssStyleName
    this.url = url
  }
}


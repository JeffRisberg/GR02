package com.incra

/**
 * The <i>MainPageItem</i> holds...
 * 
 * @author Jeff Risberg
 * @since 03/03/11
 */
class MainPageItem {

  Long id
  String label
  String imgSrc
  String cssStyleName
  String url

  /** Constructor */
  MainPageItem(Long id, String label, String imgSrc, String cssStyleName, String url) {
    this.id = id
    this.label = label
    this.imgSrc = imgSrc
    this.cssStyleName = cssStyleName
    this.url = url
  }
}

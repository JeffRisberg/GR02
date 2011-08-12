package com.incra

/**
 * The <i>ItemPageController</i> class is a work area for implementing the new item page,
 * which is a page showing exactly one ContentItem, as well as links.
 *
 * The layout is based on Tallyfox
 *
 * @author Jeffrey Risberg
 * @since 07/12/11
 */
class ItemPageController {

  def pageFrameworkService
  def contentItemService

  def index = {

    // using the parameters, determine what content item is to be displayed.

    // ask the content match service to find 10 best matches
    def contentItem = null

    def message ="hello"

    [message: message, contenItem: contentItem]
  }
}
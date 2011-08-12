package com.incra

/**
 * The <i>FrontPageController</i> class is a work area for implementing the new nonFront page,
 * such as the page for "topic" water, or "topic" energy.
 * 
 * The layout is based on Tallyfox
 *
 * @author Jeffrey Risberg
 * @since 07/12/11
 */
class NonFrontPageController {

  def pageFrameworkService
  def contentItemService

  def index = {

    // using the parameters, determine what content qualifier this non-front-page is for.

    // ask the content match service to find 10 best matches
    def bestMatches = []

    def message ="hello"

    [message: message, bestMatches: bestMatches]
  }
}
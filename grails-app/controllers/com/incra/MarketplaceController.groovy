package com.incra

import com.incra.domain.ContentItemType

/**
 * The <i>MarketplaceController</i> class implements the EcoCloud Marketplace
 *
 * This uses ContentItem.
 * Also want to use ContentQualifiers
 * 
 * @author Jeffrey, Spoorthy
 * @since 08/03/11
 */
class MarketplaceController {

  def contentItemService
  def contentQualifierService

  /** Generates the marketplace for use within an iframe */
  def index = {
    List<ContentItem> items = contentItemService.getContentItems(true);

    int itemCount = items.size()
    List<MarketplacePanel> panels = new ArrayList<MarketplacePanel>()
    List<ContentQualifier> qualifiers = ContentQualifier.withCriteria { eq("type", "m") }

    for (ContentQualifier qualifier : qualifiers) {
      MarketplacePanel panel = new MarketplacePanel(qualifier.name)

      // Ask the ContentQualifierService for the matches.
      // Add the matches as MarketplaceItems to the marketplace panel
      for (int i = 0; i < itemCount; i++) {
        ContentItem contentItem = items[(int) (Math.random() * itemCount)]

        String name = contentItem.name
        ContentItemType type = contentItem.contentItemType
        String imgSrc = contentItem.photo;
        String url = contentItem.url

        MarketplaceItem item = new MarketplaceItem(contentItem.id, name, imgSrc, "iconProduct", url)
        panel.addItem(item);
      }
      panels.add(panel)
    }

    [panels: panels]
  }

  def renderImage = {
    def contentItemInstance = ContentItem.findById(params.id)

    if (contentItemInstance && contentItemInstance?.photo) {
      response.setContentLength(contentItemInstance.photo.length)
      response.getOutputStream().write(contentItemInstance.photo)
    } else {
      contentItemInstance = ContentItem.findById(1)

      if (contentItemInstance && contentItemInstance.photo) {
        response.setContentLength(contentItemInstance.photo.length)
        response.getOutputStream().write(contentItemInstance.photo)
      }
      else {
        response.sendError(404)
      }
    }
  }

  /** display the specified content item, using its template.  */
  def show = {
    // Get the content item
    ContentItem contentItem = ContentItem.get(params.id)

    if (contentItem != null) {
      // Get the template number from the content item
      Integer templateNumber = contentItem.templateNumber
      if (!templateNumber) {
        templateNumber = 1
      }
      String templateName = "template" + templateNumber

      // Construct the output map
      def model = [name: contentItem.name, description: contentItem.description]

      // render the specified template using the output map
      render(view: templateName, model: model)
    }
    else {
      flash.message = "There is no item that Id";
      render(view: "error")
    }
  }
}


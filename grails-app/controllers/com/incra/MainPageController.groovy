package com.incra

import com.incra.domain.ContentItemType

/**
 * The <i>MainPageController</i> class implements the marketplace version 1.
 *
 * @author Jeffrey Risberg
 * @since 03/03/11
 */
class MainPageController {

  def contentItemService

  /** Generates the marketplace for use within an iframe */
  def index = {
    List<MainPagePanel> panels = getPanelContent()

    [panels: panels]
  }

  private List<MainPagePanel> getPanelContent() {

    List<ContentItem> contentItems = contentItemService.getContentItems(true);

    List<Project> projects = Project.findAll()

    List<MainPagePanel> panels = new ArrayList<MainPagePanel>()
    MainPagePanel panel

    // Marketplace
    panel = new MainPagePanel("Marketplace")

    for (ContentItem contentItem : contentItems) {
      String name = contentItem.name
      ContentItemType type = contentItem.contentItemType
      String imgSrc = contentItem.photo;
      String url = contentItem.url

      if (type == ContentItemType.ORGANIZATION) {
        MainPageItem item = new MainPageItem(contentItem.id, name, imgSrc, "iconProduct", url)
        panel.addItem(item);
      }
    }
    panels.add(panel)

    // My Articles
    panel = new MainPagePanel("My Articles")
    for (ContentItem contentItem : contentItems) {
      String name = contentItem.name
      ContentItemType type = contentItem.contentItemType
      String imgSrc = null
      String url = contentItem.url

      if (type == ContentItemType.BOOK) {
        MainPageItem item = new MainPageItem(contentItem.id, name, imgSrc, "iconBook", url)
        panel.addItem(item);
      }
    }
    panels.add(panel)

    // My Projects
    panel = new MainPagePanel("My Projects")

    for (Project project : projects) {
      String name = project.name;
      String imgSrc = null
      MainPageItem item = new MainPageItem(null, name, imgSrc, "iconFolder", null)

      panel.addItem(item);
    }
    panels.add(panel)

    panels
  }

  def renderImage = {
    def contentItemInstance = ContentItem.findById(params.id)

    if (contentItemInstance && contentItemInstance?.photo) {
      response.setContentLength(contentItemInstance.photo.length)
      response.getOutputStream().write(contentItemInstance.photo)
    } else {
      response.sendError(404)
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


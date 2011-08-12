package com.incra

import com.incra.domain.ContentItemType

/**
 * The <i>ContentItemController</i> class is based on generated code, but has
 * been updated to handle filtering and tagging.
 *
 * @author Spoorthy Ananthaiah
 * @since 11/07/10
 */
class ContentItemController {
  PageFrameworkService pageFrameworkService
  TagUsageService tagUsageService
  CommentService commentService
  def sessionFactory

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def roList = {
    params.max = Math.min(params.max ? params.int('max') : 5, 50)

    def criteria = ContentItem.createCriteria()
    def query = {
      and {
        eq("partnerFlag", true)
        eq("approvedFlag", true)
      }
    }

    def results = criteria.list(params, query)

    [contentItemInstanceList: results, contentItemInstanceTotal: results.totalCount]
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 20, 50)

    EntityType etContentItem = EntityType.findByName("ContentItem")

    List<Tag> tags = tagUsageService.getDistinctTags(etContentItem);

    Preferences prefs = Preferences.get(1)
    List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
    DisplayFilterPojo dfp;

    dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
    filters.add(dfp)

    dfp = new DisplayFilterPojo(name: 'partnerFlag', label: 'Partner Only:', type: 'CheckBox')
    filters.add(dfp)

    dfp = new DisplayFilterPojo(name: 'tags', label: 'Tags:', type: 'Select',
        values: tags)
    filters.add(dfp)

    // Keep these values so we can rerender while maintaining filter value settings
    flash.name = params.name
    flash.partnerFlag = params.partnerFlag
    flash.tags = params.tags

    List<Long> rowIds2 = new ArrayList<Long>();

    if (params.tags) {
      // Prefetch to get the contentItem ids that match the tag
      Long tagId = params.tags as Long

      def session = sessionFactory.currentSession

      def query = session.createSQLQuery("select contentItem.id from contentItem, tag_usage " +
          "where contentItem.id = tag_usage.entity_Id " +
          "  and tag_usage.tag_Id = :tagId" +
          "  and tag_usage.entity_Type_Id = :etId");
      query.setParameter("tagId", tagId)
      query.setParameter("etId", etContentItem.id);
      List<Object> rowIds = query.list()

      for (Object o : rowIds) {
        rowIds2.add(o as Long)
      }
    }

    def userIsAdministrator = pageFrameworkService.userIsAdministrator(session)

    if (params.tags && rowIds2.size() == 0) {
      [filters: filters, userIsAdministrator: userIsAdministrator,
            contentItemInstanceList: [], contentItemInstanceTotal: 0]
    }
    else {
      def criteria = ContentItem.createCriteria()
      def query = {
        and {
          if (params.name && params.name.trim()) {
            like("name", '%' + params.name + '%')
          }
          if (params.partnerFlag && params.partnerFlag.trim()) {
            eq("partnerFlag", true)
          }
          if (params.tags) {
            'in'("id", rowIds2)
          }
        }
      }

      def results = criteria.list(params, query)

      [filters: filters, userIsAdministrator: userIsAdministrator,
            contentItemInstanceList: results, contentItemInstanceTotal: results.totalCount]
    }
  }

  // tag-based
  def query = {
    params.max = Math.min(params.max ? params.int('max') : 5, 50)

    Tag tag = Tag.findByName(params.tag)
    List<Long> rowIds2 = new ArrayList<Long>();

    if (tag) {
      EntityType etContentItem = EntityType.findByName("ContentItem")
      // native query for ids
      def session = sessionFactory.currentSession

      def query = session.createSQLQuery("select contentItem.id from contentItem, tag_usage " +
          "where contentItem.id = tag_usage.entity_Id " +
          "  and tag_usage.tag_Id = :tagId" +
          "  and tag_usage.entity_Type_Id = :etId");
      query.setParameter("tagId", tag.id)
      query.setParameter("etId", etContentItem.id);
      List<Object> rowIds = query.list()

      for (Object o : rowIds) {
        rowIds2.add(o as Long)
      }

      if (rowIds2.size() > 0) {
        // regular query for records
        def criteria = ContentItem.createCriteria()
        def query2 = { 'in'("id", rowIds2) }

        def results = criteria.list(params, query2)

        render(view: "roList", model: [contentItemInstanceList: results, contentItemInstanceTotal: results.totalCount])
        return
      }
    }

    render(view: "roList", model: [contentItemInstanceList: [], contentItemInstanceTotal: 0])
  }

  def show = {
    def contentItemInstance = ContentItem.get(params.id)
    if (!contentItemInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), params.id])}"
      redirect(action: "list")
    }
    else {
      EntityType etContentItem = EntityType.findByName("ContentItem")
      Long contentItemId = params.id as Long

      List<Tag> tags = tagUsageService.getDistinctTags(etContentItem, contentItemId)
      String formattedTags = tagUsageService.getFormattedTags(tags, 60)
      List<Comment> comments = commentService.getComments(etContentItem, contentItemId)

      def userIsAdministrator = pageFrameworkService.userIsAdministrator(session)

      [userIsAdministrator: userIsAdministrator, contentItemInstance: contentItemInstance,
            comments: comments, formattedTags: formattedTags]
    }
  }

  def create = {
    EntityType etContentItem = EntityType.findByName("ContentItem")
    Long contentItemId = params.id as Long

    List<ContentItemType> contentItemTypes = ContentItemType.selectAll()
    def contentItemInstance = new ContentItem()
    contentItemInstance.properties = params
    [contentItemInstance: contentItemInstance, contentItemTypes: contentItemTypes]
  }

  def save = {
    def contentItemInstance = new ContentItem(params)
    def formattedTags = params.tags

    if (contentItemInstance.save(flush: true)) {
      if (formattedTags && formattedTags.trim()) {
        EntityType etContentItem = EntityType.findByName("ContentItem")
        Long contentItemId = contentItemInstance.id as Long
        tagUsageService.saveTags(etContentItem, contentItemId, formattedTags)
      }
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), contentItemInstance.id])}"
      redirect(action: "show", id: contentItemInstance.id)
    }
    else {
      render(view: "create", model: [contentItemInstance: contentItemInstance, formattedTags: formattedTags])
    }
  }

  def edit = {
    def contentItemInstance = ContentItem.get(params.id)
    if (!contentItemInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), params.id])}"
      redirect(action: "list")
    }
    else {
      EntityType etContentItem = EntityType.findByName("ContentItem")
      Long contentItemId = params.id as Long

      List<ContentItemType> contentItemTypes = ContentItemType.selectAll()
      List<Tag> tags = tagUsageService.getDistinctTags(etContentItem, contentItemId)
      String formattedTags = tagUsageService.getFormattedTags(tags, 999)

      [contentItemInstance: contentItemInstance,
            contentItemTypes: contentItemTypes,
            formattedTags: formattedTags]
    }
  }

  def update = {
    def contentItemInstance = ContentItem.get(params.id)

    if (contentItemInstance) {
      EntityType etContentItem = EntityType.findByName("ContentItem")
      Long contentItemId = params.id as Long

      List<Tag> tags = tagUsageService.getDistinctTags(etContentItem, contentItemId)
      String formattedTags = tagUsageService.getFormattedTags(tags, 60)

      if (params.version) {
        def version = params.version.toLong()
        if (contentItemInstance.version > version) {

          contentItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
            message(code: 'contentItem.label', default: 'ContentItem')]
          as Object[], "Another user has updated this ContentItem while you were editing")
          render(view: "edit", model: [contentItemInstance: contentItemInstance, formattedTags: formattedTags])
          return
        }
      }

      contentItemInstance.properties = params

      if (!contentItemInstance.hasErrors() && contentItemInstance.save(flush: true)) {
        if (params.tags && params.tags.trim()) {
          tagUsageService.saveTags(etContentItem, contentItemId, params.tags)
        }
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), contentItemInstance.id])}"
        redirect(action: "show", id: contentItemInstance.id)
      }
      else {
        render(view: "edit", model: [contentItemInstance: contentItemInstance, formattedTags: formattedTags])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def contentItemInstance = ContentItem.get(params.id)

    if (true) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contentItem.label', default: 'ContentItem'), params.id])}"
      redirect(action: "list")
    }
  }

  def togglePartnerFlag = {
    def contentItemInstance = ContentItem.get(params.id)
    if (contentItemInstance) {
      if (params.partnerFlag == 'true') {
        contentItemInstance.partnerFlag = true
      } else {
        contentItemInstance.partnerFlag = false
      }
      contentItemInstance.save()
    }
    render ''
  }

  def togglePaidFlag = {
    def contentItemInstance = ContentItem.get(params.id)
    println params
    if (contentItemInstance) {
      if (params.paidFlag == 'true') {
       
        contentItemInstance.paidFlag = true
      } else {
        contentItemInstance.paidFlag = false
      }
      contentItemInstance.save()
    }
    render ''
  }

  def toggleApprovedFlag = {
    println params
    def contentItemInstance = ContentItem.get(params.id)
    if (contentItemInstance) {
      if (params.approvedFlag == 'true') {
        contentItemInstance.approvedFlag = true
      } else {
        contentItemInstance.approvedFlag = false
      }
      contentItemInstance.save()
    }
    render ''
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
}

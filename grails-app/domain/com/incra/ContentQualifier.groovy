package com.incra

import java.util.Date

/**
 * The <i>ContentQualifier</i> entity...
 * 
 * @author Jeff Risberg
 * @since 07/26/11
 */
class ContentQualifier {
  String name
  String type
  String description
  String imageUrl
  String tag
  Date lastUpdated
  Date dateCreated

  static constraints = {
    name(blank: false, unique: true)
    description(nullable: true)
    imageUrl(nullable: true)
    tag(nullable: true)
    dateCreated(display: false)
  }

  String toString() {
    "${name}"
  }
}

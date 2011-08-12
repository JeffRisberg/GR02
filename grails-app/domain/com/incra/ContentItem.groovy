package com.incra

import com.incra.domain.AbstractDomain
import com.incra.domain.ContentItemType

/**
 * The <i>ContentItem</i> entity has a name and a type, and also an associated set of tags.
 * This was created by merging the ContentItem, Vendor, and Partner classes.
 * 
 * @author Jeff Risberg
 * @since 03/05/11
 */
class ContentItem extends AbstractDomain {
  String name
  String description
  ContentItemType contentItemType
  String url
  Integer templateNumber
  byte[] photo
  String body

  // Make subclasses for these specific flags
  boolean partnerFlag = false
  boolean paidFlag = false
  boolean approvedFlag = false

  static mapping = { body type: "text" }

  static hasMany = [ contentSections : ContentSection ]

  static constraints = {
    name(blank: false, unique: true)
    description(maxSize: 500, nullable: true)
    contentItemType()
    body(maxSize: 500, nullable: true)
    photo(nullable: true, maxSize: 1000000)
    templateNumber(nullable: true)
    url(maxSize: 500, nullable: true)
    partnerFlag()
    paidFlag()
    approvedFlag()
    dateCreated(display: false)
  }



  String toString() {
    name
  }
}

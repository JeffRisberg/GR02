package com.incra

import java.util.List

/**
 * The <i>ContentQualifierService</i> service supports...
 * 
 * @author Jeffrey Risberg
 * @since 07/30/11
 */
class ContentQualifierService {

  static transactional = true

  List<ContentQualifier> findAllQualifiers() {
    return null
  }

  ContentQualifier findQualifierByName() {
    null;
  }

  // This should be a smart search - using tags and keyword weights
  List<ContentItem> findContentItems(ContentQualifier contentQualifier) {
    // return matches
    return null;
  }
}

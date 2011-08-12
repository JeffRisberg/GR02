package com.incra

import java.util.List

import com.incra.domain.ContentItemType

/**
 * The <i>ContentItem</i> service supports operations on content items, such as initialization
 * and lookup.
 * 
 * @author Jeffrey Risberg
 * @since 03/10/11
 */
class ContentItemService {

  static transactional = true

  /**
   * This is used in bootstrap initialization
   */
  ContentSection addContentSection(ContentItem contentItem, String name, String description, String website) {
    ContentSection result = new ContentSection(name: name, description: description, website: website)

    contentItem.addToContentItems(result)
    result
  }

  /**
   * Fetch all featured partner if featured flag is set  else retrieve all partners
   * @param featured
   *
   * @return
   */
  public List<ContentItem> getContentItems(boolean featured) {
    List<ContentItem> result =  ContentItem.createCriteria().list {
      and {
        eq("contentItemType", ContentItemType.ORGANIZATION)
        not { eq("id", 1L) }
        if (featured) {
          eq("approvedFlag", true)
        }
        eq("partnerFlag", true)
      }
    }

    result
  }

  /**
   * Choose a random subset of items for a given contentItem list
   * @param inputList
   * @param subsetSize
   * @param rand
   * @return
   */
  public List<ContentItem> getRandomSubList(List<ContentItem> inputList, int subsetSize, Random rand) {
    def inputSize = inputList.size()
    def index = 0
    def nPicked = 0
    def subList = []

    while (subsetSize > 0) {
      def choosenNumber = rand.nextInt(inputSize)
      if (choosenNumber < subsetSize) {
        subList[nPicked++] = inputList[index];
        subsetSize--;
      }
      inputSize--;
      index++;
    }

    return subList
  }
}

package com.incra

import java.util.Random

import com.incra.domain.ContentItemType;

/**
 * The <i>FrontPageController</i> class is a work area for implementing the new front page,
 * which is paired with the new NonFrontPage and the new ItemPage.
 * 
 * The layout of the Front page is based on Heather's input.
 * The layout of the NonFront page and the ItemPage is based on Tallyfox layouts
 *
 * @author Jeffrey, Spoorthy
 * @since 07/10/11
 */
class FrontPageController {

  def pageFrameworkService
  def contentItemService
  
  def topUpperLeftSection = {
    
    User currentUser = pageFrameworkService.getCurrentUser(session)
    String welcomeMessage = ""

    if (currentUser) {
      welcomeMessage = "Welcome back to EcoCloud! We are Silicon Valley business leaders, "+
          "industry experts, researchers and policy makers--striving to make our enterprises "+
          "and our region more ecologically, equitably, and economically sustainable."
    }
    else {
      welcomeMessage = "Welcome to EcoCloud! We are Silicon Valley business leaders, "+
          "industry experts, researchers and policy makers--striving to make our enterprises "+
          "and our region more ecologically, equitably, and economically sustainable."+
          "We invite you to join us so that you can blog and share content! "+
          "It's free. Here's How."
    }

    def items = []
    def item = [title: "News & Events", left: "connect browse & share",
          text: "Event Calendar, Feature Articles, Blogs, SSV Partners in the News, Virtual Conferences/Tradeshows"]
    items.add(item)
    item = [title: "Knowledge Base", left: "explore learn & teach",
          text: "Sustainability 101, Media, White Papers, Silicon Valley Infrastructure Maps, Archives"]
    items.add(item)
    item = [title: "Marketplace", left: "find expertise & solutions",
          text: "Product & Service Vendors, Be a Vendor, comparison Matrix Virtual Conference/Tradeshows"]
    items.add(item)
    item = [title: "Project Workshop", left: "collaborate & solve problems",
          text: "EcoCloud Projects, Facilities, Management & It Tools, Collaboration Tools"]
    items.add(item)

    def resources = ContentQualifier.findAllByType("r")
    
   
    /*
     resource = [title: 'Water', imgSrc: '/images/boxShadow.png']
     resources.add(resource);
     resource = [title: 'Materials', imgSrc: '/images/boxShadow.png']
     resources.add(resource);
     resource = [title: 'Air',imgSrc: '/images/boxShadow.png']
     resources.add(resource);
     resource = [title: 'Energy', imgSrc: '/images/boxShadow.png']
     resources.add(resource);
     */
    def sectors = ContentQualifier.findAllByType("s")
    /*
     sector = [title: 'Ecosystem Research', imgSrc: '/images/boxShadow.png']
     sectors.add(sector);
     sector = [title: 'Cleantech Innovation', imgSrc: '/images/boxShadow.png']
     sectors.add(sector);
     sector = [title: 'Infrastructure & Facilities', imgSrc: '/images/boxShadow.png']
     sectors.add(sector);
     sector = [title: 'Enterprise Sustainability', imgSrc: '/images/boxShadow.png']
     sectors.add(sector);
     sector = [title: 'Public Policy & Regulation', imgSrc: '/images/boxShadow.png']
     sectors.add(sector);
     */
    
    [welcomeMessage: welcomeMessage,
      items: items, resources: resources, sectors: sectors]
  }
  
  def topUpperRightSection ={
  
    def partnerText = "Thank you for supporting our partners:"
    def featuredPartners = true
    def listSizeToDisplay = 6

    /**
     * Get the featured partners to display on the front page
     */
    def partnerList = contentItemService.getContentItems(featuredPartners)
    
    
    /**
     * Choose a random subset from the featured partners
     */
    def rand = new Random(System.currentTimeMillis())

    def chosenPartnerList = contentItemService.getRandomSubList(partnerList, listSizeToDisplay, rand)
       
     [partnerText: partnerText, chosenPartners: chosenPartnerList]
    
  }

  def bottomLowerLeftSection = {
    def newsItems = ContentItem.findAllByContentItemType(ContentItemType.NEWS)
    [newsItems:newsItems]
  }
  
  
  def index = {
    
        User currentUser = pageFrameworkService.getCurrentUser(session)
        String welcomeMessage = ""
    
        if (currentUser) {
          welcomeMessage = "Welcome back to EcoCloud! We are Silicon Valley business leaders, "+
              "industry experts, researchers and policy makers--striving to make our enterprises "+
              "and our region more ecologically, equitably, and economically sustainable."
        }
        else {
          welcomeMessage = "Welcome to EcoCloud! We are Silicon Valley business leaders, "+
              "industry experts, researchers and policy makers--striving to make our enterprises "+
              "and our region more ecologically, equitably, and economically sustainable."+
              "We invite you to join us so that you can blog and share content! "+
              "It's free. Here's How."
        }
    
        def items = []
        def item = [title: "News & Events", left: "connect browse & share",
              text: "Event Calendar, Feature Articles, Blogs, SSV Partners in the News, Virtual Conferences/Tradeshows"]
        items.add(item)
        item = [title: "Knowledge Base", left: "explore learn & teach",
              text: "Sustainability 101, Media, White Papers, Silicon Valley Infrastructure Maps, Archives"]
        items.add(item)
        item = [title: "Marketplace", left: "find expertise & solutions",
              text: "Product & Service Vendors, Be a Vendor, comparison Matrix Virtual Conference/Tradeshows"]
        items.add(item)
        item = [title: "Project Workshop", left: "collaborate & solve problems",
              text: "EcoCloud Projects, Facilities, Management & It Tools, Collaboration Tools"]
        items.add(item)
    
        def resources = ContentQualifier.findAllByType("r")
        
       
        /*
         resource = [title: 'Water', imgSrc: '/images/boxShadow.png']
         resources.add(resource);
         resource = [title: 'Materials', imgSrc: '/images/boxShadow.png']
         resources.add(resource);
         resource = [title: 'Air',imgSrc: '/images/boxShadow.png']
         resources.add(resource);
         resource = [title: 'Energy', imgSrc: '/images/boxShadow.png']
         resources.add(resource);
         */
        def sectors = ContentQualifier.findAllByType("s")
        /*
         sector = [title: 'Ecosystem Research', imgSrc: '/images/boxShadow.png']
         sectors.add(sector);
         sector = [title: 'Cleantech Innovation', imgSrc: '/images/boxShadow.png']
         sectors.add(sector);
         sector = [title: 'Infrastructure & Facilities', imgSrc: '/images/boxShadow.png']
         sectors.add(sector);
         sector = [title: 'Enterprise Sustainability', imgSrc: '/images/boxShadow.png']
         sectors.add(sector);
         sector = [title: 'Public Policy & Regulation', imgSrc: '/images/boxShadow.png']
         sectors.add(sector);
         */
    
        def partnerText = "Thank you for supporting our partners:"
        def featuredPartners = true
        def listSizeToDisplay = 6
    
        /**
         * Get the featured partners to display on the front page
         */
        def partnerList = contentItemService.getContentItems(featuredPartners)
        def newsItems = ContentItem.findAllByContentItemType(ContentItemType.NEWS)
    
        /**
         * Choose a random subset from the featured partners
         */
        def rand = new Random(System.currentTimeMillis())
    
        def chosenPartnerList = contentItemService.getRandomSubList(partnerList, listSizeToDisplay, rand)
        println chosenPartnerList
    
        [welcomeMessage: welcomeMessage,
              items: items, resources: resources, sectors: sectors,
              partnerText: partnerText, chosenPartners: chosenPartnerList, newsItems:newsItems]
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
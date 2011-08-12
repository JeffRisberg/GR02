package com.incra

import com.incra.domain.AbstractDomain

/**
 * The <i>Partner</i> class is the primary class for storing Partner information.  Partners are
 * geographic objects.  The 'description' field is a textArea.
 * 
 * @author Spoorthy Ananthaiah
 * @since 11/11/10
 */
class Partner extends AbstractDomain {

	String name
	Address address
	Contact contact
	String description
	String website
	boolean featured

	static constraints = {
		name(blank: false, unique: true, maxSize: 50)
		address()
		contact(nullable: true)
		website(maxSize: 60)
		description(maxSize: 500, nullable: true)
		featured()
		dateCreated(display: false)
	}

	static mapping = {
		address lazy: false
		contact lazy: false
	}

	String toString() {
		name
	}
}

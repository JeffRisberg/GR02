package com.incra

import java.util.Date;

/** 
 * The <i>Resource</i> entity describes something that can be bought, sold, consumed, or produced. 
 * Examples are electricity, gasoline, diesel, freshwater, graywater, etc. Resources are grouped 
 * into types.  While each of Resource and ResourcePrice have their own controllers, they both use 
 * the ResourceService for business logic such as lookups and conversions.
 * 
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class Resource {
	
	String name
	UnitOfMeasure uom
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique: true)
		type()
		uom()
		dateCreated(display: false)
	}
	
	static belongsTo = [ type : ResourceType ];
	
	String toString() {
		return name;
	}
}

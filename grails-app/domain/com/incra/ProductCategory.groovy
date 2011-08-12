package com.incra

import java.util.Date;

import com.incra.domain.AbstractDomain;

/**
 * The <i>ProductCategory</i> class...
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductCategory extends AbstractDomain {
	
	String name
	String description
	
	static constraints = {
		name(blank: false, unique: true, maxSize: 50)	
		description(nullable: true)
	}
	
	static transients = getTransients_ProductCategory();
	
	static protected def getTransients_ProductCategory() {
		def result = [];
		result.addAll(getTransients_AbstractDomain());
		return result;
	}
	
	static hasMany = [ products : ProductCategory ]
}

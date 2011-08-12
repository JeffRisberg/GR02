package com.incra

/**
 * The <i>PipelineFoot</i> entity is a data-holder that records the cost of one foot of a given
 * type of pipeline, such as buried or above-ground.
 * 
 * @author Jeffrey Risberg
 * @since 12/01/10
 */
class PipelineFoot {
	
	String name
	int seqNum
	double cost
	
	static constraints = {
		name(unique: true)
		seqNum()
		cost()
	}
	
	static mapping = { sort "seqNum" }
	
	String toString() {
		name
	}
}

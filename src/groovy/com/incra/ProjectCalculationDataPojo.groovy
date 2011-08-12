package com.incra

/**
 * The <i>ProjectCalculationDataPojo</i> is a pojo that holds information
 * calculated during the Project calc.  It is used to drive the summary 
 * display for a Project.
 * 
 * @author Jeff Risberg
 * @since 12/02/10
 */
class ProjectCalculationDataPojo {
	double currentRate
	double proposedRate
	double currentUsageHCF
	double proposedUsageHCF
	double currentChemicalCosts
	double proposedChemicalCosts
	double currentAMTCosts
	double proposedAMTCosts
	double totalCurrentCosts
	double totalProposedCosts
	double monthlySavings
	
	double totalPipelineFeet
	String infrastructureOptions
}

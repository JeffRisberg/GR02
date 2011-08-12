package com.incra

/**
 * The <i>ROIProject</i> entity contains fields that are specific to Reclaimed 
 * Water ROI projects.
 * 
 * @author Jeffrey Risberg
 * @since 11/26/10
 */
class ROIProject {
	
	double currentMonthlyWaterUse = 10000.0
	double currentCycles = 3.5
	double proposedCycles = 4.0
	Provider currentProvider
	Provider proposedProvider
	double currentChemicalCosts = 250.0
	double currentAdministrationCosts = 50.0
	double currentMonitoringAndTestingCosts = 125.0
	double proposedAdministrationCosts = 75.0
	double proposedMonitoringAndTestingCosts = 200.0
	double proposedOtherCostSavings = 0.0
	
	Double distance1 = 0.0
	PipelineFoot pipelineFoot1
	Double distance2 = 0.0
	PipelineFoot pipelineFoot2
	Double distance3 = 0.0
	PipelineFoot pipelineFoot3
	Double distance4 = 0.0
	PipelineFoot pipelineFoot4
	
	Double basinLevelControlValve = 2000.0
	Double blowdownControlValve = 1500.0
	Double chemicalFeedSkid = 12000.0
	Double corrosionCouponRack = 385.0
	Double oxidizingBiocideFeeder = 150.0
	Double pHConductivityController = 2500.0
	
	double otherCapitalImprovements = 10000.0
	
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		project()
		
		// Input information
		currentMonthlyWaterUse(min: 1000.0d)
		currentCycles(min: 2.0d)
		proposedCycles(min: 2.0d)
		currentProvider(nullable: true)
		proposedProvider(nullable: true)
		currentChemicalCosts()
		currentAdministrationCosts()
		currentMonitoringAndTestingCosts()
		proposedAdministrationCosts()
		proposedMonitoringAndTestingCosts()	
		proposedOtherCostSavings(nullable: true)	
		
		distance1(nullable: true)
		pipelineFoot1(nullable: true)
		distance2(nullable: true)
		pipelineFoot2(nullable: true)
		distance3(nullable: true)
		pipelineFoot3(nullable: true)
		distance4(nullable: true)
		pipelineFoot4(nullable: true)
		
		basinLevelControlValve(nullable: true)
		blowdownControlValve(nullable: true)
		chemicalFeedSkid(nullable: true)
		corrosionCouponRack(nullable: true)
		oxidizingBiocideFeeder(nullable: true)
		pHConductivityController(nullable: true)
		
		otherCapitalImprovements(nullable: true)
		
		dateCreated(display: false)
	}
	
	static belongsTo = [ project : Project ]
	
	String toString() {
		"ROIProject for project " + project.id
	}
}

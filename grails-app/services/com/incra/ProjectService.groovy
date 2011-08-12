package com.incra

import java.util.Date;

/**
 * The <i>ProjectService</i> supports the Project module.  The main function is the ROI calculation.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class ProjectService {
	
	def resourceService
	
	/**
	 * Get the ProjectSession, creating it if needed.
	 */
	ProjectSession getProjectSession(def session) {
		ProjectSession pSession = (ProjectSession) session.pSession;
		
		if (pSession == null) {
			pSession = new ProjectSession();
			session.pSession = pSession;
		}
		return pSession
	}
	
	/**
	 * Recalc method
	 */
	void recalc(Project projectInstance, ROIProject roiProjectInstance, 
	ProjectCalculationDataPojo pcd) {
		log.info "Recalculating project " + projectInstance.name + " for " + projectInstance.user
		
		Resource resFreshwater = Resource.findByName("Freshwater")
		Resource resRecycledWater = Resource.findByName("Recycled Water")
		
		Provider currentProvider = roiProjectInstance?.currentProvider
		Provider proposedProvider = roiProjectInstance?.proposedProvider
		
		if (currentProvider && proposedProvider) {
			// Compute the monthly savings
			double currentUsageHCF = roiProjectInstance.currentMonthlyWaterUse / 748
			double currentChemicalCosts = roiProjectInstance.currentChemicalCosts
			double currentCycles = roiProjectInstance.currentCycles
			double proposedCycles = roiProjectInstance.proposedCycles
			
			double currentWaterRate = resourceService.getPrice(currentProvider, resFreshwater)
			double proposedWaterRate = resourceService.getPrice(proposedProvider, resRecycledWater)
			
			log.info " currentUsageHCF " + currentUsageHCF
			if (pcd) pcd.currentUsageHCF = currentUsageHCF
			if (pcd) pcd.currentChemicalCosts = currentChemicalCosts
			if (pcd) pcd.currentAMTCosts = roiProjectInstance.currentAdministrationCosts + roiProjectInstance.currentMonitoringAndTestingCosts
			if (pcd) pcd.currentRate = currentWaterRate
			if (pcd) pcd.proposedRate = proposedWaterRate
			
			double totalCurrentCosts = 0.0;
			totalCurrentCosts += roiProjectInstance.currentChemicalCosts
			totalCurrentCosts += roiProjectInstance.currentAdministrationCosts
			totalCurrentCosts += roiProjectInstance.currentMonitoringAndTestingCosts
			totalCurrentCosts += currentUsageHCF * currentWaterRate
			
			log.info " totalCurrentCosts " + totalCurrentCosts
			if (pcd) pcd.totalCurrentCosts = totalCurrentCosts
			
			double chemicalUsageIncreasePercent = 100*((currentCycles/proposedCycles)-1)
			double proposedUsageHCF = currentUsageHCF * 
					((currentCycles-1)/currentCycles) *
					(proposedCycles/(proposedCycles-1))
			if (Double.isNaN(proposedUsageHCF))
				proposedUsageHCF = currentUsageHCF
			
			double proposedChemicalCosts = 
					(1 + chemicalUsageIncreasePercent/100) *
					currentChemicalCosts *
					(proposedUsageHCF/proposedCycles) *
					(currentCycles/currentUsageHCF)
			if (Double.isNaN(proposedChemicalCosts))
				proposedChemicalCosts = currentChemicalCosts
			
			log.info " proposedUsageHCF " + proposedUsageHCF
			log.info " proposedChemicalCosts " + proposedChemicalCosts
			if (pcd) pcd.proposedUsageHCF = proposedUsageHCF
			if (pcd) pcd.proposedChemicalCosts = proposedChemicalCosts
			if (pcd) pcd.proposedAMTCosts = roiProjectInstance.proposedAdministrationCosts + roiProjectInstance.proposedMonitoringAndTestingCosts			
			
			double totalProposedCosts = 0.0
			totalProposedCosts += proposedChemicalCosts
			totalProposedCosts += roiProjectInstance.proposedAdministrationCosts
			totalProposedCosts += roiProjectInstance.proposedMonitoringAndTestingCosts
			totalProposedCosts += proposedUsageHCF * proposedWaterRate
			if (roiProjectInstance.proposedOtherCostSavings)
				totalProposedCosts -= roiProjectInstance.proposedOtherCostSavings
			log.info " totalProposedCosts " + totalProposedCosts
			if (pcd) pcd.totalProposedCosts = totalProposedCosts
			
			double monthlySavings = (totalCurrentCosts - totalProposedCosts)				
			log.info "Monthly savings " + monthlySavings
			if (pcd) pcd.monthlySavings = monthlySavings
			
			projectInstance.monthlySavings = monthlySavings
			
			// Compute the capital cost
			StringBuffer sb = new StringBuffer();
			double feet = 0.0
			double sum = 0.0;
			
			if (roiProjectInstance.distance1 && roiProjectInstance.pipelineFoot1) {
				feet += roiProjectInstance.distance1;
				sum += roiProjectInstance.distance1 * roiProjectInstance.pipelineFoot1.cost;
			}
			
			if (roiProjectInstance.distance2 && roiProjectInstance.pipelineFoot2) {
				feet += roiProjectInstance.distance2
				sum += roiProjectInstance.distance2 * roiProjectInstance.pipelineFoot2.cost;
			}
			
			if (roiProjectInstance.distance3 && roiProjectInstance.pipelineFoot3) {
				feet += roiProjectInstance.distance3
				sum += roiProjectInstance.distance3 * roiProjectInstance.pipelineFoot3.cost;
			}
			
			if (roiProjectInstance.distance4 && roiProjectInstance.pipelineFoot4) {
				feet += roiProjectInstance.distance4
				sum += roiProjectInstance.distance4 * roiProjectInstance.pipelineFoot4.cost;
			}
			if (pcd) pcd.totalPipelineFeet = feet
			
			if (roiProjectInstance.basinLevelControlValve) {
				sb.append("Basin Level Control Valve")
				sum += roiProjectInstance.basinLevelControlValve
			}
			if (roiProjectInstance.blowdownControlValve) {
				if (sb.length()) sb.append(", ")
				sb.append("Blowdown Control Valve")
				sum += roiProjectInstance.blowdownControlValve
			}
			if (roiProjectInstance.chemicalFeedSkid) {
				if (sb.length()) sb.append(", ")
				sb.append("Chemical Feed Skid")
				sum += roiProjectInstance.chemicalFeedSkid
			}
			if (roiProjectInstance.corrosionCouponRack) {
				if (sb.length()) sb.append(", ")
				sb.append("Corrosion Coupon Rack")
				sum += roiProjectInstance.corrosionCouponRack
			}
			if (roiProjectInstance.oxidizingBiocideFeeder) {
				if (sb.length()) sb.append(", ")
				sb.append("Oxidizing Biocide Feeder")
				sum += roiProjectInstance.oxidizingBiocideFeeder
			}
			if (roiProjectInstance.pHConductivityController) {
				if (sb.length()) sb.append(", ")
				sb.append("pH Conductivity Controller")
				sum += roiProjectInstance.pHConductivityController
			}
			
			if (roiProjectInstance.otherCapitalImprovements) {
				sum += roiProjectInstance.otherCapitalImprovements
			}
			if (pcd) pcd.infrastructureOptions = sb.toString()
			
			log.info "Capital invest " + sum
			projectInstance.capitalInvest = sum		
			
			// Compute the paybackMonths and the ROI
			if (monthlySavings != 0.0 && sum != 0.0) {
				projectInstance.paybackMonths = sum/monthlySavings
				if (projectInstance.paybackMonths < 0.0) {
					projectInstance.paybackMonths = null
				}
				projectInstance.roi = 100.0 * (monthlySavings*12) / sum
			}
			else {
				projectInstance.paybackMonths = null
				projectInstance.roi = null
			}
			log.info "paybackMonths " + projectInstance.paybackMonths
			log.info "ROI " + projectInstance.roi
		}
		else if (currentProvider == null) {
			log.info "Missing currentProvider"
			projectInstance.paybackMonths = null
			projectInstance.roi = null
		}
		else if (proposedProvider == null) {
			log.info "Missing proposedProvider"
			projectInstance.paybackMonths = null
			projectInstance.roi = null
		}
	}
	
	/**
	 * Update project with ROI calculations
	 * @param transaction
	 * @param endDate
	 */
	ROIProject saveROIProject(Project project, Provider currentProvider, Provider proposedProvider, double currentMonthlyWaterUse, double otherCapitalImprovements ){
		
		ROIProject roiProject
		if(project){
			roiProject = new ROIProject(project: project,
					currentMonthlyWaterUse: currentMonthlyWaterUse,
					currentProvider: currentProvider, proposedProvider: proposedProvider,
					otherCapitalImprovements: otherCapitalImprovements)
			roiProject.save();
		}else{
			throw new RuntimeException("ROI calculations cannot be saved")
		}
	}
	
	/**
	 * Update roi project cycles
	 *
	 */
	void updateCycles(ROIProject roiProject, double currentCycles, double proposedCycles){
		if(roiProject){
			
			roiProject.currentCycles = currentCycles
			roiProject.proposedCycles = proposedCycles
			roiProject.save()
		}else{
			throw new RuntimeException("ROI cycles cannot be saved")
		}
	}
	
	
	/**
	 * Update Roi project pipelinefoot and distance
	 *
	 */
	void updatePipelineFootAndDistance(ROIProject roiProject, PipelineFoot pipelineFoot, Double distance, String pipelinePos){
		if(roiProject){
			
			switch (pipelinePos) {
				case 'pipeline1':
					roiProject.pipelineFoot1 = pipelineFoot
					roiProject.distance1 = distance
					roiProject.save()
					break
				case 'pipeline2':
					roiProject.pipelineFoot2 = pipelineFoot
					roiProject.distance2 = distance
					roiProject.save()
					break
				case 'pipeline3':
					roiProject.pipelineFoot3 = pipelineFoot
					roiProject.distance3 = distance
					roiProject.save()
					break
				case 'pipeline4':
					roiProject.pipelineFoot4 = pipelineFoot
					roiProject.distance4 = distance
					roiProject.save()
					break
			}
		}else{
			throw new RuntimeException("ROI cycles cannot be saved")
		}
	}
	
	
	/**
	 * Creates a project for a specified account
	 * @param account
	 * @param type
	 * @param resource
	 * @param uom
	 * @param name
	 * @param startDate
	 * @param desc
	 * @return
	 */
	Project saveProject(Account account, ProjectType type, ProjectStatus status,  
	String name, Date startDate,  String desc, int priority, double budget){
		
		Project project
		if(!account){
			throw new RuntimeException("Project cannot be created as the Account($account)does not exist" );
		}else{
			project = new Project(account: account, name: name, type: type, budget:budget,
					status: status, priority: priority, description: desc, startDate:startDate)
			project.save()
		}
		project
	}
}

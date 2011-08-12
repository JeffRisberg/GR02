<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
$(document).ready(function() {
if($(".summary-pop .close").length){
		$(".summary-pop .close").click(function(){
			$(".summary-pop").hide();
			return false;
		});
</script>

<style type="text/css">
    .blueFull { background: #C0D9AF; color: black; }
    .blueGap  { background: #C0D9AF; color: black; border-right: 5px solid white; }
</style>
<g:javascript library="jquery" plugin="jquery" />
<p:css name="jqModal" /> 
</head>
<body>
 <div class="summary-pop">
 <div class="jqmdTL"><div class="jqmdTR"><div class="jqmdTC">Project Summary</div></div></div>
 <div class="summary-pop-top"></div>
  <div class="summary-pop-bod">
 <a href="#" class="close jqmClose"></a>
<p>Name: ${projectInstance.name}</p>
<p>Type: ${projectInstance.type.name}, Priority: <i:priorityLabel value="${projectInstance.priority}" /></p>

<p>Current Usage (gals): <g:formatNumber format="##,###,##0" number="${roiProjectInstance?.currentMonthlyWaterUse}" /></p>

<div style="border: 1px solid black">
<table style="padding: 2px; border: 1px solid white !important">
	<tr>
		<td class="blueGap">Current</td>
		<td class="blueFull">Proposed</td>
	</tr>
	<tr>
		<td>Cycles: ${roiProjectInstance?.currentCycles}</td>
		<td>Cycles: ${roiProjectInstance?.proposedCycles}</td>
	</tr>
	<tr>
		<td>
		  <g:if test="${roiProjectInstance?.currentProvider}">
		    ${roiProjectInstance?.currentProvider?.name} (freshwater)
		  </g:if>
		  <g:else>
		    <i>No current provider specified</i>
		  </g:else>
		</td>
		<td>
		  <g:if test="${roiProjectInstance?.proposedProvider}">
		    ${roiProjectInstance?.proposedProvider?.name} (recycled)
		  </g:if>
		  <g:else>
		    <i>No proposed provider specified</i>
		  </g:else>
		</td>
	</tr>
	<tr>
		<td>Rate ($/HCF): ${pcd.currentRate}</td>
		<td>Rate ($/HCF): ${pcd.proposedRate}</td>
	</tr>
	<tr>
		<td>Usage (HCF): 
		<g:formatNumber format="##,###,##0" number="${pcd.currentUsageHCF}" />
		</td>
		<td>Usage (HCF): 
		<g:formatNumber format="##,###,##0" number="${pcd.proposedUsageHCF}"/>
		</td>
	</tr>
	<tr>
		<td>Chemical Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.currentChemicalCosts}"/></td>
		<td>Chemical Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.proposedChemicalCosts}"/></td>
	</tr>
	<tr>
		<td>Addtional Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.currentAMTCosts}"/></td>
		<td>Additional Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.proposedAMTCosts}"/></td>
	</tr>
	<tr>
		<td>Total Cost ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.totalCurrentCosts}"/></td>
		<td>Total Cost ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.totalProposedCosts}"/></td>
	</tr>
</table>
</div>

<p>Cost Savings ($/month): <g:formatNumber format="##,###,##0.00" number="${projectInstance.monthlySavings}" /></p>
<p>Total Pipeline Feet of Construction: ${pcd.totalPipelineFeet}</p>
<p>Infrastructure Improvements Selected:<br/>
<span style="font-size: 12px">${pcd.infrastructureOptions}</span></p>

<table style="background: skyblue;">
	<tr>
		<td style="padding: 1px">
			Budget ($):
			<g:formatNumber number="${projectInstance.budget}"
				format="##,###,###" />
		</td>
		<td style="padding: 1px">
			Investment ($):
			<g:formatNumber number="${projectInstance.capitalInvest}"
				format="##,###,###" />
		</td>
		<td style="padding: 1px">
			Payback (months):
			<g:formatNumber number="${projectInstance.paybackMonths}"
				format="###,##0.0" />&nbsp;
		</td>
		<td style="padding: 1px">
			ROI:
			<g:formatNumber number="${projectInstance.roi}"
				format="#,##0.00" />
			%
		</td>
	</tr>
</table>
</div><!-- /Summary-pop-bod -->
				<div class="summary-pop-bot">
				</div><!-- /Summary-pop-bot -->
			</div>

</body>
</html>
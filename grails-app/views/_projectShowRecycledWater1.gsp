<style type="text/css">
    .blueFull { background: #9999ff; color: white; }
    .blueGap  { background: #9999ff; color: white; border-right: 5px solid white; }
    table { border: 1px solid white !important;}
	.scrollWrapper{ float:left;width:900px;overflow:scroll;}
</style>

<table>
	<tbody class="scrollWrapper">
	  <tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentMonthlyWaterUse.label"
					default="Monthly Water Use (gals)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "currentMonthlyWaterUse")}</td>
		</tr>

    <tr class="prop">
			<td valign="top" class="name blueFull">
			<b>Current</b>
			</td>
			<td valign="top" class="value blueGap"></td>
			<td valign="top" class="name blueFull">
				<b>Proposed</b>
			</td>
			<td valign="top" class="value blueFull"></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentCycles.label" default="Cycles (ratio)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "currentCycles")}</td>
			<td valign="top" class="name">
				<g:message code="project.proposedCycles.label" default="Cycles (ratio)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "proposedCycles")}</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentProvider.label" default="Potable Water Provider" />
			</td>
			<td valign="top" class="value">
				${roiProjectInstance?.currentProvider?.encodeAsHTML()}
			</td>
			<td valign="top" class="name">
				<g:message code="project.proposedProvider.label" default="Recycled Water Provider" />
			</td>
			<td valign="top" class="value">
				${roiProjectInstance?.proposedProvider?.encodeAsHTML()}
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentChemicalCosts.label"
					default="Chemical Costs (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "currentChemicalCosts")}</td>
		  <td valign="top" class="name">&nbsp;</td>
			<td valign="top" class="value">&nbsp;</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentAdministrationCosts.label"
					default="Administration Costs (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "currentAdministrationCosts")}</td>
		<td valign="top" class="name">
				<g:message code="project.proposedAdministrationCosts.label"
					default="Administration Costs (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "proposedAdministrationCosts")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.currentMonitoringAndTestingCosts.label"
					default="Monitoring And Testing Costs (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "currentMonitoringAndTestingCosts")}
									</td>
		<td valign="top" class="name">
				<g:message code="project.proposedMonitoringAndTestingCosts.label"
					default="Monitoring And Testing Costs (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field:
				"proposedMonitoringAndTestingCosts")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">&nbsp;</td>
			<td valign="top" class="value">&nbsp;</td>
		<td valign="top" class="name">
				<g:message code="project.proposedOtherCostSavings.label"
					default="Other Cost Savings (\$/month)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field:
				"proposedOtherCostSavings")}</td>
		</tr>
    <tr><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="300"/></td></tr>				
	</tbody>
</table>

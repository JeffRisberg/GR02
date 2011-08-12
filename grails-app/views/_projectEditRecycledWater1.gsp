<style type="text/css">
    .blueFull { background: #9999ff; color: white;  }
    .blueGap  { background: #9999ff; color: white; border-right: 5px solid white; }
    table { border: 1px solid white !important;}
	.scrollWrapper{ float:left;width:900px;overflow:scroll;}
	 
</style>
 
<table>
	<tbody class="scrollWrapper">
	  <tr class="prop">
			<td valign="top" class="name">
				<label for="currentMonthlyWaterUse">
					<g:message code="project.currentMonthlyWaterUse.label"
						default="Monthly Water Use (gals)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentMonthlyWaterUse', 'errors')}">
				<g:textField name="currentMonthlyWaterUse"
					value="${fieldValue(bean: roiProjectInstance, field: 'currentMonthlyWaterUse')}" />
			</td>
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
				<label for="currentCycles">
					<g:message code="project.currentCycles.label" default="Cycles (ratio)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentCycles', 'errors')}">
				<g:textField name="currentCycles"
					value="${fieldValue(bean: roiProjectInstance, field: 'currentCycles')}" />
			</td>
			<td valign="top" class="name">
				<label for="proposedCycles">
					<g:message code="project.proposedCycles.label" default="Cycles (ratio)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'proposedCycles', 'errors')}">
				<g:textField name="proposedCycles"
					value="${fieldValue(bean: roiProjectInstance, field: 'proposedCycles')}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="currentProvider">
					<g:message code="project.currentProvider.label" default="Potable Provider" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentProvider', 'errors')}">
				<g:select name="currentProvider.id" from="${com.incra.Provider.list()}"
					optionKey="id" value="${roiProjectInstance?.currentProvider?.id}"
					noSelection="['null': '']" />
			</td>
			<td valign="top" class="name">
				<label for="proposedProvider">
					<g:message code="project.proposedProvider.label" default="Recycled Provider" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'proposedProvider', 'errors')}">
				<g:select name="proposedProvider.id" from="${com.incra.Provider.list()}"
					optionKey="id" value="${roiProjectInstance?.proposedProvider?.id}"
					noSelection="['null': '']" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="currentChemicalCosts">
					<g:message code="project.currentChemicalCosts.label"
						default="Chemical Costs (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentChemicalCosts', 'errors')}">
				<g:textField name="currentChemicalCosts"
					value="${fieldValue(bean: roiProjectInstance, field: 'currentChemicalCosts')}" />
			</td>
			<td valign="top" class="name">&nbsp;</td>
			<td valign="top">&nbsp;</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<label for="currentAdministrationCosts">
					<g:message code="project.currentAdministrationCosts.label"
						default="Administration Costs (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentAdministrationCosts', 'errors')}">
				<g:textField name="currentAdministrationCosts"
					value="${fieldValue(bean: roiProjectInstance, field: 'currentAdministrationCosts')}" />
			</td>
			<td valign="top" class="name">
				<label for="proposedAdministrationCosts">
					<g:message code="project.proposedAdministrationCosts.label"
						default="Administration Costs (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'proposedAdministrationCosts', 'errors')}">
				<g:textField name="proposedAdministrationCosts"
					value="${fieldValue(bean: roiProjectInstance, field: 'proposedAdministrationCosts')}" />
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<label for="currentMonitoringAndTestingCosts">
					<g:message code="project.currentMonitoringAndTestingCosts.label"
						default="Monitoring & Testing Costs (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'currentMonitoringAndTestingCosts', 'errors')}">
				<g:textField name="currentMonitoringAndTestingCosts"
					value="${fieldValue(bean: roiProjectInstance, field: 'currentMonitoringAndTestingCosts')}" />
			</td>
			<td valign="top" class="name">
				<label for="proposedMonitoringAndTestingCosts">
					<g:message code="project.proposedMonitoringAndTestingCosts.label"
						default="Monitoring & Testing Costs (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'proposedMonitoringAndTestingCosts', 'errors')}">
				<g:textField name="proposedMonitoringAndTestingCosts"
					value="${fieldValue(bean: roiProjectInstance, field: 'proposedMonitoringAndTestingCosts')}" />
			</td>
		</tr>
		
		<tr class="prop">
		  <td>&nbsp;</td><td>&nbsp;</td>
			<td valign="top" class="name">
				<label for="proposedOtherCostSavings">
					<g:message code="project.proposedOtherCostSavings.label"
						default="Other Cost Savings (\$/mth)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'proposedOtherCostSavings', 'errors')}">
				<g:textField name="proposedOtherCostSavings"
					value="${fieldValue(bean: roiProjectInstance, field: 'proposedOtherCostSavings')}" />
			</td>
		</tr>
	<tr><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="300"/></td></tr>				
	</tbody>
</table>

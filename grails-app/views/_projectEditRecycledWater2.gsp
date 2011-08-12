<table style="border: 1px solid white !important">
	<tbody>
		<tr>
			<td colspan="4" style="font-weight:bolder;">Piping Requirements.  Use as many pipeline fields as needed.</td>
		</tr>
		<tr class="prop">
			<td valign="top" class="name">
				<label for="distance1">
					<g:message code="ROIProject.distance1.label" default="Pipeline 1:  Linear Feet:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'distance1', 'errors')}">
				<g:textField name="distance1"
					value="${fieldValue(bean: roiProjectInstance, field: 'distance1')}" />
			</td>
			<td valign="top" class="name">
				<label for="pipelineFoot1">
					<g:message code="ROIProject.pipelineFoot1.label" default="Type:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'pipelineFoot1', 'errors')}">
				<g:select name="pipelineFoot1.id" from="${com.incra.PipelineFoot.list()}"
					optionKey="id" value="${roiProjectInstance?.pipelineFoot1?.id}"
					noSelection="['null': '']" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="distance2">
					<g:message code="ROIProject.distance2.label" default="Pipeline 2:  Linear Feet:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'distance2', 'errors')}">
				<g:textField name="distance2"
					value="${fieldValue(bean: roiProjectInstance, field: 'distance2')}" />
			</td>
			<td valign="top" class="name">
				<label for="pipelineFoot2">
					<g:message code="ROIProject.pipelineFoot2.label" default="Type:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'pipelineFoot2', 'errors')}">
				<g:select name="pipelineFoot2.id" from="${com.incra.PipelineFoot.list()}"
					optionKey="id" value="${roiProjectInstance?.pipelineFoot2?.id}"
					noSelection="['null': '']" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="distance3">
					<g:message code="ROIProject.distance3.label" default="Pipeline 3:  Linear Feet:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'distance3', 'errors')}">
				<g:textField name="distance3"
					value="${fieldValue(bean: roiProjectInstance, field: 'distance3')}" />
			</td>
			<td valign="top" class="name">
				<label for="pipelineFoot3">
					<g:message code="ROIProject.pipelineFoot3.label" default="Type:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'pipelineFoot3', 'errors')}">
				<g:select name="pipelineFoot3.id" from="${com.incra.PipelineFoot.list()}"
					optionKey="id" value="${roiProjectInstance?.pipelineFoot3?.id}"
					noSelection="['null': '']" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="distance4">
					<g:message code="ROIProject.distance4.label" default="Pipeline 4:  Linear Feet:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'distance4', 'errors')}">
				<g:textField name="distance4"
					value="${fieldValue(bean: roiProjectInstance, field: 'distance4')}" />
			</td>
			<td valign="top" class="name">
				<label for="pipelineFoot4">
					<g:message code="ROIProject.pipelineFoot4.label" default="Type:" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'pipelineFoot4', 'errors')}">
				<g:select name="pipelineFoot4.id" from="${com.incra.PipelineFoot.list()}"
					optionKey="id" value="${roiProjectInstance?.pipelineFoot4?.id}"
					noSelection="['null': '']" />
			</td>
		</tr>

	  <tr>
			<td colspan="4" style="font-weight:bolder;">Infrastructure Improvements ($).  Select ones for your project.</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<label for="basinLevelControlValve">
					<g:message code="ROIProject.basinLevelControlValve.label"
						default="Basin Level Control Valve" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'basinLevelControlValve', 'errors')}">
				<g:textField name="basinLevelControlValve"
					value="${fieldValue(bean: roiProjectInstance, field: 'basinLevelControlValve')}" />
			</td>
			<td valign="top" class="name">
				<label for="blowdownControlValve">
					<g:message code="ROIProject.blowdownControlValve.label"
						default="Blowdown Control Valve" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'blowdownControlValve', 'errors')}">
				<g:textField name="blowdownControlValve"
					value="${fieldValue(bean: roiProjectInstance, field: 'blowdownControlValve')}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="chemicalFeedSkid">
					<g:message code="ROIProject.chemicalFeedSkid.label"
						default="Chemical Feed Skid" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'chemicalFeedSkid', 'errors')}">
				<g:textField name="chemicalFeedSkid"
					value="${fieldValue(bean: roiProjectInstance, field: 'chemicalFeedSkid')}" />
			</td>
			<td valign="top" class="name">
				<label for="corrosionCouponRack">
					<g:message code="ROIProject.corrosionCouponRack.label"
						default="Corrosion Coupon Rack" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'corrosionCouponRack', 'errors')}">
				<g:textField name="corrosionCouponRack"
					value="${fieldValue(bean: roiProjectInstance, field: 'corrosionCouponRack')}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="oxidizingBiocideFeeder">
					<g:message code="ROIProject.oxidizingBiocideFeeder.label"
						default="Oxidizing Biocide Feeder" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'oxidizingBiocideFeeder', 'errors')}">
				<g:textField name="oxidizingBiocideFeeder"
					value="${fieldValue(bean: roiProjectInstance, field: 'oxidizingBiocideFeeder')}" />
			</td>
			<td valign="top" class="name">
				<label for="pHConductivityController">
					<g:message code="ROIProject.pHConductivityController.label"
						default="pH/Conductivity Controller" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'pHConductivityController', 'errors')}">
				<g:textField name="pHConductivityController"
					value="${fieldValue(bean: roiProjectInstance, field: 'pHConductivityController')}" />
			</td>
		</tr>
		
		<tr>
			<td colspan="4" style="font-weight:bolder;">Other ($)</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<label for="otherCapitalImprovements">
					<g:message code="project.otherCapitalImprovements.label"
						default="Other Capital Improvements" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: roiProjectInstance, field: 'otherCapitalImprovements', 'errors')}">
				<g:textField name="otherCapitalImprovements"
					value="${fieldValue(bean: roiProjectInstance, field: 'otherCapitalImprovements')}" />
			</td>
		</tr>

	</tbody>
</table>

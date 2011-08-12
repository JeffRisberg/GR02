<table style="border: 1px solid white !important">
	<tbody>
		<tr>
			<td colspan="4" style="font-weight:bolder;">Piping Requirements</td>
		</tr>
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.distance1.label" default="Pipeline 1: Linear feet:" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "distance1")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.pipelineFoot1.label" default="Type:" />
			</td>
			<td valign="top" class="value">
				<nobr>${roiProjectInstance?.pipelineFoot1?.encodeAsHTML()}</nobr>
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.distance2.label" default="Pipeline 2: Linear feet:" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "distance2")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.pipelineFoot2.label" default="Type:" />
			</td>
			<td valign="top" class="value">
				<nobr>${roiProjectInstance?.pipelineFoot2?.encodeAsHTML()}</nobr>
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.distance3.label" default="Pipeline 3: Linear feet:" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "distance3")}</td>
		  <td valign="top" class="name">
				<g:message code="ROIProject.pipelineFoot3.label" default="Type:" />
			</td>
			<td valign="top" class="value">
				<nobr>${roiProjectInstance?.pipelineFoot3?.encodeAsHTML()}</nobr>
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.distance4.label" default="Pipeline 4: Linear feet:" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "distance4")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.pipelineFoot4.label" default="Type:" />
			</td>
			<td valign="top" class="value">
				<nobr>${roiProjectInstance?.pipelineFoot4?.encodeAsHTML()}</nobr>
			</td>
		</tr>

		<tr>
			<td colspan="4" style="font-weight:bolder;">Infrastructure Improvements ($)</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.basinLevelControlValve.label"
					default="Basin Level Control Valve" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "basinLevelControlValve")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.blowdownControlValve.label"
					default="Blowdown Control Valve" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "blowdownControlValve")}</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.chemicalFeedSkid.label"
					default="Chemical Feed Skid" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "chemicalFeedSkid")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.corrosionCouponRack.label"
					default="Corrosion Coupon Rack" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "corrosionCouponRack")}</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="ROIProject.oxidizingBiocideFeeder.label"
					default="Oxidizing Biocide Feeder" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "oxidizingBiocideFeeder")}</td>
			<td valign="top" class="name">
				<g:message code="ROIProject.pHConductivityController.label"
					default="pH/Conductivity Controller" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: roiProjectInstance,
				field: "pHConductivityController")}</td>
		</tr>

    <tr>
			<td colspan="4" style="font-weight:bolder;">Other ($)</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.otherCapitalImprovements.label"
					default="Other Capital Improvements" />
			</td>
			<td valign="top" class="value">${fieldValue(bean:
				roiProjectInstance,
				field: "otherCapitalImprovements")}</td>
		</tr>

	</tbody>
</table>

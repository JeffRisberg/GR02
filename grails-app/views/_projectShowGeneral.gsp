<table style="border: 1px solid white !important;">
	<tbody>
	  <tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.name.label" default="Name" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: projectInstance,
				field:
				"name")}</td>
		</tr>
		<tr class="prop">
		  <td valign="top" class="name">
				<g:message code="project.status.label" default="Status" />
			</td>
			<td valign="top" class="value">
				${projectInstance?.status?.encodeAsHTML()}
			</td>
			<td valign="top" class="name">
				<g:message code="project.priority.label" default="Priority" />
			</td>
			<td valign="top" class="value"><i:priorityLabel value="${projectInstance.priority}" /></td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.type.label" default="Type" />
			</td>
			<td valign="top" class="value">
				${projectInstance?.type?.encodeAsHTML()}
			</td>
			<td valign="top" class="name">
				<g:message code="project.budget.label" default="Budget (\$)" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: projectInstance,
				field:
				"budget")}</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.account.label" default="Facility" />
			</td>
			<td valign="top" class="value">
				<g:link controller="account" action="show"
					id="${projectInstance?.account?.id}">${projectInstance?.account?.encodeAsHTML()}</g:link>
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.startDate.label" default="Start Date" />
			</td>
			<td valign="top" class="value">
				<g:formatDate format="MM/dd/yyyy" date="${projectInstance?.startDate}" />
			</td>
			<td valign="top" class="name">
				<g:message code="project.endDate.label" default="End Date" />
			</td>
			<td valign="top" class="value">
				<g:formatDate format="MM/dd/yyyy" date="${projectInstance?.endDate}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.tags.label" default="Tags" />
			</td>
			<td valign="top" class="value">${formattedTags}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<g:message code="project.description.label" default="Description" />
			</td>
			<td valign="top" class="value">${fieldValue(bean: projectInstance,
				field:
				"description")}</td>
		</tr>
		<tr><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="125"/></td><td><spacer width="300"/></td></tr>
	</tbody>
</table>
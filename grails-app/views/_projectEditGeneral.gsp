<table style="border: 1px solid white !important">
	<tbody>
		<tr class="prop">
			<td valign="top" class="name">
				<label for="name">
					<g:message code="project.name.label" default="Name" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
				<g:textField size="50" name="name" value="${projectInstance?.name}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="status">
					<g:message code="project.status.label" default="Status" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'status', 'errors')}">
				<g:select name="status.id" from="${projectStatuses}"
					optionKey="id" value="${projectInstance?.status?.id}" />
			</td>
			<td valign="top" class="name">
				<label for="type">
					<g:message code="project.type.label" default="Priority" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'type', 'errors')}">
				<i:prioritySelector name="priority"
					value="${fieldValue(bean: projectInstance, field: 'priority')}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="type">
					<g:message code="project.type.label" default="Type" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'type', 'errors')}">
				<g:select name="type.id" from="${projectTypes}" optionKey="id"
					value="${projectInstance?.type?.id}" />
			</td>
			<td valign="top" class="name">
				<label for="budget">
					<g:message code="project.budget.label" default="Budget (\$)" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'status', 'errors')}">
				<g:textField name="budget"
					value="${fieldValue(bean: projectInstance, field: 'budget')}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="account">
					<g:message code="project.account.label" default="Facility" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'account', 'errors')}">
				<g:select name="account.id" from="${accounts}" optionKey="id"
					value="${projectInstance?.account?.id}" />
			</td>
		</tr>

		<tr class="prop">
			<td valign="top" class="name">
				<label for="startDate">
					<g:message code="project.startDate.label" default="Start Date" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'startDate', 'errors')}">
				<g:jqDatePicker id="startDate" name="startDate"
					value="${projectInstance?.startDate}" />
			</td>
			<td valign="top" class="name">
				<label for="endDate">
					<g:message code="project.endDate.label" default="End Date" />
				</label>
			</td>
			<td valign="top"
				class="value ${hasErrors(bean: projectInstance, field: 'endDate', 'errors')}">
				<nobr>
					<g:jqDatePicker id="endDate" name="endDate"
						value="${projectInstance?.endDate}" />
				</nobr>
			</td>
		</tr>

    <tr class="prop">
			<td valign="top" class="name">
				<label for="tags">
					<g:message code="project.tags.label" default="Tags" />
				</label>
			</td>
			<td valign="top" colspan="3"
				class="value ${hasErrors(bean: projectInstance, field: 'tags', 'errors')}">
				<g:textField name="tags" value="${formattedTags}" size="90" />
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name">
				<label for="description">
					<g:message code="project.description.label" default="Description" />
				</label>
			</td>
			<td valign="top" colspan="3"
				class="value ${hasErrors(bean: projectInstance, field: 'description', 'errors')}">
				<g:textArea style="height: 50px !important; width: 550px !important;"
					name="description" cols="60" rows="3" value="${projectInstance?.description}" />
			</td>
		</tr>

	</tbody>
</table>

 


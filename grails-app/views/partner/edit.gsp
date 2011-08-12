

<%@ page import="com.incra.Partner" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'partner.label', default: 'Partner')}" />
		<title>
			<g:message code="default.edit.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<h2>
				<g:message code="default.edit.label" args="[entityName]" />
			</h2>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${partnerInstance}">
				<div class="errors">
					<g:renderErrors bean="${partnerInstance}" as="list" />
				</div>
			</g:hasErrors>
			<g:form method="post">
				<g:hiddenField name="id" value="${partnerInstance?.id}" />
				<g:hiddenField name="version" value="${partnerInstance?.version}" />
				<div class="dialog">
					<table>
						<tbody>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="name">
										<g:message code="partner.name.label" default="Name" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: partnerInstance, field: 'name', 'errors')}">
									<g:textField size="50" name="name"
										value="${partnerInstance?.name}" maxSize="50" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine1">
										<g:message code="partner.addressline1.label"
											default="Address Line 1" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine1', 'errors')}">
									<g:textField size="50" name="addressLine1"
										value="${addressInstance?.addressLine1}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine2">
										<g:message code="partner.addressLine2.label"
											default="Address Line 2" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine2', 'errors')}">
									<g:textField size="50" name="addressLine2"
										value="${addressInstance?.addressLine2}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine3">
										<g:message code="partner.addressLine3.label"
											default="Address Line 3" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine3', 'errors')}">
									<g:textField size="50" name="addressLine3"
										value="${addressInstance?.addressLine3}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="city">
										<g:message code="partner.city.label" default="City" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'city', 'errors')}">
									<g:textField name="city" value="${addressInstance?.city}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="stateCode">
										<g:message code="partner.stateCode.label" default="State Code" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'stateCode', 'errors')}">
									<g:textField name="stateCode" value="${addressInstance?.stateCode}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="postalCode">
										<g:message code="partner.postalCode.label" default="Postal Code" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'postalCode', 'errors')}">
									<g:textField name="postalCode" value="${addressInstance?.postalCode}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="website">
										<g:message code="partner.website.label" default="Website" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: partnerInstance, field: 'website', 'errors')}">
									<g:textField size="60" name="website"
										value="${partnerInstance?.website}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="contactName">
										<g:message code="partner.contactName.label" default="Contact Name" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contactInstance, field: 'name', 'errors')}">
									<g:textField name="contactName" value="${contactInstance?.contactName}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="contactEmail">
										<g:message code="partner.contactEmail.label" default="Contact Email" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contactInstance, field: 'email', 'errors')}">
									<g:textField size="60" name="email" value="${contactInstance?.email}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="contactPhone">
										<g:message code="partner.contactPhone.label" default="Contact Phone" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contactInstance, field: 'phoneNo', 'errors')}">
									<g:textField name="contactPhone" value="${contactInstance?.phoneNo}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="tags">
										<g:message code="partner.tags.label" default="Tags" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: partnerInstance, field: 'tags', 'errors')}">
									<g:textField name="tags" value="${formattedTags}" size="90" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="description">
										<g:message code="partner.description.label" default="Description" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: partnerInstance, field: 'description', 'errors')}">
									<g:textArea name="description" cols="60" rows="3"
										value="${partnerInstance?.description}" />
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="buttons">
					<span class="button">
						<g:actionSubmit class="save" action="update"
							value="${message(code: 'default.button.update.label', default: 'Update')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="delete" action="delete"
							value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>
				</div>
			</g:form>
		</div>
	</body>
</html>

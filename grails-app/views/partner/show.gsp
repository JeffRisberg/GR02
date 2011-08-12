
<%@ page import="com.incra.Partner" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'partner.label', default: 'Partner')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
					<tbody>
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.name.label" default="Name" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: partnerInstance,
								field: "name")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.address.label" default="Address" />
							</td>
							<td valign="top" class="value">
								${partnerInstance?.address?.encodeAsHTML()}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.website.label" default="Website" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: partnerInstance,
								field: "website")}</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.contact.label" default="Contact Name" />
							</td>
							<td valign="top" class="value">${partnerInstance?.contact?.contactName?.encodeAsHTML()}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.email.label" default="Contact Email" />
							</td>
							<td valign="top" class="value">${partnerInstance?.contact?.email?.encodeAsHTML()}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.phoneNo.label" default="Contact Phone#" />
							</td>
							<td valign="top" class="value">${partnerInstance?.contact?.phoneNo?.encodeAsHTML()}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.tags.label" default="Tags" />
							</td>
							<td valign="top" style="text-align: left;" class="value">
								${formattedTags}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.description.label" default="Description" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: partnerInstance,
								field: "description")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="partner.comment.label" default="Comments" />
							</td>
							<td>
								<g:each in="${comments}" var="comment">
									<div style="padding: 5px;">
										<b>${comment.user.profile.fullName}</b>
										<g:formatDate format="MM/dd/yyyy" date="${comment.dateCreated}" />
										<br />
										${comment.body}
									</div>
								</g:each>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<g:if test="${userIsAdministrator}">
				<div class="buttons">
					<g:form>
						<g:hiddenField name="id" value="${partnerInstance?.id}" />
						<span class="button">
							<g:actionSubmit class="edit" action="edit"
								value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
						</span>
						<span class="button">
							<g:actionSubmit class="delete" action="delete"
								value="${message(code: 'default.button.delete.label', default: 'Delete')}"
								onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</span>
					</g:form>
				</div>
			</g:if>
		</div>
	</body>
</html>

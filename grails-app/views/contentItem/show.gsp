<%@ page import="com.incra.ContentItem" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'contentItem.label', default: 'ContentItem')}" />
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
								<g:message code="contentItem.name.label" default="Name" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: contentItemInstance,
								field: "name")}</td>
						</tr>
						
						<tr class="prop">
              <td valign="top" class="name">
                <g:message code="contentItem.type.label" default="Type" />
              </td>
              <td valign="top" class="value">${fieldValue(bean: contentItemInstance,
                field: "contentItemType")}</td>
            </tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.name.label" default="Partner Flag" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: contentItemInstance,
								field: "partnerFlag")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.name.label" default="Paid Flag" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: contentItemInstance,
								field: "paidFlag")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.name.label" default="Approved Flag" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: contentItemInstance,
								field: "approvedFlag")}</td>
						</tr>

            <tr class="prop">
              <td colspan="99">
                <g:if test="${contentItemInstance.photo}">
                  <img
                    src="<g:createLink action='renderImage' id='${contentItemInstance.id}' />"
                    width="200" height="150" class="contentItemImg" />
                </g:if>
              </td>
            </tr>
            
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.section.label" default="Sections" />
							</td>
							<td>
								<g:each in="${contentItemInstance.contentSections}" var="section">
									<div style="margin: 5px; background: #fff; padding: 5px">
										<table>
											<tr>
												<td>${section.name}</td>
											</tr>
											<tr>
												<td>${section.address}</td>
											</tr>
											<tr>
												<td>${section.website}</td>
											</tr>
										</table>
									</div>
								</g:each>
							</td>
						</tr>

						<!-- tags -->
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.tags.label" default="Tags" />
							</td>
							<td valign="top" style="text-align: left;" class="value">
								${formattedTags}
              </td>
						</tr>

						<!-- description -->
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="contentItem.description.label"
									default="Description" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: contentItemInstance,
								field: "description")}</td>
						</tr>

					</tbody>
				</table>

			</div>
			<g:if test="${userIsAdministrator}">
				<div class="buttons">
					<g:form>
						<g:hiddenField name="id" value="${contentItemInstance?.id}" />
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

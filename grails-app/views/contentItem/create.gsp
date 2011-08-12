<%@ page import="com.incra.ContentItem" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'contentItem.label', default: 'ContentItem')}" />
    <title>
      <g:message code="default.create.label" args="[entityName]" />
    </title>
  </head>
  <body>
    <div class="body">
      <h2>
        Create Resource
      </h2>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${contentItemInstance}">
        <div class="errors">
          <g:renderErrors bean="${contentItemInstance}" as="list" />
        </div>
      </g:hasErrors>
      <g:uploadForm name="contentItemForm" action="save">
        <g:hiddenField name="id" value="${contentItemInstance?.id}" />
				<g:hiddenField name="version" value="${contentItemInstance?.version}" />
				<div class="dialog">
					<table>
						<tbody>							
							<tr class="prop">
								<td valign="top" class="name">
									<label for="name">
										<g:message code="contentItem.name.label" default="Name" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contentItemInstance, field: 'name', 'errors')}">
									<g:textField size="50" name="name"
										value="${contentItemInstance?.name}" />
								</td>
							</tr>
						<tr class="prop">
                			<td valign="top" class="name">
                  			<label for="contentItemType">
                  			  <g:message code="contentItem.type.label" default="Type" />
                  			</label>
                			</td>
                			<td valign="top"
                		  class="value ${hasErrors(bean: contentItemInstance, field: 'contentType', 'errors')}">
               			   <g:select name="contentItemType" optionValue="label"
                  		   value="${contentItemInstance.contentItemType}" from="${contentItemTypes}" />
                			</td>
              			</tr>
              			<tr class="prop">
								<td valign="top" class="body">
									<g:message code="contentItem.body.label" default="ContentBody" />
								</td>
								<td valign="top" class="value">
								<g:textArea id="body" name="body" rows="20" 
								cols="75" value="${contentItemInstance.body}" /> <br />
									 
								</td>
							</tr>
              			
                           
							<tr class="prop">
								<td valign="top" class="Partner Flag">
									<g:message code="contentItem.name.label" default="Partner Flag" />
								</td>
								<td valign="top" class="value">
									<g:checkBox name="partnerFlag"
										value="${contentItemInstance.partnerFlag}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<g:message code="contentItem.name.label" default="Paid Flag" />
								</td>
								<td valign="top" class="value">
									<g:checkBox name="paidFlag" value="${contentItemInstance.paidFlag}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<g:message code="contentItem.name.label" default="Approved Flag" />
								</td>
								<td valign="top" class="value">
									<g:checkBox name="approvedFlag"
										value="${contentItemInstance.approvedFlag}" />
								</td>
							</tr>

              <tr class="prop">
                <td valign="top" class="name">
                  <label for="photo">
                    <g:message code="contentItem.photo.label" default="Logo" />
                  </label>
                </td>
                <td valign="top"
                  class="value ${hasErrors(bean: contentItemInstance, field: 'photo', 'errors')}">
                  <input type="file" id="photo" name="photo" />
                </td>
              </tr>
              
							<tr class="prop">
								<td valign="top" class="name">
									<label for="tags">
										<g:message code="contentItem.tags.label" default="Tags" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contentItemInstance, field: 'tags', 'errors')}">
									<g:textField size="90" name="tags" value="${formattedTags}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="description">
										<g:message code="contentItem.description.label"
											default="Description" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: contentItemInstance, field: 'description', 'errors')}">
									<g:textArea name="description" cols="60" rows="5"
										value="${contentItemInstance?.description}" />
								</td>
							</tr>

						</tbody>
					</table>
				</div>
        <div class="buttons">
          <span class="button">
            <g:submitButton name="create" class="save"
              value="${message(code: 'default.button.create.label', default: 'Create')}" />
          </span>
        </div>
      </g:uploadForm>
    </div>
  </body>
</html>


<%@ page import="com.incra.Partner" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'partner.label', default: 'Partner')}" />
	  <g:javascript library="prototype" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			 <div style="margin-top: 6px; margin-bottom: 3px">
			  <span style="font-weight: bold; font-size: 13px; color: gray;">View and Find Partner Information and their Capability Tags</span>
			  <div class="menuButton" style="float: right; font-weight: bold">
						<g:link class="create" action="create">Create New Partner</g:link>
			  </div>
			  <div style="clear:both"></div>
			</div>
			<g:form action="list" method="post">
				<i:filterGrid numColumns="3" content="${filters}" />
  		</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="name"
								title="${message(code: 'partner.name.label', default: 'Name')}" />
							<th>
								<g:message code="partner.address.label" default="Address" />
							</th>
							<g:sortableColumn property="website"
								title="${message(code: 'partner.website.label', default: 'Website')}" />
							<g:sortableColumn property="description"
								title="${message(code: 'partner.description.label', default: 'Description')}" />
							<g:sortableColumn property="dateCreated"
								title="${message(code: 'partner.dateCreated.label', default: 'Date&nbsp;Created')}" />
						  <th>Featured</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${partnerInstanceList}" status="i"
							var="partnerInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>								
									<g:link action="show" id="${partnerInstance.id}">
									  ${fieldValue(bean: partnerInstance, field: "name")}
									</g:link>
									<g:link action="edit" id="${partnerInstance.id}">
								  <p:image src="skin/database_edit.png" alt="Edit" />
								  </g:link>
								</td>
								<td>${fieldValue(bean: partnerInstance, field: "address")}</td>
								<td>${fieldValue(bean: partnerInstance, field: "website")}</td>
								<td>${fieldValue(bean: partnerInstance, field: "description")}
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy"
										date="${partnerInstance.dateCreated}" />
								</td>
								<td>
								  <g:if test="${userIsAdministrator}">
								   <g:checkBox name="featured" value="${partnerInstance.featured}" 
								    onclick="${remoteFunction(action:'toggleFeatured', id:partnerInstance.id, params:'\'featured=\' + this.checked')}" />
								  </g:if>	
								  <g:else>
								   <input type="checkbox" ${partnerInstance.featured ? 'checked' : ''} disabled />								  
								  </g:else>							
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${partnerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

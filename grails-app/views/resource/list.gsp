
<%@ page import="com.incra.Resource" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'resource.label', default: 'Resource')}" />
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
			  <span style="font-weight: bold; font-size: 13px; color: gray;">Resource Administration</span>
			  <div class="menuButton" style="float: right; font-weight: bold">				
						<g:link class="create" action="create">Create New Resource</g:link>
				</div>
				<div style="clear:both"></div>
			</div>
			<g:form action="list" method="post">
				<i:filterGrid numColumns="2" content="${filters}" />
			</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id"
								title="${message(code: 'resource.id.label', default: 'Id')}" />
							<g:sortableColumn property="name"
								title="${message(code: 'resource.name.label', default: 'Name')}" />
							<th>
								<g:message code="resource.type.label" default="Type" />
							</th>							
							<th>
								<g:message code="resource.uom.label" default="UOM" />
							</th>
							<g:sortableColumn property="dateCreated"
								title="${message(code: 'resource.dateCreated.label', default: 'Date Created')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${resourceInstanceList}" status="i"
							var="resourceInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									<g:link action="show" id="${resourceInstance.id}">${fieldValue(bean:
										resourceInstance, field: "id")}</g:link>
								</td>
								<td>${fieldValue(bean: resourceInstance, field: "name")}</td>
								<td>${fieldValue(bean: resourceInstance, field: "type")}</td>							
								<td>${fieldValue(bean: resourceInstance, field: "uom")}</td>
								<td>
									<g:formatDate date="${resourceInstance.dateCreated}" />
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${resourceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

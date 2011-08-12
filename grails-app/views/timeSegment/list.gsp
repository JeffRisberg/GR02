
<%@ page import="com.incra.TimeSegment" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'timeSegment.label', default: 'TimeSegment')}" />
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
			  <span style="font-weight: bold; font-size: 13px; color: gray;">TimeSegment Administration</span>
			  <div class="menuButton" style="float: right; font-weight: bold">				
						<g:link class="create" action="create">Create New TimeSegment</g:link>
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
								title="${message(code: 'timeSegment.id.label', default: 'Id')}" />
							<th>
								<g:message code="timeSegment.timeScale.label" default="Time Scale" />
							</th>
							<g:sortableColumn property="label"
								title="${message(code: 'timeSegment.label.label', default: 'Label')}" />
							<g:sortableColumn property="fromDate"
								title="${message(code: 'timeSegment.fromDate.label', default: 'From Date')}" />
							<g:sortableColumn property="toDate"
								title="${message(code: 'timeSegment.toDate.label', default: 'To Date')}" />
							<g:sortableColumn property="dateCreated"
								title="${message(code: 'timeSegment.lastUpdated.label', default: 'Last Updated')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${timeSegmentInstanceList}" status="i"
							var="timeSegmentInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									<g:link action="show" id="${timeSegmentInstance.id}">${fieldValue(bean:
										timeSegmentInstance, field: "id")}</g:link>
								</td>
								<td>${fieldValue(bean: timeSegmentInstance, field: "timeScale")}
								</td>
								<td>${fieldValue(bean: timeSegmentInstance, field: "label")}
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${timeSegmentInstance.fromDate}" />
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${timeSegmentInstance.toDate}" />
								</td>
								<td>
									<g:formatDate date="${timeSegmentInstance.lastUpdated}" />
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${timeSegmentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

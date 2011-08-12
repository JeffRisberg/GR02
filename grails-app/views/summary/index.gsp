<html>
	<head>
		<title>Tools Dashboard</title>
		<meta name="layout" content="main" />
	</head>
	<body>
	  <div style="margin-top: 4px; margin-bottom: 3px">
			<span style="font-weight: bold; font-size: 13px; color: gray;">
			  EcoCloud's tools allow you to evaluate potential sustainability Projects at your Facilities	using geographic, cost, and usage data.
			</span>
		</div>
	  <div style="width: 100%">
	    <p:image src="DashboardGraphic.png" width="550" style="margin: 20px auto; display: block" />
	  </div>
	  <table>
		<g:each in="${panes}" var="pane">
				<tr>
				  <td width="54">				 
				    <p:image src="${pane.imgSrc}.png" width="48" height="48"/>
				  </td>
				  <td width="100%">
				  <div style="border-bottom: 1px solid black; margin-bottom: 6px; font-size: 16px">
					  ${pane.plural}
					</div>
					<g:if test="${pane.showManageLink}">
					  <div style="float: right; background: #ddf; padding: 4px; font-size: 14px">
						  <g:link controller="${pane.controllerName}">Manage ${pane.manageLabel}</g:link>
					  </div>
					</g:if>
					<div style="margin: 10px 10px 0px 0px; font-size: 14px">
						<g:each in="${pane.records}" var="record">
							<g:render template="${pane.templateName}" model="[record: record]"/>
						</g:each>
					</div>
					<div style="clear:both"></div>
					<g:if test="${pane.showCreateLink && userIsLoggedIn}">
					  <g:link controller="${pane.controllerName}" action="create">
					  <b>+ Create ${pane.label}</b>
					  </g:link>
					</g:if>
					<g:if test="${pane.showMoreLink}">
					  <g:link controller="${pane.controllerName}" action="list"><b>Show More</b></g:link>
					</g:if>
					</td>
				</tr>
		</g:each>
		</table>
	</body>
</html>
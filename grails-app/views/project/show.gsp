
<%@ page import="com.incra.Project" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'project.label', default: 'Project')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	    <g:javascript library="jquery" plugin="jquery" />
    	<p:css name="collapser" /> 
    	<p:javascript src="collapser" />
		<script type="text/javascript">
		$(document).ready(function(){
	      collapseExpand();
   		 });
		</script>
	</head>
	<body>
		<div class="body">
			<h2>Project Information</h2>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<div class="layer1">
				  <g:each in="${panes}" var="pane">
					 <!-- Begin: This is needed for expand collapse -->
	  						<div class="titleHeading" id="titleHeading">
								<div class="heading">${pane.label}</div>
								<div style="clear:both" ></div>
	 						</div>
	 <!--End: This is needed for expand collapse -->
						  <div class="content">
						  <g:render template="${pane.templateName}"
						    model="[projectInstance: projectInstance, roiProjectInstance: roiProjectInstance]" />
					  </div>
					</g:each>					
				</div>
			</div>
			<div class="buttons">
				<g:form>
					<g:hiddenField name="id" value="${projectInstance?.id}" />
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
		</div>
	</body>
</html>


<%@ page import="com.incra.ROIProject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ROIProject.label', default: 'ROIProject')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h2><g:message code="default.list.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'ROIProject.id.label', default: 'Id')}" />
                        
                            <th><g:message code="ROIProject.project.label" default="Project" /></th>
                        
                            <g:sortableColumn property="currentMonthlyWaterUse" title="${message(code: 'ROIProject.currentMonthlyWaterUse.label', default: 'Current Monthly Water Use')}" />
                        
                            <g:sortableColumn property="currentCycles" title="${message(code: 'ROIProject.currentCycles.label', default: 'Current Cycles')}" />
                        
                            <g:sortableColumn property="proposedCycles" title="${message(code: 'ROIProject.proposedCycles.label', default: 'Proposed Cycles')}" />
                        
                            <th><g:message code="ROIProject.currentProvider.label" default="Current Provider" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ROIProjectInstanceList}" status="i" var="ROIProjectInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${ROIProjectInstance.id}">${fieldValue(bean: ROIProjectInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: ROIProjectInstance, field: "project")}</td>
                        
                            <td>${fieldValue(bean: ROIProjectInstance, field: "currentMonthlyWaterUse")}</td>
                        
                            <td>${fieldValue(bean: ROIProjectInstance, field: "currentCycles")}</td>
                        
                            <td>${fieldValue(bean: ROIProjectInstance, field: "proposedCycles")}</td>
                        
                            <td>${fieldValue(bean: ROIProjectInstance, field: "currentProvider")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ROIProjectInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

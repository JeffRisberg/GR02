
<%@ page import="com.incra.PipelineFoot" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pipelineFoot.label', default: 'PipelineFoot')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h2><g:message code="default.list.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'pipelineFoot.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'pipelineFoot.name.label', default: 'Pipeline (HDPE)')}" />
                        
                            <g:sortableColumn property="seqNum" title="${message(code: 'pipelineFoot.seqNum.label', default: 'Seq Num')}" />
                        
                            <g:sortableColumn property="cost" title="${message(code: 'pipelineFoot.cost.label', default: 'Cost per Linear Foot (Installed)')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${pipelineFootInstanceList}" status="i" var="pipelineFootInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${pipelineFootInstance.id}">${fieldValue(bean: pipelineFootInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: pipelineFootInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: pipelineFootInstance, field: "seqNum")}</td>
                        
                            <td>${fieldValue(bean: pipelineFootInstance, field: "cost")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${pipelineFootInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

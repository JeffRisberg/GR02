

<%@ page import="com.incra.PipelineFoot" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pipelineFoot.label', default: 'PipelineFoot')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h2><g:message code="default.edit.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${pipelineFootInstance}">
            <div class="errors">
                <g:renderErrors bean="${pipelineFootInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${pipelineFootInstance?.id}" />
                <g:hiddenField name="version" value="${pipelineFootInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="pipelineFoot.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pipelineFootInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${pipelineFootInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="seqNum"><g:message code="pipelineFoot.seqNum.label" default="Seq Num" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pipelineFootInstance, field: 'seqNum', 'errors')}">
                                    <g:textField name="seqNum" value="${fieldValue(bean: pipelineFootInstance, field: 'seqNum')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cost"><g:message code="pipelineFoot.cost.label" default="Cost" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pipelineFootInstance, field: 'cost', 'errors')}">
                                    <g:textField name="cost" value="${fieldValue(bean: pipelineFootInstance, field: 'cost')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>

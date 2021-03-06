

<%@ page import="com.incra.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
            <g:hasErrors bean="${userInstance}">
            <div class="errors">
                <g:renderErrors bean="${userInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${userInstance?.id}" />
                <g:hiddenField name="version" value="${userInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="userId"><g:message code="user.userId.label" default="User Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'userId', 'errors')}">
                                    <g:textField name="userId" maxlength="20" value="${userInstance?.userId}" />
                                </td>
                            </tr>                                                  
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastLogin"><g:message code="user.lastLogin.label" default="Last Login" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'lastLogin', 'errors')}">
                                    ${userInstance?.lastLogin?.toString()}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="loginCount"><g:message code="user.loginCount.label" default="Login Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'loginCount', 'errors')}">
                                    <g:textField name="loginCount" value="${fieldValue(bean: userInstance, field: 'loginCount')}" />
                                </td>
                            </tr>                                                
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="profile"><g:message code="user.profile.label" default="Profile" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'profile', 'errors')}">
                                    <g:select name="profile.id" from="${com.incra.Profile.list()}" optionKey="id" value="${userInstance?.profile?.id}" noSelection="['null': '']" />
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

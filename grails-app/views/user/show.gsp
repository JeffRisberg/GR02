
<%@ page import="com.incra.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>           
        </div>
        <div class="body">
            <h2><g:message code="default.show.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.id.label" default="Id" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "id")}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.userId.label" default="User Id" /></td>                           
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "userId")}</td>                            
                        </tr>                                        
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.lastLogin.label" default="Last Login" /></td>                           
                            <td valign="top" class="value"><g:formatDate date="${userInstance?.lastLogin}" /></td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.loginCount.label" default="Login Count" /></td>                           
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "loginCount")}</td>                            
                        </tr>                                          
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.profile.label" default="Profile" /></td>                           
                            <td valign="top" class="value"><g:link controller="profile" action="show" id="${userInstance?.profile?.id}">${userInstance?.profile?.encodeAsHTML()}</g:link></td>                           
                        </tr>
                    
                    </tbody>
                </table>
            </div>            
        </div>
    </body>
</html>

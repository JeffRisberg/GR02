
<%@ page import="com.incra.Profile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
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
                            <td valign="top" class="name"><g:message code="profile.id.label" default="Id" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: profileInstance, field: "id")}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profile.fullName.label" default="Full Name" /></td>                           
                            <td valign="top" class="value">${fieldValue(bean: profileInstance, field: "fullName")}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profile.email.label" default="Email" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: profileInstance, field: "email")}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profile.photo.label" default="Photo" /></td>                           
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profile.country.label" default="Country" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: profileInstance, field: "country")}</td>                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profile.timeZone.label" default="Time Zone" /></td>                            
                            <td valign="top" class="value">${fieldValue(bean: profileInstance, field: "timeZone")}</td>                           
                        </tr>
                    
                    </tbody>
                </table>
            </div>            
        </div>
    </body>
</html>

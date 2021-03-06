
<%@ page import="com.incra.Profile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'profile.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="fullName" title="${message(code: 'profile.fullName.label', default: 'Full Name')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'profile.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="photo" title="${message(code: 'profile.photo.label', default: 'Photo')}" />
                        
                            <g:sortableColumn property="country" title="${message(code: 'profile.country.label', default: 'Country')}" />
                        
                            <g:sortableColumn property="timeZone" title="${message(code: 'profile.timeZone.label', default: 'Time Zone')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${profileInstanceList}" status="i" var="profileInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${profileInstance.id}">${fieldValue(bean: profileInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "fullName")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "photo")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "country")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "timeZone")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${profileInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

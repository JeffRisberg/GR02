
<%@ page import="com.incra.ContentSection" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contentSection.label', default: 'ContentSection')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'contentSection.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'contentSection.name.label', default: 'Name')}" />
                        
                            <th><g:message code="contentSection.contact.label" default="Contact" /></th>
                        
                            <th><g:message code="contentSection.address.label" default="Address" /></th>
                        
                            <g:sortableColumn property="website" title="${message(code: 'contentSection.website.label', default: 'Website')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'contentSection.dateCreated.label', default: 'Date Created')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contentSectionInstanceList}" status="i" var="contentSectionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${contentSectionInstance.id}">${fieldValue(bean: contentSectionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: contentSectionInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: contentSectionInstance, field: "contact")}</td>
                        
                            <td>${fieldValue(bean: contentSectionInstance, field: "address")}</td>
                        
                            <td>${fieldValue(bean: contentSectionInstance, field: "website")}</td>
                        
                            <td><g:formatDate date="${contentSectionInstance.dateCreated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contentSectionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

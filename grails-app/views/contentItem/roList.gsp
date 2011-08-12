
<%@ page import="com.incra.ContentItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="noheader" />
        <g:set var="entityName" value="${message(code: 'contentItem.label', default: 'ContentItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>
           body { font-family:"Lucida Grande","Lucida Sans Unicode",sans-serif; }
        </style>
    </head>
    <body>
        <div class="nav">            
        </div>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>                        
                            <g:sortableColumn property="name" title="${message(code: 'contentItem.name.label', default: 'Name')}" />                        
                            <th><g:message code="contentItem.address.label" default="Address" /></th>                        
                            <g:sortableColumn property="website" title="${message(code: 'contentItem.website.label', default: 'Website')}" />                        
                            <g:sortableColumn property="description" title="${message(code: 'contentItem.description.label', default: 'Description')}" />                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'contentItem.dateCreated.label', default: 'Date&nbsp;Created')}" />                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contentItemInstanceList}" status="i" var="contentItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">                        
                            <td>${fieldValue(bean: contentItemInstance, field: "name")}</td>                        
                            <td>${fieldValue(bean: contentItemInstance, field: "address")}</td>                        
                            <td>${fieldValue(bean: contentItemInstance, field: "website")}</td>                        
                            <td>${fieldValue(bean: contentItemInstance, field: "description")}</td>                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${contentItemInstance.dateCreated}" /></td>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contentItemInstanceTotal}" params="[tag: params.tag]" />
            </div>
        </div>
    </body>
</html>

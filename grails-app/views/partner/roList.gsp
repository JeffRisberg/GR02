
<%@ page import="com.incra.Partner" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="noheader" />
        <g:set var="entityName" value="${message(code: 'partner.label', default: 'Partner')}" />
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
                            <g:sortableColumn property="name" title="${message(code: 'partner.name.label', default: 'Name')}" />                        
                            <th><g:message code="partner.address.label" default="Address" /></th>                        
                            <g:sortableColumn property="website" title="${message(code: 'partner.website.label', default: 'Website')}" />                        
                            <g:sortableColumn property="description" title="${message(code: 'partner.description.label', default: 'Description')}" />                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'partner.dateCreated.label', default: 'Date&nbsp;Created')}" />                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partnerInstanceList}" status="i" var="partnerInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">                        
                            <td>${fieldValue(bean: partnerInstance, field: "name")}</td>                        
                            <td>${fieldValue(bean: partnerInstance, field: "address")}</td>                        
                            <td>${fieldValue(bean: partnerInstance, field: "website")}</td>                        
                            <td>${fieldValue(bean: partnerInstance, field: "description")}</td>                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${partnerInstance.dateCreated}" /></td>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partnerInstanceTotal}" params="[tag: params.tag]"/>
            </div>
        </div>
    </body>
</html>

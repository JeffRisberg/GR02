
<%@ page import="com.incra.ContentItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contentItem.label', default: 'ContentItem')}" />
        <g:javascript library="prototype" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create">
            New Content Resource</g:link>
            </span>
        </div>
        <div class="body">
            <h2>Content Resource List</h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>                        
                            <g:sortableColumn property="name" title="${message(code: 'contentItem.name.label', default: 'Name')}" />                        
                            <g:sortableColumn property="contentItemType" title="${message(code: 'contentItem.contentItemType.label', default: 'Content Item Type')}" />
                            <g:sortableColumn property="partnerFlag" title="Partner" /> 
                            <g:sortableColumn property="paidFlag" title="Paid" /> 
                            <g:sortableColumn property="approvedFlag" title="Approved" />                        
                            <g:sortableColumn property="url" title="${message(code: 'contentItem.url.label', default: 'Url')}" />                        
                            <g:sortableColumn property="description" title="${message(code: 'contentItem.description.label', default: 'Description')}" />                                                                           
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contentItemInstanceList}" status="i" var="contentItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">                        
                            <td><g:link action="show" id="${contentItemInstance.id}">
                              ${fieldValue(bean: contentItemInstance, field: "name")}
                            </g:link></td>                                                                         
                            <td>${fieldValue(bean: contentItemInstance, field: "contentItemType")}</td>
                
                            <td><g:checkBox name="partnerFlag" value="${contentItemInstance.partnerFlag}" 
                            onclick="${remoteFunction(action:'togglePartnerFlag', id:contentItemInstance.id, params:'\'partnerFlag=\' + this.checked')}" />
                            </td>                        
                            <td><g:checkBox name="paidFlag" value="${contentItemInstance.paidFlag}" 
                             onclick="${remoteFunction(action:'togglePaidFlag', id:contentItemInstance.id, params:'\'paidFlag=\' + this.checked')}" />
                             </td>                        
                            <td><g:checkBox name="approvedFlag" value="${contentItemInstance.approvedFlag}"  
                            	onclick="${remoteFunction(action:'toggleApprovedFlag', id:contentItemInstance.id, params:'\'approvedFlag=\' + this.checked')}" />
                            </td>                        
                            <td>${fieldValue(bean: contentItemInstance, field: "url")}</td>                        
                            <td>${fieldValue(bean: contentItemInstance, field: "description")}</td>                                                  
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contentItemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>



<%@ page import="com.incra.ContentSection" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contentSection.label', default: 'ContentSection')}" />
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
            <g:hasErrors bean="${contentSectionInstance}">
            <div class="errors">
                <g:renderErrors bean="${contentSectionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${contentSectionInstance?.id}" />
                <g:hiddenField name="version" value="${contentSectionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="contentSection.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentSectionInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="128" value="${contentSectionInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="contact"><g:message code="contentSection.contact.label" default="Contact" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentSectionInstance, field: 'contact', 'errors')}">
                                    <g:select name="contact.id" from="${com.incra.Contact.list()}" optionKey="id" value="${contentSectionInstance?.contact?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address"><g:message code="contentSection.address.label" default="Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentSectionInstance, field: 'address', 'errors')}">
                                    <g:select name="address.id" from="${com.incra.Address.list()}" optionKey="id" value="${contentSectionInstance?.address?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="website"><g:message code="contentSection.website.label" default="Website" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentSectionInstance, field: 'website', 'errors')}">
                                    <g:textField name="website" maxlength="80" value="${contentSectionInstance?.website}" />
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

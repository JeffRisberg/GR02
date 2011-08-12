

<%@ page import="com.incra.ContentSection" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contentSection.label', default: 'ContentSection')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h2><g:message code="default.create.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contentSectionInstance}">
            <div class="errors">
                <g:renderErrors bean="${contentSectionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>

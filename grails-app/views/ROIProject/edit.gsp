

<%@ page import="com.incra.ROIProject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ROIProject.label', default: 'ROIProject')}" />
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
            <g:hasErrors bean="${ROIProjectInstance}">
            <div class="errors">
                <g:renderErrors bean="${ROIProjectInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${ROIProjectInstance?.id}" />
                <g:hiddenField name="version" value="${ROIProjectInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="project"><g:message code="ROIProject.project.label" default="Project" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'project', 'errors')}">
                                    <g:select name="project.id" from="${com.incra.Project.list()}" optionKey="id" value="${ROIProjectInstance?.project?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentMonthlyWaterUse"><g:message code="ROIProject.currentMonthlyWaterUse.label" default="Current Monthly Water Use" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentMonthlyWaterUse', 'errors')}">
                                    <g:textField name="currentMonthlyWaterUse" value="${fieldValue(bean: ROIProjectInstance, field: 'currentMonthlyWaterUse')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentCycles"><g:message code="ROIProject.currentCycles.label" default="Current Cycles" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentCycles', 'errors')}">
                                    <g:textField name="currentCycles" value="${fieldValue(bean: ROIProjectInstance, field: 'currentCycles')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposedCycles"><g:message code="ROIProject.proposedCycles.label" default="Proposed Cycles" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'proposedCycles', 'errors')}">
                                    <g:textField name="proposedCycles" value="${fieldValue(bean: ROIProjectInstance, field: 'proposedCycles')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentProvider"><g:message code="ROIProject.currentProvider.label" default="Current Provider" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentProvider', 'errors')}">
                                    <g:select name="currentProvider.id" from="${com.incra.Provider.list()}" optionKey="id" value="${ROIProjectInstance?.currentProvider?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposedProvider"><g:message code="ROIProject.proposedProvider.label" default="Proposed Provider" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'proposedProvider', 'errors')}">
                                    <g:select name="proposedProvider.id" from="${com.incra.Provider.list()}" optionKey="id" value="${ROIProjectInstance?.proposedProvider?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentChemicalCosts"><g:message code="ROIProject.currentChemicalCosts.label" default="Current Chemical Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentChemicalCosts', 'errors')}">
                                    <g:textField name="currentChemicalCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'currentChemicalCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentAdministrationCosts"><g:message code="ROIProject.currentAdministrationCosts.label" default="Current Administration Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentAdministrationCosts', 'errors')}">
                                    <g:textField name="currentAdministrationCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'currentAdministrationCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currentMonitoringAndTestingCosts"><g:message code="ROIProject.currentMonitoringAndTestingCosts.label" default="Current Monitoring And Testing Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'currentMonitoringAndTestingCosts', 'errors')}">
                                    <g:textField name="currentMonitoringAndTestingCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'currentMonitoringAndTestingCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposedChemicalCosts"><g:message code="ROIProject.proposedChemicalCosts.label" default="Proposed Chemical Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'proposedChemicalCosts', 'errors')}">
                                    <g:textField name="proposedChemicalCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'proposedChemicalCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposedAdministrationCosts"><g:message code="ROIProject.proposedAdministrationCosts.label" default="Proposed Administration Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'proposedAdministrationCosts', 'errors')}">
                                    <g:textField name="proposedAdministrationCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'proposedAdministrationCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposedMonitoringAndTestingCosts"><g:message code="ROIProject.proposedMonitoringAndTestingCosts.label" default="Proposed Monitoring And Testing Costs" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'proposedMonitoringAndTestingCosts', 'errors')}">
                                    <g:textField name="proposedMonitoringAndTestingCosts" value="${fieldValue(bean: ROIProjectInstance, field: 'proposedMonitoringAndTestingCosts')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distance1"><g:message code="ROIProject.distance1.label" default="Distance1" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'distance1', 'errors')}">
                                    <g:textField name="distance1" value="${fieldValue(bean: ROIProjectInstance, field: 'distance1')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pipelineFoot1"><g:message code="ROIProject.pipelineFoot1.label" default="Pipeline Foot1" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'pipelineFoot1', 'errors')}">
                                    <g:select name="pipelineFoot1.id" from="${com.incra.PipelineFoot.list()}" optionKey="id" value="${ROIProjectInstance?.pipelineFoot1?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distance2"><g:message code="ROIProject.distance2.label" default="Distance2" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'distance2', 'errors')}">
                                    <g:textField name="distance2" value="${fieldValue(bean: ROIProjectInstance, field: 'distance2')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pipelineFoot2"><g:message code="ROIProject.pipelineFoot2.label" default="Pipeline Foot2" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'pipelineFoot2', 'errors')}">
                                    <g:select name="pipelineFoot2.id" from="${com.incra.PipelineFoot.list()}" optionKey="id" value="${ROIProjectInstance?.pipelineFoot2?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distance3"><g:message code="ROIProject.distance3.label" default="Distance3" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'distance3', 'errors')}">
                                    <g:textField name="distance3" value="${fieldValue(bean: ROIProjectInstance, field: 'distance3')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pipelineFoot3"><g:message code="ROIProject.pipelineFoot3.label" default="Pipeline Foot3" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'pipelineFoot3', 'errors')}">
                                    <g:select name="pipelineFoot3.id" from="${com.incra.PipelineFoot.list()}" optionKey="id" value="${ROIProjectInstance?.pipelineFoot3?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distance4"><g:message code="ROIProject.distance4.label" default="Distance4" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'distance4', 'errors')}">
                                    <g:textField name="distance4" value="${fieldValue(bean: ROIProjectInstance, field: 'distance4')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pipelineFoot4"><g:message code="ROIProject.pipelineFoot4.label" default="Pipeline Foot4" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'pipelineFoot4', 'errors')}">
                                    <g:select name="pipelineFoot4.id" from="${com.incra.PipelineFoot.list()}" optionKey="id" value="${ROIProjectInstance?.pipelineFoot4?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="otherCapitalImprovements"><g:message code="ROIProject.otherCapitalImprovements.label" default="Other Capital Improvements" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'otherCapitalImprovements', 'errors')}">
                                    <g:textField name="otherCapitalImprovements" value="${fieldValue(bean: ROIProjectInstance, field: 'otherCapitalImprovements')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="basinLevelControlValve"><g:message code="ROIProject.basinLevelControlValve.label" default="Basin Level Control Valve" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'basinLevelControlValve', 'errors')}">
                                    <g:textField name="basinLevelControlValve" value="${fieldValue(bean: ROIProjectInstance, field: 'basinLevelControlValve')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="blowdownControlValve"><g:message code="ROIProject.blowdownControlValve.label" default="Blowdown Control Valve" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'blowdownControlValve', 'errors')}">
                                    <g:textField name="blowdownControlValve" value="${fieldValue(bean: ROIProjectInstance, field: 'blowdownControlValve')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="chemicalFeedSkid"><g:message code="ROIProject.chemicalFeedSkid.label" default="Chemical Feed Skid" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'chemicalFeedSkid', 'errors')}">
                                    <g:textField name="chemicalFeedSkid" value="${fieldValue(bean: ROIProjectInstance, field: 'chemicalFeedSkid')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="corrosionCouponRack"><g:message code="ROIProject.corrosionCouponRack.label" default="Corrosion Coupon Rack" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'corrosionCouponRack', 'errors')}">
                                    <g:textField name="corrosionCouponRack" value="${fieldValue(bean: ROIProjectInstance, field: 'corrosionCouponRack')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="oxidingBiocideFeeder"><g:message code="ROIProject.oxidingBiocideFeeder.label" default="Oxiding Biocide Feeder" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'oxidingBiocideFeeder', 'errors')}">
                                    <g:textField name="oxidingBiocideFeeder" value="${fieldValue(bean: ROIProjectInstance, field: 'oxidingBiocideFeeder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pHConductivityController"><g:message code="ROIProject.pHConductivityController.label" default="PHC onductivity Controller" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ROIProjectInstance, field: 'pHConductivityController', 'errors')}">
                                    <g:textField name="pHConductivityController" value="${fieldValue(bean: ROIProjectInstance, field: 'pHConductivityController')}" />
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

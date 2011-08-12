
<%@ page import="com.incra.ROIProject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ROIProject.label', default: 'ROIProject')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                            <td valign="top" class="name"><g:message code="ROIProject.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.project.label" default="Project" /></td>
                            
                            <td valign="top" class="value"><g:link controller="project" action="show" id="${ROIProjectInstance?.project?.id}">${ROIProjectInstance?.project?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentMonthlyWaterUse.label" default="Current Monthly Water Use" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "currentMonthlyWaterUse")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentCycles.label" default="Current Cycles" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "currentCycles")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.proposedCycles.label" default="Proposed Cycles" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "proposedCycles")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentProvider.label" default="Current Provider" /></td>
                            
                            <td valign="top" class="value"><g:link controller="provider" action="show" id="${ROIProjectInstance?.currentProvider?.id}">${ROIProjectInstance?.currentProvider?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.proposedProvider.label" default="Proposed Provider" /></td>
                            
                            <td valign="top" class="value"><g:link controller="provider" action="show" id="${ROIProjectInstance?.proposedProvider?.id}">${ROIProjectInstance?.proposedProvider?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentChemicalCosts.label" default="Current Chemical Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "currentChemicalCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentAdministrationCosts.label" default="Current Administration Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "currentAdministrationCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.currentMonitoringAndTestingCosts.label" default="Current Monitoring And Testing Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "currentMonitoringAndTestingCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.proposedChemicalCosts.label" default="Proposed Chemical Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "proposedChemicalCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.proposedAdministrationCosts.label" default="Proposed Administration Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "proposedAdministrationCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.proposedMonitoringAndTestingCosts.label" default="Proposed Monitoring And Testing Costs" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "proposedMonitoringAndTestingCosts")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.distance1.label" default="Distance1" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "distance1")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.pipelineFoot1.label" default="Pipeline Foot1" /></td>
                            
                            <td valign="top" class="value"><g:link controller="pipelineFoot" action="show" id="${ROIProjectInstance?.pipelineFoot1?.id}">${ROIProjectInstance?.pipelineFoot1?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.distance2.label" default="Distance2" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "distance2")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.pipelineFoot2.label" default="Pipeline Foot2" /></td>
                            
                            <td valign="top" class="value"><g:link controller="pipelineFoot" action="show" id="${ROIProjectInstance?.pipelineFoot2?.id}">${ROIProjectInstance?.pipelineFoot2?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.distance3.label" default="Distance3" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "distance3")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.pipelineFoot3.label" default="Pipeline Foot3" /></td>
                            
                            <td valign="top" class="value"><g:link controller="pipelineFoot" action="show" id="${ROIProjectInstance?.pipelineFoot3?.id}">${ROIProjectInstance?.pipelineFoot3?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.distance4.label" default="Distance4" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "distance4")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.pipelineFoot4.label" default="Pipeline Foot4" /></td>
                            
                            <td valign="top" class="value"><g:link controller="pipelineFoot" action="show" id="${ROIProjectInstance?.pipelineFoot4?.id}">${ROIProjectInstance?.pipelineFoot4?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.otherCapitalImprovements.label" default="Other Capital Improvements" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "otherCapitalImprovements")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.basinLevelControlValve.label" default="Basin Level Control Valve" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "basinLevelControlValve")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.blowdownControlValve.label" default="Blowdown Control Valve" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "blowdownControlValve")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.chemicalFeedSkid.label" default="Chemical Feed Skid" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "chemicalFeedSkid")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.corrosionCouponRack.label" default="Corrosion Coupon Rack" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "corrosionCouponRack")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.oxidingBiocideFeeder.label" default="Oxiding Biocide Feeder" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "oxidingBiocideFeeder")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="ROIProject.pHConductivityController.label" default="PHC onductivity Controller" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: ROIProjectInstance, field: "pHConductivityController")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${ROIProjectInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>

<div style="margin-top: 3px">
	<g:link controller="project" action="show" id="${record?.id}"><b>${record.name}</b></g:link> (${record.type.name})
	at
	<g:link controller="account" action="show" id="${record?.account?.id}">${record?.account?.name}</g:link>
</div>
<div style="font-size: 11px; font-style: italic">
		${record.description}
</div>
<div style="width: 650px; font-size: 12px">
  <table style="background: skyblue">
  <tr>
   <td style="padding: 1px">Status: ${record.status}</td>
   <td style="padding: 1px">Priority: <i:priorityLabel value="${record.priority}"/></td>
   <td style="padding: 1px">Start Date: <g:formatDate format="MM/dd/yyyy" date="${record?.startDate}" /></td>
   <td style="padding: 1px">End Date: <g:formatDate format="MM/dd/yyyy" date="${record?.endDate}" /></td>
  </tr>
  <tr>
   <td style="padding: 1px">Budget ($): <g:formatNumber number="${record.budget}" format="##,###,###" /></td>
   <td style="padding: 1px">Investment ($): <g:formatNumber number="${record.capitalInvest}" format="##,###,###" /></td>
   <td style="padding: 1px">Payback (months): <g:formatNumber number="${record.paybackMonths}" format="###,##0.0" />&nbsp;</td>
   <td style="padding: 1px">ROI: <g:formatNumber number="${record.roi}" format="#,##0.00" /> %</td>
  </tr>
  </table>
</div>
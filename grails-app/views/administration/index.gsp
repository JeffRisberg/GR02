
<html>
  <head>
	  <title>Administration</title>
	  <meta name="layout" content="main" />
	  <style>
	    td { padding: 5px; }
    </style>
  </head>
  <body style="margin: 30px">
  <h2>Adminstration</h2>
   <table cellspacing="6" cellpadding="3">
   	<tr>
    <td><g:link controller="user">Users</g:link></td>
   	<td><g:link controller="logEntry">Log Entries</g:link></td>
   	<td><g:link controller="timeSegment">Time Segments</g:link></td>
   	</tr>
   	<tr>  	
   	<td><g:link controller="unitOfMeasure">Units of Measure</g:link></td>
   	<td><g:link controller="resourceType">Resource Types</g:link></td>
   	<td><g:link controller="resource">Resources</g:link></td>
   	</tr>
   	<tr>
   	<td><g:link controller="resourcePrice">Resource Prices</g:link></td>
   	<td><g:link controller="accountType">Account Types</g:link></td>
   	<td><g:link controller="transactionType">Transaction Types</g:link></td>   	
   	</tr>
   	<tr>
   	<td><g:link controller="transactionCategory">Transaction Categories</g:link></td>
   	<td><g:link controller="pipelineFoot">PipelineFoot Entries</g:link></td>   	
   	<td><g:link controller="projectType">Project Types</g:link></td>
    </tr>
   	<tr>
   	<td><g:link controller="flow">Flows</g:link></td>
   	<td><g:link controller="transaction">Transactions</g:link></td>
   	<td><g:link controller="contentItem">Content Resources</g:link></td>
   	</tr>
   	<tr style="background: skyblue">
   	<td><g:link controller="account" action="listAll">All Accounts</g:link></td>
   	<td><g:link controller="project" action="listAll">All Projects</g:link></td>
   	<td><g:link controller="flow" action="listAll">All Flows</g:link></td>
   	</tr>
   	<tr>
   	<td><g:link controller="project" action="recalcAll">Recalc All Projects</g:link></td>
   	<td><g:link controller="administration" action="memory">Memory</g:link></td>
   	</tr>
   </table>
  </body>
</html>
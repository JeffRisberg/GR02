<%@ page import="com.incra.MarketplacePanel" %>
<%@ page import="com.incra.MarketplaceItem" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="noheader" />
        
        <style type="text/css">
      p { font-size: 1.4em; }
      .content a { text-decoration: none; }    </style>
      <p:css name="marketplaceCollapser" /> 
       <p:javascript src="marketplaceCollapser" />
    </head>
    <body>
    <g:set var="counter" value="${0}" />
        <div style="width: 653px">            
          <g:each in="${panels}" status="i" var="panel">
          <g:set var="counter" value="${counter + 1}" />
              <div class="marketplacePanel" id='marketplacePanel${counter}'>
                <div class="titleHeading" id='titleHeading${counter}'>
                   <div class="labelHeading">${panel.label}</div>
                 </div>
                <div class="content" id='content${counter}'>
                <g:each in="${panel.items}" var="item">
                <div style="float:left; width:300px;">
         	       <div style="float:left; margin: 5px;">         
				            <img src="<g:createLink action='renderImage' id='${item.id}' />"
				              width="55" height="55" border="0" style="border: 1px solid red"/>           
				       </div>   
				          <div style="float:left; margin-top: 5px; max-width:200px;">
				            ${item.label}
				          </div>  
				    </div>   
				                      
                </g:each>
                </div>
              </div>
          </g:each>                            
        </div>
        <script type="text/javascript">
       $(document).ready(function() {
        collapseExpand();
       });
    </script>
    </body>
</html>

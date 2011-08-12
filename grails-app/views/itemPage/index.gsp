<html>
	<head>
		<title>My Page</title>
		<meta name="layout" content="noheader" />
		<style type="text/css">
      p { font-size: 1.4em; }
      .content { font-family: sans-serif; }
      .content a { text-decoration: none; }    </style>
    <p:css name="collapser" /> 
    <p:javascript src="collapser" />
	</head>
	<body>
	<g:set var="counter" value="${0}" />
    <g:each in="${panels}" var="panel" >
    <g:set var="counter" value="${counter + 1}" />
    <!-- Begin: This is needed for expand collapse -->
	  						<div class="titleHeading" id='titleHeading${counter}'>
								<div class="heading" style="font-family: sans-serif">${panel.label}</div>
								<div class="maximize">Max</div>
								<div style="clear:both" ></div>
	 						</div>
	 <!--End: This is needed for expand collapse -->
    
    <div class="content" id='content${counter}'>
       <g:each in="${panel.items}" var="item">
         <g:if test="${item.imgSrc}">
          <div style="float: left; margin: 5px;">
          <g:if test="${item.url}">
           <g:link url="${item.url}" target="_top">
            <img src="<g:createLink action='renderImage' id='${item.id}' />"
              width="140" height="120" border="0"/>
            </g:link>
          </g:if> 
          <g:else>
           <img src="<g:createLink action='renderImage' id='${item.id}' />"
              width="140" height="120" />
          </g:else>  
          </div>           
         </g:if>
         <g:else>         
          <div class="${item.cssStyleName}" style="float: left; margin: 5px;">
            <g:if test="${item.cssStyleName == 'iconProduct'}">
             <div style="margin: 50px 10px 0px 42px;">              
               <g:if test="${item.url}">
                <g:link url="${item.url}" target="_top">${item.label}</g:link>
               </g:if>
               <g:else>
                ${item.label}
               </g:else>              
             </div>             
            </g:if>
            <g:else>
             <div style="margin: 40px 10px 0px 30px;">             
               <g:if test="${item.url}">
                <g:link url="${item.url}" target="_top">${item.label}</g:link>
               </g:if>
               <g:else>
                ${item.label}
               </g:else>              
             </div>
            </g:else>
          </div>
         </g:else>
       </g:each>
       <div style="clear:both"></div>
    </div>
    </g:each>
    <script type="text/javascript">
       $(document).ready(function() {
        collapseExpand();
       });
    </script>
  </body>
</html>
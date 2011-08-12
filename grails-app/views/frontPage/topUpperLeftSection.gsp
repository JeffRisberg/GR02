<html>
	<head>
		<title>Help</title>
		<meta name="layout" content="noheader" />
		<p:css name="frontPage" />
	</head>
	<body>
		<div id="leftColumn">
			<div id="upperLeft">
				<div id="contentitems">
					<g:each in="${items}" var="item" status="itemIndex">
						<div class="contentitem">
							<div class="itemLeft" id="${itemIndex}">
								${item.left}
		       			</div>
							<div class="itemRight">
								<div class="itemTitle">
									${item.title}
			        		</div>								
							</div>
						</div>
						<div style="clear:both"></div>
						<script type="text/javascript">
						$(document).ready(function() {  						    
					      $('#contentitems #${itemIndex}').qtip({					      
					      content: '<table><tr><td><img src="http://www.ggmark.com/people.gif" hspace="6" border="0"/></td><td class="itemText">${item.text}</td></tr></table>'
					      })
					  });
					  </script>
					</g:each>
				</div><!-- contentitems ends here -->

				<div id="resources">
					Resources
					<g:each in="${resources}" var="resource">
						<div class="resource">
							${resource.name}
					</div>
					</g:each>
				</div> <!-- Resources ends here -->

				<div id="sectors">
					Sectors
					<g:each in="${sectors}" var="sector">
						<div class="sector">
							${sector.name}
          				</div>
					</g:each>
				</div> <!-- sectors ends here -->
			</div><!-- UpperLeft ends here -->

			<div style="clear:both; border-top: 15px solid #7887b6; width: 600px;"></div>

			
		</div> <!-- left column ends here -->

		
	</body>
</html>
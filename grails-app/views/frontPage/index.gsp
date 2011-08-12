<html>
	<head>
		<title>Help</title>
		<meta name="layout" content="alternate" />
		<p:css name="frontPage" />
		<p:css name="scrollable-vertical" />
		<p:javascript src="easySlider" />
		<script type="text/javascript" src="http://cdn.jquerytools.org/1.2.5/all/jquery.tools.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
			$("#slider").easySlider({
			auto: true,
			continuous: true
			});
			});	
	</script>
	<script>
	// execute your scripts when DOM is ready. this is a good habit
		$(function() {		
			// initialize scrollable
			$(".scrollable").scrollable({ vertical: true});
			// get access to the API
			var api = $('.scrollable').data('scrollable')
			// we scroll one items forward
			api.move(0);	
			
		});
		</script>
	

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

			<div id="lowerLeft"> <!-- lowerLeft starts here -->
					<div class="lowerLeftText">
						SSV Partners in the News
					</div>
				<div id="upperlowerLeft">
		 	     <div id="actions">
   					<a class="prev">&laquo; Back</a>
  					<a class="next">More News &raquo;</a>
				</div>

					<!-- root element for scrollable -->
					<div id="list" class="scrollable vertical">
	 
							<!-- root element for the items -->
					<div class="items">
					 <g:each status="i" in="${newsItems}" var="newsItem">
					 <div>
							<div class="item">
							<g:if test="${newsItem.photo}">
                  			<img src="<g:createLink action='renderImage' id='${newsItem.id}' />"/>
                			</g:if>
						     <h3>${newsItem.name}</h3>
						     <strong>
					 			${newsItem.description}
							</strong>
							<p>
					 			${newsItem.body}
							</p>
				
							<p>
							<a href="#">Read more</a> &nbsp; <a href="#">Show in map</a>
							</p>
				
							</div>
					 	 
					</div>
					</g:each> 
					
            
            <!-- next page -->

          
					
			</div>   <!-- items -->
			 
			</div><!-- list -->
			 
			</div> <!--upperlowerLeft-->

				<div id="maps"> <!-- maps starts here -->
					<div class="lowerLeftText">
						Maps
					</div>
				<div class="upperlowerRight">
				</div>
				</div> <!-- maps ends here -->

				<div class="middlelowerLeft">
				</div>
				<div class="middlelowerRight">
				</div>
				<div class="bottomlowerLeft">
				</div>
				<div class="bottomlowerRight">
				</div>
			</div> <!-- lowerLeft ends here -->
		</div> <!-- left column ends here -->

		<!-- right column starts here -->
		<div id="rightColumn">

			<div class="searchText">
				<div style="border: 1px solid gray;">
					Site Search
			  </div>
			</div>

			<div id="chosenPartners">
				${partnerText}
				<br>
					<div id="slider">
						<g:each var="partner" in="${chosenPartners}">
							<g:if test="${partner.photo}">
								<ul>
									<li>
										<img src="<g:createLink action='renderImage' id='${partner.id}' />"
											width="120" height="100" border="0" />
									</li>
								</ul>
							</g:if>
						</g:each>
					</div>
			</div>
			<div class="greeting">
				<div class="greetingText">
					${welcomeMessage}	    
		  		</div>
			</div>
			<!-- event -->
			<!-- member List -->
		</div>


 


 
	</body>
</html>
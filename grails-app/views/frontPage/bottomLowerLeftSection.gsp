<html>
	<head>
		<title>BottomLowerLeft</title>
		<meta name="layout" content="noheader" />
		<p:css name="frontPage" />
		<p:css name="scrollable-vertical" />
		<script type="text/javascript" src="http://cdn.jquerytools.org/1.2.5/all/jquery.tools.min.js"></script>
	 
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


 
	</body>
</html>
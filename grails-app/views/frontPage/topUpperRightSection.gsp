<html>
	<head>
		<title>Help</title>
		<meta name="layout" content="noheader" />
		<p:css name="frontPage" />
	 	<p:javascript src="easySlider" />
		<script type="text/javascript">
			$(document).ready(function(){
			$("#slider").easySlider({
			auto: true,
			continuous: true
			});
			});	
	</script>
</head>
	<body>
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
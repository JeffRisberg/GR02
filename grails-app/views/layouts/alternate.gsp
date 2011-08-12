<!DOCTYPE html>
<html>
	<head>
		<title>EcoCloud - A Virtual Industrial Ecosystem</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
		<link rel="SHORTCUT ICON" href="images/favicon.ico" type="image/x-icon" />
		<meta name="description"
			content="Brought to you by Sustainable Silicon Valley to connect industry leaders with information." />
		<meta name="keywords"
			content="valley, sustainability, water, energy, carbon, silicon, industrial, ecosystem, ecology" />
		<g:javascript library="jquery" plugin="jquery" />
		<p:css name="bundle" />
		
		<p:css name="PageDesign" />  
		<p:css name="PageStructure" />
		
		<p:javascript src="jquery-and-ui" />
		<g:layoutHead />
	</head>
	<body class="">
		<div class="xj_before_content">
			<div id="xn_bar">
				<div id="xn_bar_menu">
					<div id="xn_bar_menu_branding">
					</div>

					<div id="xn_bar_menu_more">
						<form id="xn_bar_menu_search" method="GET"
							action="http://ecocloud1.ning.com/main/search/search">
							<fieldset>
								<input type="text" name="q" id="xn_bar_menu_search_query"
									value="Search EcoCloud" _hint="Search EcoCloud" accesskey="4"
									class="text xj_search_hint" />
								<a id="xn_bar_menu_search_submit" href="#"
									onclick="document.getElementById('xn_bar_menu_search').submit();return false">Search</a>
							</fieldset>
						</form>
					</div>

					<ul id="xn_bar_menu_tabs">
						<li>
							<a
								href="http://ecocloud1.ning.com/profile/${session.pfSession.user?.userId}"
								id="xn_username">
								${session.pfSession.profile?.fullName}</a>
						</li>
						<li>
							<a id="xn_signout" href="http://ecocloud1.ning.com/main/authorization/signOut">Sign Out</a>
						</li>
					</ul>
				</div>
			</div>
			</div>
			<div class="xg_theme" data-layout-pack="romeo">
				<div id="xg_themebody">
					<div id="xg_ad_above_header" class="xg_ad xj_ad_above_header dy-displaynone">
					</div>
					<div id="xg_head">
						<div id="xg_masthead">
							<h1 id="xg_sitename" class="xj_site_name">
								<a id="application_name_header_link" href="http://ecocloud1.ning.com">
									<img
										src="http://api.ning.com:80/files/rroftL81PWsNfVcUae6Pubyvr06I9YMWSEfTZN-Top42LgwjEXF4pm1ZfGpIskieJM00epaNu*egAscGxYoeMp*W8sPgkVb*/ecocloud_header.png"
										alt="EcoCloud" />
								</a>
							</h1>
							<p id="xg_sitedesc" class="xj_site_desc">A Virtual Industrial Ecosystem</p>
						</div>
						<div id="xg_navigation">
							<ul>
								<li id="xg_tab_main" class="xg_subtab">
									<a href="http://ecocloud1.ning.com">
										<span>Home</span>
									</a>
								</li>
								<li class="xg_subtab">
									<a href="http://ecocloud1.ning.com/page/contribute-content">
										<span>Community Content</span>
									</a>
								</li>
								<li class="xg_subtab">
									<a href="http://ecocloud1.ning.com/page/topics-2">
										<span>Topics</span>
									</a>
								</li>
								<li id="xg_tab_groups" class="xg_subtab this">
									<g:link controller="dashboard">
										<span>Tools</span>
									</g:link>
								</li>
								<li id="xg_tab_groups" class="xg_subtab">
                  <a href="http://ecocloud1.ning.com/page/marketplace-and-personalized">
                    <span>Marketplace</span>
                  </a>
                </li>
								<li id="xg_tab_xn18" class="xg_subtab">
									<a href="http://ecocloud1.ning.com/page/about-ecocloud">
										<span>About</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
					<div style="clear:both;"></div>
				</div>
			</div>
			<div id="xg_body" style="min-height: 500px">				
			  <g:layoutBody />
			</div>
			<div id="xg_foot">
				<g:render template="/footer" />
			</div>
			<div class="jqmWindow" id="ex2"></div>		
	</body>
</html>
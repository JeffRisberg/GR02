<html>
	<head>
		<title>Help</title>
		<meta name="layout" content="main" />
	</head>
	<body>
	 <style type="text/css">
	    p { font-size: 0.8em; }
	 </style>
		<h2>Introduction</h2>
		<p>EcoCloud's tools allow you to determine the return on investment (ROI) 
		  of projects such as switching to recycled water by enabling you fetch and
		  use geographic information, enter usage and cost information, and review 
		  vendors.
		</p>
		<h2>Getting Started</h2>
		<p>The basic steps are:</p>
		<ol>
      <li>Define Facilities</li>
      <li>Create Projects</li>
      <li>View ROI Results</li>
    </ol>
    <h2>Defining a Facility</h2>
      <p>In general, a facility is a location at which projects can be carried out, 
      such as a building.  However, you can also model the detailed structure of 
      your facilities, such as sources and uses of water or other resources within
      a building, or the macro-scale organization of water usage within an entire 
      region.</p>
		<p>The current release of EcoCloud (Phase I) focuses on ROI analysis of 
		  individual buildings, while the upcoming Phase II release addresses water and
		  cost balances based on the flows between facilities at the building, cluster,
		  catchment, or region scale.</p>
		<ol>
		 <li>In the Dashboard, click "Create Facility."</li> 
		 <li>Enter facility information such as name and address.</li>
		 <li>Leave the type as "Building" for now.</li> 
     <li>Leave Elevation as is for now. In Phase II, elevation data will 
      be used in the calculation of pumping costs for water transport.</li>
     <li>Click “Create.”</li>
    </ol>
    <p>The facility should now be listed on the Dashboard and in the Facilities tab.</p>
    <h2>Creating a Project</h2>
    <p>After you have defined at least one facility, you can start to define projects.</p>
		<ol>
		 <li>In the Dashboard, click "Create Project."</li>
		 <li>The General Data Entry pane appears.</li>
     <li>Give the project a name.</li>
     <li>Choose the project type. This will always be "Recycled Water" in Phase I.</li>
     <li>Choose the facility where the project is to be pursued. The facility must be one 
         of the facilities you have defined.</li>
     <li>Enter additional, optional information, such as the start date and end date. The
         fields called "Budget" and "Priority" are used only for annotation of the 
         project and do not affect the calculation.</li>
     <li>Click "Recycled Water Data Entry".<br/>
         The pane will roll up, displaying more fields like Current Monthly Water Use.</li>
     <li>Fill out the recycled water data inputs. These values will be used in the ROI 
         calculation. Default values have been supplied.<br/>
         The fields called "Cycles" specify how many times a given amount of water
         is recirculated through a device such as a cooling tower, and this number
         must always be at least 2.  The model requires a water usage of at least
         1,000 gallons/month.</li>
     <li>Click "Capital Costs Data Entry".
      <br/>The pane will roll up, displaying more fields.</li>
     <li>Fill out the capital costs inputs. A project about conversion to Recycled Water
        will typically involve some pipe-laying, and some improvements to valve and
        monitoring infrastructure.  These values can be left as defaults until you have
        more specific values.</li>
     <li>Click “Create.”</li>
     </ol>
     <p>The ROI is calculated and the Project list page is displayed, with additional
        fields filled on on the right.</p>
     <h2>Viewing ROI Results</h2>
     <p>Whenever you click Create or Update in the Projects window, the ROI will be calculated. 
     You can view the results of the ROI calculation in three places:</p>
     <ul>
      <li>Click the Projects tab. The ROI for each project is shown in the rightmost column.</li>
      <li>Click the "Summarize" button for a Project shown on the Projects tab. The Summary 
        will display, which collects data values from the panes of the Project editing screen,
        along with intermediate and final calculation values. The ROI is at the bottom right.</li>
      <li>Click the Dashboard tab and look in the Projects section. The ROI for each project 
         is shown in the summary details below the project name.</li>
     </ul>
		 <h2>Using the Map</h2>
		 <p>The EcoCloud Tools include a map that you can use to compare your facility position 
		 to the path of recycled water sources (“purple pipe”); you can also find 
		 facilities nearby that are already using recycled water. The map contains Google Map 
		 terrain and geographic features, and
			supports overlays which show additional details and place markers.</p>
		<p>You can turn on overlays for recycled water pipelines, current
			customers of the networks, and locations of your facilities.</p>
		<ol>
		   <li>Click the Map tab.</li>
		   <li>To add or remove features from the map, use the check boxes on the right to select which 
		   overlay(s) to display.</li>
		</ol>
		<h2>Using Partners and Vendors</h2>
		<p>The EcoCloud Tools include lists of relevant partners and vendors. Partners are 
		 typically agencies that assist or regulate projects.  Vendors are typically 
		 organizations that supply needed materials or resources.</p>
		<ol>
		  <li>In the Dashboard, click Manage Partners or Manage Vendors.</li>
		  <li>To find a particular partner or vendor, enter search terms in Name, Featured, or Tags, then click Search.</li>
		  <li>To edit partner or vendor information, click the name, then click Edit. For example, you might want 
		    to add more tags or correct an address. The tags can be used for searching, and are 
		    also used to extract a subset of the entries for topic-specific Partner and Vendor 
		    tabs (such as those in the Recycled Water section of the EcoCloud site).</li>
		  <li>Only certain users have the required level of access to edit partners and 
		    vendors.</li>
      <li>Administrative users can change the 'Featured' status for partners and 
        vendors.</li>
    </ol>
		<h2>Using Balances</h2>
		<p>The Balance screen is not fully functional until Phase II. At this
			time, it shows an example.</p>
		<p>A "Balance" is a depiction of the individual and netted flows of
			water or energy between different facilities, such as from a water
			reservoir to a building and then to a	recycling center.</p>
		<h2>Using Preferences</h2>
		<p>In the Preferences screen, you can define the scope and timeframe for 
		  scenarios and their water balances, as well as financial information to 
		  calculate Net Present Value (NPV) of projects and investments. These 
		  parameters are shown for demonstration purposes only, and will not take 
		  effect until Phase II of EcoCloud.</p>
	</body>
</html>
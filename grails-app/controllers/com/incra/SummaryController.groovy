package com.incra

import com.incra.domain.ChartType

/**
 * The <i>DashboardController</i> class implements operations such as creating a 
 * display of Accounts and their parents.
 *
 * @author Jeffrey Risberg
 * @since 09/20/10
 */
class SummaryController {

  def pageFrameworkService

  static loggingInstructions = [
    index: [key: "Dashboard Display", severity: LogEntrySeverity.MEDIUM],
  ]

  /**
   * The index method is the primary method for this screen.  It assembles the content out of 
   * DashboardPanePojo's, and then renders index.gsp.
   */
  def index = {
    pageFrameworkService.setControllerName(session, "dashboard")

    User currentUser = pageFrameworkService.getCurrentUser(session);
    List<DashboardPanePojo> panes = new ArrayList<DashboardPanePojo>();
    DashboardPanePojo pane;
    List<Object> paneContents

    // Start populating the accounts portion
    AccountType atCustomer = AccountType.findByName("Customer")
    pane = new DashboardPanePojo("Facility", "Facilities", "Facilities", "IconFacility", "account", "/dashboardAccount", EntityType.findByName("Account"));
    pane.showCreateLink = true

    def criteria = Account.createCriteria()
    def query = {
      and {
        if (currentUser != null) {
          or {
            isNull('user')
            eq('user', currentUser)
          }
        }
        if (currentUser == null) {
          isNull('user')
        }
        ne("type", atCustomer)
      }
    }

    paneContents = criteria.list(params, query)
    pane.addRecords(paneContents)
    panes.add(pane)

    // Start populating the projects portion
    pane = new DashboardPanePojo("Project", "Projects", "Projects", "IconProject", "project", "/dashboardProject", EntityType.findByName("Project"));
    pane.showCreateLink = true

    criteria = Project.createCriteria()
    query = {
      and {
        if (currentUser != null) {
          or {
            isNull('user')
            eq('user', currentUser)
          }
        }
        if (currentUser == null) {
          isNull('user')
        }
      }
    }

    paneContents = criteria.list(params, query)
    pane.addRecords(paneContents)
    panes.add(pane)

    // Start populating the featured vendors pane
    pane = new DashboardPanePojo("Vendor", "Vendors", "Featured Vendors", "IconVendor", "vendor", "/dashboardVendor", EntityType.findByName("Vendor"));

    criteria = Vendor.createCriteria()
    query = { eq('featured', true) }

    paneContents = criteria.list(params, query)
    pane.addRecords(paneContents)
    panes.add(pane)

    // Start populating the featured partners pane
    pane = new DashboardPanePojo("Partner", "Partners", "Featured Partners", "IconPartner", "partner", "/dashboardPartner", EntityType.findByName("Partner"));

    criteria = Partner.createCriteria()
    query = { eq('featured', true) }

    paneContents = criteria.list(params, query)
    pane.addRecords(paneContents)
    panes.add(pane)

    [ panes: panes, userIsLoggedIn: currentUser != null ]
  }

  /**
   * Begin a session of updating the configuration for a user's panel.
   */
  def configure = {
    UserDashboardPanel userDashboardPanelInstance =
        UserDashboardPanel.findByUserAndPanelIndex(loginUser, params.id)

    if (userDashboardPanelInstance) {
      List<Metric> metrics = Metric.findAll()
      List<ChartType> chartTypes = ChartType.selectAll()

      [user: loginUser, isAdmin: isAdmin, isCompanyAdmin: isCompanyAdmin,
            metrics: metrics, chartTypes: chartTypes,
            rangeTypes: activitySummaryService.CONST_RangeTypes,
            userDashboardPanelInstance: userDashboardPanelInstance ]
    }
    else {
      redirect action: index, params: params
    }
  }

  /**
   * Complete a session of updating the configuration for a user's panel.
   */
  def configureUpdate = {
    UserDashboardPanel userDashboardPanelInstance = UserDashboardPanel.get( params.id )

    if (userDashboardPanelInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (userDashboardPanelInstance.version > version) {

          userDashboardPanelInstance.errors.rejectValue("version", "userDashboardPanelInstance.optimistic.locking.failure", "Another user has updated this ActivityCategory while you were editing.")
          List<Metric> metrics = Metric.findAll()
          List<ChartType> chartTypes = ChartType.selectAll()

          render(view:'configure', model:[user: loginUser, isAdmin: isAdmin, isCompanyAdmin: isCompanyAdmin,
                metrics: metrics, chartTypes: chartTypes,
                rangeTypes: activitySummaryService.CONST_RangeTypes,
                userDashboardPanelInstance: userDashboardPanelInstance ])
          return
        }
      }

      def metricId = params.remove('metric')

      if (metricId) {
        userDashboardPanelInstance.viveMetric = ViveMetric.get(metricId as long)
      } else {
        userDashboardPanelInstance.viveMetric = null
      }
      userDashboardPanelInstance.properties = params

      if (!userDashboardPanelInstance.hasErrors() && userDashboardPanelInstance.save()) {
        analyticsService.calculateAll(loginUser)

        redirect(action:index)
      }
      else {
        List<Metric> metrics = Metric.findAll()
        List<ChartType> chartTypes = ChartType.selectAll()

        render(view:'configure', model:[user: loginUser, isAdmin: isAdmin, isCompanyAdmin: isCompanyAdmin,
              metrics: metrics, chartTypes: chartTypes,
              rangeTypes: activitySummaryService.CONST_RangeTypes,
              userDashboardPanelInstance: userDashboardPanelInstance ])
      }
    }
    else {
      redirect(action:index)
    }
  }
}

package com.incra

import groovy.sql.Sql

import com.incra.domain.ChartType

/**
 * The <i>DashboardController</i> class collects information for the dashboard display.
 *
 * @author Jeffrey Risberg
 * @since 09/20/10
 */
class DashboardController {

  def pageFrameworkService
  def dataSource

  /**
   * Show the dashboard panes.  If an optional id parameter is passed, it specifies 0 for tableView
   * and 1 for chartView.  The default is chartView.
   */
  def index = {
    pageFrameworkService.showScenarioSelector(session)
    pageFrameworkService.showTimeSegmentSelector(session)
    pageFrameworkService.showMetricsSelector(session)
    pageFrameworkService.setControllerName(session, "dashboard")

    Sql db = new Sql(dataSource)

    boolean chartView = true
    int itemCount = 5

    if (params.id != null) {
      chartView = (params.id == "1")
    }
    if (!chartView) itemCount = 9

    List<DashboardPanel> panels = new ArrayList<DashboardPanel>()

    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Facilities", chart: chartView)
      panel.addLeftRow("Facilities Record Count", "82", null)
      panel.addLeftRow("Facilities Record Sources", "4", null)

      panel.addRightRow("MV Bldg-2",  "88", 0)
      panel.addRightRow("MV Bldg-3",  "56", 0)
      panel.addRightRow("SD Bldg-10", "42", 0)
      panel.addRightRow("OR Bldg-11", "38", 0)

      panels.add(panel)
    }

    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Emissions", color: '#FF0000', chart: chartView)
      panel.addLeftRow("Emission Record Count", "78", null)
      panel.addLeftRow("Emission Record Sources", "12", null)

      panel.addRightRow("key1", "20", 0)
      panel.addRightRow("key2", "40", 0)
      panel.addRightRow("key3", "60", 0)
      panel.addRightRow("key4", "80", 0)

      panels.add(panel)
    }

    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Water", chart: chartView)
      panel.addLeftRow("Water Record Count", "111", null)
      panel.addLeftRow("Water Record Sources", "32", null)

      panel.addRightRow("key1", "66", 0)
      panel.addRightRow("key2", "44", 0)
      panel.addRightRow("key3", "55", 0)
      panel.addRightRow("key4", "52", 0)

      panels.add(panel)
    }

    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Energy", color: '#00FF66', chart: chartView)
      panel.addLeftRow("Energy Record Count", "251", null)
      panel.addLeftRow("Energy Record Sources", "19", null)

      panel.addRightRow("key1", "56", 0)
      panel.addRightRow("key2", "78", 0)
      panel.addRightRow("key3", "122", 0)
      panel.addRightRow("key4", "167", 0)

      panels.add(panel)
    }

    [ panels: panels ]
  }

  def changeTimeSegment = {
    TimeSegment timeSegment = TimeSegment.get(params.timeSegmentId)

    session.timeSegment = timeSegment
    redirect(action: "index")
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

package com.incra.dto

/**
 * The <i>DashboardPanelDTO</i> holds the contents of one panel that is 
 * shown on the dashboard, where each panel has a title string, a controller, and 
 * information to be shown in the left and right sides.
 * 
 * @author Jeff Risberg
 * @since 11/19/10
 */
class DashboardPanelDTO {
  String leftTitle
  String rightTitle
  String controller
  boolean chart
  String chartType
  String color
  String rangeType
  List<DashboardPanelRowDTO> leftRows
  List<DashboardPanelRowDTO> rightRows

  /** Constructor */
  DashboardPanelDTO() {
    this.chart = false
    this.chartType = 'bar'
    this.color = '#6BC97D'
    this.leftRows = new ArrayList<DashboardPanelRowDTO>()
    this.rightRows = new ArrayList<DashboardPanelRowDTO>()
  }

  boolean isTable() {
    table
  }

  void addLeftRow(String key, String value, Long id) {
    leftRows.add(new DashboardPanelRowDTO(key: key, value: value, id: id))
  }

  void addRightRow(String key, String value, Long id) {
    rightRows.add(new DashboardPanelRowDTO(key: key, value: value, id: id))
  }
}

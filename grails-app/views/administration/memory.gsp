<%-- based on http://www.freshblurbs.com/explaining-java-lang-outofmemoryerror-permgen-space --%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JVM Memory Monitor</title>
    <meta name="layout" content="main" />
  </head>
<body>
<style type="text/css">
      td {
        text-align: right;
      }
    </style>
  <h3>Total Memory</h3>
  <table border="1" width="100%">
    <tr>
      <th>Usage</th>
      <th>Init</th>
      <th>Used</th>
      <th>Committed</th>
      <th>Max</th>
    </tr>
    <tr>
      <td style="text-align: left">Heap Memory Usage</td>
      <td><g:formatNumber number="${memoryBean.heapMemoryUsage.init / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.heapMemoryUsage.used / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.heapMemoryUsage.committed / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.heapMemoryUsage.max / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
    </tr>
    <tr>
      <td style="text-align: left">Non-heap Memory Usage</td>
      <td><g:formatNumber number="${memoryBean.nonHeapMemoryUsage.init / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.nonHeapMemoryUsage.used / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.nonHeapMemoryUsage.committed / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      <td><g:formatNumber number="${memoryBean.nonHeapMemoryUsage.max / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
    </tr>
  </table>

  <h3>Memory Pools</h3>
  <table border="1" width="100%">
    <tr>
      <th>Name</th>
      <th>Usage</th>
      <th>Init</th>
      <th>Used</th>
      <th>Committed</th>
      <th>Max</th>
    </tr>
    <g:each in="${poolBeans}" var="bean">
      <tr>
        <td style="text-align: left">${bean.name}</td>
        <td style="text-align: left">Memory Usage</td>
        <td><g:formatNumber number="${bean.usage.init / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.usage.used / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.usage.committed / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.usage.max / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      </tr>
      <tr>
        <td></td>
        <td style="text-align: left">Peak Usage</td>
        <td><g:formatNumber number="${bean.peakUsage.init / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.peakUsage.used / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.peakUsage.committed / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
        <td><g:formatNumber number="${bean.peakUsage.max / (1024 * 1024)}" format="#,###,##0"/> Mb</td>
      </tr>
    </g:each>
  </table>

</body>
</html>

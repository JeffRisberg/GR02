<html>
	<head>
		<title>Dashboard Show Memory</title>
		<meta name="layout" content="skinfour_admin" />
		<style type="text/css">
				td {
				text-align: right;
				}
    </style>
	</head>
	<body>
		<div class="panel">
			<div style="margin-top: 6px; margin-bottom: 3px">
				<span style="font-weight: bold; font-size: 13px; color: gray;">Total Memory</span>
			</div>
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
					<td>
						<g:formatNumber
							number="${memoryBean.heapMemoryUsage.init / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.heapMemoryUsage.used / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.heapMemoryUsage.committed / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.heapMemoryUsage.max / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
				</tr>
				<tr>
					<td style="text-align: left">Non-heap Memory Usage</td>
					<td>
						<g:formatNumber
							number="${memoryBean.nonHeapMemoryUsage.init / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.nonHeapMemoryUsage.used / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.nonHeapMemoryUsage.committed / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
					<td>
						<g:formatNumber
							number="${memoryBean.nonHeapMemoryUsage.max / (1024 * 1024)}"
							format="###,##0" />
						Mb
					</td>
				</tr>
			</table>

			<div style="margin-top: 6px; margin-bottom: 3px">
				<span style="font-weight: bold; font-size: 13px; color: gray;">Memory Pools</span>
			</div>
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
						<td>
							<g:formatNumber number="${bean.usage.init / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.usage.used / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.usage.committed / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.usage.max / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left">Peak Usage</td>
						<td>
							<g:formatNumber number="${bean.peakUsage.init / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.peakUsage.used / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.peakUsage.committed / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
						<td>
							<g:formatNumber number="${bean.peakUsage.max / (1024 * 1024)}"
								format="###,##0" />
							Mb
						</td>
					</tr>
				</g:each>
			</table>
		</div>
		
		<div class="jqmWindow" id="ex2"></div>

    <script type="text/javascript">
      $().ready(function() {
        $('#ex2').jqm({ajax:'@href', trigger: 'a.ex2trigger'});
      });
    </script>
	</body>
</html>
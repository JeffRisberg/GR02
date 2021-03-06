//GR02
dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "developer"
	password = "123456"
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost:3306/gr02?autoreconnect=true"
			show_sql = true
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:file:testDb"
			driverClassName = "org.hsqldb.jdbcDriver"
			username = "sa"
			password = ""
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/gr02?autoreconnect=true"
		}
	}
}

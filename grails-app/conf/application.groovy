

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'task.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'task.UserRole'
grails.plugin.springsecurity.authority.className = 'task.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/userRole/**',	 access: ['isAuthenticated()']],
	[pattern: '/allUsersPost/**',access: ['permitAll']],
	[pattern: '/user/**', 		 access: ['permitAll']],
	[pattern: '/report/**',		 access: ['permitAll']],
	[pattern: '/test/**',		 access: ['permitAll']],
	[pattern: '/role/**', 		 access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.useBasicAuth = true

//jasper.dir.reports='../src/main/webapp/reports'
jasper.dir.reports='reports'
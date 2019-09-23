class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        "/user/"(resources: 'user', includes:['index', 'show', 'save'])
        "/role/"(resources: 'role', includes:['index', 'show', 'save'])
        "/allUsersPost/"(resources: 'allUsersPost', includes:['index'])

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view: '/notFound')
    }
}

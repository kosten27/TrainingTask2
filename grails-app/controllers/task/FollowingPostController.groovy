package task

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('isAuthenticated()')
class FollowingPostController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def subscriptionService

    FollowingPostController() {
        super(Post)
    }

    def index() {
        respond subscriptionService.getFollowingPostsForCurrentUser()
    }
}

package task

import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class FollowingPostController {
    static responseFormats = ['json', 'xml']

    def subscriptionService

    def index() {
        respond subscriptionService.getFollowingPostsForCurrentUser()
    }
}

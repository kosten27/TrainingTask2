package task

import TrainingTask2.SubscriptionService
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('isAuthenticated()')
class FollowingPostController extends RestfulController {
    static responseFormats = ['json', 'xml']

    SubscriptionService subscriptionService

    FollowingPostController() {
        super(Post)
    }

    def index() {
        respond subscriptionService.getFollowingPostsForCurrentUser()
    }
}

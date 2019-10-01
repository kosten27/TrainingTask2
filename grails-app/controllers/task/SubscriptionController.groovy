package task


import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured('isAuthenticated()')
class SubscriptionController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def subscriptionService

    SubscriptionController() {
        super(Subscription)
    }

    def index() {
        respond subscriptionService.getFollowingUsersForCurrentUser()
    }

    def create() {
        Long userId = request.JSON.userId as Long
        if (!subscriptionService.subscribeCurrentUserToUser(userId)) {
            response.status = 500;
        } else {
            response.status = 200;
        }
    }
}

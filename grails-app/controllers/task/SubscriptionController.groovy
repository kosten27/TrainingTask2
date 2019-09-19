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
        def subscribe = subscriptionService.subscribe(userId)
        if (subscribe == false) {
            response.status = 500;
        }
    }
}

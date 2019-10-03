package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import task.User

@Integration
@Rollback
class SubscriptionServiceIntegrationSpec extends Specification {

    @Autowired
    SubscriptionService subscriptionService

    void setup() {
        subscriptionService.springSecurityService = Stub(SpringSecurityService)
    }

    void 'subscribe to user returned correct result'() {
        given:
        User currentUser = new User(username: 'currentUser', password: 'password').save()
        User userForSubscription = new User(username: 'userForSubscription', password: 'password').save()
        subscriptionService.springSecurityService.getCurrentUser() >> currentUser

        when:
        def result = subscriptionService.subscribeCurrentUserToUser(userForSubscription.id)
        then:
        result

        when:
        def secondResult = subscriptionService.subscribeCurrentUserToUser(userForSubscription.id)
        then:
        !secondResult
    }

    void 'subscribe to current should return false'() {
        given:
        User currentUser = new User(username: 'currentUser', password: 'password').save()
        subscriptionService.springSecurityService.getCurrentUser() >> currentUser

        when:
        def result = subscriptionService.subscribeCurrentUserToUser(currentUser.id)

        then:
        !result
    }
}

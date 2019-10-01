package TrainingTask2

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SubscriptionService)
class SubscriptionServiceSpec extends Specification {

    void 'subscribeCurrentUserToUser must be successful'() {
        given:
        Long userId = 2


        when:
        def subscriptionResult = service.subscribeCurrentUserToUser(userId)

        then:
        subscriptionResult

    }
}

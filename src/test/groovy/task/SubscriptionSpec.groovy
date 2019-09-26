package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    void 'Subscription must be created'() {
        when:
        def user = new User()
        def followingUser = new User()
        def subscription = new Subscription(user: user, followingUser: followingUser)

        then:
        subscription.validate()
    }

    void 'User can not be blank'() {
        when:
        def followingUser = new User()
        def subscription = new Subscription(followingUser: followingUser)

        then:
        !subscription.validate()
    }

    void 'FollowingUser can not be blank'() {
        when:
        def user = new User()
        def subscription = new Subscription(user: user)

        then:
        !subscription.validate()
    }
}

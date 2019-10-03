package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    void 'Subscription must be created'() {
        when:
        def user = new User(username: 'user')
        def followingUser = new User(username: 'followingUser')
        def subscription = new Subscription(user: user, followingUser: followingUser)

        then:
        subscription.validate()
    }

    void 'User cannot be blank'() {
        when:
        def followingUser = new User(username: 'followingUser')
        def subscription = new Subscription(followingUser: followingUser)

        then:
        !subscription.validate()
    }

    void 'FollowingUser cannot be blank'() {
        when:
        def user = new User(username: 'user')
        def subscription = new Subscription(user: user)

        then:
        !subscription.validate()
    }
}

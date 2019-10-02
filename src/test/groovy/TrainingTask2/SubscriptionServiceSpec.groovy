package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import task.Subscription
import task.Post
import task.User

@TestFor(SubscriptionService)
@Mock([Subscription])
class SubscriptionServiceSpec extends Specification {

    void setup() {
        service.springSecurityService = Stub(SpringSecurityService)
        service.userService = Stub(UserService)
        service.postService = Stub(PostService)
    }

    void 'subscribe current user to user'() {
        given:
        User currentUser = new User(username: 'currentUser')
        Long userId = 2
        User user = new User(username: 'user')
        service.userService.findById(userId) >> user
        service.springSecurityService.getCurrentUser() >> currentUser

        when:
        def subscriptionResult = service.subscribeCurrentUserToUser(userId)
        then:
        subscriptionResult

        when:
        def reSubscriptionResult = service.subscribeCurrentUserToUser(userId)
        then:
        !reSubscriptionResult
    }

    void 'subscribe to current user must not work'() {
        given:
        Long userId = 1
        User currentUser = new User(username: 'currentUser')
        service.userService.findById(userId) >> currentUser
        service.springSecurityService.getCurrentUser() >> currentUser

        when:
        def subscriptionResult = service.subscribeCurrentUserToUser(userId)

        then:
        !subscriptionResult
    }

    void 'getFollowingUsersForCurrentUser returned correct result'() {
        given:
        User currentUser = new User(username: 'currentUser')
        service.springSecurityService.getCurrentUser() >> currentUser
        Long userId = 2
        User user = new User(username: 'user')
        service.userService.findById(userId) >> user

        when:
        service.subscribeCurrentUserToUser(userId)
        def followingUsers = service.getFollowingUsersForCurrentUser()

        then:
        followingUsers == [user]
    }

    void 'getFollowingPostsForCurrentUser returned correct result'() {
        given:
        User currentUser = new User(username: 'currentUser')
        service.springSecurityService.getCurrentUser() >> currentUser
        Long userId2 = 2
        User user = new User(username: 'user')
        service.userService.findById(userId2) >> user
        Long userId3 = 3
        service.userService.findById(userId3) >> null
        Post post1 = new Post(user: user, message: 'message1', date: new Date(2019, 1, 1))
        Post post2 = new Post(user: user, message: 'message2', date: new Date(2019, 1, 2))
        service.postService.findAllByUserInList([user]) >> [post1, post2]
        service.postService.findAllByUserInList([]) >> []

        when:
        def followingPostsNullUser = service.getFollowingPostsForCurrentUser()
        then:
        followingPostsNullUser == []

        when:
        service.subscribeCurrentUserToUser(userId2)
        def followingPosts = service.getFollowingPostsForCurrentUser()
        then:
        followingPosts == [post2, post1]
    }
}

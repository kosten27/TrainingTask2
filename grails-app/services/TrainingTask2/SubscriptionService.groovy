package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import task.Post
import task.Subscription
import task.User

@Transactional
class SubscriptionService {

    SpringSecurityService springSecurityService
    UserService userService
    PostService postService

    boolean subscribeCurrentUserToUser(Long userId) {
        boolean result = false
        User user = userService.findById(userId)
        if (!currentUserFollowingTo(user)) {
            result = addSubscribe(user) ? true : false
        }
        return result
    }

    boolean currentUserFollowingTo(User user) {
        User currentUser = springSecurityService.currentUser as User
        return Subscription.findByUserAndFollowingUser(currentUser, user) ? true : false
    }

    Subscription addSubscribe(User user) {
        User currentUser = springSecurityService.currentUser as User
        Subscription subscription = new Subscription(user: currentUser, followingUser: user)
        return saveSubscription(subscription)
    }

    Subscription saveSubscription(Subscription subscription) {
        return subscription.save()
    }

    List<User> getFollowingUsersForCurrentUser() {
        User currentUser = springSecurityService.currentUser as User
        return Subscription.findAllByUser(currentUser)*.followingUser
    }

    List<Post> getFollowingPostsForCurrentUser() {
        List followingUsers = getFollowingUsersForCurrentUser()
        if (followingUsers?.size() > 0) {
            return postService.findAllByUserInList(followingUsers)
                    .sort { post1, post2 -> post2.date <=> post1.date }
        } else {
            return []
        }
    }

}

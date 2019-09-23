package TrainingTask2

import grails.transaction.Transactional
import task.Post
import task.Subscription
import task.User

@Transactional
class SubscriptionService {

    def springSecurityService

    def subscribe(Long userId) {
        def result
        User user = User.findById(userId)
        if (currentUserFollowingTo(user)) {
            result = false
        } else {
            result = addSubscribe(user) ? true : false
        }
        result
    }

    def currentUserFollowingTo(User user) {
        def currentUser = springSecurityService.currentUser
        Subscription.findByUserAndFollowingUser(currentUser, user) ? true : false
    }

    def addSubscribe(User user) {
        def currentUser = springSecurityService.currentUser
        new Subscription(user: currentUser, followingUser: user).save()
    }

    def getFollowingUsersForCurrentUser() {
        def currentUser = springSecurityService.currentUser
        Subscription.findAllByUser(currentUser)*.followingUser
    }

    def getFollowingPostsForCurrentUser() {
        List followingUsers = getFollowingUsersForCurrentUser()
        if (followingUsers?.size() > 0) {
            Post.findAllByUserInList(followingUsers)
                    .sort { post1, post2 -> post2.id <=> post1.id }
        } else {
            []
        }
    }

}

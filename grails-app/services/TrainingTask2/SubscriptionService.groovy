package TrainingTask2

import grails.transaction.Transactional
import task.Post
import task.Subscription
import task.User

@Transactional
class SubscriptionService {

    def springSecurityService
    def userService
    def postService

    def subscribeCurrentUserToUser(Long userId) {
        def result = false
        User user = userService.findById(userId)
        if (!currentUserFollowingTo(user)) {
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
        def subscription = new Subscription(user: currentUser, followingUser: user)
        saveSubscription(subscription)
    }

    def saveSubscription(Subscription subscription) {
        subscription.save()
    }

    def getFollowingUsersForCurrentUser() {
        def currentUser = springSecurityService.currentUser
        Subscription.findAllByUser(currentUser)*.followingUser
    }

    def getFollowingPostsForCurrentUser() {
        List followingUsers = getFollowingUsersForCurrentUser()
        if (followingUsers?.size() > 0) {
            postService.findAllByUserInList(followingUsers)
                    .sort { post1, post2 -> post2.date <=> post1.date }
        } else {
            []
        }
    }

}

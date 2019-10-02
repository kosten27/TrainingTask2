package TrainingTask2

import grails.transaction.Transactional
import task.Post
import task.User

@Transactional
class PostService {

    def springSecurityService
    def userService

    def getRecentPosts() {
        def posts = findAll()
        def recentPosts = posts.groupBy({ post -> post.getUser() })
                .collect { it -> it.value.max { post1, post2 -> post1.getDate() <=> post2.getDate() } }
                .sort { post1, post2 -> post2.getDate() <=> post1.getDate() }
        recentPosts
    }

    def getUsersPostsById(Long userId) {
        User user = userService.findById(userId)
        findAllByUser(user)
    }

    def findAllByUser(User user) {
        Post.findAllByUser(user)
    }

    def findAll() {
        Post.findAll()
    }

    def saveCurrentUserPost(Post post) {
        post.setUser(springSecurityService.currentUser)
        post.setDate(new Date())
        post.save()
        post
    }

    def getCurrentUserPosts() {
        findAllByUser(springSecurityService.currentUser)
    }

    def findByIdAndCurrentUser(Long postId) {
        Post.findByIdAndUser(postId, springSecurityService.currentUser) ?: new ArrayList()
    }

    def findAllByUserInList(List users) {
        Post.findAllByUserInList(users)
    }
}

package TrainingTask2

import grails.transaction.Transactional
import task.Post
import task.User

@Transactional
class PostService {

    def springSecurityService

    def getRecentPosts() {
        def posts = Post.findAll()
        def recentPosts = posts.groupBy({ post -> post.user })
                .collect { it -> it.value.max { post1, post2 -> post1.id <=> post2.id } }
                .sort { post1, post2 -> post2.id <=> post1.id }
        recentPosts
    }

    def getUsersPostsById(Long userId) {
        User user = User.findById(userId)
        getUsersPosts(user)
    }

    def getUsersPosts(User user) {
        Post.findAllByUser(user)
    }

    def getAllPosts() {
        Post.findAll()
    }

    def saveCurrentUserPost(Post post) {
        post.user = springSecurityService.currentUser
        post.save()
        post
    }

    def getCurrentUserPosts() {
        getUsersPosts(springSecurityService.currentUser)
    }

    def getCurrentUserPostById(Long postId) {
        Post.findByIdAndUser(postId, springSecurityService.currentUser) ?: new ArrayList()
    }
}

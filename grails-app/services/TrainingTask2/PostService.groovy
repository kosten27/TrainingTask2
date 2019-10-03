package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import task.Post
import task.User

@Transactional
class PostService {

    SpringSecurityService springSecurityService
    UserService userService

    List<Post> getRecentPosts() {
        List<Post> posts = findAll()
        List<Post> recentPosts = posts.groupBy({ post -> post.getUser() })
                .collect { it -> it.value.max { post1, post2 -> post1.getDate() <=> post2.getDate() } }
                .sort { post1, post2 -> post2.getDate() <=> post1.getDate() }
        return recentPosts
    }

    List<Post> getUsersPostsById(Long userId) {
        User user = userService.findById(userId)
        return findAllByUser(user)
    }

    List<Post> findAllByUser(User user) {
        return Post.findAllByUser(user)
    }

    List<Post> findAll() {
        return Post.findAll()
    }

    Post saveCurrentUserPost(Post post) {
        post.setUser(springSecurityService.currentUser as User)
        post.setDate(new Date())
        post.save()
        return post
    }

    List<Post> getCurrentUserPosts() {
        return findAllByUser(springSecurityService.currentUser as User)
    }

    Post findByIdAndCurrentUser(Long postId) {
        return Post.findByIdAndUser(postId, springSecurityService.currentUser as User) ?: null
    }

    List<Post> findAllByUserInList(List users) {
        Post.findAllByUserInList(users)
    }
}

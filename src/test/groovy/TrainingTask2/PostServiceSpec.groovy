package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import spock.lang.Specification
import task.Post
import task.User

@TestFor(PostService)
class PostServiceSpec extends Specification {

    void setup() {
        service.springSecurityService = Stub(SpringSecurityService)
        service.userService = Stub(UserService)
    }

    void 'getRecentPosts returned correct result'() {
        given:
        User user1 = new User(username: 'user1')
        User user2 = new User(username: 'user2')
        Post post1 = new Post(user: user1, message: "message1", date: new Date(2019, 1, 1))
        Post post2 = new Post(user: user1, message: "message2", date: new Date(2019, 1, 3))
        Post post3 = new Post(user: user2, message: "message3", date: new Date(2019, 1, 2))
        GroovyStub(Post, global: true)
        Post.findAll() >> [post1, post2, post3]

        when:
        def recentPost = service.getRecentPosts()

        then:
        recentPost.size() == 2
        recentPost[0] == post2
        recentPost[1] == post3
    }

    void 'getUsersPostsById returned correct result'() {
        given:
        def userId = 1
        User user = new User(username: 'user')
        service.userService.findById(userId) >> user
        Post post = Stub(Post)
        GroovyStub(Post, global: true)
        Post.findAllByUser(user) >> [post]

        when:
        def posts = service.getUsersPostsById(userId)

        then:
        posts == [post]
    }

    void 'saveCurrentUserPost fills user and date before saving'() {
        given:
        User user = new User(username: 'user')
        def post = Mock(Post)
        service.springSecurityService.getCurrentUser() >> user

        when:
        service.saveCurrentUserPost(post)

        then:
        1 * post.setUser(user)
        1 * post.setDate(_ as Date)
        1 * post.save()
    }

    void 'getCurrentUserPosts returned correct result'() {
        given:
        User user = new User(username: 'user')
        service.springSecurityService.getCurrentUser() >> user
        Post post = Stub(Post)
        GroovyStub(Post, global: true)
        Post.findAllByUser(user) >> [post]

        when:
        def userPosts = service.getCurrentUserPosts()

        then:
        userPosts == [post]
    }

    void 'findByIdAndCurrentUser returned correct result'() {
        given:
        User user = new User(username: 'user')
        service.springSecurityService.getCurrentUser() >> user
        Long postId = 1
        Post post = Mock(Post)
        GroovyStub(Post, global: true)
        Post.findByIdAndUser(postId, user) >> post

        when:
        def userPost = service.findByIdAndCurrentUser(postId)

        then:
        userPost == post
    }

//    void 'findAllByUserInList returned correct result'() {
//        given:
//        User user = new User(username: 'username')
//        List<User> users = [user]
//
//
//        when:
//        service.findAllByUserInList()
//
//        then:
//
//
//
//    }
}

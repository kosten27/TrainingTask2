package TrainingTask2

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import task.Post
import task.User

@TestFor(PostService)
@Mock([Post])
class PostServiceSpec extends Specification {

    void setup() {
        service.springSecurityService = Stub(SpringSecurityService)
        service.userService = Stub(UserService)
    }

    void 'getRecentPosts returned correct result'() {
        given:
        User user1 = new User(username: 'user1')
        User user2 = new User(username: 'user2')
        Post post1 = new Post(user: user1, message: "message1", date: new Date(2019, 1, 1)).save()
        Post post2 = new Post(user: user1, message: "message2", date: new Date(2019, 1, 3)).save()
        Post post3 = new Post(user: user2, message: "message3", date: new Date(2019, 1, 2)).save()

        when:
        def recentPost = service.getRecentPosts()

        then:
        recentPost == [post2, post3]
    }

    void 'getUsersPostsById returned correct result'() {
        given:
        def userId = 1
        User user = new User(username: 'user')
        service.userService.findById(userId) >> user
        Post post = new Post(user: user, message: 'message', date: new Date()).save()

        when:
        def posts = service.getUsersPostsById(userId)

        then:
        posts == [post]
    }

    void 'saveCurrentUserPost fills user and date before saving'() {
        given:
        User user = new User(username: 'user')
        Post post = new Post(message: 'message')
        service.springSecurityService.getCurrentUser() >> user

        when:
        service.saveCurrentUserPost(post)

        then:
        post.user == user
        post.date != null
    }

    void 'getCurrentUserPosts returned correct result'() {
        given:
        User user = new User(username: 'user')
        service.springSecurityService.getCurrentUser() >> user
        Post post = new Post(user: user, message: 'message', date: new Date()).save()

        when:
        def userPosts = service.getCurrentUserPosts()

        then:
        userPosts == [post]
    }

    void 'findByIdAndCurrentUser returned correct result'() {
        given:
        User user = new User(username: 'user')
        service.springSecurityService.getCurrentUser() >> user
        Post post = new Post(user: user, message: 'message', date: new Date()).save()

        when:
        def userPost = service.findByIdAndCurrentUser(post.id)

        then:
        userPost == post
    }

    void 'findAllByUserInList returned correct result'() {
        given:
        User user = new User(username: 'username')
        List<User> users = [user]
        Post post = new Post(user: user, message: 'message', date: new Date())

        when:
        def emptyUsersPosts = service.findAllByUserInList(users)
        then:
        emptyUsersPosts == []

        when:
        post.save()
        def usersPosts = service.findAllByUserInList(users)
        then:
        usersPosts == [post]
    }
}

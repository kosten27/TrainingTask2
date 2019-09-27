package TrainingTask2

import grails.test.mixin.TestFor
import spock.lang.Specification
import task.Post
import task.User

@TestFor(PostService)
class PostServiceSpec extends Specification {

    def setup() {
//        GroovyStub(Post, global: true)
    }

    def cleanup() {
    }

    void 'getRecentPosts returned recent posts'() {
        given:
        User user = new User(username: 'user', password: 'password')
        Post post = new Post(user: user, message: 'message', date: new Date())
//        Post.findAll() >> [post]

        when:
        def recentPost = service.getRecentPosts()

        then:
        recentPost.size() == 1

//        expect: "fix me"
//        true == false
    }
}

package TrainingTask2

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import task.Post
import task.User

@Integration
@Rollback
class PostServiceIntegrationSpec extends Specification {

    @Autowired
    PostService postService

    def setup() {
    }

    void 'getRecentPosts returned correct result'() {
        given:
        User user1 = new User(username: 'user1', password: 'password').save()
        User user2 = new User(username: 'user2', password: 'password').save()
        Post post1 = new Post(user: user1, message: "message1", date: new Date(2019, 1, 1)).save()
        Post post2 = new Post(user: user1, message: "message2", date: new Date(2019, 1, 3)).save()
        Post post3 = new Post(user: user2, message: "message3", date: new Date(2019, 1, 2)).save()

        when:
        def recentPosts = postService.getRecentPosts()

        then:
        recentPosts == [post2, post3]
    }
}

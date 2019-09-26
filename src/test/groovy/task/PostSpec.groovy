package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Post)
class PostSpec extends Specification {

    void 'Post must be created'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def post = new Post(user: user, message: 'message', date: new Date())

        then:
        post.validate()
    }

    void 'User can not be blank'() {
        when:
        def post = new Post(message: 'message', date: new Date())

        then:
        !post.validate()
    }

    void 'Message can not be blank'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def postWithNullMessage = new Post(user: user, date: new Date())
        def postWithBlankMessage = new Post(user: user, message: '', date: new Date())

        then:
        !postWithNullMessage.validate()
        !postWithBlankMessage.validate()
    }

    void 'Date can not be null'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def postWithNullDate = new Post(user: user, message: 'message')

        then:
        !postWithNullDate.validate()
    }

    void 'User and message can not be blank'() {
        when:
        def postWithNullUserMessageAndDate = new Post()
        User user
        def postWithBlankUserAndMessage = new Post(user: user, message: '', date: new Date())

        then:
        !postWithNullUserMessageAndDate.validate()
        !postWithBlankUserAndMessage.validate()
    }
}

package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(User)
class UserSpec extends Specification {

    void 'Username can not be blank'() {
        when:
        def userWithNullUsername = new User(password: 'password')
        def userWithEmptyUsername = new User(username: '', password: 'password')

        then:
        !userWithNullUsername.validate()
        !userWithEmptyUsername.validate()
    }

    void 'Password can not be blank'() {
        when:
        def userWithNullPassword = new User(username: 'user')
        def userWithEmptyPassword = new User(username: 'user', password: '')

        then:
        !userWithNullPassword.validate()
        !userWithEmptyPassword.validate()
    }

    void 'Username and password can not be blank'() {
        when:
        def userWithNullUsernameAndPassword = new User()
        def userWithEmptyUsernameAndPassword = new User(username: '', password: '')

        then:
        !userWithNullUsernameAndPassword.validate()
        !userWithEmptyUsernameAndPassword.validate()
    }

    void 'User must be created'() {
        when:
        def user = new User(username: 'user', password: 'password')

        then:
        user.validate()
    }
}

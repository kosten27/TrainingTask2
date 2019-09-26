package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserRole)
class UserRoleSpec extends Specification {

    void 'UserRole must be created'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def role = new Role(authority: 'ROLE_USER')
        def userRole = new UserRole(user: user, role: role)

        then:
        userRole.validate()
    }

    void 'User can not be blank'() {
        when:
        def role = new Role(authority: 'ROLE_USER')
        def userRole = new UserRole(role: role)

        then:
        !userRole.validate()
    }

    void 'Role can not be blank'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def userRole = new UserRole(user: user)

        then:
        !userRole.validate()
    }

    void 'User and role can not be blank'() {
        when:
        def userRole = new UserRole()

        then:
        !userRole.validate()
    }
}

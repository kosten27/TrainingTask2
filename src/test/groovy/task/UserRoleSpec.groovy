package task

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserRole)
@Mock([User, Role])
class UserRoleSpec extends Specification {

    void 'UserRole must be created'() {
        when:
        def user = new User(username: 'user', password: 'password').save()
        def role = new Role(authority: 'ROLE_USER').save()
        def userRole = new UserRole(user: user, role: role)

        then:
        userRole.validate()
    }

    void 'User cannot be blank'() {
        when:
        def role = new Role(authority: 'ROLE_USER')
        def userRole = new UserRole(role: role)

        then:
        !userRole.validate()
    }

    void 'Role cannot be blank'() {
        when:
        def user = new User(username: 'user', password: 'password')
        def userRole = new UserRole(user: user)

        then:
        !userRole.validate()
    }

    void 'User and role cannot be blank'() {
        when:
        def userRole = new UserRole()

        then:
        !userRole.validate()
    }
}

package task

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Role)
class RoleSpec extends Specification {

    void 'Role must be created'() {
        when:
        def role = new Role(authority: 'ROLE_USER')

        then:
        role.validate()
    }

    void 'Authority cannot be blank'() {
        when:
        def roleWithNullAuthority = new Role()
        def roleWithEmptyAuthority = new Role(authority: '')

        then:
        !roleWithNullAuthority.validate()
        !roleWithEmptyAuthority.validate()
    }
}

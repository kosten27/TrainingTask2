import task.Post
import task.Role
import task.User
import task.UserRole

class BootStrap {

    def init = {

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()
        new User(username: 'test', password: 'test').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        new Post(message: "Message1", user: testUser).save()
        new Post(message: "Message2", user: testUser).save()

        assert User.count() == 2
        assert Role.count() == 1
        assert UserRole.count() == 1
        assert Post.count() == 2

    }
    def destroy = {
    }
}

import task.Post
import task.Role
import task.User
import task.UserRole

import java.time.LocalDateTime

class BootStrap {

    def init = {
//
//        def adminRole = new Role(authority: 'ROLE_ADMIN')
//        adminRole.save()
//        def userRole = new Role(authority: 'ROLE_USER')
//        userRole.save()
//
//        def admin = new User(username: 'admin', password: 'admin')
//        admin.save()
//        def user = new User(username: 'user', password: 'user')
//        user.save()
//
//        def adminWithAdminRole = new UserRole(user: admin, role: adminRole)
//        adminWithAdminRole.save()
//        def adminWithUserRole = new UserRole(user: admin, role: userRole)
//        adminWithUserRole.save()
//        def userWithUserRole = new UserRole(user: user, role: userRole)
//        userWithUserRole.save()
//
////        UserRole.create testUser, adminRole
////
////        UserRole.withSession {
////            it.flush()
////            it.clear()
////        }
//
//        new Post(message: "Message1", user: admin, date: new Date()).save()
//        new Post(message: "Message2", user: admin, date: new Date()).save()
//        new Post(message: "Message3", user: user, date: new Date()).save()
//
//        assert User.count() == 2
//        assert Role.count() == 2
//        assert UserRole.count() == 3
//        assert Post.count() == 3
//
    }
    def destroy = {
    }
}

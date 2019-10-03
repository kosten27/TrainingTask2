package TrainingTask2

import grails.transaction.Transactional
import task.UserRole

@Transactional
class UserRoleService {

    UserRole saveUserRole(UserRole userRole) {
        if (userRole.validate()) {
            return userRole.save()
        } else {
            return null
        }
    }
}

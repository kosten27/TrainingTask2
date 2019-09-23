package TrainingTask2

import grails.transaction.Transactional
import task.UserRole

@Transactional
class UserRoleService {

    def saveUserRole(UserRole userRole) {
        userRole.save()
    }
}

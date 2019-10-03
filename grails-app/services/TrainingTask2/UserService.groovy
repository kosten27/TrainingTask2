package TrainingTask2

import grails.transaction.Transactional
import task.User

@Transactional
class UserService {

    User findById(long id) {
        return User.findById(id)
    }
}

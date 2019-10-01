package TrainingTask2

import grails.transaction.Transactional
import task.User

@Transactional
class UserService {

    def findById(long id) {
        User.findById(id)
    }
}

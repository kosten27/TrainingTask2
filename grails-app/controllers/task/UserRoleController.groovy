package task

import TrainingTask2.UserRoleService
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

@Transactional(readOnly = true)
class UserRoleController extends RestfulController {
    static responseFormats = ['json', 'xml']

    UserRoleService userRoleService

    UserRoleController() {
        super(UserRole)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserRole.list(params), model:[userRoleCount: UserRole.count()]
    }

    @Secured('ROLE_ADMIN')
    def create() {
        UserRole userRole = new UserRole(request.JSON as JSONObject)
        respond userRoleService.saveUserRole(userRole)
    }
}

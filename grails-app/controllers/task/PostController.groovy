package task

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import net.minidev.json.JSONObject

@Secured('isAuthenticated()')
class PostController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def springSecurityService

    PostController() {
        super(Post)
    }

    def index() {
        respond Post.findAllByUser(springSecurityService.currentUser)
    }

    def create() {
        JSONObject postJson = request.JSON as JSONObject
        Post post = new Post(postJson)
        post.user = springSecurityService.currentUser
        post.save()
        respond post
    }

    def show() {
        def post = Post.findByIdAndUser(params.id, springSecurityService.currentUser)
        if (post) {
            respond post
        } else {
            respond([], status: 204)
        }
    }
}
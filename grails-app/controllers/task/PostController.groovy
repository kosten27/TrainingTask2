package task

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.grails.web.json.JSONObject

@Secured('isAuthenticated()')
class PostController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def postService

    PostController() {
        super(Post)
    }

    def index() {
        respond postService.getCurrentUserPosts()
    }

    def create() {
        def jsonObject = request.JSON as JSONObject
        Post post = new Post(jsonObject)
        respond postService.saveCurrentUserPost(post)
    }

    def show() {
        respond postService.getCurrentUserPostById(params.id as Long)
    }
}
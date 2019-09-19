package task

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import net.minidev.json.JSONObject

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
        JSONObject postJson = request.JSON as JSONObject
        Post post = new Post(postJson)
        respond postService.saveCurrentUserPost(post)
    }

    def show() {
        respond postService.getCurrentUserPostById(params.id as Long)
    }
}
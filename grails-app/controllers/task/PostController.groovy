package task

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

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
        Post post = new Post(request.JSON)
        respond postService.saveCurrentUserPost(post)
    }

    def show() {
        respond postService.getCurrentUserPostById(params.id as Long)
    }
}
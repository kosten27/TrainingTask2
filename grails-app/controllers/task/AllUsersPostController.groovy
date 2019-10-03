package task

import TrainingTask2.PostService
import grails.rest.RestfulController

class AllUsersPostController extends RestfulController  {
    static responseFormats = ['json', 'xml']

    PostService postService

    AllUsersPostController() {
        super(Post)
    }

    def index() {
        if (params.recent == 'true') {
            respond postService.getRecentPosts()
        } else if (params.userId) {
            respond postService.getUsersPostsById(params.userId as Long)
        } else {
            respond postService.findAll()
        }
    }
}

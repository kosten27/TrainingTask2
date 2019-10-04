package task

import TrainingTask2.PostService
import grails.rest.RestfulController

class AllUsersPostController extends RestfulController  {
    static responseFormats = ['json', 'xml']

    PostService postService

    AllUsersPostController() {
        super(Post)
    }

    def index(AllUsersPostInfo allUsersPostInfo) {
        if (allUsersPostInfo.recent) {
            respond postService.getRecentPosts()
        } else if (allUsersPostInfo.userId) {
            respond postService.getUsersPostsById(allUsersPostInfo.userId)
        } else {
            respond postService.findAll()
        }
    }
}

class AllUsersPostInfo {
    Boolean recent
    Long userId
}

package task

class AllUsersPostController {
    static responseFormats = ['json', 'xml']

    def postService

    def index() {
        if (params.recent == 'true') {
            respond postService.getRecentPosts()
        } else if (params.userId) {
            respond postService.getUsersPostsById(params.userId as Long)
        } else {
            respond postService.getAllPosts()
        }
    }
}

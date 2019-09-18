package task

class AllUsersPostController {
    static responseFormats = ['json', 'xml']

    def index() {
        if (params.recent == 'true') {
            def posts = Post.findAll()
            def postsGroupByUser = posts.groupBy({post -> post.user})
            respond posts
        } else {
            respond Post.findAll()
        }
    }
}

package task

class AllUsersPostController {
    static responseFormats = ['json', 'xml']

    def index() {
        if (params.recent == 'true') {
            def posts = Post.findAll()
            def recentPosts = posts.groupBy({ post -> post.user })
                    .collect { it -> it.value.max{post1, post2 -> post1.id <=> post2.id }}
                    .sort {post1, post2 -> post2.id <=> post1.id}
            respond recentPosts
        } else {
            respond Post.findAll()
        }
    }
}

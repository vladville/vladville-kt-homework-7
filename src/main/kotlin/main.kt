package ru.netology;

data class Post(
    val id: Int,
    val authorId: Int,
    val authorName: String,
    val date: Int,
    val text: String,
    val comments: Int = 0,
    val likes: Int = 0,
    val views: Int,
    val canEdit: Boolean,
    val reposts: Int = 0,
    val attachments: Array<Attachment> = emptyArray(),
    val original: Post? = null
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var lastPostId = 1

    fun createComment(postId: Int, comment: Comment): Comment? {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }

        return null
    }

    fun add(post: Post): Post {
        posts += post.copy(id = lastPostId)
        lastPostId++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postEl) in posts.withIndex()) {
            if (post.id == postEl.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        lastPostId = 1
    }
}

fun main() {
    val postOne = Post(5, 1, "Vladimir", 0, "First post", 1, 5, 5, true, 5, emptyArray())
    val postTwo = postOne.copy(id = 10, 0, text = "Second post", likes = postOne.likes + 1)

    //show default data
    println(postOne)
    println(postTwo)

    //try to add
    println(WallService.add(postOne))
    val postForEdit = WallService.add(postTwo)

    //try add comment to existing post
    val comment = Comment(5, 1, 2, 0, "First Comment")
    println("Successful added " + WallService.createComment(1, comment))

    //try add comment to not existing post and get exception
    val postId = 5;
    //val commentPost = WallService.createComment(postId, comment) ?: throw PostNotFoundException("no post found $postId")
}
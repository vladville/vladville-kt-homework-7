package ru.netology;

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun TestAdd() {
        val service = WallService
        val result = service.add(Post(5, 1, "Vladimir", 0, "First post", 0, 5, 5, true, 5, emptyArray()))

        assertEquals(1, result.id)
    }

    @Test
    fun updateExistingTrue() {
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(5, 1, "Vladimir", 0, "First post", 0, 5, 5, true, 5, emptyArray()))
        service.add(Post(15, 1, "Vladimir", 0, "Second post", 0, 5, 5, true, 15, emptyArray()))
        service.add(Post(25, 1, "Vladimir", 0, "Third post", 0, 5, 5, true, 25, emptyArray()))

        // создаём информацию об обновлении
        val update = Post(3, 1, "Vladimir", 0, "Third post", 0, 5, 5, true, 35, emptyArray())

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }

    @Test
    fun updateExistingFalse() {
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(5, 1, "Vladimir", 0, "First post", 0, 5, 5, true, 5, emptyArray()))
        service.add(Post(15, 1, "Vladimir", 0, "Second post", 0, 5, 5, true, 15, emptyArray()))
        service.add(Post(25, 1, "Vladimir", 0, "Third post", 0, 5, 5, true, 25, emptyArray()))
        // создаём информацию об обновлении
        val update = Post(45, 1, "Vladimir", 0, "Third post", 0, 5, 5, true, 25)

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Test
    fun NotshouldThrow() {
        val service = WallService
        service.add(Post(5, 1, "Vladimir", 0, "First post", 0, 5, 5, true, 5, emptyArray()))

        val comment = Comment(5, 1, 2, 0, "First Comment")
        val postId = 1;
        val result =
            service.createComment(postId, comment) ?: throw PostNotFoundException("no post found $postId")

        assertEquals(1, result.id)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val service = WallService
        service.add(Post(5, 1, "Vladimir", 0, "First post", 0, 5, 5, true, 5, emptyArray()))

        val comment = Comment(5, 1, 2, 0, "First Comment")
        val postId = 10;
        val commentPost =
            service.createComment(postId, comment) ?: throw PostNotFoundException("no post found $postId")
    }
}
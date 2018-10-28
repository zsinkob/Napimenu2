package hu.zsinko.dailymenu.provider

import hu.zsinko.dailymenu.loggerFor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.social.facebook.api.Post
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class FacebookService(private val facebook: FacebookTemplate) {
    companion object {
        val logger = loggerFor<FacebookService>()
    }

    fun getPost(page: String, filter: (post: Post) -> Boolean): Post? {
        logger.info("Getting posts for $page")
        val feed = facebook.feedOperations().getPosts(page)
        logger.info("Received ${feed.size} posts")
        for (post in feed) {
            if (filter(post)) {
                logger.info("Post found")
                return post
            }
        }
        logger.info("NO result, returning empty")
        return null
    }

    fun getPhoto(postId: String): String {
        val uri = "${facebook.baseGraphApiUrl}$postId/picture"
        val exchange = facebook.restTemplate.exchange(uri, HttpMethod.GET, HttpEntity(listOf(HttpHeaders())), ByteArray::class.java)
        return if (exchange.statusCode == HttpStatus.OK) {
            String(Base64.getEncoder().encode(exchange.body))
        } else {
            ""
        }
    }
}





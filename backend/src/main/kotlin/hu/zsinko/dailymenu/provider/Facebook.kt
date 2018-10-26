package hu.zsinko.dailymenu.provider

import hu.zsinko.dailymenu.MenuProperties
import hu.zsinko.dailymenu.loggerFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.stereotype.Service

@Service
class FacebookService(private val properties: MenuProperties) {
    companion object {
        val logger = loggerFor<FacebookService>()
    }

    private val facebook = FacebookTemplate(properties.facebooktoken)
    fun getPost(page: String, filter: (regex: String) -> Boolean): String {
        logger.info("Getting posts for $page")
        val feed = facebook.feedOperations().getFeed(page)
        logger.info("Received ${feed.size} posts")
        for (post in feed) {
            if (post.message != null && filter(post.message)) {
                logger.info("Post found")
                return post.message
            }
        }
        logger.info("NO result, returning empty")
        return ""
    }
}





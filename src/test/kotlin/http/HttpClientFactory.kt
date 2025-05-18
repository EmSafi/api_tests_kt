package http

import com.typesafe.config.Config
import http.clients.AccountHttpClient
import http.clients.BookHttpClient
import models.TestUser

object HttpClientFactory {
    fun createAccountClient(user: TestUser, baseUrlKey: String) = AccountHttpClient(user, baseUrlKey)
    fun createBookClient(baseUrlKey: String) = BookHttpClient(baseUrlKey)
}
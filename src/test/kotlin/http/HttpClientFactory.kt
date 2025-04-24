package http

import com.typesafe.config.Config
import http.clients.AccountHttpClient
import http.clients.BookHttpClient
import models.TestUser

object HttpClientFactory {
    fun createAccountClient(user: TestUser) = AccountHttpClient(user)
    fun createBookClient(config: Config) = BookHttpClient(config)
}
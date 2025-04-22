package http

import com.typesafe.config.Config
import http.clients.AccountHttpClient
import http.clients.BookHttpClient

object HttpClientFactory {
    fun createAccountClient(config: Config) = AccountHttpClient(config)
    fun createBookClient(config: Config) = BookHttpClient(config)
}
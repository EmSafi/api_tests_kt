package actions

import http.HttpClientFactory
import models.requests.Isbn
import utils.TestProperties

class AddBooksToUserAction: Action {

    private val isbnList = TestProperties.getListProperty("isbnList").map { Isbn(it) }
    private val httpClient = HttpClientFactory.createBookClient("baseUrl")
    private val basicToken = TestProperties.getProperty("basicToken")
    private val userID = TestProperties.getProperty("userID")

    override fun run() {
        val response = postBooks()
    }

    private fun postBooks(){
        httpClient.postBooks(basicToken, userID, isbnList)
    }
}
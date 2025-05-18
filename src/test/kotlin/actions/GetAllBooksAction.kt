package actions

import com.google.gson.Gson
import http.HttpClientFactory
import io.qameta.allure.Step
import io.restassured.response.Response
import models.responses.AllBooksResponse
import utils.TestProperties
import validation.ResponseValidation

class GetAllBooksAction: Action {

    private val httpClient = HttpClientFactory.createBookClient("baseUrl")

    override fun run() {

        val response = getAllBooks()
        val bookResponse = parseResponse(response)
        validateResponse(response, bookResponse)

        val isbnList = bookResponse.books.map { it.isbn }
        TestProperties.setListProperty("isbnList", isbnList)
    }

    /**
     *
     */
    @Step("Http Запрос получения списка книг")
    private fun getAllBooks(): Response {
        return httpClient.getBooks()
    }

    /**
     *
     */
    @Step("Проверка ответа сервера получения списка книг")
    private fun validateResponse(response: Response, bookResponse: AllBooksResponse){

        ResponseValidation()
            .checkStatusCode(response.statusCode, 200)
            .checkIsNotNull(bookResponse.books[0].isbn)
    }

    /**
     *
     */
    private fun parseResponse(response: Response): AllBooksResponse {
        val responseBody = response.body().asString()
        return Gson().fromJson(responseBody, AllBooksResponse::class.java)
    }
}
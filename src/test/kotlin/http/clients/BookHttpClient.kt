package http.clients

import com.google.gson.Gson
import com.typesafe.config.Config
import http.BaseHttpClient
import io.qameta.allure.Step
import io.restassured.mapper.ObjectMapperType
import io.restassured.response.Response
import models.requests.AddListsOfBooks
import models.requests.Isbn
import models.responses.AllBooksResponse
import utils.Logger

class BookHttpClient(baseUrlKey: String): BaseHttpClient(baseUrlKey) {

    /**
     * Метод получения списка всех книг
     */
    @Step("Запрос получения книги: GET /BookStore/v1/Books")
    fun getBooks(): Response {
        Logger.info("Запрос GET /Books")
        return handleResponse(
            configureRequest()
                .get("/BookStore/v1/Books")
        )
    }

    /**
     * Метод добавления списка книг юзеру
     */
    @Step("Запрос добавления книг: POST /BookStore/v1/Books")
    fun postBooks(basicToken: String, userID:String, isbnList: List<Isbn>): Response {
        Logger.info("Запрос GET /Books")
        return handleResponse(
            configureRequest()
                .header("authorization", "Basic $basicToken")
                .body(getPostBooksBody(userID, isbnList))
                .post("/BookStore/v1/Books")
        )
    }

    private fun getPostBooksBody(userID: String, isbnList: List<Isbn>): String {
        return Gson().toJson(AddListsOfBooks(userId = userID, collectionOfIsbns = isbnList))
    }
}
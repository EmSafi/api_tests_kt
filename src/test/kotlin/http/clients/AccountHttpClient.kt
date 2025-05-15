package http.clients

import http.BaseHttpClient
import io.qameta.allure.Step
import io.restassured.response.Response
import models.TestUser
import utils.TestProperties

class AccountHttpClient(private val user: TestUser) : BaseHttpClient("baseUrl") {

    /**
     * Метод создания юзера
     */
    @Step("Запрос создания юзера: POST /Account/v1/User")
    fun postUser(): Response {
        return handleResponse(
            configureRequest()
                .body(getUserBody(user))
                .post("/Account/v1/User")
        )
    }

    /**
     * Метод получения юзера по UUID
     */
    fun getUser() {
        TODO()
    }

    /**
     * Метод удаления юзера по UUID
     */
    @Step("Запрос удаления юзера: DELETE /Account/v1/User/{userID}")
    fun deleteUser(userID: String, bearerToken: String, basicToken: String): Response {
        return handleResponse(
            configureRequest()
                .header("authorization", basicToken)
                .header("Authorization", bearerToken)
                .delete("/Account/v1/User/$userID")
        )
    }

    /**
     * Метод генерации токена
     */
    @Step("Запрос получения токена: POST /Account/v1/GenerateToken")
    fun postGenerateToken(): Response {
        return handleResponse(
            configureRequest()
                .body(getUserBody(user))
                .post("/Account/v1/GenerateToken")
        )
    }

    /**
     * Метод получения признака - авторизован ли юзер
     */
    fun postAuthorized() {
        TODO()
    }

    /**
     *
     */
    private fun getUserBody(user: TestUser): String {
        return "{\"userName\": \"${user.login}\", \"password\": \"${user.password}\"}"
    }
}
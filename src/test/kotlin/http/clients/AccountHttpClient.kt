package http.clients

import http.BaseHttpClient
import io.qameta.allure.Step
import io.restassured.response.Response
import models.TestUser
import utils.Logger

class AccountHttpClient(private val user: TestUser, baseUrlKey: String) : BaseHttpClient(baseUrlKey) {

    /**
     * Метод создания юзера
     */
    @Step("Запрос создания юзера: POST /Account/v1/User")
    fun postUser(): Response {
        Logger.info("Запрос POST /User для юзера: ${user.login}")
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
        Logger.info("Запрос DELETE /User для юзера c id = $userID")
        return handleResponse(
            configureRequest()
                .header("authorization", "Basic $basicToken")
                .header("Authorization", bearerToken)
                .delete("/Account/v1/User/$userID")
        )
    }

    /**
     * Метод генерации токена
     */
    @Step("Запрос получения токена: POST /Account/v1/GenerateToken")
    fun postGenerateToken(): Response {
        Logger.info("Запрос /GenerateToken для юзера: ${user.login}")
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
package http.clients

import http.BaseHttpClient
import io.restassured.response.Response
import models.TestUser

class AccountHttpClient(private val user: TestUser) : BaseHttpClient("baseUrl") {

    /**
     * Метод создания юзера
     */
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
    fun deleteUser() {
        TODO()
    }

    /**
     * Метод генерации токена
     */
    fun postGenerateToken() {
        TODO()
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
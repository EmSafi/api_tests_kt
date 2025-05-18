package actions

import com.google.gson.Gson
import http.HttpClientFactory
import io.qameta.allure.Step
import io.restassured.response.Response
import models.TestUser
import models.messages.ErrorMessages
import models.responses.MessageResponse
import models.responses.UserCreateResponse
import utils.Logger
import utils.TestProperties
import validation.ResponseValidation

class CreateUserAction(private val user: TestUser) : Action {

    private val userHttpClient = HttpClientFactory.createAccountClient(user, "baseUrl")

    override fun run() {

        Logger.info("Создаем юзера: ${user.login}")
        val response = postUserRequest()

        Logger.info("Валидируем респонс: ${user.login}")
        if (!user.isValid) {
            if (user.description == "Пустой пароль") validateEmptyResponse(response)
            else validateNegativeResponse(response)
        } else {
            validateResponse(response)
        }
    }

    @Step("Http Запрос создания юзера")
    private fun postUserRequest(): Response {
        return userHttpClient.postUser()
    }

    @Step("Проверка ответа сервера на валидные данные")
    private fun validateResponse(response: Response) {
        val userResponse = parseValidResponse(response)

        ResponseValidation()
            .checkStatusCode(response.statusCode, 201)
            .checkIsNotNull(userResponse.userID)
            .checkFieldEquality(user.login, userResponse.username)

        //Сохраняем контекст для последующих шагов
        Logger.info("Сохраняем userID = ${userResponse.userID}")
        TestProperties.setProperty("userID", userResponse.userID)
    }

    @Step("Проверка ответа сервера на невалидные данные")
    private fun validateNegativeResponse(response: Response) {
        val userResponse = parseNegativeResponse(response)

        ResponseValidation()
            .checkStatusCode(response.statusCode, 400)
            .checkFieldEquality(userResponse.code, "1300")
            .checkFieldEquality(userResponse.message, ErrorMessages.INVALID_PASSWORD.msg)
    }

    @Step("Проверка ответа сервера на пустой пароль")
    private fun validateEmptyResponse(response: Response) {
        val userResponse = parseNegativeResponse(response)

        ResponseValidation()
            .checkStatusCode(response.statusCode, 400)
            .checkFieldEquality(userResponse.code, "1200")
            .checkFieldEquality(userResponse.message, ErrorMessages.EMPTY_PASSWORD.msg)
    }

    /**
     * Метод для проверки респонса на валидные логин и пароль
     */
    private fun parseValidResponse(response: Response): UserCreateResponse {
        val responseBody = response.body().asString()
        return Gson().fromJson(responseBody, UserCreateResponse::class.java)
    }

    /**
     *Метод для проверки респонса на невалидные логин и пароль
     */
    private fun parseNegativeResponse(response: Response): MessageResponse {
        val responseBody = response.body().asString()
        return Gson().fromJson(responseBody, MessageResponse::class.java)
    }
}
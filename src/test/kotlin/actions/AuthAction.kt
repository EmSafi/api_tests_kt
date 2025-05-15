package actions

import com.google.gson.Gson
import config.Configurations
import http.HttpClientFactory
import io.restassured.response.Response
import models.TestUser
import models.responses.TokenViewResponse
import utils.TestProperties
import validation.ResponseValidation
import java.util.Base64

class AuthAction(private val basicUser: TestUser): Action {

    //http клиент
    private val httpClient = HttpClientFactory.createAccountClient(basicUser)

    override fun run() {
        val tokenResponse = getToken()
        validatePositiveResponse(tokenResponse)

        TestProperties.setProperty("bearerToken", tokenResponse.body.jsonPath().getString("token"))
        TestProperties.setProperty("basicToken", getBearerToken())
    }


    private fun validatePositiveResponse (response: Response) {
        val responseModel = parseResponse(response)

        ResponseValidation()
            .checkStatusCode(response.statusCode, 200)
            .checkIsNotNull(responseModel.token.toString())
            .checkFieldEquality(responseModel.status, "Success")
            .checkFieldEquality(responseModel.result, "User authorized successfully.")
    }
    /**
     * Получение токена авторизации
     */
    private fun getToken(): Response {
        return httpClient.postGenerateToken()
    }

    /**
     * Валидация ответа сервера по модели TokenView
     */
    private fun parseResponse(response: Response): TokenViewResponse {
        val responseBody = response.body.asString()
        return Gson().fromJson(responseBody, TokenViewResponse::class.java)
    }

    /**
     *
     */
    private fun getBearerToken(): String {
        val tokenString = "${basicUser.login}:${basicUser.password}"
        return Base64.getEncoder().encodeToString(tokenString.toByteArray())
    }
}
package actions

import com.google.gson.Gson
import http.HttpClientFactory
import io.qameta.allure.Step
import io.restassured.response.Response
import models.TestUser
import models.responses.MessageResponse
import org.testng.Assert
import utils.Logger
import utils.TestProperties
import validation.ResponseValidation

class DeleteUserAction (user: TestUser): Action {

    private val userID: String = TestProperties.getProperty("userID")
    private val username: String = user.login
    private val userHttpClient = HttpClientFactory.createAccountClient(user)
    private val bearerToken = TestProperties.getProperty("bearerToken")
    private val basicToken = TestProperties.getProperty("basicToken")

    override fun run() {
        val response = deleteUser()

        Logger.info("начинаем валидацию ответа сервера")
        validateResponse(response)
    }

    @Step ("Удаление юзера")
    private fun deleteUser(): Response {
        Logger.info("Удаляем юзера ($username) с id = $userID")
       return userHttpClient.deleteUser(userID, bearerToken, basicToken)
    }

    @Step("Валидация ответа сервера после удаления юзера")
    private fun validateResponse(response: Response) {
        Assert.assertEquals(200, response.statusCode)
        val responseModel = parseResponse(response)

        ResponseValidation()
            .checkStatusCode(response.statusCode, 200)
            .checkFieldEquality(responseModel.code, "1200")
            .checkFieldEquality(responseModel.message, "User not authorized!")
    }

    /**
     *
     */
    private fun parseResponse(response: Response): MessageResponse {
        val responseBody = response.body().toString()
        return Gson().fromJson(responseBody, MessageResponse::class.java)
    }
}
package actions

import http.HttpClientFactory
import io.qameta.allure.Step
import io.restassured.response.Response
import models.TestUser
import org.testng.Assert

class CreateUserAction(private val user: TestUser): AbstractAction() {

    override fun run() {
        val response = postUserRequest(user)
        validateResponse(response)
    }

    @Step("Http Запрос создания юзера")
    private fun postUserRequest(user: TestUser): Response {
        return HttpClientFactory.createAccountClient(user).postUser()
    }

    @Step("Проверка ответа сервера")
    private fun validateResponse(response: Response) {
        Assert.assertTrue(response.statusCode >= 200)
    }
}
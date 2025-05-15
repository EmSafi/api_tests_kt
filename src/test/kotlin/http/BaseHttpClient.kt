package http

import config.Configurations
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import utils.ErrorHandler

abstract class BaseHttpClient(
    baseUrlKey: String
) {
    /**
     * Базовый URI
     */
    private val baseUrl: String = Configurations.get(baseUrlKey)


    /**
     *
     */
    protected fun configureRequest(contentType: ContentType = ContentType.JSON): RequestSpecification {
        return RestAssured.given()
            .filter(AllureRestAssured())
            .baseUri(baseUrl)
            .accept(ContentType.JSON)
            .contentType(contentType)
            .urlEncodingEnabled(true)
    }

    /**
     *
     */
    protected fun handleResponse(response: Response): Response {
        if (!response.contentType.contains("json")) {
            throw ErrorHandler.handle(Exception(), "Non-JSON response: ${response.body.asString()}")
        }
        return response
    }
}
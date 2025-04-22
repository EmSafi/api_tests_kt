package http

import com.typesafe.config.Config
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import utils.ErrorHandler

abstract class BaseHttpClient(
    protected val config: Config,
    baseUrlKey: String
) {
    protected val baseUrl: String = config.getString(baseUrlKey)

    protected fun configureRequest(contentType: ContentType = ContentType.JSON): RequestSpecification {
        return RestAssured.given()
            .baseUri(baseUrl)
            .accept(ContentType.JSON)
            .contentType(contentType)
            .urlEncodingEnabled(true)
    }

    protected fun handleResponse(response: Response): Response {
        if (!response.contentType.contains("json")) {
            throw ErrorHandler.handle(Exception(), "Non-JSON response: ${response.body.asString()}")
        }

        return response.takeIf { it.statusCode in 200..299 }
            ?: throw ErrorHandler.handle(
                Exception(),
                "Status: ${response.statusCode}; Response: ${response.body.asPrettyString()}"
            )
    }
}
package validation

import io.qameta.allure.*
import org.testng.Assert

class ResponseValidation {

    @Step ("Проверка статуса респонса: ожидаемый {expected}, фактический {actual}")
    fun checkStatusCode (actual: Int, expected: Int): ResponseValidation {
        Assert.assertEquals(actual, expected)
        return this
    }

    @Step ("Проверка совпадения значения полей: Ожидалось {expected}, получено {actual}")
    fun checkFieldEquality(actual: String, expected: String): ResponseValidation {
        Assert.assertEquals(actual, expected)
        return this
    }

    @Step ("Проверка что поле {expected} не пустое")
    fun checkIsNotNull(expected: String): ResponseValidation {
        Assert.assertNotNull(expected)
        return this
    }
}
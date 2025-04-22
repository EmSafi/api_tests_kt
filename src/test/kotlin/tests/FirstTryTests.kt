package tests

import actions.FirstTest
import io.qameta.allure.*
import org.testng.annotations.Test

class FirstTryTests {

    @Feature("Пробный тест №1")
    @TmsLink("https://testng.org/")
    @Story("Описание дополнительное")
    @Test (testName = "пробный тест 1", groups = ["smoke"])
    fun firstTest() {
        FirstTest().run()
    }

    @Feature("Пробный тест №2")
    @TmsLink("https://testng.org/")
    @Story("Описание дополнительное")
    @Test (testName = "пробный тест 2", groups = ["smoke"])
    fun secondTest() {
        FirstTest().run()
    }

    @Feature("Пробный тест №3")
    @TmsLink("https://testng.org/")
    @Story("Описание дополнительное")
    @Test (testName = "пробный тест 3", groups = ["simple"])
    fun thirdTest() {
        FirstTest().run()
    }
}
package tests

import actions.CreateUserAction
import dataProviders.TestsDataProvider
import io.qameta.allure.*
import models.TestUser
import org.testng.annotations.Test


open class TestsAccount: Tests() {

    @Test(
        testName = "Параметризованный тест создания юзеров",
        groups = ["smoke"],
        dataProvider = "jsonUsers",
        dataProviderClass = TestsDataProvider::class
        )
    @Epic("Проверка создания юзера")
    @Story("Параметризованный тест с валидными и невалидными паролями")
    fun testAccount_check_creating_user(user: TestUser) {
        CreateUserAction(user).run()
    }
}
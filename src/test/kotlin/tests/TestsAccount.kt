package tests

import actions.AuthAction
import actions.CreateUserAction
import actions.DeleteUserAction
import config.Configurations
import dataProviders.TestsDataProvider
import io.qameta.allure.*
import models.TestUser
import org.testng.annotations.Test
import scenario.TestScenario


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

    @Test(
        testName = "Проверка цепочки действий",
        groups = ["smoke"]
        )
    @Epic("Проверка цепочки действий")
    fun testAccount_auth_and_delete() {
        val basicUser = Configurations.getUserByRole("basic")
        TestScenario()
            .addAction(AuthAction(basicUser))
            .addAction(CreateUserAction(basicUser))
            .addAction(DeleteUserAction(basicUser))
            .execute()
    }
}
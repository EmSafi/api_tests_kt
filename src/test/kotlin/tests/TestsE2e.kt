package tests

import actions.AddBooksToUserAction
import actions.AuthAction
import actions.CreateUserAction
import actions.GetAllBooksAction
import config.Configurations
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.testng.annotations.Test
import utils.EntityUtils

class TestsE2e {

    @Test(
        testName = "E2e тест",
        groups = ["e2e"]
    )
    @Epic("E2e тест")
    @Story("Тест на создание юзера и добавление для него книги")
    fun testE2e_createUser_and_addBook() {
        val baseUser = EntityUtils().getFirstValidUser()

        CreateUserAction(baseUser).run()
        AuthAction(baseUser).run()
        GetAllBooksAction().run()
        AddBooksToUserAction().run()
    }
}
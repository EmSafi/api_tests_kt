package actions

import io.qameta.allure.Allure
import io.qameta.allure.Step
import models.TestUser
import org.testng.Assert

class CreateUserAction(private val user: TestUser): AbstractAction() {

    override fun run() {
        getUserData()
        validateUser()
    }

    @Step ("получение данных юзера")
    fun getUserData() {
        val name = user.login
        val pass = user.password

        Allure.addAttachment("имя", name)
        Allure.addAttachment("пароль", pass)
    }

    @Step ("Валидация юзера")
    private fun validateUser(){
        Allure.addAttachment("isValid", user.isValid.toString())
        Assert.assertTrue(user.isValid, "юзер не прошел валидацию")
    }
}
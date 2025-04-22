package actions

import com.typesafe.config.Config
import config.Configurations
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.testng.Assert

class FirstTest: AbstractAction() {

    @Step ("Запуск первого теста")
    override fun run() {
        logger.info("Запускаем первый тест:")

        val config: Config = Configurations.getUserByRole("correctUser")
        val login: String = config.getString("login")
        val password: String = config.getString("password")
        logger.info("логин = $login, пароль = $password")

        Allure.addAttachment("Логин", login)
        Allure.addAttachment("Пароль", password)
        Assert.assertTrue("Emil" == login && "XSW@zaq1"== password)
    }
}
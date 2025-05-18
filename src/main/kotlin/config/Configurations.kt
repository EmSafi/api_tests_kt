package config

import com.typesafe.config.ConfigFactory
import models.TestUser
import utils.ErrorHandler
import java.io.File

/**
 * Синглтон для получения настроек из файла resources/application.conf
 * Тут же создаем кастомные методы, для получения набора настроек
 */
object Configurations {

    private var configName = "data/stand_configs/demoqa.conf"


    /**
     * Получение конфигов
     * @throws ConfigExceptionParse в случае если в файле ошибки
     * @throws ConfigExceptionMissing в случае отсутствия файла в системе
     */
    private val config = try {
        val configPath = System.getProperty("config_name")
            ?: "data/stand_configs/demoqa.conf" // Путь по умолчанию для IDE

        ConfigFactory.parseFile(File(configPath))
    } catch (e: Exception) {
        throw ErrorHandler.handle(e, "Failed to load config from: ${System.getProperty("config_name") ?: "default path"}")
    }


    /**
     * Метод получения конкретного значения из application.conf
     * @param value - строка в формате "objectName.elementName" -> "blogicDss.authUrl"
     * @return String значение из файла конфигураций
     * @throws ConfigExceptionMissing в случае если элемент был не найден
     */
    fun get(value: String): String {
        return try {
            config.getString(value)
        } catch (e: Exception) {
            throw ErrorHandler.handle(e, value)
        }
    }

    /**
     * Получение юзера из конфигураций по роли
     */
    fun getUserByRole(role: String): TestUser {
        return config.getConfigList("Users")
            .firstOrNull { it.getString("role") == role }
            .let { userConfig ->
                userConfig?.let {
                    TestUser(
                        login = it.getString("login"),
                        password = userConfig.getString("password"),
                        isValid = userConfig.getBoolean("isValid"),
                        role = userConfig.getString("role"),
                        description = userConfig.getString("description")
                    )
                }
            }
            ?: throw ErrorHandler.handle(Exception(), "В списке юзеров отсутствует юзер с ролью = $role")

    }
}
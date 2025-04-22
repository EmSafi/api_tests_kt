package config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import utils.ErrorHandler
import java.io.File

/**
 * Синглтон для получения настроек из файла resources/application.conf
 * Тут же создаем кастомные методы, для получения набора настроек
 */
object Configurations {

    /**
     * Получение конфигов
     * @throws ConfigExceptionParse в случае если в файле ошибки
     * @throws ConfigExceptionMissing в случае отсутствия файла в системе
     */
    private val config =
        try {
            ConfigFactory.parseFile(File(System.getProperty("config_name")))
        } catch (e: Exception) {
            throw ErrorHandler.handle(e, "")
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
     * Получение юзеров
     */
    fun getUserByRole(role: String): Config {
        return config.getConfigList("Users")
            .firstOrNull { it.getString("role") == role }
            ?: throw ErrorHandler.handle(Exception(), "В списке юзеров отсутствует юзер с ролью = $role")

    }
}
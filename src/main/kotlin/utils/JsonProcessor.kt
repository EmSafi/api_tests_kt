package utils

import java.io.File
import java.io.IOException


/**
 * Класс для подготовки json-файлов описания теста. Осуществляет подмену шаблонизированных значений.
 */
object JsonProcessor {

    /**
     * Функция, которая считывает json, заменяет шаблонные значения и возвращает обработанный json
     *
     * @param pathToJson       Путь к json'у
     * @param jsonParameters   Список замен (ключ - строка для поиска, значение - подмена)
     *
     * @return Обработанный json с произведенными подстановками.
     */
    @JvmStatic
    fun processedJson(pathToJson: String, jsonParameters: Map<String, String>): String {
        return try {
            val jsonTemplate = File(pathToJson).readText()
            return process(jsonTemplate, jsonParameters)
        } catch (e: IOException) {
            ErrorHandler.handle(e, "Ошибка чтения файла $pathToJson.")
            ""
        }
    }

    /**
     * Функция, осуществляющая подмену шаблонизированных значений, используя мапу [replaceMap] и список пользователей.
     *
     * @param string        Строка-содержимое json-файла, которая будет обработана.
     * @param replaceMap    Список замен (ключ - строка для поиска, значение - подмена).
     *
     * @return  Обработанный json с произведенными подстановками.
     */
    @JvmStatic
    private fun process(string: String, replaceMap: Map<String, String> = mapOf()): String {
        var result = string
        replaceMap.forEach { (key, value) ->
            result = result.replace(key, value)
        }
        return result
    }
}

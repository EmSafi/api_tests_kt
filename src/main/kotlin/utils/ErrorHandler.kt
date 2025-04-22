package utils

/**
 * Единый хендлер для обработки ошибок
 */
object ErrorHandler {
    fun handle(e: Exception, argument: Any): Throwable{
        when (e) {
            is com.typesafe.config.ConfigException.Missing -> {
                throw IllegalArgumentException("Ключ '$argument' отсутствует в файле настроек, либо указан некорректно", e)
            }
            is com.typesafe.config.ConfigException.Parse -> {
                throw IllegalArgumentException("Ошибка при парсинге файла application.conf, проверьте файл на корректность заполнения", e)
            }
            else -> {
                throw RuntimeException("Возникла ошибка: ${argument}. Для более подробной информации смотрите логи", e)
            }
        }
    }
}
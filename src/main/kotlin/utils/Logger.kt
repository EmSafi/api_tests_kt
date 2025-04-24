package utils

import org.apache.logging.log4j.LogManager

/**
 * Синглтон (один экземпляр, доступный из любого класса), реализующий логгер.
 */
object Logger {
    private val logger = LogManager.getLogger()

    fun info(message: String) {
        val stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
        val callerInfo = stackWalker.walk { stream ->
            stream.skip(1) // Пропускаем текущий класс
                .findFirst()
                .orElseThrow() // Получаем первый элемент
        }
        logger.info("[${callerInfo.className}:${callerInfo.lineNumber}] $message")
    }
}
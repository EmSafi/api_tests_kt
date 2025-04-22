package utils

object TestProperties {

    private val properties: ThreadLocal<HashMap<String, String>> = ThreadLocal.withInitial { HashMap<String,String>() }

    /**
     * Метод инициализации параметра.
     */
    fun setProperty(parameterName: String, parameterValue: String) {
        properties.get()[parameterName] = parameterValue
    }

    /**
     * Метод получения параметра теста.
     */
    fun getProperty(parameterName: String): String {
        return properties.get()[parameterName].toString()
    }
}
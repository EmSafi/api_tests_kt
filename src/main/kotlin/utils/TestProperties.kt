package utils

object TestProperties {

    private val properties: ThreadLocal<HashMap<String, String>> = ThreadLocal.withInitial { HashMap<String,String>() }
    private val propertiesList: ThreadLocal<HashMap<String, List<String>>> = ThreadLocal.withInitial { HashMap<String,List<String>>() }
    /**
     * Метод инициализации параметра.
     */
    fun setProperty(parameterName: String, parameterValue: String) {
        properties.get()[parameterName] = parameterValue
    }

    fun setListProperty(parameterName: String, parameterValue: List<String>) {
        propertiesList.get()[parameterName] = parameterValue
    }

    /**
     * Метод получения параметра теста.
     */
    fun getProperty(parameterName: String): String {
        return properties.get()[parameterName].toString()
    }

    fun getListProperty(parameterName: String): List<String> {
        return propertiesList.get()[parameterName]
            ?: throw IllegalArgumentException("Property $parameterName not found or invalid type")
    }
}
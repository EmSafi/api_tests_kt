package http.clients

import com.typesafe.config.Config
import http.BaseHttpClient

class AccountHttpClient(config: Config) : BaseHttpClient(config, "baseUrl") {

    /**
     * Метод создания юзера
     */
    fun postUser() {
        TODO()
    }

    /**
     * Метод получения юзера по UUID
     */
    fun getUser() {
        TODO()
    }

    /**
     * Метод удаления юзера по UUID
     */
    fun deleteUser() {
        TODO()
    }

    /**
     * Метод генерации токена
     */
    fun postGenerateToken() {
        TODO()
    }

    /**
     * Метод получения признака - авторизован ли юзер
     */
    fun postAuthorized() {
        TODO()
    }
}
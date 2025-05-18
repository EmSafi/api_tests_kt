package utils

import dataProviders.TestsDataProvider
import models.TestUser

class EntityUtils {

    /**
     * Метод для получения первого валидного юзера из дата_провайдера
     */
    fun getFirstValidUser(): TestUser {
        return TestsDataProvider.jsonUsersProvider()
            .first { user -> user.iterator().next().isValid }.first()
    }
}

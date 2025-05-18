package dataProviders

import com.google.gson.Gson
import models.Book
import models.TestUser
import org.testng.annotations.DataProvider
import java.io.File

class TestsDataProvider {

    companion object {

        /**
         * Data провайдер параметризованных тестов для ручки POST Account/v1/User
         */
        @JvmStatic
        @DataProvider(name = "jsonUsers")
        fun jsonUsersProvider(): Array<Array<TestUser>> {
            val json = File("src/test/kotlin/resources/users.json").readText()
            val users = Gson().fromJson(json, Array<TestUser>::class.java).toList()

            users.forEach { user ->
                user.login = "${user.login}_${System.currentTimeMillis()}"
            }
            return users.map { arrayOf(it) }.toTypedArray()
        }

        /**
         * Data провайдер параметризованных тестов для "BookStore" API
         */
        @JvmStatic
        @DataProvider(name = "jsonBooks")
        fun jsonBooksProvider(): Array<Array<Book>> {
            val json = File("src/test/kotlin/resources/books.json").readText()
            val users = Gson().fromJson(json, Array<Book>::class.java)
            return users.map { arrayOf(it) }.toTypedArray()
        }

    }
}
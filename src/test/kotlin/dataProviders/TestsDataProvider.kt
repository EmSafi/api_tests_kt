package dataProviders

import com.google.gson.Gson
import models.TestUser
import org.testng.annotations.DataProvider
import java.io.File

class TestsDataProvider {

    companion object {
        @JvmStatic
        @DataProvider(name = "jsonUsers")
        fun jsonUsersProvider(): Array<Array<TestUser>> {
            val json = File("src/test/kotlin/resources/users.json").readText()
            val users = Gson().fromJson(json, Array<TestUser>::class.java)
            return users.map { arrayOf(it) }.toTypedArray()
        }
    }
}
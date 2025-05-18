package models

data class TestUser (
    var login: String,
    val password: String,
    val isValid: Boolean,
    var role: String? = null,
    val description: String,
    val userID: String? = null
)
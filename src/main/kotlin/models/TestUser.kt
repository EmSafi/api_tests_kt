package models

data class TestUser (
    val login: String,
    val password: String,
    val isValid: Boolean,
    val role: String = "",
    val description: String
)
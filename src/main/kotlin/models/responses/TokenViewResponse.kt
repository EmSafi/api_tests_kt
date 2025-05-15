package models.responses

data class TokenViewResponse (
    val token: String?,
    val expires: String?,
    val status: String,
    val result: String
)

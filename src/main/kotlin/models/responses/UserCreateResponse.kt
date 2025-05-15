package models.responses

import models.Book

data class UserCreateResponse (
    val userID: String,
    val username: String,
    val books: List<Book>
)
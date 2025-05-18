package models.responses

import models.Book

data class AllBooksResponse (
    val books: List<Book>
)
package models.requests

data class AddListsOfBooks(
    val userId: String,
    val collectionOfIsbns: List<Isbn>
)

data class Isbn(
    val isbn: String
)

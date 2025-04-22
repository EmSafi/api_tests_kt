package models

/**
 * Модель, описывающая книгу
 */
data class Book (

    /**
     * ИСБН
     */
    val isbn: String,

    /**
     * Название книги
     */
    val title: String,

    /**
     * Подзаголовок книги
     */
    val subTitle: String,

    /**
     * Автор книги
     */
    val author: String,

    /**
     * Дата публикации
     */
    val publishDate: String,

    /**
     * Издательство
     */
    val publisher: String,

    /**
     * Количество страниц в книге
     */
    val pages: Int,

    /**
     * Описание книги
     */
    val description: String,

    /**
     * Ссылка на веб сайт книги
     */
    val website: String
)
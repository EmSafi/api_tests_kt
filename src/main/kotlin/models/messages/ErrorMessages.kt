package models.messages

enum class ErrorMessages(val msg: String) {

    EMPTY_PASSWORD("UserName and Password required."),
    INVALID_PASSWORD("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.")
}

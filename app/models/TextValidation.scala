package models

case class TextValidation(
    required: String,
    password: String,
    minlength: String,
    maxlength: String,
    rangelength: String,
    creditcard: String,
    remote: String,
    email: String,
    url: String
)

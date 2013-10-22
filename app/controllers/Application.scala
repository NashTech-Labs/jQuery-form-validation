package controllers

import play.api.data.Form
import play.api.data.Forms.date
import play.api.data.Forms.email
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.number
import play.api.data.Forms.tuple
import play.api.mvc.Action
import play.api.mvc.Controller

object Application extends Controller {

  val textValidationForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "dob" -> nonEmptyText,
      "email" -> email,
      "phone" -> nonEmptyText,
      "website" -> nonEmptyText,
      "userId" -> nonEmptyText,
      "password" -> nonEmptyText,
      "confPassword" -> nonEmptyText))

  val dateValidationForm = Form(
    tuple(
      "dateValue" -> nonEmptyText,
      "dateISOValue" -> nonEmptyText))

  val numberValidationForm = Form(
    tuple(
      "minValue" -> number,
      "maxValue" -> number,
      "rangeValue" -> number,
      "numberValue" -> number,
      "digitsValue" -> number,
      "creditCard" -> nonEmptyText))

  def index = Action { implicit request =>
    Ok(views.html.index("Text", textValidationForm))
  }

  def showTextValidationForm = Action { implicit request =>
    Ok(views.html.index("Text", textValidationForm))
  }

  def showNumberValidationForm = Action { implicit request =>
    Ok(views.html.numberValidationForm("Number", numberValidationForm))
  }

  def showDateValidationForm = Action { implicit request =>
    Ok(views.html.dateValidationForm("Date", dateValidationForm))
  }

  def handleTextForm = Action { implicit request =>
    textValidationForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.index("Text", formWithErrors)),
      {
        case (name, dob, email, phone, website, userId, password, confPassword) =>
          Redirect(routes.Application.index).flashing("SUCCESS" -> "Form submited successfully")
      })
  }
  
  def handleNumberForm = Action { implicit request =>
    numberValidationForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.numberValidationForm("Number", formWithErrors)),
      {
        case (minValue, maxValue, rangeValue, numberValue, digitsValue, creditCard) =>
          Redirect(routes.Application.showNumberValidationForm).flashing("SUCCESS" -> "Form submited successfully")
      })
  }
  
  def handleDateForm = Action { implicit request =>
    dateValidationForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.dateValidationForm("Date", formWithErrors)),
      {
        case (dateValue, dateISOValue) =>
          Redirect(routes.Application.index).flashing("SUCCESS" -> "Form submited successfully")
      })
  }

}

package models

import play.api.libs.json.Json

case class PersonalDetails(
                            firstName: String,
                            surname: String,
                            middleName: Option[String],
                            dob: String
                          )

object PersonalDetails {
  implicit val format = Json.format[PersonalDetails]
}
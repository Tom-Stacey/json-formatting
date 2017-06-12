package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class PersonalDetails(
                            firstName: String,
                            surname: String,
                            middleName: Option[String],
                            dob: String
                          )

object PersonalDetails {
  implicit val format = (
      (__ \ "name").format[String] and
      (__ \ "last-name").format[String] and
      (__ \ "other-name").formatNullable[String] and
      (__ \ "date-of-birth").format[String]
    )(PersonalDetails.apply, unlift(PersonalDetails.unapply))
}

package models

import java.time.LocalDate

import formatters.DetailsFormatters
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

case class PersonalDetails(
                            firstName: String,
                            surname: String,
                            middleName: Option[String],
                            starSign: Option[String],
                            favouriteWrench: Option[String],
                            dob: LocalDate
                          )

object PersonalDetails {

  implicit val format = (
      (__ \ "name").format[String] and
      (__ \ "last-name").format[String] and
      (__ \ "other-name").formatNullable[String] and
      (__ \ "star-sign").formatNullable[String] and
      (__ \ "favoured-wrench").formatNullable[String] and
      (__ \ "date-of-birth").format[LocalDate](DetailsFormatters.dateFormat)
    )(PersonalDetails.apply, unlift(PersonalDetails.unapply))
      .filter(ValidationError("Neither star sign nor preferred wrench provided"))(deets =>
        deets.starSign.isDefined || deets.favouriteWrench.isDefined)
}

package models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

  val dateFmt = DateTimeFormatter.ofPattern("yyyy/MM/dd")
  val dateReads = Reads[LocalDate]( js =>
    js.validate[String].map[LocalDate]( dtString =>
      LocalDate.parse(dtString, dateFmt)
    )
  )
  val dateWrites = Writes[LocalDate](dt =>
    JsString(dt.toString)
  )
  val dateFormat: Format[LocalDate] = Format(dateReads, dateWrites)

  implicit val format = (
      (__ \ "name").format[String] and
      (__ \ "last-name").format[String] and
      (__ \ "other-name").formatNullable[String] and
      (__ \ "star-sign").formatNullable[String] and
      (__ \ "favoured-wrench").formatNullable[String] and
      (__ \ "date-of-birth").format[LocalDate](dateFormat)
    )(PersonalDetails.apply, unlift(PersonalDetails.unapply))
}

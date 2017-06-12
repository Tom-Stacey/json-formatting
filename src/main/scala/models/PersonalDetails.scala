package models

import play.api.libs.json.Json

case class PersonalDetails(
                            firstName: String,
                            surname: String,
                            middleName: Option[String],
                            dob: String
                          )


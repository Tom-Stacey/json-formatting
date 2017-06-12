package formatters

import java.time.LocalDate

import models.PersonalDetails
import play.api.libs.json._
import org.scalatest.{Matchers, WordSpec}

class FormattersSpec extends WordSpec with Matchers {

  "Reading basic personal details" should {
    "succeed" in {
      val json = Json.parse(
        """
          |{
          |  "name":"Thomas",
          |  "last-name":"Stacey",
          |  "other-name":"Anthony",
          |  "date-of-birth":"2000/03/15"
          |}
        """.stripMargin
      )

      val deets = PersonalDetails(
        firstName = "Thomas",
        middleName = Some("Anthony"),
        surname = "Stacey",
        dob = LocalDate.of(2000,3,15)
      )

      Json.fromJson[PersonalDetails](json) shouldBe JsSuccess(deets)
    }
  }

}

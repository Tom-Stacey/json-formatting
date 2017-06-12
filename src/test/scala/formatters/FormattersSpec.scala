package formatters

import java.time.LocalDate

import helpers.JsonValidationTestHelpers
import models.PersonalDetails
import play.api.data.validation.ValidationError
import play.api.libs.json._
import org.scalatest.{Matchers, WordSpec}

class FormattersSpec extends WordSpec with Matchers with JsonValidationTestHelpers {

  "Reading basic personal details" should {
    "succeed" in {
      val json = Json.parse(
        """
          |{
          |  "name":"Thomas",
          |  "last-name":"Stacey",
          |  "other-name":"Anthony",
          |  "favoured-wrench":"torque wrench",
          |  "date-of-birth":"2000/03/15"
          |}
        """.stripMargin
      )

      val deets = PersonalDetails(
        firstName = "Thomas",
        middleName = Some("Anthony"),
        surname = "Stacey",
        starSign = None,
        favouriteWrench = Some("torque wrench"),
        dob = LocalDate.of(2000,3,15)
      )

      Json.fromJson[PersonalDetails](json) shouldBe JsSuccess(deets)
    }


    "fail" when {
      "Neither star-sign nor wrench preference is provided"in {
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

        val res = Json.fromJson[PersonalDetails](json)
        shouldHaveErrors(res, JsPath(), Seq(ValidationError("Neither star sign nor preferred wrench provided")))
      }

      "First name is not capitalised"in {
        val json = Json.parse(
          """
            |{
            |  "name":"thomas",
            |  "last-name":"Stacey",
            |  "other-name":"Anthony",
            |  "favoured-wrench":"torque wrench",
            |  "date-of-birth":"2000/03/15"
            |}
          """.stripMargin
        )

        val res = Json.fromJson[PersonalDetails](json)
        shouldHaveErrors(res, JsPath() \ "name", Seq(ValidationError("non-capitalised first name")))
      }
    }
  }

}

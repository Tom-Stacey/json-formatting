package formatters

import models.PersonalDetails
import play.api.libs.json._
import org.scalatest.{Matchers, WordSpec}

class FormattersSpec extends WordSpec with Matchers {

  "Reading basic personal details" should {
    "succeed" in {
      val json = Json.parse(
        """
          |{
          |  "firstName":"Thomas",
          |  "surname":"Stacey",
          |  "middleName":"Anthony",
          |  "dob":"2000/03/15"
          |}
        """.stripMargin
      )

      val deets = PersonalDetails(
        firstName = "Thomas",
        middleName = Some("Anthony"),
        surname = "Stacey",
        dob = "2000/03/15"
      )

      Json.fromJson[PersonalDetails](json) shouldBe JsSuccess(deets)
    }
  }

}

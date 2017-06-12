package helpers

import org.scalatest.{Matchers, WordSpec}
import play.api.data.validation.ValidationError
import play.api.libs.json.{JsError, JsSuccess, JsPath, JsResult}

trait JsonValidationTestHelpers {
  this: WordSpec with Matchers =>

  def shouldHaveErrors[T](result: JsResult[T], errorPath: JsPath, expectedErrors: Seq[ValidationError]): Unit = {
    shouldHaveErrors[T](result, Map(errorPath -> expectedErrors))
  }

  def shouldHaveErrors[T](result: JsResult[T], expectedErrors: Map[JsPath, Seq[ValidationError]]): Unit = {
    result match {
      case JsSuccess(value, path) => fail(s"read should have failed and didn't - produced ${value}")
      case JsError(errors) => {
        errors.length shouldBe expectedErrors.keySet.toSeq.length

        for( error <- errors ) {
          error match {
            case (path, valErrs) => {
              expectedErrors.keySet should contain(path)
              expectedErrors(path) shouldBe valErrs
            }
          }
        }
      }
    }
  }
}

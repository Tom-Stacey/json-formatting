package formatters

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import play.api.libs.json.{Format, JsString, Writes, Reads}

object DetailsFormatters {

  private val dateFmt = DateTimeFormatter.ofPattern("yyyy/MM/dd")
  private val dateReads = Reads[LocalDate]( js =>
    js.validate[String].map[LocalDate]( dtString =>
      LocalDate.parse(dtString, dateFmt)
    )
  )
  private val dateWrites = Writes[LocalDate](dt =>
    JsString(dt.toString)
  )

  val dateFormat: Format[LocalDate] = Format(dateReads, dateWrites)
}

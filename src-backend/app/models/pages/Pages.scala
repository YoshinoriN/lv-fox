package models.pages

import io.circe.Encoder
import io.circe.generic.semiauto._

final case class Pages(
  url: String,
  title: String,
  content: String,
  publishedAt: Long,
  updatedAt: Long
)

object Pages {
  implicit val encodeComment: Encoder[Pages] = deriveEncoder[Pages]
  implicit val encodeComments: Encoder[List[Pages]] = Encoder.encodeList[Pages]
}

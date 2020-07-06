package models.pages

import io.circe.Encoder
import io.circe.generic.semiauto._
import utils.StringOps._

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

final case class PageRequest(
  url: String,
  title: String,
  content: String,
  publishedAt: Long,
  updatedAt: Long
)

object PageRequest {
  implicit val encodeComment: Encoder[PageRequest] = deriveEncoder[PageRequest]
  implicit val encodeComments: Encoder[List[PageRequest]] = Encoder.encodeList[PageRequest]

  def apply(url: String, title: String, content: String, publishedAt: Long, updatedAt: Long): PageRequest =
    new PageRequest(
      url,
      title,
      content.stripHtmlTags,
      publishedAt,
      updatedAt
    )
}

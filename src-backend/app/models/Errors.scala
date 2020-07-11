package models

import io.circe.Encoder
import io.circe.generic.semiauto._

final case class ErrorResponse(statusCode: Int, code: String, message: String)

object ErrorResponse {
  implicit val encodeErrorResponse: Encoder[ErrorResponse] = deriveEncoder[ErrorResponse]
}

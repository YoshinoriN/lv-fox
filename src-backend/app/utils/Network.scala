package utils

import play.api.mvc.Request

trait Network {

  def getIpAddress[A](request: Request[A]): String = {
    val xForwardedFor = request.headers.get("X-Forwarded-For").getOrElse(request.remoteAddress)
    xForwardedFor.split(",").head
  }

}

package actions

import config.Config
import play.api.mvc.{ActionRefiner, Request, Result, Results}
import play.api.http.MimeTypes
import play.api.Logging
import utils.Network

import scala.concurrent.{ExecutionContext, Future}

class PostAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] with Logging with Network {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    val clientIp = getIpAddress(request)
    request.headers.get("Authorization") match {
      case None =>
        logger.error(s"${request.uri} ${clientIp}: missing auth token")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiPostToken) {
          logger.info(s"${request.uri} ${clientIp}: authentication succeeded")
          Future(Right(request))
        } else {
          logger.error(s"${request.uri} ${clientIp}: authentication failed")
          Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
        }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

class SearchAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] with Logging with Network {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    val clientIp = getIpAddress(request)
    request.headers.get("Authorization") match {
      case None =>
        logger.error(s"${request.uri} ${clientIp}: missing auth token")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiSearchToken) {
          logger.info(s"${request.uri} ${clientIp}: authentication succeeded")
          Future(Right(request))
        } else {
          logger.error(s"${request.uri} ${clientIp}: authentication failed")
          Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
        }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

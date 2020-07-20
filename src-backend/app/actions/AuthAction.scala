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
        logger.error(s"${clientIp} - ${request.uri}: missing auth token")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiPostToken) {
          logger.info(s"${clientIp} - ${request.uri}: authentication succeeded")
          Future(Right(request))
        } else {
          logger.error(s"${clientIp} - ${request.uri}}: authentication failed")
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
        logger.error(s"${clientIp} - ${request.uri}: missing auth token")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiSearchToken) {
          logger.info(s"${clientIp} - ${request.uri}}: authentication succeeded")
          Future(Right(request))
        } else {
          logger.error(s"${clientIp} - ${request.uri}: authentication failed")
          Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
        }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

class PostIpFilterAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] with Logging with Network {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    if (Config.apiPostIpFilter == "0.0.0.0") {
      Future(Right(request))
    } else {
      val clientIp = getIpAddress(request)
      if (clientIp == Config.apiPostIpFilter) {
        Future(Right(request))
      } else {
        logger.error(s"${clientIp} - ${request.uri}: access denied")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

package actions

import config.Config
import play.api.mvc.{ActionRefiner, Request, Result, Results}
import play.api.http.MimeTypes
import play.api.Logging

import scala.concurrent.{ExecutionContext, Future}

class PostAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] with Logging {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    request.headers.get("Authorization") match {
      case None =>
        logger.error(s"missing auth token: ${request.remoteAddress}")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiPostToken) {
          logger.info(s"authentication succeeded: ${request.remoteAddress}")
          Future(Right(request))
        } else {
          logger.error(s"authentication failed: ${request.remoteAddress}")
          Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
        }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

class SearchAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] with Logging {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    request.headers.get("Authorization") match {
      case None =>
        logger.error(s"missing auth token: ${request.remoteAddress}")
        Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiSearchToken) {
          logger.info(s"authentication succeeded: ${request.remoteAddress}")
          Future(Right(request))
        } else {
          logger.error(s"authentication failed: ${request.remoteAddress}")
          Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
        }
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

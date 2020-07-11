package actions

import config.Config
import play.api.mvc.{ActionRefiner, Request, Result, Results}
import play.api.http.MimeTypes

import scala.concurrent.{ExecutionContext, Future}

class PostAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    request.headers.get("Authorization") match {
      case None => Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiPostToken) Future(Right(request))
        else Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

class SearchAuthAction(implicit exContext: ExecutionContext) extends ActionRefiner[Request, Request] {

  override protected def refine[A](request: Request[A]): Future[Either[Result, Request[A]]] = {

    request.headers.get("Authorization") match {
      case None => Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
      case Some(token) =>
        if (token.replaceAll("Bearer", "").trim == Config.apiSearchToken) Future(Right(request))
        else Future(Left(Results.Unauthorized.as(MimeTypes.JSON)))
    }

  }

  override protected def executionContext: ExecutionContext = exContext

}

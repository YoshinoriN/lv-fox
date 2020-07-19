package controllers

import actions.Actions
import javax.inject.Inject
import models.pages.{PageRequest, Pages}
import play.api.mvc._
import io.circe.syntax._
import services.PagesService
import io.circe.generic.auto._
import play.api.Logging
import play.api.libs.circe.Circe

class PageController @Inject()(controllerComponents: ControllerComponents, actions: Actions, pagesService: PagesService)
    extends AbstractController(controllerComponents)
    with Circe
    with Logging {

  def search(q: List[String]): Action[AnyContent] = actions.searchAuth { implicit request: Request[AnyContent] =>
    pagesService.validateQueryString(q) match {
      case Left(l) => {
        logger.error(s"${request.uri} ${request.remoteAddress}: ${l.asJson.toString.replaceAll("\n", "").replaceAll(" ", "")}")
        Results.Status(l.statusCode)(l.asJson).as(JSON)
      }
      case Right(_) => {
        logger.info(s"${request.remoteAddress}: ${q}")
        Ok(pagesService.find(q).asJson).as(JSON)
      }
    }
  }

  def upsert: Action[PageRequest] = Action(circe.json[PageRequest]).andThen(actions.postAuth) { implicit request =>
    val result = pagesService.upsert(Pages(request.body.url, request.body.title, request.body.content, request.body.publishedAt, request.body.updatedAt))
    logger.info(s"${request.uri} ${request.remoteAddress}: ${result.url}")
    Created(result.asJson).as(JSON)
  }
}

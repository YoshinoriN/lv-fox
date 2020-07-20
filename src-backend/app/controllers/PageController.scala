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
import utils.Network

class PageController @Inject()(controllerComponents: ControllerComponents, actions: Actions, pagesService: PagesService)
    extends AbstractController(controllerComponents)
    with Circe
    with Logging
    with Network {

  def search(q: List[String]): Action[AnyContent] = actions.searchAuth { implicit request: Request[AnyContent] =>
    val clientIp = getIpAddress(request)
    pagesService.validateQueryString(q) match {
      case Left(l) => {
        logger.error(s"${clientIp} - ${request.uri}: ${l.asJson.toString.replaceAll("\n", "").replaceAll(" ", "")}")
        Results.Status(l.statusCode)(l.asJson).as(JSON)
      }
      case Right(_) => {
        logger.info(s"${clientIp} - ${request.uri}: ${q}")
        Ok(pagesService.find(q).asJson).as(JSON)
      }
    }
  }

  def upsert: Action[PageRequest] = Action(circe.json[PageRequest]).andThen(actions.postAuth) { implicit request =>
    val clientIp = getIpAddress(request)
    val result = pagesService.upsert(Pages(request.body.url, request.body.title, request.body.content, request.body.publishedAt, request.body.updatedAt))
    logger.info(s"${clientIp} - ${request.uri}: ${result.url}")
    Created(result.asJson).as(JSON)
  }
}

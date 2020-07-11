package controllers

import actions.Actions
import javax.inject.Inject
import models.pages.{PageRequest, Pages}
import play.api.mvc._
import io.circe.syntax._
import services.PagesService
import io.circe.generic.auto._
import play.api.libs.circe.Circe

class PageController @Inject()(controllerComponents: ControllerComponents, actions: Actions, pagesService: PagesService)
    extends AbstractController(controllerComponents)
    with Circe {

  // TODO: CORS, DDos protection
  def search(q: List[String]): Action[AnyContent] = actions.searchAuth { implicit request: Request[AnyContent] =>
    pagesService.validateQueryString(q) match {
      case Left(l) => Results.Status(l.statusCode)(l.asJson).as(JSON)
      case Right(_) => Ok(pagesService.find(q).asJson).as(JSON)
    }
  }

  def upsert: Action[PageRequest] = Action(circe.json[PageRequest]).andThen(actions.postAuth) { implicit request =>
    val result = pagesService.upsert(Pages(request.body.url, request.body.title, request.body.content, request.body.publishedAt, request.body.updatedAt))
    Created(result.asJson).as(JSON)
  }
}

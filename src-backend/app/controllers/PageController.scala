package controllers

import javax.inject.Inject
import models.pages.PageRequest
import play.api.mvc._
import play.api.libs.circe
import services.PagesService
import io.circe.syntax._
import io.circe.generic.auto._
import play.api.libs.circe.Circe

class PageController @Inject()(controllerComponents: ControllerComponents, pagesService: PagesService)
    extends AbstractController(controllerComponents)
    with Circe {

  // TODO: CORS, Auth(with Action)
  def search(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"todo - get\"}").as(JSON)
  }

  // TODO: Auth(with Action), IpFilter(With Action), replace ignore Chars(with Action)
  def upsert: Action[PageRequest] = Action(circe.json[PageRequest]) { implicit request =>
    Ok("{\"status\":\"todo - post\"}").as(JSON)
  }
}

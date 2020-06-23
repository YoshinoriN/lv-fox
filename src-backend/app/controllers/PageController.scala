package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

class PageController @Inject()(controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) {

  def search() = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"todo - get\"}").as(JSON)
  }

  def upsert = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"todo - post\"}").as(JSON)
  }
}

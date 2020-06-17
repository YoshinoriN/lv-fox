package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class StatusController @Inject()(controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"operational\"}").as(JSON)
  }
}

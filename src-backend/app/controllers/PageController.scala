package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.PagesService

class PageController @Inject()(controllerComponents: ControllerComponents, pagesService: PagesService) extends AbstractController(controllerComponents) {

  // TODO: CORS, Auth(with Action)
  def search(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"todo - get\"}").as(JSON)
  }

  // TODO: Auth(with Action), IpFilter(With Action), replace ignore Chars(with Action)
  def upsert: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("{\"status\":\"todo - post\"}").as(JSON)
  }
}

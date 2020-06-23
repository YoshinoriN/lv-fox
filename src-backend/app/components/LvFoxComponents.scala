package components

import controllers.{PageController, StatusController}
import modules.ErrorHandler
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes

class LvFoxComponents(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context) with HttpFiltersComponents {

  private val errorHandler = new ErrorHandler
  private val statusController: StatusController = new StatusController(controllerComponents)
  private val pageController: PageController = new PageController(controllerComponents)

  override def router: Router = new Routes(
    errorHandler,
    statusController,
    pageController
  )

  override def httpFilters: Seq[EssentialFilter] = super.httpFilters
}

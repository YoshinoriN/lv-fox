package components

import controllers.{PageController, StatusController}
import models.pages.PagesRepositoryImpl
import modules.ErrorHandler
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes
import services.PagesService

class LvFoxComponents(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context) with HttpFiltersComponents {

  private val errorHandler = new ErrorHandler
  private val pagesService = new PagesService(new PagesRepositoryImpl)
  private val statusController: StatusController = new StatusController(controllerComponents)
  private val pageController: PageController = new PageController(controllerComponents, pagesService)

  override def router: Router = new Routes(
    errorHandler,
    statusController,
    pageController
  )

  override def httpFilters: Seq[EssentialFilter] = super.httpFilters
}

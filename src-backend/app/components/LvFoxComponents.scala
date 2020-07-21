package components

import actions.{Actions, PostAuthAction, PostIpFilterAction, SearchAuthAction}
import controllers.{PageController, StatusController}
import models.pages.PagesRepositoryImpl
import modules.ErrorHandler
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import play.filters.cors.{CORSConfig, CORSFilter}
import router.Routes
import services.PagesService

class LvFoxComponents(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context) with HttpFiltersComponents {

  lazy val errorHandler = new ErrorHandler
  lazy val postAuthAction = new PostAuthAction()
  lazy val postIpFilterAuthAction = new PostIpFilterAction()
  lazy val searchAuthAction = new SearchAuthAction()
  lazy val actions = new Actions(postAuthAction, postIpFilterAuthAction, searchAuthAction, defaultActionBuilder)
  lazy val pagesService = new PagesService(new PagesRepositoryImpl)
  lazy val statusController: StatusController = new StatusController(controllerComponents)
  lazy val pageController: PageController = new PageController(controllerComponents, actions, pagesService)

  override def router: Router = new Routes(
    errorHandler,
    statusController,
    pageController
  )

  lazy val corsConfig = CORSConfig.fromConfiguration(configuration)
  lazy val corsFilter: CORSFilter = new CORSFilter(corsConfig, errorHandler, Seq("/"))

  override def httpFilters: Seq[EssentialFilter] = Seq(corsFilter, securityHeadersFilter, allowedHostsFilter)
}

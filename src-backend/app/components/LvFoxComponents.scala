package components

import com.typesafe.config.{Config, ConfigFactory}
import controllers.StatusController
import modules.{DbMigration, ErrorHandler}
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes

class LvFoxComponents(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context) with HttpFiltersComponents {

  private val config: Config = ConfigFactory.load

  private val dbUrl: String = config.getString("db.ctx.dataSource.url")
  private val dbUser: String = config.getString("db.ctx.dataSource.user")
  private val dbPassword: String = config.getString("db.ctx.dataSource.password")
  val dbMigration = new DbMigration(dbUrl, dbUser, dbPassword)

  private val errorHandler = new ErrorHandler
  private val statusController: StatusController = new StatusController(controllerComponents)

  override def router: Router = new Routes(
    errorHandler,
    statusController
  )

  override def httpFilters: Seq[EssentialFilter] = super.httpFilters
}

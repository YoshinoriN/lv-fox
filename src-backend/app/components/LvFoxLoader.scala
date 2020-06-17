package components

import config.Config
import modules.DbMigration
import play.api.{Application, ApplicationLoader}
import play.api.ApplicationLoader.Context

class LvFoxLoader extends ApplicationLoader {

  private val dbMigration = new DbMigration(Config.dbUrl, Config.dbUser, Config.dbPassword)

  def load(context: Context): Application = {
    dbMigration.migrate()
    val lvFoxComponents: LvFoxComponents = new LvFoxComponents(context)
    lvFoxComponents.application
  }

}

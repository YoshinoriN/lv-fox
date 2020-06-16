package modules

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration

class DbMigration(dbUrl: String, dbUser: String, dbPassword: String) {

  private val flywayConfig: FluentConfiguration = Flyway.configure().dataSource(dbUrl, dbUser, dbPassword)
  private val flyway: Flyway = new Flyway(flywayConfig)

  /**
   * Do migration
   */
  def migrate(): Unit = flyway.migrate()

  /**
   * DROP all tables
   * NOTE: for development
   */
  def clean(): Unit = flyway.clean()

  /**
   * DROP all tables and re-create
   * NOTE: for development
   */
  def recrate(): Unit = {
    this.clean()
    this.migrate()
  }

}

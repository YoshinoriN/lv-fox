package config

import com.typesafe.config.{Config, ConfigFactory}

object Config {

  private val config: Config = ConfigFactory.load

  val dbUrl: String = config.getString("db.ctx.dataSource.url")
  val dbUser: String = config.getString("db.ctx.dataSource.user")
  val dbPassword: String = config.getString("db.ctx.dataSource.password")

  val apiSearchToken: String = config.getString("api.auth.searchToken")
  val apiPostToken: String = config.getString("api.auth.postToken")

}

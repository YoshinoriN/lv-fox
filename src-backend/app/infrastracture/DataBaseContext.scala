package infrastracture

import io.getquill.{MysqlJdbcContext, SnakeCase}

object DataBaseContext {

  lazy val ctx = new MysqlJdbcContext[SnakeCase](SnakeCase, "db.ctx")

}

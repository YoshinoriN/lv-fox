package infrastracture

import io.getquill.{MysqlJdbcContext, SnakeCase}

trait DataBaseContext {

  lazy val ctx = new MysqlJdbcContext[SnakeCase](SnakeCase, "db.ctx")

}

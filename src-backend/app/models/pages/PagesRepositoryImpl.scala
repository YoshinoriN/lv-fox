package models.pages

import scala.concurrent.Future
import infrastracture.DataBaseContext.ctx

// class PagesRepositoryImpl extends PagesRepository with DataBaseContext <- ??????
class PagesRepositoryImpl extends PagesRepository {

  import ctx._

  override def find(): Option[Pages] = ???

  override def upsert(pages: Pages): String = {
    run(query[Pages].insert(lift(pages)).onConflictIgnore(_.url).returningGenerated(_.url))
  }

}

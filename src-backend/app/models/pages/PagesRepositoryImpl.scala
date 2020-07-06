package models.pages

import scala.concurrent.Future
import infrastracture.DataBaseContext.ctx

// class PagesRepositoryImpl extends PagesRepository with DataBaseContext <- ??????
class PagesRepositoryImpl extends PagesRepository {

  import ctx._

  override def find(): Option[Pages] = ???

  override def upsert(pages: Pages): String = {
    run(
      query[Pages]
        .insert(lift(pages))
        .onConflictUpdate(
          (existingRow, newRow) => existingRow.title -> (newRow.title),
          (existingRow, newRow) => existingRow.content -> (newRow.content),
          (existingRow, newRow) => existingRow.publishedAt -> (newRow.publishedAt),
          (existingRow, newRow) => existingRow.updatedAt -> (newRow.updatedAt)
        )
    )
    pages.url // TODO: MariaDB can not use returning
  }

}

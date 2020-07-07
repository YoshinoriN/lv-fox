package models.pages

import infrastracture.DataBaseContext

class PagesRepositoryImpl extends PagesRepository with DataBaseContext {

  import ctx._

  override def find(): Option[Pages] = ???

  override def upsert(pages: Pages): Pages = {
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
    pages // TODO: MariaDB can not use returning
  }

}

package models.pages

import infrastracture.DataBaseContext.ctx

class PagesRepositoryImpl extends PagesRepository {

  import ctx._

  override def find(word: String): Seq[Pages] = {
    ctx.run(
      query[Pages]
        .filter(p => p.content like lift(s"%$word%"))
    )
  }

  override def upsert(pages: Pages): Pages = {
    ctx.run(
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

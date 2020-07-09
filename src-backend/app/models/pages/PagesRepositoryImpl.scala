package models.pages

import infrastracture.DataBaseContext.ctx

import scala.annotation.tailrec

class PagesRepositoryImpl extends PagesRepository {

  import ctx._

  override def find(words: List[String]): Seq[Pages] = {
    val q = queryBuilder(
      words.tail,
      1,
      query[Pages]
        .filter(p => p.content like lift(s"%${words.head}%"))
    )
    ctx.run(q)
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

  @tailrec
  private def queryBuilder(words: List[String], idx: Int = 0, acc: Quoted[Query[Pages]]): Quoted[Query[Pages]] = {
    words.lift(idx) match {
      case None => acc
      case Some(w) =>
        queryBuilder(
          words,
          idx + 1,
          acc
            .filter(p => p.content like lift(s"%$w%"))
        )
    }
  }

}

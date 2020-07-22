package models.pages

import infrastracture.DataBaseContext.ctx

import scala.annotation.tailrec

class PagesRepositoryImpl extends PagesRepository {

  import ctx._

  override def find(words: List[String]): Seq[Pages] = {
    val q = likeQueryBuilder(
      words.tail,
      0,
      query[Pages].filter(p => p.content like lift(s"%${words.head}%"))
    )
    ctx.run(q.sortBy(p => p.publishedAt)(Ord.descNullsLast).take(30))
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
    pages // NOTE: MariaDB can not use returning
  }

  @tailrec
  private def likeQueryBuilder(words: List[String], idx: Int = 0, acc: Quoted[Query[Pages]]): Quoted[Query[Pages]] = {
    words.lift(idx) match {
      case None => acc
      case Some(w) =>
        likeQueryBuilder(
          words,
          idx + 1,
          acc.filter(p => p.content like lift(s"%$w%"))
        )
    }
  }

}

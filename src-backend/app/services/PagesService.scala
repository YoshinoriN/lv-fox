package services

import models.pages.{PageRequest, PageResponse, Pages, PagesRepository}

import scala.annotation.tailrec

class PagesService(pagesRepository: PagesRepository) {

  def find(words: List[String]): Seq[PageResponse] = {
    pagesRepository
      .find(words.head)
      .map(
        page =>
          PageResponse(
            page.url,
            page.title,
            substrRecursively(page.content, createNewSeq(getAllWordsPosition(words, page.content))),
            page.publishedAt,
            page.updatedAt
          )
      )
  }

  def upsert(page: PageRequest): Pages = {
    pagesRepository.upsert(Pages(page.url, page.title, page.content, page.publishedAt, page.updatedAt))
  }

  private def getAllWordsPosition(words: List[String], sentence: String): Seq[(Int, Int)] =
    words.flatMap(_.r.findAllMatchIn(sentence).map(s => (s.start - 8, s.end + 8)).toSeq).sortBy(_._1)

  @tailrec
  private def createNewSeq(indexes: Seq[(Int, Int)], acc: Seq[(Int, Int)] = Nil): Seq[(Int, Int)] = {
    if (indexes.nonEmpty) {
      val i = indexes.head
      indexes.lift(1) match {
        case None =>
          if (acc == Nil) createNewSeq(indexes.tail, Seq(indexes.head))
          else acc
        case Some(n) =>
          if (n._1 > i._2) {
            createNewSeq(indexes.tail, Seq(i, n))
          } else {
            if (acc.size > 1 && acc.tail.head._1 == i._1) createNewSeq(indexes.tail, acc.dropRight(1) :+ (i._1, n._2))
            else createNewSeq(indexes.tail, acc :+ (i._1, n._2))
          }
      }
    } else {
      acc
    }
  }

  @tailrec
  private def substrRecursively(sentence: String, indexes: Seq[(Int, Int)], current: Int = 0, acc: String = ""): String = {
    if (indexes.size > current) {
      val i = indexes(current)
      substrRecursively(
        sentence,
        indexes,
        current + 1,
        acc + sentence.slice(i._1, i._2) + "...    "
      )
    } else {
      acc
    }
  }

}

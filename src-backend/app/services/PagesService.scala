package services

import models.pages.{PageRequest, PageResponse, Pages, PagesRepository}

import scala.annotation.tailrec

class PagesService(pagesRepository: PagesRepository) {

  def find(words: List[String]): Seq[PageResponse] = {
    pagesRepository
      .find(words)
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
  private def createNewSeq(idxes: Seq[(Int, Int)], acc: Seq[(Int, Int)] = Nil): Seq[(Int, Int)] = {
    if (idxes.nonEmpty) {
      val i = idxes.head
      idxes.lift(1) match {
        case None =>
          if (acc == Nil) createNewSeq(idxes.tail, Seq(idxes.head))
          else acc
        case Some(n) =>
          if (n._1 > i._2) {
            createNewSeq(idxes.tail, Seq(i, n))
          } else {
            if (acc.size > 1 && acc.tail.head._1 == i._1) createNewSeq(idxes.tail, acc.dropRight(1) :+ (i._1, n._2))
            else createNewSeq(idxes.tail, acc :+ (i._1, n._2))
          }
      }
    } else {
      acc
    }
  }

  @tailrec
  private def substrRecursively(sentence: String, idxes: Seq[(Int, Int)], current: Int = 0, acc: String = ""): String = {
    if (idxes.size > current) {
      val i = idxes(current)
      substrRecursively(
        sentence,
        idxes,
        current + 1,
        acc + sentence.slice(i._1, i._2) + "...    "
      )
    } else {
      acc
    }
  }

}

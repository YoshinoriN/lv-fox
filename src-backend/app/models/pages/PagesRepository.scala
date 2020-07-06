package models.pages

import scala.concurrent.Future

trait PagesRepository {

  // TODO: Argument
  def find(): Option[Pages]

  // return URL
  def upsert(page: Pages): String

}

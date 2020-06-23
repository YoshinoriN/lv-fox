package models.pages

import scala.concurrent.Future

trait PagesRepository {

  // TODO: Argument
  def find(): Future[Option[Pages]]

  def insert(page: Pages): Future[Option[Pages]]

  def update(page: Pages): Future[Option[Pages]]

}

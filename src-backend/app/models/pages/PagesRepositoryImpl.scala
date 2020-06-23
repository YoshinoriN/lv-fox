package models.pages
import scala.concurrent.Future

class PagesRepositoryImpl extends PagesRepository {

  override def find(): Future[Option[Pages]] = ???

  override def insert(pages: Pages): Future[Option[Pages]] = ???

  override def update(pages: Pages): Future[Option[Pages]] = ???

}

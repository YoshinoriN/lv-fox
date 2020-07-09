package models.pages

trait PagesRepository {

  def find(words: List[String]): Seq[Pages]

  def upsert(page: Pages): Pages

}

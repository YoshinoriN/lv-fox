package models.pages

trait PagesRepository {

  def find(word: String): Seq[Pages]

  def upsert(page: Pages): Pages

}

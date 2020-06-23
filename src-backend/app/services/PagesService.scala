package services

import models.pages.{Pages, PagesRepository}

import scala.concurrent.Future

class PagesService(pagesRepository: PagesRepository) {

  // TODO
  def find: Future[Option[Pages]] = pagesRepository.find()

  // TODO
  def insert(page: Pages): Future[Option[Pages]] = pagesRepository.insert(page)

  // TODO
  def update(page: Pages): Future[Option[Pages]] = pagesRepository.update(page)

}

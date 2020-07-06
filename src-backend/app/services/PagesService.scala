package services

import models.pages.{PageRequest, Pages, PagesRepository}

class PagesService(pagesRepository: PagesRepository) {

  // TODO
  def find: Option[Pages] = pagesRepository.find()

  def upsert(page: PageRequest): String = {
    pagesRepository.upsert(Pages(page.url, page.title, page.content, page.publishedAt, page.updatedAt))
  }

}

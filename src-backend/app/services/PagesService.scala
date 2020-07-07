package services

import models.pages.{PageRequest, PageResponse, Pages, PagesRepository}

class PagesService(pagesRepository: PagesRepository) {

  def find(word: String): Seq[PageResponse] = {
    pagesRepository.find(word).map(x => PageResponse(x.url, x.title, x.content, x.publishedAt, x.updatedAt))
  }

  def upsert(page: PageRequest): Pages = {
    pagesRepository.upsert(Pages(page.url, page.title, page.content, page.publishedAt, page.updatedAt))
  }

}

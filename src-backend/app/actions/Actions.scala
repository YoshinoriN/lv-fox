package actions

import play.api.mvc.{ActionBuilder, AnyContent, DefaultActionBuilder, Request}

class Actions(postAuthAction: PostAuthAction, searchAuthAction: SearchAuthAction, defaultActionBuilder: DefaultActionBuilder) {

  def postAuth: ActionBuilder[Request, AnyContent] = defaultActionBuilder.andThen(postAuthAction)

  def searchAuth: ActionBuilder[Request, AnyContent] = defaultActionBuilder.andThen(searchAuthAction)

}

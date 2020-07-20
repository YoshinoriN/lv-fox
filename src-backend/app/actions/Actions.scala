package actions

import play.api.mvc.{ActionBuilder, AnyContent, DefaultActionBuilder, Request}

class Actions(
  postAuthAction: PostAuthAction,
  postIpFilterAction: PostIpFilterAction,
  searchAuthAction: SearchAuthAction,
  defaultActionBuilder: DefaultActionBuilder
) {

  def postAuth: ActionBuilder[Request, AnyContent] = defaultActionBuilder.andThen(postAuthAction).andThen(postIpFilterAction)

  def searchAuth: ActionBuilder[Request, AnyContent] = defaultActionBuilder.andThen(searchAuthAction)

}

package components

import play.api.{Application, ApplicationLoader}
import play.api.ApplicationLoader.Context

class LvFoxLoader extends ApplicationLoader {

  def load(context: Context): Application = {
    new LvFoxComponents(context).application
  }

}

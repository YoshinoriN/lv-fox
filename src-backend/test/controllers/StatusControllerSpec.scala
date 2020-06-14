package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

// testOnly controllers.StatusControllerSpec
class StatusControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "StatusController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new StatusController(stubControllerComponents())
      val result = controller.index().apply(FakeRequest(GET, "/"))

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")
      contentAsString(result) must include("operational")
    }
  }
}

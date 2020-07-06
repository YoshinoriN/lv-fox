package utils

import org.scalatestplus.play._

// testOnly utils.StringOpsSpec
class StringOpsSpec extends PlaySpec {

  import utils.StringOps._

  "StringOps - stripHtmlTags" should {

    "replace all html tags" in {
      val x = "<html>aiu<p>eo</p><script>kakiku</script>keko<a>sashisu</a>seso<br><code></code></html>owari".stripHtmlTags
      x mustBe "aiueokakikukekosashisusesoowari"
    }

  }

}

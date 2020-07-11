package utils

object StringOps {

  private val ignoreCharsRegex = "[!\"#$%&'()-^\\@[;:],./\\=~|`{+*}<>?_、。，．・：；？！゛゜´｀¨＾￣＿]"

  implicit class StringOps(val s: String) {

    def stripHtmlTags: String = s.replaceAll("""<(\"[^\"]*\"|'[^']*'|[^'\">])*>""", "")

    def hasIgnoreChars: Boolean = ignoreCharsRegex.r.findFirstMatchIn(s) match {
      case None => false
      case Some(_) => true
    }

    def filterIgnoreChars: String = s.replaceAll(ignoreCharsRegex, "")

  }
}

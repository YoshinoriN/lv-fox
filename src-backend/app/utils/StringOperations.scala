package utils

object StringOps {
  implicit class StringOps(val s: String) {
    def stripHtmlTags: String = s.replaceAll("""<(\"[^\"]*\"|'[^']*'|[^'\">])*>""", "")
  }
}

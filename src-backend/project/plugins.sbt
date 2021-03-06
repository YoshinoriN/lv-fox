import sbt.util

logLevel := util.Level.Warn

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.2")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.1")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")

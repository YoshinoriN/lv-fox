name := """lv-fox"""
organization := "net.yoshinorin"
version := "0.0.1"
scalaVersion := "2.13.2"

scalacOptions ++= Seq(
  "-Yrangepos",
  "-Ywarn-unused",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-encoding",
  "UTF-8"
)

val circeVersion = "0.13.0"
lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe" % "config" % "1.4.0",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "com.dripower" %% "play-circe" % "2812.0",
      "io.getquill" %% "quill-jdbc" % "3.5.1",
      "org.flywaydb" % "flyway-core" % "6.4.1",
      "org.mariadb.jdbc" % "mariadb-java-client" % "2.6.0",
      "org.slf4j" % "slf4j-api" % "1.7.29",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      guice
    )
  )
  .enablePlugins(PlayScala)

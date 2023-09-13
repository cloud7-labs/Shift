val AkkaVersion = "2.8.3"
val AlpakkaVersion = "6.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "Shift",
    organization := "app.cloud7",
    scalaVersion := "2.12.17",
    maxErrors := 3,
    startYear := Some(2023),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-file" % AlpakkaVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-csv" % AlpakkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "ch.qos.logback" % "logback-classic" % "1.4.7",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-Xfatal-warnings",
      "-Xlint:_,-missing-interpolator",
      "-Yno-adapted-args",
      "-Ywarn-unused-import",
      "-Xfuture"
    ),
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x                   => (assembly / assemblyMergeStrategy).value(x)
    },
    semanticdbEnabled := true,
    sbtPlugin := true
  )
  .settings(publishSettings *)

val publishSettings = Seq(
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/cloud7-labs/Shift"),
      "https://github.com/cloud7-labs/Shift.git"
    )
  ),
  homepage := Some(url("https://github.com/cloud7-labs/Shift")),
  developers := List(
    Developer("crotodev",
              "Christian Rotondo",
              "chris.rotondo6@gmail.com",
              url("https://github.com/crotodev")
    )
  ),
  publishTo := Some(
    if (isSnapshot.value)
      "snapshots".at(
        "https://s01.oss.sonatype.org/content/repositories/snapshots"
      )
    else
      "releases".at(
        "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
      )
  ),
  publishMavenStyle := true,
  versionScheme := Some("early-semver"),
  licenses += (("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.txt"))),
  publishTo := sonatypePublishToBundle.value,
  sonatypeCredentialHost := "s01.oss.sonatype.org",
  sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
)

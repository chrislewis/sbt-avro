import sbt._
import Keys._

object build extends Build {
    val sbtAvro = Project(
        id = "sbt-avro",
        base = file("."),
        settings = Defaults.defaultSettings ++ Seq[Project.Setting[_]](
            organization := "com.cavorite",
            version := "0.3.2-meetup3",
            sbtPlugin := true,
            libraryDependencies ++= Seq(
                    "org.apache.avro" % "avro" % "1.7.2",
                    "org.apache.avro" % "avro-compiler" % "1.7.2"
            ),
            scalaVersion := "2.10.2",
            scalacOptions in Compile ++= Seq("-deprecation"),
            crossScalaVersions := Seq("2.10.2"),
            description := "Sbt plugin for compiling Avro sources",

            credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),

            publishTo := {
              val nexus = "http://git.dev.meetup.com:8081/nexus/content/repositories/"
              if(isSnapshot.value)
                Some("Meetup Snapshots" at nexus + "snapshots")
              else
                Some("Meetup Releases" at nexus + "releases")
            },

            publishMavenStyle := true
        )
    )
}

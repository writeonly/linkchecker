import sbt.inConfig
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}
import scoverage.ScoverageKeys.coverageEnabled

resolvers += Resolver.sonatypeRepo("snapshots")

val ScalaFixScalacOptions = Seq(
  "-Ywarn-adapted-args", // for NoAutoTupling
  "-Ywarn-unused", // for RemoveUnused
)

val ScalaFixScalacOptionsOff = Seq(
  "-Xfatal-warnings",   // it should be disabled for scalafix
)

val mainClassString = "pl.writeonly.linkchecker.scala.impl.LinkCheckerApp"
val mainClassSome = Some(mainClassString)

scalaVersion := "2.12.10"
scapegoatVersion in ThisBuild := "1.3.8"
scalacOptions ++= scalacOptionsFor(scalaVersion.value)
val ScalaPropsVersion = "0.5.5"
val SloggingVersion = "0.6.1"
val ScalazVersion = "7.2.28"

val SharedSettings = Seq(
  scalaVersion := "2.12.10",
  scalacOptions ++= scalacOptionsFor(scalaVersion.value),
  scalacOptions ++= ScalaFixScalacOptions,
  scalacOptions --= ScalaFixScalacOptionsOff,
  mainClass in (Compile, run) := Some(mainClassString),
  testFrameworks += new TestFramework("utest.runner.Framework"),
  resolvers += Opts.resolver.sonatypeReleases,
  libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.5" % "test",
  libraryDependencies ++= Seq(
    "com.github.scalaprops" %%% "scalaprops" % ScalaPropsVersion % "test,it",
    "com.github.scalaprops" %%% "scalaprops-scalazlaws" % ScalaPropsVersion % "test,it",
  ),
  libraryDependencies += "org.scalaz" %%% "scalaz-core" % ScalazVersion,
  libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % ScalazVersion,
  libraryDependencies += "org.scalaz" %%% "scalaz-effect" % ScalazVersion,
  libraryDependencies += "org.scalaz" %% "scalaz-ioeffect" % "2.10.1",
  libraryDependencies ++= Seq(
    "biz.enef" %%% "slogging" % SloggingVersion,
  ),
  libraryDependencies += "com.lihaoyi" %%% "fastparse" % "1.0.0",
  addCompilerPlugin(scalafixSemanticdb),
  wartremoverErrors ++= Warts.unsafe,
  scapegoatVersion := "1.3.8",
  trapExit := false,
)

val jvmSettings = Seq(
  coverageEnabled := true,
  mainClass in Compile := mainClassSome,
  libraryDependencies ++= Seq(
    "biz.enef" %% "slogging-slf4j" % SloggingVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",
  ),
)

lazy val linkchecker = crossProject(JVMPlatform)
  .withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .settings(SharedSettings)
  .jvmSettings(jvmSettings)
  // IntegrationTest
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(
    inConfig(IntegrationTest)(scalafixConfigSettings(IntegrationTest)),
    inConfig(IntegrationTest)(ScalafmtPlugin.scalafmtConfigSettings),
    unmanagedSourceDirectories in IntegrationTest ++= CrossType.Full.sharedSrcDir(baseDirectory.value, "it").toSeq
  )
  // PropsTest
  .settings(scalapropsCoreSettings)

lazy val hydeJVM = linkchecker.jvm



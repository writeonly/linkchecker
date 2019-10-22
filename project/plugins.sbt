addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.4")

// cross project
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.5.0")
addSbtPlugin("org.portable-scala" % "sbt-scala-native-crossproject" % "0.5.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.24")
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.3.9")
// coverage
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")

// static code analysis read-write
//// Scalafix - Refactoring and linting tool for Scala
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.7")
//// Scalafmt - Code formatter for Scala
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")
//// sbt-scalariform - sbt plugin adding support for source code formatting using Scalariform
//addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// static code analysis read-only
//// Scalastyle - Scala style checker
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
//// WartRemover: a flexible scala linter
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.3")
//// Scapegoat - Scala compiler plugin for static code analysis
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.9")
//// linter - Static Analysis Compiler Plugin for Scala
addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17")
//
//// stats - An sbt plugin for source code statistics
addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.7")
//// scala-clippy - Good advice for Scala compiler errors
addSbtPlugin("com.softwaremill.clippy" % "plugin-sbt" % "0.6.1")
//// sbt-cpd Copy & Paste Detector plugin using PMD for sbt.
addSbtPlugin("com.github.sbt" % "sbt-cpd" % "2.0.0")

//// scalaprops -  property based testing library for Scala
addSbtPlugin("com.github.scalaprops" % "sbt-scalaprops" % "0.2.6")

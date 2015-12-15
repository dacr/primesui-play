name := """primesui-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "fr.janalyse"        %% "primes"               % "1.2.2-SNAPSHOT",
  "fr.janalyse"        %% "unittools"            % "0.2.7-SNAPSHOT",
  "fr.janalyse"        %% "janalyse-jmx"         % "0.7.1",
  "org.squeryl"        %% "squeryl"              % "0.9.5-7",
  "com.mchange"         % "c3p0"                 % "0.9.2.1",
  "net.sf.ehcache"      % "ehcache-core"         % "2.6.11",
  "javax.transaction"   % "jta"                  % "1.1", // required for ehcache
  "mysql"               % "mysql-connector-java" % "5.1.36",
  "ch.qos.logback"      % "logback-classic"      % "1.1.3",
  "org.codehaus.janino" % "janino"               % "2.7.8" // Allow logback config file conditionals
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "sonatype repository" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "JAnalyse Repository" at "http://www.janalyse.fr/repository/"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

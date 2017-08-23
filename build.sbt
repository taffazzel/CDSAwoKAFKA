name := "TwitterStream"
version := "1.6.3"
scalaVersion := "2.10.4"

assemblyMergeStrategy in assembly := {
    case PathList("com",   "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com",   "squareup", xs @ _*) => MergeStrategy.last
    case PathList("com",   "sun", xs @ _*) => MergeStrategy.last
    case PathList("com",   "thoughtworks", xs @ _*) => MergeStrategy.last
    case PathList("commons-beanutils", xs @ _*) => MergeStrategy.last
    case PathList("commons-cli", xs @ _*) => MergeStrategy.last
    case PathList("commons-collections", xs @ _*) => MergeStrategy.last
    case PathList("commons-io", xs @ _*) => MergeStrategy.last
    case PathList("io",    "netty", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("javax", "xml", xs @ _*) => MergeStrategy.last
    case PathList("org",   "apache", xs @ _*) => MergeStrategy.last
    case PathList("org",   "codehaus", xs @ _*) => MergeStrategy.last
    case PathList("org",   "fusesource", xs @ _*) => MergeStrategy.last
    case PathList("org",   "mortbay", xs @ _*) => MergeStrategy.last
    case PathList("org",   "tukaani", xs @ _*) => MergeStrategy.last
    case PathList("xerces", xs @ _*) => MergeStrategy.last
    case PathList("xmlenc", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
    case "log4j.properties" => MergeStrategy.last
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first

}

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.3",
  "org.apache.spark" %% "spark-streaming" % "1.6.3",
  "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.6.3",
  "com.google.code.gson" % "gson" % "2.7",
  "org.twitter4j" % "twitter4j-core" % "3.0.3",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3",
  "org.elasticsearch" % "elasticsearch-spark_2.10" % "2.3.2"
  "org.json" % "json" % "20090211"
)

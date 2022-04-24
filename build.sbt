name := "delta-playground"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-avro" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-catalyst" % "3.2.1"
libraryDependencies += "io.delta" %% "delta-core" % "1.2.0"
libraryDependencies += "io.delta" %% "delta-sharing-spark" % "0.4.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "3.3.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.3.0"

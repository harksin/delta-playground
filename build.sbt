name := "delta-playground"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"
libraryDependencies += "io.delta" %% "delta-core" % "0.7.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "3.3.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.3.0"

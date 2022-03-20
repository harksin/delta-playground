import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types._


object main_stream_s3 extends App {

  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)
  rootLogger.setLevel(Level.INFO)


  val spark = SparkSession.builder()
    .appName("Delta stream s3")
    .master("local[*]")
    .config("spark.executor.instances", "1")
    .config("spark.executor.memory", "4g")
    .config("spark.sql.debug.maxToStringFields", "1000000")
    .getOrCreate()

  spark.sparkContext.hadoopConfiguration.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.endpoint", "http://127.0.0.1:9000");
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", "FAKE_ACCESS_KEY");
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", "FAKE_SECRET_KEY");
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.path.style.access", "true")

  val schema = new StructType()
    .add("city", StringType, true)
    .add("numHuman", StringType, true)
    .add("lvl", LongType, true)
    .add("ts", LongType, true)


  //Reading a directory
  val s = spark.readStream.schema(schema).format("json").load("s3a://epsi/topics/raw/*/*.json")

//repartition(col("city"))
  val x = s.writeStream
    .partitionBy("city")
    .format("delta")
    .outputMode("append")
    .option("checkpointLocation", "s3a://epsi/byCity/_checkpoints/tracking-stream")
    .start("s3a://epsi/byCity")

  x.awaitTermination()

  spark.stop()


}


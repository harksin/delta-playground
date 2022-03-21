import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._


object main_local extends App {

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
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", "minio_access_key");
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", "minio_secret_key");
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.path.style.access", "true")


  val data = Seq(("James ","Rose","Smith","36636","M",3000),
    ("Michael","Rose","","40288","M",4000),
    ("Robert","Mary","Williams","42114","M",4000),
    ("Maria","Anne","Jones","39192","F",4000),
    ("Jen","Mary","Brown","1234","F",-1)
  )

  val columns = Seq("firstname","middlename","lastname","dob","gender","salary")
  import spark.sqlContext.implicits._
  val df = data.toDF(columns:_*)

  df.show()
  df.printSchema()

  df.write
    .mode(SaveMode.Overwrite)
    .parquet("./people.parquet")


  val parqDF = spark.read.parquet("./people.parquet")
  parqDF.createOrReplaceTempView("ParquetTable")

  spark.sql("select * from ParquetTable where salary >= 4000").explain()
  val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")

  parkSQL.show()
  parkSQL.printSchema()

  df.write
    .mode(SaveMode.Overwrite)
    .partitionBy("gender","salary")
    .parquet("./people2.parquet")

  spark.stop()



}


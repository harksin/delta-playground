import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}


object main_minio extends App {

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
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.endpoint", "http://127.0.0.1:9000")
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", sys.env.get("MINIO_ACCES_KEY").get)
  spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", sys.env.get("MINIO_SECRET_KEY").get)
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
    .parquet("s3a://lake-0/people.parquet")


  val parqDF = spark.read.parquet("s3a://lake-0/people.parquet")
  parqDF.createOrReplaceTempView("ParquetTable")

  spark.sql("select * from ParquetTable where salary >= 4000").explain()
  val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")

  parkSQL.show()
  parkSQL.printSchema()

  df.write
    .mode(SaveMode.Overwrite)
    .partitionBy("gender","salary")
    .parquet("s3a://lake-0/people2.parquet")

  spark.stop()



}


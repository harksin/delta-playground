import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object main_local_json extends App {

  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)
  rootLogger.setLevel(Level.INFO)

  val spark = SparkSession
    .builder()
    .appName("Delta stream s3")
    .master("local[*]")
    .config("spark.executor.instances", "1")
    .config("spark.executor.memory", "4g")
    .config("spark.sql.debug.maxToStringFields", "1000000")
    .getOrCreate()

  /** read the file ./data-sample/VilleMTP_MTP_Presidentielle_20220410.json
    */
  // TODO

  /** compute the result for the city (candidate, %, total votes)
    */
  // TODO

  /** extract the date from the first column
    */
  // TODO

  /** write one file as output (json)
    */
  // TODO

  spark.stop()

}

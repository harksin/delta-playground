import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object main_local_select extends App {

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

  /** read the file ./data-sample/VilleMTP_MTP_Presidentielle_20220410.parquet
    */
  // TODO

  /** select these column : "bureau" "inscrits" "votants" "nuls" "blancs"
    * "candidat" "nb_voix"
    */
  // TODO

  /** filter out all candidates with less than 5% votes
    */

  /** write the output file
    */
  // TODO

  spark.stop()

}

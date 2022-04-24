import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object main_local_join extends App {

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

  /** Dataframe 1 : select these column : "bureau" "inscrits" "votants"
    * "candidat" "nb_voix"
    *
    * filter out all candidates with < 5% votes
    */
  // TODO

  /** Dataframe 2 : select these column : "bureau" "votants" "inscrits"
    * "candidat" "nb_voix"
    *
    * filter out all candidates with >= votes
    */
  // TODO

  /** join Dataframe_1 & Dataframe_2
    */

  /** write the output file
    */
  // TODO

  spark.stop()

}

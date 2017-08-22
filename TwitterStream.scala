import org.elasticsearch.spark.rdd.EsSpark
import org.elasticsearch.spark._
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.dstream._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import twitter4j.Status
import org.json.JSONObject

object TwitterStream {

        def main(args: Array[String]): Unit = {

                val conf = new SparkConf().setMaster("local[*]").setAppName("Twitter-Stream")
                //val sc = new SparkContext(conf)
                //sc.setLogLevel("WARN")
                val index_name = "twitter"
                val type_name = "Spark"
                val ssc = new StreamingContext(conf, Seconds(6))
                System.setProperty("twitter4j.oauth.consumerKey", "pIhJP9BBX7QDkMrieXWOUcEv5")
                System.setProperty("twitter4j.oauth.consumerSecret", "YGUIPrmTGCyoO40wKCv3PboBpdwkgmTxw1iytjHfwlmjQfOPvu")
                System.setProperty("twitter4j.oauth.accessToken", "1627712238-HlOCH9yPSyCrOsATa7QRCCvTrZrKfmESw754cny")
                System.setProperty("twitter4j.oauth.accessTokenSecret", "W6M7KUHy9RHcWXxNb8OUSPJWhJk6H3XheHea2g05YLb3B")
                val streams= TwitterUtils.createStream(ssc, None, Array(type_name))
                val statuses = streams.map( status => {

                        var lonlat: Array[Double] = Array()
                        val hashmap = new java.util.HashMap[String, Object]()
                        hashmap.put("user_name", status.getUser().getName())
                        hashmap.put("user_lang", status.getUser().getLang())
                        hashmap.put("text", status.getText())

                        if(status.getGeoLocation() != null) {
                                lonlat = Array(status.getGeoLocation().getLongitude(),status.getGeoLocation().getLatitude())
                        }
                        hashmap.put("location", lonlat)
                         (new org.json.JSONObject(hashmap).toString())
                }).foreachRDD(jsonRDD => {
                        EsSpark.saveJsonToEs(jsonRDD, index_name+"/" + type_name)
                        }
                )
                ssc.start()
                ssc.awaitTermination()


        }
}

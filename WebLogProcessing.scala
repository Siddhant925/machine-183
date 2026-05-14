import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().appName("WebLog").master("local[*]").getOrCreate()
val sc = spark.sparkContext
val data = sc.textFile("weblog.csv")
val ipcount = data.map(x=>(x.split("\\s+")(0),1)).reduceByKey(_ + _)
println("IP Address Count")
val statusCount = data.map(x=>(x.split("\\s+").last,1)).reduceByKey(_ + _)
println("Status Code Count")
statusCount.collect().foreach(println)

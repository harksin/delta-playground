# setup 

create a folder `local-data` in this repository.

start the minio server :
```
docker-compose up -d
```

access to minio [there](http://127.0.0.1:9001) 

login : minio
password : minio123

create a service account in minio 
and export these 2 key in environement variables
```
MINIO_ACCES_KEY="<your_AK>"
MINIO_SECRET_KEY="<your_SK>"
```

create a bucket `lake-0`

# requierements
java 11

# Step 1 minimal spark job

simple ! 

```
sbt run
> 1
```

this [minimal job](src/main/scala/main_local.scala) will :
* create a sample of data,
* write it on your disk, 
* re read these data
* repartition these data
* write them on your disk
 
## formats

read & compute files in various formats

json :
todo [here](src/main/scala/main_local_json.scala)

avro :
todo [here](src/main/scala/main_local_avro.scala)

parquet : 
todo [here](src/main/scala/main_local_parquet.scala)

csv : 
todo [here](src/main/scala/main_local_csv.scala)

Any opinion ?

## repartition

from the format of you choice, repartition the data on the 'bureau' value.
todo [here](src/main/scala/main_local_repartition.scala)
## select & filter
from the format of you choice, select column and filter.
todo [here](src/main/scala/main_local_select.scala)

## transform
TBD
## join !
from the format of you choice, select column and filter and join
todo [here](src/main/scala/main_local_join.scala)

is there an issue ?

# Step 2 read and write from minio

## read and write
simple ! 

```
sbt run
> 10
```

=> check that your data are well in minio
## small files
take a look at the `people-raw.parquet` folder.
how many files is there ? why ?
Is their size acceptable?
can we do better ? 

# Step 3 repartition avro and convert to parquet data offloaded by kafka connect.
Now a more real word use case...
first, take the power grid simulator you built in the previous sessions.
configure the kafka stack with a schema registry, kafka connect and your minio (in the same network).
and launch your simulator!

## 3.1 kafka connect s3/avro : 
Your mission, based on the kafka connect demo !
Any trouble => A.M.A

## 3.2 reactive trigger.
Here we go ... spark again
we would like to "watch" the s3 directory and process the new data each time a new file is added, process it and update accordingly the "repartitioned data"
who can we do that ? 

repartition rules : "powerplant/yyyy/mm/dd/"

=> 30 min

# Step 4 time machine & acid layer
Wait, what? our spark job had a bug and deleted all the data from Minio instead of updating it...
as our Minio bucket is not historized, we are screwed...

Post mortem time : how can we be sure that this will not happen again ?

=> whiteboard sessions

# Step 5 data sharing

Dear data engineer team, we would like to allow our `oldest` customer to access `this awesome dataset` from our datalake, can you make this possible for yesterday ?
thanks.

=> 🔥 session
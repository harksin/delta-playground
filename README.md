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
## small files
take a look at the `people-raw.parquet` folder.
how many files is there ? why ?
Is their size acceptable?
can we do better ? 
# Step 3 repartition avro and convert to parquet data offloaded by kafka connect.

## 3.1 kafka connect s3/avro

## 3.2 reactive trigger.
we would like to "watch" the s3 directory and process the new data each time a new file is added, process it and update accordingly the "repartitioned data"

# Step 4 time machine & acid layer

# Step 5 data sharing


#!/bin/bash
set -a
source env_qa.txt
set +a
TAG=$1
docker pull    172.16.0.253:5000/report_server:$TAG
docker pull   172.16.0.253:5000/report_client:$TAG


#docker network create \
#>   --driver overlay \
#>   mybridge


VOLUME="--mount type=bind,source=$LOG_PATH,destination=/logs "
UPLOAD_VOLUME="--mount type=bind,source=$UPLOAD_PATH,destination=/uploads "
#TIMEZONE_FILE="--mount type=bind,source=$LOCALTIME_PATH,destination=/etc/localtime "

docker service create  -d  --network mybridge   --replicas 1 --env-file=./env_qa.txt $VOLUME   $UPLOAD_VOLUME --name crs_server  -p 4230:4230   172.16.0.253:5000/report_server:$TAG
docker service create  -d --network mybridge    --replicas 1    --env-file=./env_prod.txt   $VOLUME   -p 80:80  --name crs_client     172.16.0.253:5000/report_client:$TAG

#docker service update --image   172.16.0.253:5000/report_server:$TAG report
#docker service update --image   172.16.0.253:5000/report_client:$TAG report
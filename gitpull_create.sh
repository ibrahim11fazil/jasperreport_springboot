#!/bin/bash
set -a
source env_report_qa.txt
set +a
TAG=$1
docker pull    172.16.0.253:5000/treport:$TAG



#docker network create \
#>   --driver overlay \
#>   mybridge


VOLUME="--mount type=bind,source=$LOG_PATH,destination=/logs "
UPLOAD_VOLUME="--mount type=bind,source=$UPLOAD_PATH,destination=/uploads "
#TIMEZONE_FILE="--mount type=bind,source=$LOCALTIME_PATH,destination=/etc/localtime "

docker service create  -d  --network mybridge   --replicas 1 --env-file=./env_qa.txt $VOLUME   $UPLOAD_VOLUME --name treport  -p 9085:9085   172.16.0.253:5000/treport:$TAG


#docker service update --image   172.16.0.253:5000/treport:$TAG report

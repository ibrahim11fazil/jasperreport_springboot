#!/bin/bash
source ~/.bash_profile
 echo "Current version is "   $(git describe --abbrev=0)
 branch=$(git branch | grep \* | cut -d ' ' -f2)
if [ "$1" != "" ]
    then
     echo "New version is "   $1
     mvn versions:set -DnewVersion=$1
     git tag -a $1 -m "version $1"
     git push --tags
     mvn versions:commit
     git add -A
     git commit -m "build version $1"
     git push origin $branch
     echo "Version updated to "   $(git describe --abbrev=0)
     git status
     [ $? -eq 0 ] || exit 1
     mvn clean install
     [ $? -eq 0 ] || exit 1
     TAG=$(git describe --abbrev=0)
     if [ "$1" != "" ]
     then
      TAG=$(git describe --abbrev=0)
      docker build .  -t 172.16.0.253:5000/report_server:$TAG
      docker push 172.16.0.253:5000/report_server:$TAG
      echo "Done building"
      echo $TAG
     fi

    else
     echo "Enter the version"
     echo "Enter the git branch to push to server , use current and master"
     echo "Current version is "   $(git describe --abbrev=0)
     echo "sample ./gittag.sh version "

     exit 1
fi





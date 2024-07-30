#!/bin/bash

ROOT=$(dirname -- $0)
PARENT=$ROOT/..

echo "Creating shadow jar ..."
pushd $PARENT > /dev/null && ./gradlew shadowJar || popd > /dev/null

JAR_DIR=$PARENT/jar
if [ -d $JAR_DIR ]; then
    echo -e "\nRemoving ..."
    rm -rv $JAR_DIR
fi

echo -e "\nCreating jar folder ..."
mkdir $JAR_DIR

echo -e "Moving jar file to created folder ..."
mv $PARENT/build/libs/japan-tickets-1.0-SNAPSHOT-all.jar $JAR_DIR/japan-tickets.jar

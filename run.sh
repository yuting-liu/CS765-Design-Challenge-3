#!/bin/bash

if [ "$1" == "" ]; then
	echo "usage: source run.sh <path_to_csv>"
	exit
fi

export FILE_PATH=$1

export JAR_PATH=.:./Assets/core.jar

# remove all class file in Analysis
rm -rf ./Analysis/*.class

echo "Compilation of analysis: start"

javac -cp ./Analysis ./Analysis/*.java
echo ""


echo "Get analyzed result: start"
java -classpath ./Analysis Data ${FILE_PATH}
echo ""


# visualization
rm -rf ./Visualization/*.class


echo "Compilation of visualization: start"
javac -cp .:${JAR_PATH} ./Visualization/*.java
echo ""

echo "Get visualization: start"
java -cp ./Visualization:${JAR_PATH} Processing
echo ""

# clean up
rm -rf ./Analysis/*.class
rm -rf ./Visualization/*.class

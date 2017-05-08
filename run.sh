#!/bin/bash

export FILE_PATH=~/Documents/CS765/DesignChallenge3/Data/20x10.csv

export JAR_PATH=.:/Applications/Processing.app/Contents/Java/core/library/core.jar

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
cd ./Visualization
javac -cp ${JAR_PATH} *.java
echo ""

echo "Get visualization: start"
java -cp ${JAR_PATH} Processing
echo ""

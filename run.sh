#!/bin/bash

export FILE_PATH=~/Documents/CS765/DesignChallenge3/Data/20x10.csv
export JAR_PATH=.:/Applications/Processing.app/Contents/Java/core/library/core.jar

# remove all class file in Analysis
cd ./Analysis
rm -rf *.class

javac *.java

echo "Compilation of analysis: done"

java Data ${FILE_PATH}

echo "Get analyzed result: done"

# visualization
cd ../Visualization

rm -rf *.class

javac -cp ${JAR_PATH} *.java

echo "Compilation of visualization: done"
java -cp ${JAR_PATH} Processing

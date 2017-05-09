#!/bin/bash

if [ "$1" == "" ]; then
	echo "usage: source run.sh <path_to_csv>"
	return
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
javac -cp .:/home/shixuan/Documents/CS765/processing-3.3.3/core/library/core.jar ./Visualization/*.java
echo ""

echo "Get visualization: start"
java -cp ./Visualization:/home/shixuan/Documents/CS765/processing-3.3.3/core/library/core.jar Processing
echo ""

# clean up
#rm -rf ./Analysis/*.class
#rm -rf ./Visualization/*.class

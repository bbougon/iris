#! /bin/bash

mvn clean package

find target -name "*dependencies*.jar" -print0 | xargs -0 java -jar
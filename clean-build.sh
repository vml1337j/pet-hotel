#!/bin/bash
cd ./pet-hotel-app && ./gradlew clean bootJar && \
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted && cd ..

cd ./pet-service && ./gradlew clean bootJar && \
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted && cd ..

cd ./registration-service && ./gradlew clean bootJar && \
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted && cd ..

cd ./validation-service && ./gradlew clean bootJar && \
java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted

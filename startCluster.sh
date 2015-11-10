#!/bin/bash

mvn clean && mvn package && export CLASSPATH=./target/tp-pod-jar-with-dependencies.jar && java com.hazelcast.console.ConsoleApp


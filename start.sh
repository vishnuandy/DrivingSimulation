#!/bin/sh

set -e

cd "$(dirname "$0")"

mvn -q clean test

exec mvn -q exec:java \
  -Dexec.mainClass="org.example.Application"
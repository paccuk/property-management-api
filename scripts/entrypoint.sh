#!/bin/sh
set -e

echo "Starting application in $APP_ENV mode..."

exec java -jar /app/app.jar

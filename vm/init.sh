#!/bin/sh

#envsubst < //template.yml > /config/application.yml

#cat /config/application.yml

exec java -jar /app/parking-web-1.0.0.jar --spring.config.location=/config/application.yml
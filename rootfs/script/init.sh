#!/bin/sh

#echo "Generating application.yml from template..."
#envsubst < /templates/template.yml > /config/application.yml

#echo "Generated application.yml:"
#cat /config/application.yml
echo "run parking-web-1.0.0.jar..."
exec java -jar /app/parking-web-1.0.0.jar
#--spring.config.location=/config/application.yml

echo "run parking-web-1.0.0.jar succeed."
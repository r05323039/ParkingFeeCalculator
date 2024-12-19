#!/bin/bash

docker-compose down -v --remove-orphans

docker-compose up -d --force-recreate

echo "container restarted successfully."

#!/bin/bash

    PROFILE=${1:-sql}
        echo "-------------------------------------------------"
        echo "  STARTING DBA1LibrarySystem (Profile: $PROFILE)"
        echo "-------------------------------------------------"

        if ! docker ps | grep -q "postgres"; then
            echo "Starting dockers containers"
            docker compose up -d
            sleep 5
        fi

        trap "kill 0" SIGINT SIGTERM EXIT



        echo "Starting Backend..."
        (cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE) &

        echo "Starting Frontend..."
        (cd frontend && npm install && npm run dev --) &

        echo "-------------------------------------------------"
        echo "  Application Running! ($PROFILE)"
        echo "  Navigate to: http://localhost:5173" 
        echo "-------------------------------------------------"

        wait

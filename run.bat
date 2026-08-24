@echo off
SET PROFILE=%1
IF "%PROFILE%"=="" SET PROFILE=sql


ECHO "-------------------------------------------------"
ECHO "  STARTING DBA1LibrarySystem (Profile: %PROFILE%)"
ECHO "-------------------------------------------------"

ECHO "Starting dockers containers"
docker compose up -d



ECHO "Starting Backend..."
start "DBA1 Backend (%PROFILE%)" cmd /c "cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE"


ECHO "Starting Frontend..."
start "DBA1 Frontend" cmd "cd frontend && npm install && npm run dev"

ECHO "-------------------------------------------------"
ECHO "  Application Running! ($PROFILE)"
ECHO "  Navigate to: http://localhost:5173" 
ECHO "-------------------------------------------------"

PAUSE

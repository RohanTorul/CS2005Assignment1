@echo off
REM Get the directory of this script
set SCRIPT_DIR=%~dp0

REM Change to the script's directory
cd /d "%SCRIPT_DIR%" || exit /b 1

REM Compile all Java files in this directory
javac *.java

REM Move up one directory
cd ..

REM Run the Java program with classpath set to current directory
java -cp . Assignment1.Driver

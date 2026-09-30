@echo off
setlocal

set "JDK=%USERPROFILE%\.jdks\temurin-11.0.32.1\bin"

echo Compilando fbcmd4j...
echo.

if not exist "%JDK%\javac.exe" (
    echo ERROR: No se encontro JDK 11.
    pause
    exit /b 1
)

if exist build rmdir /s /q build
if exist dist rmdir /s /q dist

mkdir build
mkdir dist

"%JDK%\javac.exe" -d build src\*.java

if errorlevel 1 (
    echo.
    echo ERROR durante la compilacion.
    pause
    exit /b 1
)

"%JDK%\jar.exe" --create --file dist\fbcmd4j.jar --main-class Main -C build .

if errorlevel 1 (
    echo.
    echo ERROR al crear el archivo JAR.
    pause
    exit /b 1
)

echo.
echo ==========================================
echo FAT JAR creado correctamente
echo ==========================================
echo.
echo Archivo:
echo dist\fbcmd4j.jar
echo.
echo Para ejecutarlo:
echo java -jar dist\fbcmd4j.jar
echo.

pause
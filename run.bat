@echo off
REM Compilar el proyecto
javac -d bin ./src/utils/EnvLoader.java ./src/*.java ./src/dao/*.java ./src/view/*.java ./src/model/*.java

REM Verificar si la compilación fue exitosa
if %errorlevel% neq 0 (
    echo Error durante la compilación. Verifica el código fuente.
    pause
    exit /b %errorlevel%
)

REM Ejecutar la aplicación
java -cp "bin" App
if %errorlevel% neq 0 (
    echo Error al ejecutar la aplicación. Verifique la configuración.
    pause
    exit /b %errorlevel%
)
pause
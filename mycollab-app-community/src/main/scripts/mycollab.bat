@echo off

if "%OS%" == "Windows_NT" setlocal
rem ---------------------------------------------------------------------------
rem Start/Stop Script for the MyCollab Server
rem
rem Environment Variable Prerequisites
rem
rem   MYCOLLAB_OPTS   (Optional) Java runtime options used when the "start",
rem                    "stop" command is executed.
rem                   Include here and not in JAVA_OPTS all options, that should
rem                   only be used by MyCollab itself, not by the stop process,
rem                   the version command etc.
rem                   Examples are heap size, GC logging, JMX ports etc.
rem
rem   JAVA_HOME       Must point at your Java Development Kit installation.
rem                   Required to run the with the "debug" argument.
rem ---------------------------------------------------------------------------

set _RUNJAVA=java
set MYCOLLAB_OPTS=-noverify -server -Xms394m -Xmx768m -XX:NewSize=128m -XX:+DisableExplicitGC -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC

rem Suppress Terminate batch job on CTRL+C
if not ""%1"" == ""run"" goto mainEntry
if "%TEMP%" == "" goto mainEntry
if exist "%TEMP%\%~nx0.run" goto mainEntry
echo Y>"%TEMP%\%~nx0.run"
if not exist "%TEMP%\%~nx0.run" goto mainEntry
echo Y>"%TEMP%\%~nx0.Y"
call "%~f0" %* <"%TEMP%\%~nx0.Y"
rem Use provided errorlevel
set RETVAL=%ERRORLEVEL%
del /Q "%TEMP%\%~nx0.Y" >NUL 2>&1
exit /B %RETVAL%
:mainEntry
del /Q "%TEMP%\%~nx0.run" >NUL 2>&1

rem Guess MYCOLLAB_HOME if not defined
set "CURRENT_DIR=%cd%"
if not "%MYCOLLAB_HOME%" == "" goto gotHome
cd ..
set "MYCOLLAB_HOME=%cd%"
cd "%CURRENT_DIR%"
:gotHome

if exist "%MYCOLLAB_HOME%\bin\mycollab.bat" goto okHome
echo The MYCOLLAB_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

rem ----- Execute The Requested Command ---------------------------------------

echo Using MYCOLLAB_HOME:   "%MYCOLLAB_HOME%"

set _EXECJAVA=%_RUNJAVA%


if ""%1"" == ""--start"" goto doStart
if ""%1"" == ""--stop"" goto doStop

echo Usage:  mycollab ( commands ... )
echo commands:
echo   start             Start MyCollab in a separate window
echo   stop              Stop MyCollab
goto end

:doStart
shift
if not "%OS%" == "Windows_NT" goto noTitle
if "%TITLE%" == "" set TITLE=MyCollab
set _EXECJAVA=start "%TITLE%" %_RUNJAVA% %MYCOLLAB_OPTS%
goto gotTitle
:noTitle
set _EXECJAVA=start %_RUNJAVA%
:gotTitle
shift
goto execCmd

:doStop
shift
goto execCmd

:execCmd

rem Execute Java with the applicable properties
cd ..
%_EXECJAVA% -jar executor.jar %* %MYCOLLAB_OPTS% -Dserver.port=%MYCOLLAB_PORT%
goto end

:end

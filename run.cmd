@echo off
chcp 65001 >nul
setlocal
pushd "%~dp0"
if not exist "target\design-patterns-lab.jar" (
  echo 请先运行 mvn clean verify 构建项目。
  popd
  exit /b 1
)
java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -jar "target\design-patterns-lab.jar" %*
set "LAB_EXIT=%ERRORLEVEL%"
popd
exit /b %LAB_EXIT%

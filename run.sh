#!/usr/bin/env sh
cd "$(dirname "$0")" || exit 1
if [ ! -f target/design-patterns-lab.jar ]; then
  echo '请先运行 mvn clean verify 构建项目。' >&2
  exit 1
fi
exec java -Dfile.encoding=UTF-8 -jar target/design-patterns-lab.jar "$@"

#!/usr/bin/env bash
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

mkdir -p out/production/TD3 out/test/TD3

echo "=== Compiling main sources ==="
javac -d out/production/TD3 $(find src -name "*.java")

echo "=== Compiling test sources ==="
javac -cp "out/production/TD3:lib/junit-platform-console-standalone-1.14.0.jar" \
      -d out/test/TD3 \
      $(find test -name "*.java")

echo "=== Running JUnit 5 tests ==="
java -jar lib/junit-platform-console-standalone-1.14.0.jar execute \
     --class-path "out/production/TD3:out/test/TD3" \
     --scan-class-path \
     --details=tree \
     "$@"

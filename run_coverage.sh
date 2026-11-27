#!/usr/bin/env bash
set -uo pipefail

JACOCO_VERSION="0.8.16"
JUNIT_JAR="lib/junit-platform-console-standalone-1.14.0.jar"

MIN_LINE_COVERAGE="0"
MIN_BRANCH_COVERAGE="0"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

TOOLS_DIR=".tools"
JACOCO_HOME="$TOOLS_DIR/jacoco-$JACOCO_VERSION"
OUT_DIR="out/coverage"
CLASSES_DIR="$OUT_DIR/classes"
TEST_CLASSES_DIR="$OUT_DIR/test-classes"
EXEC_FILE="$OUT_DIR/jacoco.exec"
REPORT_DIR="$OUT_DIR/report"

install_jacoco() {
    [ -f "$JACOCO_HOME/lib/jacococli.jar" ] && return
    echo "=== Downloading JaCoCo $JACOCO_VERSION ==="
    mkdir -p "$JACOCO_HOME"
    curl -fsSL -o "$TOOLS_DIR/jacoco.zip" \
        "https://github.com/jacoco/jacoco/releases/download/v$JACOCO_VERSION/jacoco-$JACOCO_VERSION.zip"
    unzip -q -o "$TOOLS_DIR/jacoco.zip" -d "$JACOCO_HOME"
    rm "$TOOLS_DIR/jacoco.zip"
}

install_jacoco

rm -rf "$OUT_DIR"
mkdir -p "$CLASSES_DIR" "$TEST_CLASSES_DIR" "$REPORT_DIR"

echo "=== Compiling main sources ==="
javac --release 21 -d "$CLASSES_DIR" $(find src -name "*.java") || exit 1

echo "=== Compiling test sources ==="
javac --release 21 -cp "$CLASSES_DIR:$JUNIT_JAR" -d "$TEST_CLASSES_DIR" $(find test -name "*.java") || exit 1

echo "=== Running JUnit 5 tests with JaCoCo ==="
java -javaagent:"$JACOCO_HOME/lib/jacocoagent.jar=destfile=$EXEC_FILE" \
     -jar "$JUNIT_JAR" execute \
     --class-path "$CLASSES_DIR:$TEST_CLASSES_DIR" \
     --scan-class-path \
     --details=summary \
     --disable-banner
tests_status=$?

echo "=== Generating coverage report ==="
java -jar "$JACOCO_HOME/lib/jacococli.jar" report "$EXEC_FILE" \
     --classfiles "$CLASSES_DIR" \
     --sourcefiles src \
     --name "Village du Pere Noel" \
     --html "$REPORT_DIR/html" \
     --xml "$REPORT_DIR/jacoco.xml" \
     --csv "$REPORT_DIR/jacoco.csv" \
     --quiet || exit 1

read -r line_pct branch_pct < <(awk -F, 'NR > 1 { bm += $6; bc += $7; lm += $8; lc += $9 }
    END {
        lt = lm + lc; bt = bm + bc
        printf "%d %d\n", (lt ? 100 * lc / lt : 100), (bt ? 100 * bc / bt : 100)
    }' "$REPORT_DIR/jacoco.csv")

echo "Line coverage:   $line_pct% (minimum $MIN_LINE_COVERAGE%)"
echo "Branch coverage: $branch_pct% (minimum $MIN_BRANCH_COVERAGE%)"
echo "HTML report:     $REPORT_DIR/html/index.html"

status=0
if [ "$tests_status" -ne 0 ]; then
    echo "=== Tests failed ===" >&2
    status=1
fi
if [ "$line_pct" -lt "$MIN_LINE_COVERAGE" ] || [ "$branch_pct" -lt "$MIN_BRANCH_COVERAGE" ]; then
    echo "=== Coverage below minimum ===" >&2
    status=1
fi
[ "$status" -eq 0 ] && echo "=== Coverage passed ==="
exit "$status"

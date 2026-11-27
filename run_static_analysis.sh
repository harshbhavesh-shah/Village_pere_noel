#!/usr/bin/env bash
set -uo pipefail

PMD_VERSION="7.29.0"
SPOTBUGS_VERSION="4.9.8"

PMD_MIN_PRIORITY="2"
SPOTBUGS_MAX_RANK="9"
SPOTBUGS_THRESHOLD="medium"
SPOTBUGS_EFFORT="max"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

TOOLS_DIR=".tools"
PMD_HOME="$TOOLS_DIR/pmd-bin-$PMD_VERSION"
SPOTBUGS_HOME="$TOOLS_DIR/spotbugs-$SPOTBUGS_VERSION"
CLASSES_DIR="out/static-analysis/classes"

WHAT="${1:-all}"

install_pmd() {
    [ -x "$PMD_HOME/bin/pmd" ] && return
    echo "=== Downloading PMD $PMD_VERSION ==="
    mkdir -p "$TOOLS_DIR"
    curl -fsSL -o "$TOOLS_DIR/pmd.zip" \
        "https://github.com/pmd/pmd/releases/download/pmd_releases%2F$PMD_VERSION/pmd-dist-$PMD_VERSION-bin.zip"
    unzip -q -o "$TOOLS_DIR/pmd.zip" -d "$TOOLS_DIR"
    rm "$TOOLS_DIR/pmd.zip"
}

install_spotbugs() {
    [ -f "$SPOTBUGS_HOME/lib/spotbugs.jar" ] && return
    echo "=== Downloading SpotBugs $SPOTBUGS_VERSION ==="
    mkdir -p "$TOOLS_DIR"
    curl -fsSL -o "$TOOLS_DIR/spotbugs.tgz" \
        "https://github.com/spotbugs/spotbugs/releases/download/$SPOTBUGS_VERSION/spotbugs-$SPOTBUGS_VERSION.tgz"
    tar -xzf "$TOOLS_DIR/spotbugs.tgz" -C "$TOOLS_DIR"
    rm "$TOOLS_DIR/spotbugs.tgz"
}

run_pmd() {
    install_pmd
    echo "=== PMD (minimum priority $PMD_MIN_PRIORITY) ==="
    "$PMD_HOME/bin/pmd" check \
        --dir src \
        --rulesets config/pmd-ruleset.xml \
        --minimum-priority "$PMD_MIN_PRIORITY" \
        --format text \
        --no-cache \
        --no-progress
}

run_spotbugs() {
    install_spotbugs
    echo "=== Compiling main sources for SpotBugs ==="
    rm -rf "$CLASSES_DIR"
    mkdir -p "$CLASSES_DIR"
    javac --release 21 -d "$CLASSES_DIR" $(find src -name "*.java") || return 1
    echo "=== SpotBugs (max rank $SPOTBUGS_MAX_RANK, threshold $SPOTBUGS_THRESHOLD, effort $SPOTBUGS_EFFORT) ==="
    java -jar "$SPOTBUGS_HOME/lib/spotbugs.jar" -textui \
        -effort:"$SPOTBUGS_EFFORT" \
        -"$SPOTBUGS_THRESHOLD" \
        -maxRank "$SPOTBUGS_MAX_RANK" \
        -exclude config/spotbugs-exclude.xml \
        -exitcode \
        "$CLASSES_DIR"
}

status=0
case "$WHAT" in
    pmd)      run_pmd      || status=1 ;;
    spotbugs) run_spotbugs || status=1 ;;
    all)
        run_pmd      || status=1
        run_spotbugs || status=1
        ;;
    *) echo "Usage: $0 [pmd|spotbugs]" >&2; exit 2 ;;
esac

if [ "$status" -eq 0 ]; then
    echo "=== Static analysis passed ==="
else
    echo "=== Static analysis found problems ===" >&2
fi
exit "$status"

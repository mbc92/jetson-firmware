#!/bin/bash

set -e

BOARD="$1"

if [ -z "$BOARD" ]; then
    echo "Usage: source setup-env.sh <jetson-nano|rpi|nxp>"
    return 1
fi

# Map friendly names → Yocto MACHINE
case "$BOARD" in
    jetson-nano)
        MACHINE="jetson-nano-devkit"
        BUILD_DIR="jetson-nano-build"
        ;;
    rpi)
        MACHINE="raspberrypi4-64"
        BUILD_DIR="rpi-build"
        ;;
    nxp)
        MACHINE="imx8mp-evk"
        BUILD_DIR="nxp-build"
        ;;
    *)
        echo "Unknown board: $BOARD"
        return 1
        ;;
esac

echo "=== Yocto Setup ==="
echo "Board: $BOARD"
echo "Machine: $MACHINE"
echo "Build dir: $BUILD_DIR"

# Initialize Yocto environment
source poky/oe-init-build-env "$BUILD_DIR"

# Inject MACHINE safely (only if not already set)
if ! grep -q "^MACHINE" conf/local.conf; then
    echo "MACHINE ?= \"$MACHINE\"" >> conf/local.conf
fi

echo "✔ Environment ready for $BOARD"
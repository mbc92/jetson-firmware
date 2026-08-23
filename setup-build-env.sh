#!/bin/bash

BOARD="$1"
DISTRO="$2"

if [ -z "$BOARD" ]; then
    echo "Usage: source setup-env.sh <jetson-nano|rpi|nxp> <distro>"
    return 1
fi

if [ -z "$DISTRO" ]; then
    echo "Usage: source setup-env.sh <compute> <distro>"
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
echo "Board:  $BOARD"
echo "Machine: $MACHINE"
echo "Distro: $DISTRO"
echo "Build dir: $BUILD_DIR"

# Initialize Yocto environment
source poky/oe-init-build-env "$BUILD_DIR" || {
    echo "ERROR: Failed to initialize Yocto environment"
    return 1
}

# Set MACHINE if not already configured
if ! grep -qE '^[[:space:]]*MACHINE[[:space:]]*=' conf/local.conf; then
    echo "MACHINE ?= \"$MACHINE\"" >> conf/local.conf
fi

# Always set DISTRO
if grep -qE '^[[:space:]]*DISTRO[[:space:]]*=' conf/local.conf; then
    sed -i -E "s|^[[:space:]]*DISTRO[[:space:]]*=.*|DISTRO = \"$DISTRO\"|" conf/local.conf
else
    echo "DISTRO = \"$DISTRO\"" >> conf/local.conf
fi


echo "✔ Environment ready"
echo "  Board:   $BOARD"
echo "  MACHINE: $MACHINE"
echo "  DISTRO:  $DISTRO"

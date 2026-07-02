SUMMARY = "Core packages for the product"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = " \
    networkmanager \
    networkmanager-config \
    openssh\
    openssh-sftp-server \
    kernel-modules \
    linux-firmware-ath9k \
    vim \
    htop \
"
SUMMARY = "Disable eth0 at boot"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://disable-eth.service"

inherit systemd

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/disable-eth.service \
        ${D}${systemd_system_unitdir}/
}

SYSTEMD_SERVICE:${PN} = "disable-eth.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

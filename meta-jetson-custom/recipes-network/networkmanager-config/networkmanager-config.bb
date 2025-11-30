SUMMARY = "NetworkManager configuration to manage all interfaces"
LICENSE = "CLOSED"

SRC_URI = "file://NetworkManager.conf"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}

FILES_${PN} += "${sysconfdir}/NetworkManager/NetworkManager.conf"
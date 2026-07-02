SUMMARY = "NetworkManager configuration"
LICENSE = "CLOSED"

SRC_URI = "file://NetworkManager.conf"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0644 ${WORKDIR}/NetworkManager.conf \
        ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}

FILES:${PN} += "${sysconfdir}/NetworkManager/NetworkManager.conf"
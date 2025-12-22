SUMMARY = "NetworkManager config"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = " \
    file://default-wifi.nmconnection \
    file://NetworkManager.conf \
"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/default-wifi.nmconnection \
        ${D}${sysconfdir}/NetworkManager/system-connections/

    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0644 ${WORKDIR}/NetworkManager.conf \
        ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}
FILES:${PN} += " \
    ${sysconfdir}/NetworkManager/system-connections/default-wifi.nmconnection \
    ${sysconfdir}/NetworkManager/NetworkManager.conf \
"
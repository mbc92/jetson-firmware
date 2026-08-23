SUMMARY = "NetworkManager WiFi configuration"
LICENSE = "CLOSED"

SRC_URI = " \
    file://default-wifi.nmconnection \
    file://10-unmanaged-eth0.conf \
    file://NetworkManager.conf \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/default-wifi.nmconnection \
        ${D}${sysconfdir}/NetworkManager/system-connections/

    install -d ${D}${sysconfdir}/NetworkManager/conf.d
    install -m 0644 ${WORKDIR}/10-unmanaged-eth0.conf \
        ${D}${sysconfdir}/NetworkManager/conf.d/

    install -m 0644 ${WORKDIR}/NetworkManager.conf \
        ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}

FILES:${PN} += " \
    ${sysconfdir}/NetworkManager/system-connections/default-wifi.nmconnection \
    ${sysconfdir}/NetworkManager/conf.d/10-unmanaged-eth0.conf \
"
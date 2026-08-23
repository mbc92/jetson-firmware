SUMMARY = "NetworkManager WiFi configuration"
LICENSE = "CLOSED"

SRC_URI = " \
    file://default-wifi.nmconnection \
    file://NetworkManager.conf \
    file://disable-eth0.service \
"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "disable-eth0.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
RDEPENDS:${PN} += "iproute2"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/default-wifi.nmconnection \
        ${D}${sysconfdir}/NetworkManager/system-connections/

    install -m 0644 ${WORKDIR}/NetworkManager.conf \
        ${D}${sysconfdir}/NetworkManager/NetworkManager.conf

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/disable-eth0.service \
        ${D}${systemd_system_unitdir}/disable-eth0.service
}

FILES:${PN} += " \
    ${sysconfdir}/NetworkManager/system-connections/default-wifi.nmconnection \
    ${systemd_system_unitdir}/disable-eth0.service \
"
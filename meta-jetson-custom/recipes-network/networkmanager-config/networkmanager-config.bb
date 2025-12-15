SUMMARY = "Default WiFi connection"
LICENSE = "MIT"
SRC_URI = "file://default-wifi.nmconnection"

do_install() {
    install -d ${D}/etc/NetworkManager/system-connections
    install -m 600 ${WORKDIR}/default-wifi.nmconnection \
        ${D}/etc/NetworkManager/system-connections/
}

FILES:${PN} += "/etc/NetworkManager/system-connections/default-wifi.nmconnection"
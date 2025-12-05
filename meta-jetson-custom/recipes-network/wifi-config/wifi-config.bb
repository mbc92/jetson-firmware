DESCRIPTION = "WiFi configuration"
LICENSE = "CLOSED"

SRC_URI = "file://wpa_supplicant.conf"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/wpa_supplicant
    install -m 600 ${WORKDIR}/wpa_supplicant.conf ${D}/etc/wpa_supplicant/wpa_supplicant.conf
}

FILES_${PN} += "/etc/wpa_supplicant/wpa_supplicant.conf"
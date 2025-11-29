require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "\
    networkmanager \
    openssh-sftp-server \
    openssh \
"

DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"

SYSTEMD_SERVICE_${PN} += "sshd.service"
SYSTEMD_AUTO_ENABLE += "NetworkManager.service"
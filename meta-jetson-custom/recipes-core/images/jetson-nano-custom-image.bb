require recipes-core/images/core-image-base.bb

DISTRO_FEATURES:append = " systemd network-manager"
VIRTUAL-RUNTIME_init_manager = "systemd"

IMAGE_INSTALL += "\
    networkmanager \
    openssh \
    openssh-sftp-server \
    vim \
    networkmanager-config \
    kernel-modules \
    linux-firmware-ath9k\ 
    htop \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"
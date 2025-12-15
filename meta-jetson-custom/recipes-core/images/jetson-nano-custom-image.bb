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
    ros-core \ 
    ros-base \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"

ROOTFS_POSTPROCESS_COMMAND += "update_extlinux_conf;"

update_extlinux_conf() {
    sed -i 's|root=[^ ]*|root=/dev/mmcblk0p1|' ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf
}
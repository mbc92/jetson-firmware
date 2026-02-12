require recipes-core/images/core-image-base.bb

DISTRO_FEATURES:append = "systemd networkmanager wifi "
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_wpa-supplicant = ""

IMAGE_INSTALL += "\
    networkmanager \
    openssh \
    openssh-sftp-server \
    vim \
    networkmanager-config \
    kernel-modules \
    linux-firmware-ath9k \
    htop \
    ros-core \
    ros-base \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"

ROOTFS_POSTPROCESS_COMMAND += "update_extlinux_conf;"

update_extlinux_conf() {
    if ! grep -q "isolcpus=3" ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf; then
        sed -i '/^ *APPEND / s|$| isolcpus=3 nohz_full=3 rcu_nocbs=3 irqaffinity=0-2|' \
            ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf
    fi
}
require recipes-core/images/core-image-base.bb

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
    gstreamer1.0 \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-tegra \
    gstreamer1.0-rtsp-server \
    v4l-utils \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"

ROOTFS_POSTPROCESS_COMMAND += "update_extlinux_conf;"

update_extlinux_conf() {

    # Force root device
    sed -i 's|root=[^ ]*|root=/dev/mmcblk0p1|' \
        ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf

    # Add CPU isolation parameters if not present
    if ! grep -q "isolcpus=3" ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf; then
        sed -i '/^ *APPEND / s|$| isolcpus=3 nohz_full=3 rcu_nocbs=3 irqaffinity=0-2|' \
            ${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf
    fi
}
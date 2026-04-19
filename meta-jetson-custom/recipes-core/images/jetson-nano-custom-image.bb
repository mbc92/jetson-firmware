require recipes-core/images/core-image-base.bb

VIRTUAL-RUNTIME_wpa-supplicant = ""

TOOLCHAIN_TARGET_TASK += " \
    gstreamer1.0-dev \
    gstreamer1.0-plugins-base-dev \
"

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
    rt-tests \
    tensorrt-core \
    tensorrt-plugins \
    tensorrt-trtexec \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"

ROOTFS_POSTPROCESS_COMMAND += "update_extlinux_conf;"

update_extlinux_conf() {

    CONF="${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf"

    # Force root device (replace first occurrence only)
    sed -i '0,/root=[^ ]*/s||root=/dev/mmcblk0p1|' "$CONF"

    # Add CPU isolation parameters if not present
    if ! grep -q "isolcpus=" "$CONF"; then
        sed -i '/APPEND / s|$| isolcpus=managed_irq,3 nohz_full=3 rcu_nocbs=3 irqaffinity=0-2|' "$CONF"
    fi
}
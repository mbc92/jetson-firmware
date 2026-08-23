require recipes-core/images/core-image-base.bb

IMAGE_INSTALL += "\
    packagegroup-core \
    packagegroup-multimedia \
    packagegroup-tensorrt \
"

SYSTEMD_AUTO_ENABLE += "sshd.service NetworkManager.service"

ROOTFS_POSTPROCESS_COMMAND += "update_extlinux_conf;"

update_extlinux_conf() {

    CONF="${IMAGE_ROOTFS}/boot/extlinux/extlinux.conf"

    # Force root device (replace first occurrence only)
    sed -i '0,/root=[^ ]*/s||root=/dev/mmcblk0p1|' "$CONF"
    sed -i '/APPEND / s|$| console=ttyTHS1,115200n8|' "$CONF"

    # Add CPU isolation parameters if not present
    if ! grep -q "isolcpus=" "$CONF"; then
        sed -i '/APPEND / s|$| isolcpus=managed_irq,3 nohz_full=3 rcu_nocbs=3 irqaffinity=0-2|' "$CONF"
    fi
}
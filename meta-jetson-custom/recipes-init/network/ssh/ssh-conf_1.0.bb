DESCRIPTION = "Custom SSH and networking config for Jetson Nano"
LICENSE = "MIT"
PR = "r0"

# This recipe doesn’t build anything; it just modifies the rootfs
inherit allarch

# Add to rootfs
do_install() {
    #nothing to do here
}

# Postprocess rootfs
ROOTFS_POSTPROCESS_COMMAND += "enable_root_ssh;set_root_password;open_ssh_port;"

enable_root_ssh() {
    echo "PermitRootLogin yes" >> ${IMAGE_ROOTFS}/etc/ssh/sshd_config
}

set_root_password() {
    echo "root:merkelbeek" | chroot ${IMAGE_ROOTFS} /usr/sbin/chpasswd
}

open_ssh_port() {
    chroot ${IMAGE_ROOTFS} iptables -I INPUT -p tcp --dport 22 -j ACCEPT
}

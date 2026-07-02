SUMMARY = "Multimedia packagegroup"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-good \
    gstreamer1.0-rtsp-server \
    gstreamer1.0-plugins-tegra \
    v4l-utils \
"
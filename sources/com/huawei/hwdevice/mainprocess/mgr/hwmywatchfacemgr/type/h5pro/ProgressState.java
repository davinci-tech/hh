package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

/* loaded from: classes5.dex */
public enum ProgressState {
    INFO_GOT(0),
    DOWNLOAD(1),
    DOWNLOADING(2),
    DOWNLOAD_PER(3),
    INSTALLING(4),
    INSTALL_PER(5),
    INSTALLED(8),
    APPLIED(6);

    private final int mValue;

    ProgressState(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}

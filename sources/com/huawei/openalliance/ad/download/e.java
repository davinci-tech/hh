package com.huawei.openalliance.ad.download;

/* loaded from: classes5.dex */
public enum e {
    IDLE(0),
    WAITING(1),
    DOWNLOADING(2),
    DOWNLOADED(3),
    FAILED(4),
    INSTALLING(5),
    INSTALLED(6),
    WAITING_FOR_WIFI(7);

    private int i;

    public int a() {
        return this.i;
    }

    public static e a(int i) {
        for (e eVar : values()) {
            if (eVar.a() == i) {
                return eVar;
            }
        }
        return IDLE;
    }

    e(int i) {
        this.i = i;
    }
}

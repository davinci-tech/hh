package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public enum bm {
    CUSTOM(-1),
    UNKNOWN(0),
    ANY(1),
    LAND(2),
    PORTRAIT(3),
    SQUARE(4);

    private int g;

    public boolean a(int i) {
        for (int i2 = 0; i2 < values().length; i2++) {
            if (i == values()[i2].a()) {
                return true;
            }
        }
        return false;
    }

    public int a() {
        return this.g;
    }

    bm(int i) {
        this.g = i;
    }
}

package com.huawei.openalliance.ad.constant;

/* loaded from: classes5.dex */
public enum Scheme {
    HTTP("http://"),
    HTTPS("https://"),
    FILE(com.huawei.operation.utils.Constants.PREFIX_FILE),
    CONTENT("content://"),
    RES("res://"),
    ASSET("asset://"),
    DISKCACHE("diskcache://"),
    DATAPARTITION("/data/");

    String mScheme;

    @Override // java.lang.Enum
    public String toString() {
        return this.mScheme;
    }

    Scheme(String str) {
        this.mScheme = str;
    }
}

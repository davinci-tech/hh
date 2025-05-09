package com.huawei.openalliance.ad.constant;

/* loaded from: classes5.dex */
public enum VastVersion {
    VAST_20("2.0"),
    VAST_30("3.0");

    private String version;

    public String getVersion() {
        return this.version;
    }

    public static VastVersion getVerFromStr(String str) {
        for (VastVersion vastVersion : values()) {
            if (vastVersion.getVersion().equals(str)) {
                return vastVersion;
            }
        }
        return VAST_30;
    }

    VastVersion(String str) {
        this.version = str;
    }
}

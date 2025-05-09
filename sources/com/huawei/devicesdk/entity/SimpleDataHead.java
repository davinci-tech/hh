package com.huawei.devicesdk.entity;

/* loaded from: classes3.dex */
public enum SimpleDataHead {
    DB((byte) -37),
    DC((byte) -36),
    BD((byte) -67),
    CD((byte) -51),
    INVALID((byte) -1);

    private byte dataHead;

    SimpleDataHead(byte b) {
        this.dataHead = b;
    }

    public byte getDataHead() {
        return this.dataHead;
    }

    public static SimpleDataHead get(byte b) {
        for (SimpleDataHead simpleDataHead : values()) {
            if (simpleDataHead.dataHead == b) {
                return simpleDataHead;
            }
        }
        return INVALID;
    }

    public static boolean isEncryptedDataHead(byte b) {
        SimpleDataHead simpleDataHead = get(b);
        return simpleDataHead == DC || simpleDataHead == CD;
    }

    public static SimpleDataHead getDownloadDataHead(boolean z) {
        return z ? DC : DB;
    }
}

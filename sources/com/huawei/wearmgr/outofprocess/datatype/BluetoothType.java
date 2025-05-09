package com.huawei.wearmgr.outofprocess.datatype;

/* loaded from: classes2.dex */
public enum BluetoothType {
    AW(1),
    BR(2),
    BLE(4),
    DUAL(8),
    ALL(255);

    private int value;

    BluetoothType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

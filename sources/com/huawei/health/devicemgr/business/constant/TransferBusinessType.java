package com.huawei.health.devicemgr.business.constant;

/* loaded from: classes.dex */
public enum TransferBusinessType {
    FIVE_TEN("5.10"),
    FIVE_FORTY("5.40"),
    FIVE_FORTY_FOUR("5.44");

    private String mValue;

    TransferBusinessType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public static TransferBusinessType getBusiTypeByValue(String str) {
        if (str == null) {
            return null;
        }
        for (TransferBusinessType transferBusinessType : values()) {
            if (transferBusinessType.getValue().equals(str)) {
                return transferBusinessType;
            }
        }
        return null;
    }
}

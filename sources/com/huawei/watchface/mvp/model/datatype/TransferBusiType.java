package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public enum TransferBusiType {
    FIVE_TEN("5.10"),
    FIVE_FORTY("5.40"),
    FIVE_FORTY_FOUR("5.44");

    private String mValue;

    TransferBusiType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public static TransferBusiType getBusiTypeByValue(String str) {
        if (str == null) {
            return null;
        }
        for (TransferBusiType transferBusiType : values()) {
            if (transferBusiType.getValue().equals(str)) {
                return transferBusiType;
            }
        }
        return null;
    }
}

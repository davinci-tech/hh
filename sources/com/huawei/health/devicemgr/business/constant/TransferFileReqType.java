package com.huawei.health.devicemgr.business.constant;

/* loaded from: classes3.dex */
public enum TransferFileReqType {
    APP_DELIVERY(0),
    DEVICE_REGISTRATION(1),
    DEVICE_UNREGISTRATION(2),
    APP_STOP(3),
    FILEREQUEST_DELIVERY(20);

    private int mValue;

    TransferFileReqType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public static TransferFileReqType getFileReqTypeByValue(int i) {
        for (TransferFileReqType transferFileReqType : values()) {
            if (transferFileReqType.getValue() == i) {
                return transferFileReqType;
            }
        }
        return null;
    }
}

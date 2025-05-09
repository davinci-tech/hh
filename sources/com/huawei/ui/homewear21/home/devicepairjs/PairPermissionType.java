package com.huawei.ui.homewear21.home.devicepairjs;

/* loaded from: classes9.dex */
public enum PairPermissionType {
    SMS("SMS", new String[]{"android.permission.SEND_SMS", "android.permission.READ_SMS"}),
    READ_CALL_LOG("READ_CALL_LOG", new String[]{"android.permission.READ_CALL_LOG", "android.permission.PROCESS_OUTGOING_CALLS"}),
    READ_CONTACTS("READ_CONTACTS", new String[]{"android.permission.READ_CONTACTS"}),
    CALL_PHONE("CALL_PHONE", new String[]{"android.permission.CALL_PHONE", "android.permission.ANSWER_PHONE_CALLS"}),
    READ_PHONE_STATE("READ_PHONE_STATE", new String[]{"android.permission.READ_PHONE_STATE"}),
    LOCATION("LOCATION", new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}),
    CALENDAR("CALENDAR", new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"});

    private String mPermissionKey;
    private String[] mPermissionValue;

    PairPermissionType(String str, String[] strArr) {
        this.mPermissionKey = str;
        this.mPermissionValue = strArr;
    }

    public String key() {
        return this.mPermissionKey;
    }

    public String[] value() {
        return this.mPermissionValue;
    }
}

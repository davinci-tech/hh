package com.huawei.hianalytics.core.storage;

/* loaded from: classes4.dex */
public class CommonHeaderEx {
    public String commonHeaderEx;
    public String evtExHashCode;
    public static final Property COLUMN_HASHCODE = new Property(String.class, "EVT_EX_HASH_CODE");
    public static final String TABLE_NAME = "COMMON_HEADER_EX";
    public static final Property COLUMN_HEADER = new Property(String.class, TABLE_NAME);

    public void setEvtExHashCode(String str) {
        this.evtExHashCode = str;
    }

    public void setCommonHeaderEx(String str) {
        this.commonHeaderEx = str;
    }

    public String getEvtExHashCode() {
        String str = this.evtExHashCode;
        return str == null ? "" : str;
    }

    public String getCommonHeaderEx() {
        String str = this.commonHeaderEx;
        return str == null ? "" : str;
    }

    public CommonHeaderEx(String str, String str2) {
        this.evtExHashCode = str;
        this.commonHeaderEx = str2;
    }

    public CommonHeaderEx() {
    }
}

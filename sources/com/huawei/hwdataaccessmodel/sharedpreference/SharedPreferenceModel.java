package com.huawei.hwdataaccessmodel.sharedpreference;

/* loaded from: classes.dex */
public class SharedPreferenceModel {
    private int encryptType = 0;
    private String value = "";

    public int getEncryptType() {
        return this.encryptType;
    }

    public void setEncryptType(int i) {
        this.encryptType = i;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}

package com.huawei.agconnect.remoteconfig.internal.a;

import com.huawei.agconnect.https.annotation.Result;

/* loaded from: classes2.dex */
public class b {

    @Result("name")
    private String key;

    @Result("value")
    private String value;

    public void setValue(String str) {
        this.value = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public String getKey() {
        return this.key;
    }
}

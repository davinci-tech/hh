package com.huawei.agconnect.apms.collect.model.basic;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes2.dex */
public class ApplicationInformation extends CollectableArray {
    private String appName;
    private String appVersion;
    private String packageId;
    private int versionCode;

    public ApplicationInformation() {
        this.versionCode = -1;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(this.appName));
        jsonArray.add(m0.abc(this.appVersion));
        jsonArray.add(m0.abc(Integer.valueOf(this.versionCode)));
        jsonArray.add(m0.abc(this.packageId));
        return jsonArray;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setPackageId(String str) {
        this.packageId = str;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public ApplicationInformation(String str, String str2, String str3) {
        this();
        this.appName = str;
        this.appVersion = str2;
        this.packageId = str3;
    }
}

package com.huawei.health.h5pro.dfx.bi;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.Map;

/* loaded from: classes3.dex */
public class AnalyzerObj {

    @SerializedName("id")
    public String c;

    @SerializedName(JsbMapKeyNames.H5_CUSTOM_DATA)
    public Map<String, String> e;

    public void setId(String str) {
        this.c = str;
    }

    public void setCustomData(Map<String, String> map) {
        this.e = map;
    }

    public String getId() {
        return this.c;
    }

    public Map<String, String> getCustomData() {
        return this.e;
    }
}

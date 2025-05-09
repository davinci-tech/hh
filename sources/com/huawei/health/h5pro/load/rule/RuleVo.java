package com.huawei.health.h5pro.load.rule;

import com.google.gson.annotations.SerializedName;

@Deprecated
/* loaded from: classes8.dex */
public class RuleVo {

    @SerializedName("columns")
    public String[] b;

    @SerializedName("rule")
    public String e;

    public void setRule(String str) {
        this.e = str;
    }

    public void setColumns(String[] strArr) {
        this.b = strArr;
    }

    public String getRule() {
        return this.e;
    }

    public String[] getColumns() {
        return this.b;
    }
}

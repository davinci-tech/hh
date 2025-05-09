package com.huawei.healthcloud.plugintrack.golf.cloud.beans;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class BranchInfo {

    @SerializedName("branchId")
    private int branchId;

    @SerializedName("name")
    private String name;

    public int getBranchId() {
        return this.branchId;
    }

    public String getName() {
        return this.name;
    }
}

package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class ResourceSummary {
    private String resourceId;
    private int resourceType;

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public String toString() {
        return "ResourceSummary{resourceType=" + this.resourceType + ", resourceId='" + this.resourceId + "'}";
    }
}

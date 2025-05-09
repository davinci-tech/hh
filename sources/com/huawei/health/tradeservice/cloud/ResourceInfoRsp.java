package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.ResourceSummary;

/* loaded from: classes8.dex */
public class ResourceInfoRsp extends BaseRsp {
    private ResourceSummary resourceSummary;

    public ResourceSummary getResourceSummary() {
        return this.resourceSummary;
    }

    public void setResourceSummary(ResourceSummary resourceSummary) {
        this.resourceSummary = resourceSummary;
    }
}

package com.huawei.health.marketing.datatype;

import com.huawei.operation.utils.Constants;
import defpackage.koq;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ResourceResultInfo {
    private final List<ResourceBriefInfo> resources;
    private final long resourcesLatestModifyTime;
    private final int totalNum;

    public ResourceResultInfo(Builder builder) {
        this.totalNum = builder.totalNum;
        this.resourcesLatestModifyTime = builder.resourcesLatestModifyTime;
        this.resources = builder.resources;
    }

    public String toString() {
        List<ResourceBriefInfo> list = this.resources;
        return list == null ? Constants.NULL : list.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceResultInfo)) {
            return false;
        }
        ResourceResultInfo resourceResultInfo = (ResourceResultInfo) obj;
        return getTotalNum() == resourceResultInfo.getTotalNum() && getResourcesLatestModifyTime() == resourceResultInfo.getResourcesLatestModifyTime() && koq.b(getResources(), resourceResultInfo.getResources(), false);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getTotalNum()), Long.valueOf(getResourcesLatestModifyTime()), getResources());
    }

    public static class Builder {
        private List<ResourceBriefInfo> resources;
        private long resourcesLatestModifyTime;
        private int totalNum;

        public Builder setTotalNum(int i) {
            this.totalNum = i;
            return this;
        }

        public Builder setResources(List<ResourceBriefInfo> list) {
            this.resources = list;
            return this;
        }

        public Builder setResourcesLatestModifyTime(long j) {
            this.resourcesLatestModifyTime = j;
            return this;
        }

        public ResourceResultInfo build() {
            return new ResourceResultInfo(this);
        }
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public List<ResourceBriefInfo> getResources() {
        return this.resources;
    }

    public long getResourcesLatestModifyTime() {
        return this.resourcesLatestModifyTime;
    }
}

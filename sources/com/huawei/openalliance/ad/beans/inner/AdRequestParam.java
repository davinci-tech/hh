package com.huawei.openalliance.ad.beans.inner;

import com.huawei.openalliance.ad.beans.metadata.AdTimeStatistics;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import java.util.List;

/* loaded from: classes5.dex */
public class AdRequestParam {
    private AdSlotParam adSlotParam;
    private int adType;
    private List<String> cachedContentIds;
    private List<String> cachedSloganIds;
    private List<String> cachedTemplateIds;
    private List<String> removedContentIds;
    private String requestId;
    private long requestTime;
    private AdTimeStatistics timeStatistics;

    public AdTimeStatistics i() {
        return this.timeStatistics;
    }

    public long h() {
        return this.requestTime;
    }

    public String g() {
        return this.requestId;
    }

    public List<String> f() {
        return this.removedContentIds;
    }

    public List<String> e() {
        return this.cachedTemplateIds;
    }

    public List<String> d() {
        return this.cachedSloganIds;
    }

    public List<String> c() {
        return this.cachedContentIds;
    }

    public AdSlotParam b() {
        return this.adSlotParam;
    }

    public void a(String str) {
        this.requestId = str;
    }

    public void a(AdTimeStatistics adTimeStatistics) {
        this.timeStatistics = adTimeStatistics;
    }

    public void a(long j) {
        this.requestTime = j;
    }

    public int a() {
        return this.adType;
    }

    public static class Builder {
        private AdSlotParam adSlotParam;
        private int adType;
        private List<String> cachedContentIds;
        private List<String> cachedSloganIds;
        private List<String> cachedTemplateIds;
        private List<String> removedContentIds;
        private String requestId;
        private long requestTime;
        private AdTimeStatistics timeStatistics;

        public Builder d(List<String> list) {
            this.removedContentIds = list;
            return this;
        }

        public Builder c(List<String> list) {
            this.cachedTemplateIds = list;
            return this;
        }

        public Builder b(List<String> list) {
            this.cachedSloganIds = list;
            return this;
        }

        public AdRequestParam a() {
            AdRequestParam adRequestParam = new AdRequestParam();
            adRequestParam.adType = this.adType;
            adRequestParam.adSlotParam = this.adSlotParam;
            adRequestParam.cachedContentIds = this.cachedContentIds;
            adRequestParam.cachedSloganIds = this.cachedSloganIds;
            adRequestParam.cachedTemplateIds = this.cachedTemplateIds;
            adRequestParam.removedContentIds = this.removedContentIds;
            adRequestParam.requestId = this.requestId;
            adRequestParam.requestTime = this.requestTime;
            adRequestParam.timeStatistics = this.timeStatistics;
            return adRequestParam;
        }

        public Builder a(List<String> list) {
            this.cachedContentIds = list;
            return this;
        }

        public Builder a(AdSlotParam adSlotParam) {
            this.adSlotParam = adSlotParam;
            return this;
        }

        public Builder a(int i) {
            this.adType = i;
            return this;
        }
    }

    private AdRequestParam() {
    }
}

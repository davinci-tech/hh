package com.huawei.openalliance.ad.beans.inner;

import java.util.Map;

/* loaded from: classes5.dex */
public class ApiReqParam {
    private boolean supportTptAd;
    private Map<String, Integer> unsupportedTags;

    /* loaded from: classes9.dex */
    public static class Builder {
        private boolean supportTptAd = false;
        private Map<String, Integer> unsupportedTags;

        public Builder setUnsupportedTags(Map<String, Integer> map) {
            this.unsupportedTags = map;
            return this;
        }

        public Builder setSupportTptAd(boolean z) {
            this.supportTptAd = z;
            return this;
        }

        public ApiReqParam build() {
            return new ApiReqParam(this);
        }
    }

    public Map<String, Integer> b() {
        return this.unsupportedTags;
    }

    public boolean a() {
        return this.supportTptAd;
    }

    private ApiReqParam(Builder builder) {
        this.supportTptAd = false;
        this.supportTptAd = builder.supportTptAd;
        this.unsupportedTags = builder.unsupportedTags;
    }

    public ApiReqParam() {
        this.supportTptAd = false;
    }
}

package com.huawei.openalliance.ad.inter.data;

/* loaded from: classes5.dex */
public class RewardVerifyConfig {

    /* renamed from: a, reason: collision with root package name */
    private String f7049a;
    private String b;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f7050a;
        private String b;

        public Builder setUserId(String str) {
            this.b = str;
            return this;
        }

        public Builder setData(String str) {
            this.f7050a = str;
            return this;
        }

        public RewardVerifyConfig build() {
            return new RewardVerifyConfig(this);
        }
    }

    public String getUserId() {
        return this.b;
    }

    public String getData() {
        return this.f7049a;
    }

    private RewardVerifyConfig(Builder builder) {
        if (builder != null) {
            this.f7049a = builder.f7050a;
            this.b = builder.b;
        }
    }

    private RewardVerifyConfig() {
    }
}

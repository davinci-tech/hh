package com.huawei.openalliance.ad.processor.eventbeans;

/* loaded from: classes9.dex */
public class MagLockEventInfo {

    /* renamed from: a, reason: collision with root package name */
    private Integer f7450a;
    private String b;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Integer f7451a;
        private String b;

        public Builder setPosition(String str) {
            this.b = str;
            return this;
        }

        public Builder setFailReason(Integer num) {
            this.f7451a = num;
            return this;
        }

        public MagLockEventInfo build() {
            return new MagLockEventInfo(this);
        }
    }

    public String b() {
        return this.b;
    }

    public Integer a() {
        return this.f7450a;
    }

    private MagLockEventInfo(Builder builder) {
        this.f7450a = builder.f7451a;
        this.b = builder.b;
    }
}

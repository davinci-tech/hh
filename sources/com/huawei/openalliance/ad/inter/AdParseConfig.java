package com.huawei.openalliance.ad.inter;

/* loaded from: classes5.dex */
public class AdParseConfig {

    /* renamed from: a, reason: collision with root package name */
    private boolean f6962a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f6963a = false;
        private boolean b = false;
        private boolean c = false;
        private boolean d = false;
        private boolean e = false;

        public Builder setUpdateConfig(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setPreCheckNotifyEnable(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setEncryptInContentRrd(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setEnableFullLog(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setAccelerate(boolean z) {
            this.f6963a = z;
            return this;
        }

        public AdParseConfig build() {
            return new AdParseConfig(this);
        }
    }

    public boolean e() {
        return this.e;
    }

    public boolean d() {
        return this.d;
    }

    public boolean c() {
        return this.c;
    }

    public boolean b() {
        return this.b;
    }

    public boolean a() {
        return this.f6962a;
    }

    private AdParseConfig(Builder builder) {
        this.f6962a = builder.f6963a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
    }
}

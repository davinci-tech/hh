package com.huawei.hms.ads.jsb;

/* loaded from: classes4.dex */
public class JsbConfig {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4329a;
    private String b;
    private String c;
    private boolean d;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4330a = true;
        private String b;
        private String c;
        private boolean d;

        public final Builder initGrs(String str, String str2) {
            this.b = str;
            this.c = str2;
            return this;
        }

        public final Builder initGrs(String str) {
            this.b = str;
            return this;
        }

        public final Builder enableUserInfo(boolean z) {
            this.f4330a = z;
            return this;
        }

        public final Builder enableLog(boolean z) {
            this.d = z;
            return this;
        }

        public final JsbConfig build() {
            return new JsbConfig(this);
        }
    }

    public boolean d() {
        return this.d;
    }

    public String c() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public boolean a() {
        return this.f4329a;
    }

    private JsbConfig(Builder builder) {
        this.f4329a = true;
        this.f4329a = builder.f4330a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
    }
}

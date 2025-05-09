package com.huawei.health.h5pro.jsbridge.system.storage;

/* loaded from: classes3.dex */
public class FileMeta {

    /* renamed from: a, reason: collision with root package name */
    public long f2432a;
    public String b;
    public String c;
    public String d;
    public boolean e;
    public long f;
    public String j;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        public boolean f2433a = true;
        public long b;
        public String c;
        public String d;
        public String e;
        public long f;
        public String g;

        public Builder size(long j) {
            this.f = j;
            return this;
        }

        public Builder path(String str) {
            this.g = str;
            return this;
        }

        public Builder noExist() {
            this.f2433a = false;
            return this;
        }

        public Builder name(String str) {
            this.c = str;
            return this;
        }

        public Builder mimeType(String str) {
            this.e = str;
            return this;
        }

        public Builder dateAdded(long j) {
            this.b = j;
            return this;
        }

        public FileMeta build() {
            return new FileMeta(this);
        }

        public Builder absolutePath(String str) {
            this.d = str;
            return this;
        }
    }

    public boolean isExist() {
        return this.e;
    }

    public long getSize() {
        return this.f;
    }

    public String getPath() {
        return this.j;
    }

    public String getName() {
        return this.d;
    }

    public String getMimeType() {
        return this.b;
    }

    public long getDateAdded() {
        return this.f2432a;
    }

    public String getAbsolutePath() {
        return this.c;
    }

    public FileMeta(Builder builder) {
        this.j = builder.g;
        this.d = builder.c;
        this.c = builder.d;
        this.f = builder.f;
        this.b = builder.e;
        this.f2432a = builder.b;
        this.e = builder.f2433a;
    }
}

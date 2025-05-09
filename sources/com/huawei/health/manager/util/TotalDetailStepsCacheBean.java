package com.huawei.health.manager.util;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class TotalDetailStepsCacheBean {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("runningSteps")
    private int f2809a;

    @SerializedName("activeCount")
    private int b;

    @SerializedName("sysClockTime")
    private long c;

    @SerializedName("climbSteps")
    private int d;

    @SerializedName("intensityTime")
    private int e;

    @SerializedName("walkSteps")
    private int f;

    @SerializedName("timestamp")
    private long h;

    @SerializedName("todaySensorSteps")
    private int j;

    public TotalDetailStepsCacheBean(Builder builder) {
        this.h = builder.g;
        this.j = builder.j;
        this.f = builder.f;
        this.f2809a = builder.b;
        this.d = builder.f2810a;
        this.c = builder.d;
        this.e = builder.e;
        this.b = builder.c;
    }

    public long f() {
        return this.h;
    }

    public int j() {
        return this.j;
    }

    public int g() {
        return this.f;
    }

    public int a() {
        return this.f2809a;
    }

    public int b() {
        return this.d;
    }

    public long d() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public int e() {
        return this.b;
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private int f2810a;
        private int b;
        private int c;
        private long d;
        private int e;
        private int f;
        private long g;
        private int j;

        public Builder c(long j) {
            this.g = j;
            return this;
        }

        public Builder c(int i) {
            this.j = i;
            return this;
        }

        public Builder i(int i) {
            this.f = i;
            return this;
        }

        public Builder d(int i) {
            this.b = i;
            return this;
        }

        public Builder a(int i) {
            this.f2810a = i;
            return this;
        }

        public Builder a(long j) {
            this.d = j;
            return this;
        }

        public Builder e(int i) {
            this.e = i;
            return this;
        }

        public Builder b(int i) {
            this.c = i;
            return this;
        }

        public TotalDetailStepsCacheBean c() {
            return new TotalDetailStepsCacheBean(this);
        }
    }
}

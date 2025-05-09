package com.huawei.health.h5pro.scalable.storage;

/* loaded from: classes3.dex */
public class H5AppLoadStorageInfo {

    /* renamed from: a, reason: collision with root package name */
    public long f2449a;
    public long c;
    public String d;
    public long e;

    public String toString() {
        return "H5AppLoadStorageInfo{name='" + this.d + "', count=" + this.e + ", loadTime=" + this.c + ", preLoadTime=" + this.f2449a + '}';
    }

    public void setPreLoadTime(long j) {
        this.f2449a = j;
    }

    public void setName(String str) {
        this.d = str;
    }

    public void setLoadTime(long j) {
        this.c = j;
    }

    public void setCount(long j) {
        this.e = j;
    }

    public long getPreLoadTime() {
        return this.f2449a;
    }

    public String getName() {
        return this.d;
    }

    public long getLoadTime() {
        return this.c;
    }

    public long getCount() {
        return this.e;
    }
}

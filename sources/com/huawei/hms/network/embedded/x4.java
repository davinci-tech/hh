package com.huawei.hms.network.embedded;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class x4 implements y4 {

    /* renamed from: a, reason: collision with root package name */
    public int f5567a;
    public int b;
    public long c;
    public long d;
    public String e;

    public String toString() {
        return "DetectImpl{detectType=" + this.f5567a + ", statusCode=" + this.b + ", totalTime=" + this.c + ", detectStartTime=" + this.d + ", domain=" + this.e + '}';
    }

    @Override // com.huawei.hms.network.embedded.y4
    public long e() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.y4
    public int d() {
        return this.f5567a;
    }

    @Override // com.huawei.hms.network.embedded.y4
    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    @Override // com.huawei.hms.network.embedded.y4
    public long b() {
        return this.d;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.e = str;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(int i) {
        this.f5567a = i;
    }

    @Override // com.huawei.hms.network.embedded.y4
    public String a() {
        return this.e;
    }

    public x4(int i) {
        this.e = "unknown";
        this.f5567a = i;
        this.d = System.currentTimeMillis();
    }

    public x4() {
        this.e = "unknown";
        this.f5567a = -1;
        this.d = System.currentTimeMillis();
    }
}

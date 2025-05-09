package com.huawei.hms.network.embedded;

import android.net.NetworkInfo;

/* loaded from: classes9.dex */
public class b5 implements c5 {

    /* renamed from: a, reason: collision with root package name */
    public int f5171a;
    public NetworkInfo.DetailedState b;
    public long c;

    public String toString() {
        return "NetworkInfoImpl{networkType=" + this.f5171a + ", networkDetailState=" + this.b + ", networkTimeStamp=" + this.c + '}';
    }

    @Override // com.huawei.hms.network.embedded.c5
    public int c() {
        return this.f5171a;
    }

    @Override // com.huawei.hms.network.embedded.c5
    public long b() {
        return this.c;
    }

    public void a(NetworkInfo.DetailedState detailedState) {
        this.b = detailedState;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(int i) {
        this.f5171a = i;
    }

    @Override // com.huawei.hms.network.embedded.c5
    public NetworkInfo.DetailedState a() {
        return this.b;
    }
}

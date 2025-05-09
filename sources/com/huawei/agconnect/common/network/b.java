package com.huawei.agconnect.common.network;

import com.huawei.agconnect.datastore.annotation.SharedPreference;

/* loaded from: classes8.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f1700a = new b();

    @SharedPreference(fileName = "AGConnectAccessNetwork", key = "enableAccessNetwork")
    boolean enableAccessNetwork = false;

    public boolean b() {
        c.a().d(f1700a);
        return this.enableAccessNetwork;
    }

    public void a(boolean z) {
        this.enableAccessNetwork = z;
        c.a().b(f1700a);
    }

    public static b a() {
        return f1700a;
    }

    private b() {
    }
}

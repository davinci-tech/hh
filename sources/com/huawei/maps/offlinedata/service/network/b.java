package com.huawei.maps.offlinedata.service.network;

import android.content.IntentFilter;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6505a;
    private static final byte[] c = new byte[0];
    private volatile a b = null;

    public static b a() {
        if (f6505a == null) {
            synchronized (b.class) {
                if (f6505a == null) {
                    f6505a = new b();
                }
            }
        }
        return f6505a;
    }

    public boolean b() {
        if (this.b != null) {
            return false;
        }
        g.a("NetWorkService", "netTypeReceiver is null and register");
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        synchronized (c) {
            this.b = new a();
            com.huawei.maps.offlinedata.utils.a.a().registerReceiver(this.b, intentFilter);
        }
        return true;
    }

    public void c() {
        if (this.b != null) {
            g.a("NetWorkService", "netTypeReceiver unregister");
            synchronized (c) {
                com.huawei.maps.offlinedata.utils.a.a().unregisterReceiver(this.b);
                this.b = null;
            }
        }
    }
}

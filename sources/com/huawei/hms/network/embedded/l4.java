package com.huawei.hms.network.embedded;

import com.huawei.hms.network.inner.api.NetworkReceiver;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public final class l4 {
    public static final l4 b = new l4();

    /* renamed from: a, reason: collision with root package name */
    public List<NetworkReceiver> f5357a = new CopyOnWriteArrayList();

    public void b(NetworkReceiver networkReceiver) {
        if (networkReceiver != null) {
            this.f5357a.remove(networkReceiver);
        }
    }

    public void a(NetworkReceiver networkReceiver) {
        if (networkReceiver != null) {
            this.f5357a.add(networkReceiver);
        }
    }

    public List<NetworkReceiver> a() {
        return this.f5357a;
    }

    public static l4 b() {
        return b;
    }
}

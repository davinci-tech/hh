package com.huawei.agconnect.remoteconfig.internal;

import com.huawei.agconnect.common.api.HaBridge;
import com.huawei.hmf.tasks.Task;
import java.util.Map;

/* loaded from: classes2.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final b f1816a = new b();
    private HaBridge b;

    public void b() {
        this.b = new HaBridge(HaBridge.HA_SERVICE_TAG_CONFIG);
    }

    public Task<Map<String, String>> a(boolean z) {
        return this.b.getUserProfiles(z);
    }

    public static b a() {
        b bVar;
        synchronized (b.class) {
            bVar = f1816a;
        }
        return bVar;
    }

    private b() {
        if (this.b == null) {
            this.b = new HaBridge(HaBridge.HA_SERVICE_TAG_CONFIG);
        }
    }
}

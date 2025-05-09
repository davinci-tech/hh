package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class nl implements IPPSWebEventCallback {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, IPPSWebEventCallback> f7321a;

    @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
    public void onWebloadFinish() {
        for (IPPSWebEventCallback iPPSWebEventCallback : this.f7321a.values()) {
            if (iPPSWebEventCallback != null) {
                iPPSWebEventCallback.onWebloadFinish();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
    public void onWebOpen() {
        for (IPPSWebEventCallback iPPSWebEventCallback : this.f7321a.values()) {
            if (iPPSWebEventCallback != null) {
                iPPSWebEventCallback.onWebOpen();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
    public void onWebClose(int i) {
        for (IPPSWebEventCallback iPPSWebEventCallback : this.f7321a.values()) {
            if (iPPSWebEventCallback != null) {
                iPPSWebEventCallback.onWebClose(i);
            }
        }
    }

    public void b(IPPSWebEventCallback iPPSWebEventCallback) {
        if (iPPSWebEventCallback == null) {
            this.f7321a.remove("jsb_listener_key");
        } else {
            this.f7321a.put("jsb_listener_key", iPPSWebEventCallback);
        }
    }

    public void a(IPPSWebEventCallback iPPSWebEventCallback) {
        if (iPPSWebEventCallback == null) {
            this.f7321a.remove("outer_listener_key");
        } else {
            this.f7321a.put("outer_listener_key", iPPSWebEventCallback);
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final nl f7322a = new nl();
    }

    public static nl a() {
        return a.f7322a;
    }

    private nl() {
        this.f7321a = new ConcurrentHashMap();
    }
}

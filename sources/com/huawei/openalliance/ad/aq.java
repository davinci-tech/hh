package com.huawei.openalliance.ad;

import com.huawei.hms.ads.jsb.inner.data.WebInfo;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class aq extends j {

    public static class b implements IPPSWebEventCallback {

        /* renamed from: a, reason: collision with root package name */
        private String f6641a;
        private Map<String, RemoteCallResultCallback<String>> b = Collections.synchronizedMap(new a(5));
        private Map<String, RemoteCallResultCallback<String>> c = Collections.synchronizedMap(new a(5));
        private Map<String, RemoteCallResultCallback<String>> d = Collections.synchronizedMap(new a(5));

        @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
        public void onWebloadFinish() {
            a(this.d, new WebInfo());
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
        public void onWebOpen() {
            a(this.b, new WebInfo());
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback
        public void onWebClose(int i) {
            a(this.c, new WebInfo(i));
        }

        public void c(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.d.put(str2, remoteCallResultCallback);
            this.f6641a = str;
        }

        public void b(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.c.put(str2, remoteCallResultCallback);
            this.f6641a = str;
        }

        public void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.b.put(str2, remoteCallResultCallback);
            this.f6641a = str;
        }

        private void a(Map<String, RemoteCallResultCallback<String>> map, WebInfo webInfo) {
            RemoteCallResultCallback<String> value;
            if (map == null || map.size() <= 0) {
                return;
            }
            for (Map.Entry<String, RemoteCallResultCallback<String>> entry : map.entrySet()) {
                if (entry != null && (value = entry.getValue()) != null) {
                    j.a(value, this.f6641a, 1000, com.huawei.openalliance.ad.utils.be.b(webInfo), false);
                }
            }
        }

        public b() {
            ho.a("JsbOnWebChange", "WebListener init");
        }
    }

    static class a<K, V> extends LinkedHashMap<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private final int f6640a;

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.f6640a;
        }

        a(int i) {
            this.f6640a = i;
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static b f6642a = new b();

        static {
            ho.a("JsbOnWebChange", "register global Jsb Web Listener.");
            nl.a().b(f6642a);
        }
    }

    protected b b() {
        return c.f6642a;
    }

    public aq(String str) {
        super(str);
    }
}

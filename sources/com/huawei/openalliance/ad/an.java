package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.jsb.inner.data.AppDownloadInfo;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.download.app.AppStatusV1;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class an extends ag {

    public static class b implements AppDownloadListener {

        /* renamed from: a, reason: collision with root package name */
        private String f6622a;
        private String b;
        private String c;
        private String d;
        private int e = 20;
        private Map<String, RemoteCallResultCallback<String>> f = Collections.synchronizedMap(new e(this.e));
        private Map<String, RemoteCallResultCallback<String>> g = Collections.synchronizedMap(new e(this.e));
        private Map<String, RemoteCallResultCallback<String>> h = Collections.synchronizedMap(new e(this.e));
        private Map<String, RemoteCallResultCallback<String>> i = Collections.synchronizedMap(new e(this.e));

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(String str) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onUserCancel(AppInfo appInfo) {
            Map<String, RemoteCallResultCallback<String>> map;
            if (appInfo == null || (map = this.i) == null || map.size() == 0) {
                return;
            }
            ho.b("JsbOnDownloadChange", "onUserCancel, app: %s", appInfo.getPackageName());
            for (RemoteCallResultCallback<String> remoteCallResultCallback : this.i.values()) {
                if (remoteCallResultCallback != null) {
                    j.a(remoteCallResultCallback, this.d, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, AppStatus.DOWNLOAD)), false);
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onStatusChanged(AppStatus appStatus, AppInfo appInfo) {
            RemoteCallResultCallback<String> value;
            Map<String, RemoteCallResultCallback<String>> map = this.f;
            if (map == null || map.size() <= 0) {
                ho.c("JsbOnDownloadChange", "status map is null");
                return;
            }
            for (Map.Entry<String, RemoteCallResultCallback<String>> entry : this.f.entrySet()) {
                if (entry != null && (value = entry.getValue()) != null) {
                    j.a(value, this.f6622a, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, appStatus)), false);
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onDownloadProgress(AppInfo appInfo, int i) {
            AppDownloadTask c = com.huawei.openalliance.ad.download.app.e.h().c(appInfo);
            if (c != null) {
                onStatusChanged(com.huawei.openalliance.ad.utils.j.a(c), appInfo);
            }
            Map<String, RemoteCallResultCallback<String>> map = this.g;
            if (map == null || map.size() <= 0) {
                return;
            }
            Iterator<RemoteCallResultCallback<String>> it = this.g.values().iterator();
            while (it.hasNext()) {
                j.a(it.next(), this.b, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, i)), false);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(AppInfo appInfo) {
            Map<String, RemoteCallResultCallback<String>> map = this.h;
            if (map == null || map.size() <= 0) {
                return;
            }
            for (RemoteCallResultCallback<String> remoteCallResultCallback : this.h.values()) {
                if (remoteCallResultCallback != null) {
                    j.a(remoteCallResultCallback, this.c, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, AppStatus.INSTALLED)), false);
                }
            }
        }

        public void d(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.i.put(str2, remoteCallResultCallback);
            this.d = str;
        }

        public void c(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.h.put(str2, remoteCallResultCallback);
            this.c = str;
        }

        public void b(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            ho.b("JsbOnDownloadChange", "add app progress cb %s", str2);
            this.g.put(str2, remoteCallResultCallback);
            this.b = str;
        }

        public void a(String str) {
            if (str == null) {
                return;
            }
            ho.b("JsbOnDownloadChange", "remove app status cb %s", str);
            this.f.remove(str);
            this.g.remove(str);
            this.h.remove(str);
            this.i.remove(str);
        }

        public void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            ho.b("JsbOnDownloadChange", "add app status cb %s", str2);
            this.f.put(str2, remoteCallResultCallback);
            this.f6622a = str;
        }

        public void a(int i) {
            this.e = i;
        }

        public b() {
            ho.a("JsbOnDownloadChange", "DownloadListener init");
        }
    }

    public static class d implements AppDownloadListenerV1 {

        /* renamed from: a, reason: collision with root package name */
        private String f6624a;
        private String b;
        private String c;
        private Map<String, RemoteCallResultCallback<String>> d = Collections.synchronizedMap(new e(5));
        private Map<String, RemoteCallResultCallback<String>> e = Collections.synchronizedMap(new e(5));
        private Map<String, RemoteCallResultCallback<String>> f = Collections.synchronizedMap(new e(5));

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
        public void onNewAppOpen(String str) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
        public void onNewStatusChanged(AppStatusV1 appStatusV1, int i, AppInfo appInfo) {
            RemoteCallResultCallback<String> value;
            Map<String, RemoteCallResultCallback<String>> map = this.d;
            if (map == null || map.size() <= 0) {
                return;
            }
            for (Map.Entry<String, RemoteCallResultCallback<String>> entry : this.d.entrySet()) {
                if (entry != null && (value = entry.getValue()) != null) {
                    j.a(value, this.f6624a, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, appStatusV1, i)), false);
                }
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
        public void onNewDownloadProgress(AppInfo appInfo, int i) {
            Map<String, RemoteCallResultCallback<String>> map = this.e;
            if (map == null || map.size() <= 0) {
                return;
            }
            Iterator<RemoteCallResultCallback<String>> it = this.e.values().iterator();
            while (it.hasNext()) {
                j.a(it.next(), this.b, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, i)), false);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
        public void onNewAppOpen(AppInfo appInfo) {
            Map<String, RemoteCallResultCallback<String>> map = this.f;
            if (map == null || map.size() <= 0) {
                return;
            }
            for (RemoteCallResultCallback<String> remoteCallResultCallback : this.f.values()) {
                if (remoteCallResultCallback != null) {
                    j.a(remoteCallResultCallback, this.c, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(appInfo, AppStatus.INSTALLED)), false);
                }
            }
        }

        public void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.d.put(str2, remoteCallResultCallback);
            this.f6624a = str;
        }

        public d() {
            ho.a("JsbOnDownloadChange", "BrowserDownloadListener init");
        }
    }

    protected d c() {
        return a.f6621a;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static d f6621a = new d();

        static {
            ho.a("JsbOnDownloadChange", "register global Jsb browser app download Listener.");
            com.huawei.openalliance.ad.download.app.d.a().b(f6621a);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static b f6623a = new b();

        static {
            ho.a("JsbOnDownloadChange", "register global Jsb app download Listener.");
            com.huawei.openalliance.ad.download.app.d.a().d(f6623a);
        }
    }

    static class e<K, V> extends LinkedHashMap<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private final int f6625a;

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.f6625a;
        }

        e(int i) {
            this.f6625a = i;
        }
    }

    protected b b(Context context) {
        c.f6623a.a(fh.b(context).r());
        return c.f6623a;
    }

    protected b b() {
        return c.f6623a;
    }

    public an(String str) {
        super(str);
    }
}

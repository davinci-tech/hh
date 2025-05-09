package com.huawei.openalliance.ad.download.app;

import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.utils.dc;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public class d implements AutoOpenListener, AppDownloadListener, AppDownloadListenerV1 {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, AppDownloadListener> f6757a;
    private Map<String, AppDownloadListenerV1> b;
    private Map<String, AutoOpenListener> c;
    private final CopyOnWriteArraySet<AppDownloadListener> d;

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
    public void onUserCancel(AppInfo appInfo) {
        for (AppDownloadListener appDownloadListener : this.f6757a.values()) {
            if (appDownloadListener != null) {
                appDownloadListener.onUserCancel(appInfo);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
    public void onStatusChanged(AppStatus appStatus, AppInfo appInfo) {
        for (AppDownloadListener appDownloadListener : this.f6757a.values()) {
            if (appDownloadListener != null) {
                appDownloadListener.onStatusChanged(appStatus, appInfo);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
    public void onNewStatusChanged(AppStatusV1 appStatusV1, int i, AppInfo appInfo) {
        for (AppDownloadListenerV1 appDownloadListenerV1 : this.b.values()) {
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewStatusChanged(appStatusV1, i, appInfo);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
    public void onNewDownloadProgress(AppInfo appInfo, int i) {
        for (AppDownloadListenerV1 appDownloadListenerV1 : this.b.values()) {
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewDownloadProgress(appInfo, i);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
    public void onNewAppOpen(String str) {
        for (AppDownloadListenerV1 appDownloadListenerV1 : this.b.values()) {
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewAppOpen(str);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1
    public void onNewAppOpen(AppInfo appInfo) {
        for (AppDownloadListenerV1 appDownloadListenerV1 : this.b.values()) {
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewAppOpen(appInfo);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
    public void onDownloadProgress(AppInfo appInfo, int i) {
        for (AppDownloadListener appDownloadListener : this.f6757a.values()) {
            if (appDownloadListener != null) {
                appDownloadListener.onDownloadProgress(appInfo, i);
            }
        }
        onNewDownloadProgress(appInfo, i);
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
    public void onAppOpen(String str) {
        for (AppDownloadListener appDownloadListener : this.f6757a.values()) {
            if (appDownloadListener != null) {
                appDownloadListener.onAppOpen(str);
            }
        }
        Iterator<AppDownloadListener> it = this.d.iterator();
        while (it.hasNext()) {
            AppDownloadListener next = it.next();
            if (next != null) {
                next.onAppOpen(str);
            }
        }
        onNewAppOpen(str);
    }

    @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
    public void onAppOpen(AppInfo appInfo) {
        for (AppDownloadListener appDownloadListener : this.f6757a.values()) {
            if (appDownloadListener != null) {
                appDownloadListener.onAppOpen(appInfo);
            }
        }
        Iterator<AppDownloadListener> it = this.d.iterator();
        while (it.hasNext()) {
            AppDownloadListener next = it.next();
            if (next != null) {
                next.onAppOpen(appInfo);
            }
        }
        onNewAppOpen(appInfo);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener
    public boolean isNeedAutoOpen(final AppInfo appInfo) {
        try {
            return ((Boolean) dc.a(new Callable<Boolean>() { // from class: com.huawei.openalliance.ad.download.app.d.1
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Boolean call() {
                    boolean z;
                    Iterator it = d.this.c.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        AutoOpenListener autoOpenListener = (AutoOpenListener) it.next();
                        if (autoOpenListener != null && !autoOpenListener.isNeedAutoOpen(appInfo)) {
                            z = false;
                            break;
                        }
                    }
                    return Boolean.valueOf(z);
                }
            }, 100L, true)).booleanValue();
        } catch (Throwable th) {
            ho.c("AppDownloadListenerRegister", "AutoOpen err: " + th.getClass().getSimpleName());
            return true;
        }
    }

    public void d(AppDownloadListener appDownloadListener) {
        if (appDownloadListener == null) {
            this.f6757a.remove("jsb_listener_key");
        } else {
            this.f6757a.put("jsb_listener_key", appDownloadListener);
        }
    }

    public void c(AppDownloadListener appDownloadListener) {
        if (appDownloadListener == null) {
            return;
        }
        this.d.remove(appDownloadListener);
    }

    public void b(AppDownloadListenerV1 appDownloadListenerV1) {
        if (appDownloadListenerV1 == null) {
            this.b.remove("jsb_listener_key");
        } else {
            this.b.put("jsb_listener_key", appDownloadListenerV1);
        }
    }

    public void b(AppDownloadListener appDownloadListener) {
        if (appDownloadListener == null) {
            return;
        }
        this.d.add(appDownloadListener);
    }

    public Map<String, AutoOpenListener> b() {
        return this.c;
    }

    public void a(AppDownloadListenerV1 appDownloadListenerV1) {
        if (appDownloadListenerV1 == null) {
            this.b.remove("outer_listener_key");
        } else {
            this.b.put("outer_listener_key", appDownloadListenerV1);
        }
    }

    public void a(AppDownloadListener appDownloadListener) {
        if (appDownloadListener == null) {
            this.f6757a.remove("outer_listener_key");
        } else {
            this.f6757a.put("outer_listener_key", appDownloadListener);
        }
    }

    public void a(AutoOpenListener autoOpenListener) {
        if (autoOpenListener == null) {
            this.c.remove("outer_listener_key");
        } else {
            this.c.put("outer_listener_key", autoOpenListener);
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final d f6759a = new d();
    }

    public static d a() {
        return a.f6759a;
    }

    private d() {
        this.f6757a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.d = new CopyOnWriteArraySet<>();
        e.h().a((AppDownloadListener) this);
        e.h().a((AppDownloadListenerV1) this);
        e.h().a((AutoOpenListener) this);
    }
}

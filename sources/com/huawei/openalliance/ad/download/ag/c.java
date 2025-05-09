package com.huawei.openalliance.ad.download.ag;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.huawei.openalliance.ad.activity.AgProtocolActivity;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.download.app.AppStatusV1;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.download.l;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.lf;
import com.huawei.openalliance.ad.ll;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bo;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class c implements com.huawei.openalliance.ad.download.a<AppDownloadTask> {
    private static Map<String, Method> h = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private Context f6717a;
    private String b;
    private long c;
    private AppDownloadListener e;
    private AppDownloadListenerV1 f;
    private AutoOpenListener g;
    private AppDownloadButton.OnResolutionRequiredListener j;
    private long k;
    private Map<String, Set<l>> d = new ConcurrentHashMap();
    private Map<String, Set<AppDownloadButton.OnResolutionRequiredListener>> i = new ConcurrentHashMap();
    private String l = Constants.TIMEOUT_TASK_ID + hashCode();
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.ag.c.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb;
            if (intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                ho.a("AgDe", "appRe action: %s", action);
                c.this.a(intent, action);
            } catch (IllegalStateException e) {
                e = e;
                sb = new StringBuilder("appRe ");
                sb.append(e.getClass().getSimpleName());
                ho.c("AgDe", sb.toString());
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("appRe ");
                sb.append(e.getClass().getSimpleName());
                ho.c("AgDe", sb.toString());
            }
        }
    };
    private BroadcastReceiver n = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.ag.c.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            StringBuilder sb;
            if (intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                ho.b("AgDe", "itRe action: %s", action);
                String dataString = intent.getDataString();
                if (TextUtils.isEmpty(dataString)) {
                    ho.c("AgDe", "itRe dataString is empty, " + action);
                } else {
                    final String substring = dataString.substring(8);
                    c.this.a(action, substring);
                    if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.ag.c.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                c.this.onAppInstalled(d.a(context).a(substring));
                            }
                        });
                    }
                }
            } catch (IllegalStateException e) {
                e = e;
                sb = new StringBuilder("itRe:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AgDe", sb.toString());
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("itRe:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AgDe", sb.toString());
            }
        }
    };
    private BroadcastReceiver o = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.ag.c.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            try {
                if ("android.intent.action.PACKAGE_DATA_CLEARED".equals(new SafeIntent(intent).getAction())) {
                    String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                    if (TextUtils.isEmpty(schemeSpecificPart)) {
                        ho.b("AgDe", "a bad intent");
                    } else if (schemeSpecificPart.equals(i.e(context))) {
                        ho.b("AgDe", "on hms data cleared.");
                        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.ag.c.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                d.a(context).b();
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                ho.c("AgDe", "on data clear received ex: %s", th.getClass().getSimpleName());
            }
        }
    };
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.ag.c.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ho.b("AgDe", "app remove from hms");
            try {
                String stringExtra = intent.getStringExtra("package_name");
                if (TextUtils.isEmpty(stringExtra)) {
                    ho.b("AgDe", "a bad removed intent");
                } else {
                    c.this.a(context, stringExtra);
                }
            } catch (Throwable th) {
                ho.c("AgDe", "appRemovedReceiver err, %s", th.getClass().getSimpleName());
            }
        }
    };
    private BroadcastReceiver q = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.ag.c.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppInfo appInfo;
            boolean z;
            try {
                ho.b("AgDe", "get check is need auto open app");
                if (intent == null) {
                    return;
                }
                String stringExtra = intent.getStringExtra("packageName");
                if (!TextUtils.isEmpty(stringExtra)) {
                    AppDownloadTask a2 = d.a(context).a(stringExtra);
                    if (a2 != null && a2.B() != null) {
                        appInfo = a2.B();
                        if (c.this.g != null && !c.this.g.isNeedAutoOpen(appInfo)) {
                            ho.b("AgDe", "no need auto open");
                            z = false;
                            Intent intent2 = new Intent();
                            intent2.setAction("huawei.intent.action.AUTO_OPEN_APP");
                            intent2.putExtra("autoOpen", z);
                            intent2.setPackage(i.e(context));
                            context.sendBroadcast(intent2);
                            ho.b("AgDe", "send auto open");
                        }
                        ho.b("AgDe", " need auto open");
                    }
                    ho.b("AgDe", "task is remove");
                    AppInfo appInfo2 = new AppInfo();
                    appInfo2.a(stringExtra);
                    appInfo = appInfo2;
                    if (c.this.g != null) {
                        ho.b("AgDe", "no need auto open");
                        z = false;
                        Intent intent22 = new Intent();
                        intent22.setAction("huawei.intent.action.AUTO_OPEN_APP");
                        intent22.putExtra("autoOpen", z);
                        intent22.setPackage(i.e(context));
                        context.sendBroadcast(intent22);
                        ho.b("AgDe", "send auto open");
                    }
                    ho.b("AgDe", " need auto open");
                }
                z = true;
                Intent intent222 = new Intent();
                intent222.setAction("huawei.intent.action.AUTO_OPEN_APP");
                intent222.putExtra("autoOpen", z);
                intent222.setPackage(i.e(context));
                context.sendBroadcast(intent222);
                ho.b("AgDe", "send auto open");
            } catch (Throwable th) {
                ho.c("AgDe", "autoOpenRec err: " + th.getClass().getSimpleName());
            }
        }
    };

    @Override // com.huawei.openalliance.ad.download.a
    public void b(AppDownloadTask appDownloadTask) {
    }

    public void onSystemInstallStart(AppDownloadTask appDownloadTask) {
        g(appDownloadTask);
        e(appDownloadTask);
        a(AppStatus.INSTALL, appDownloadTask);
        a(AppStatusV1.START_INSTALL, 1000, appDownloadTask);
    }

    public void onSilentInstallSuccess(AppDownloadTask appDownloadTask) {
        e(appDownloadTask);
        a(AppStatus.INSTALLED, appDownloadTask);
        a(AppStatusV1.INSTALL_SUCCESS, 1000, appDownloadTask);
    }

    public void onSilentInstallStart(AppDownloadTask appDownloadTask) {
        g(appDownloadTask);
        e(appDownloadTask);
        a(AppStatus.INSTALLING, appDownloadTask);
        a(AppStatusV1.START_INSTALL, 1000, appDownloadTask);
    }

    public void onSilentInstallFailed(AppDownloadTask appDownloadTask) {
        AppStatus appStatus;
        if (c(appDownloadTask)) {
            return;
        }
        e(appDownloadTask);
        if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.FAILED) {
            f(appDownloadTask);
            appStatus = AppStatus.DOWNLOAD;
        } else {
            appStatus = AppStatus.INSTALL;
        }
        a(appStatus, appDownloadTask);
        a(AppStatusV1.INSTALL_FAILED, -1000, appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void onDownloadWaiting(AppDownloadTask appDownloadTask) {
        e(appDownloadTask);
        a(AppStatus.WAITING, appDownloadTask);
        a(AppStatusV1.WAITING, 1000, appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void onDownloadSuccess(AppDownloadTask appDownloadTask) {
        Object[] objArr = new Object[1];
        objArr[0] = appDownloadTask == null ? null : appDownloadTask.n();
        ho.b("AgDe", "down success: %s", objArr);
        this.k = fh.b(this.f6717a).f();
        a(AppStatus.DOWNLOADED, appDownloadTask);
        a(AppStatusV1.DOWNLOAD_SUCCESS, 1000, appDownloadTask);
        d();
        c();
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void onDownloadStart(AppDownloadTask appDownloadTask) {
        AppStatusV1 appStatusV1;
        e(appDownloadTask);
        a(AppStatus.DOWNLOADING, appDownloadTask);
        if (appDownloadTask.l() > 0 || appDownloadTask.g() > 0) {
            appStatusV1 = AppStatusV1.RESUME;
        } else {
            a(AppStatusV1.PRE_CHECK_SUCCESS, 1000, appDownloadTask);
            appStatusV1 = AppStatusV1.START_DOWNLOAD;
        }
        a(appStatusV1, 1000, appDownloadTask);
    }

    public void onDownloadResumed(AppDownloadTask appDownloadTask) {
        e(appDownloadTask);
        a(AppStatus.RESUME, appDownloadTask);
        a(AppStatusV1.RESUME, 1000, appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void onDownloadProgress(AppDownloadTask appDownloadTask) {
        d(appDownloadTask);
        AppDownloadListener appDownloadListener = this.e;
        if (appDownloadListener != null) {
            appDownloadListener.onDownloadProgress(appDownloadTask.B(), appDownloadTask.l());
        }
    }

    public void onDownloadPaused(AppDownloadTask appDownloadTask) {
        e(appDownloadTask);
        a(AppStatus.PAUSE, appDownloadTask);
        a(AppStatusV1.PAUSE, appDownloadTask.S(), appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void onDownloadFail(AppDownloadTask appDownloadTask) {
        if (appDownloadTask != null) {
            ho.b("AgDe", "onDownloadFail, current way: %s", appDownloadTask.H());
        }
        if (c(appDownloadTask)) {
            return;
        }
        appDownloadTask.b(0);
        appDownloadTask.b(0L);
        f(appDownloadTask);
        e(appDownloadTask);
        a(AppStatus.DOWNLOADFAILED, appDownloadTask);
        a(AppStatusV1.DOWNLOAD_FAILED, appDownloadTask.S(), appDownloadTask);
    }

    public void onDownloadDeleted(AppDownloadTask appDownloadTask) {
        appDownloadTask.b(0);
        appDownloadTask.b(0L);
        appDownloadTask.a(com.huawei.openalliance.ad.download.e.FAILED);
        f(appDownloadTask);
        e(appDownloadTask);
        a(f() ? AppStatus.DOWNLOADCANCELLED : AppStatus.DOWNLOADFAILED, appDownloadTask);
        if (a(appDownloadTask, System.currentTimeMillis())) {
            a(AppStatusV1.CANCEL_DOWNLOAD, appDownloadTask.S(), appDownloadTask);
        }
    }

    public void onAppUnInstalled(AppDownloadTask appDownloadTask) {
        if (appDownloadTask != null) {
            String packageName = appDownloadTask.B().getPackageName();
            Set<l> a2 = a(packageName);
            if (a2 != null && a2.size() > 0) {
                Iterator<l> it = a2.iterator();
                while (it.hasNext()) {
                    it.next().b(packageName);
                }
            }
            a(AppStatus.DOWNLOAD, appDownloadTask);
        }
    }

    public void onAppInstalled(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null || appDownloadTask.L() == AppDownloadTask.b.DOWN_LOAD_MODE_FROM_SELF) {
            return;
        }
        appDownloadTask.a(com.huawei.openalliance.ad.download.e.INSTALLED);
        a(AppStatus.INSTALLED, appDownloadTask);
        a(AppStatusV1.INSTALL_SUCCESS, 1000, appDownloadTask);
        e(appDownloadTask);
        AppInfo B = appDownloadTask.B();
        if (B != null && B.m() == 1 && !TextUtils.isEmpty(B.d()) && appDownloadTask.T()) {
            new lf(this.f6717a, appDownloadTask).b();
        }
        new g(this.f6717a, appDownloadTask).a();
    }

    public boolean c(AppDownloadTask appDownloadTask) {
        return com.huawei.openalliance.ad.download.app.e.h().d(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void c(AppDownloadTask appDownloadTask, boolean z) {
        onDownloadResumed(appDownloadTask);
        a(z ? AppStatusV1.USER_RESUME : AppStatusV1.RESUME, 1000, appDownloadTask);
    }

    public void b(String str, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        synchronized (this) {
            Set<AppDownloadButton.OnResolutionRequiredListener> set = this.i.get(str);
            if (!bg.a(set)) {
                set.remove(onResolutionRequiredListener);
                if (set.size() <= 0) {
                    this.i.remove(str);
                }
            }
        }
    }

    public void b(String str, l lVar) {
        synchronized (this) {
            Set<l> set = this.d.get(str);
            if (set != null && set.size() > 0) {
                set.remove(lVar);
                if (set.size() <= 0) {
                    this.d.remove(str);
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void b(AppDownloadTask appDownloadTask, boolean z) {
        onDownloadPaused(appDownloadTask);
    }

    public void b() {
        ho.b("AgDe", "remove all OnResolutionRequiredListener");
        this.j = null;
        this.i.clear();
    }

    public void a(String str, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        synchronized (this) {
            Set<AppDownloadButton.OnResolutionRequiredListener> set = this.i.get(str);
            if (set == null) {
                set = new HashSet<>();
                this.i.put(str, set);
            }
            set.add(onResolutionRequiredListener);
        }
    }

    public void a(String str, l lVar) {
        synchronized (this) {
            Set<l> set = this.d.get(str);
            if (set == null) {
                set = new HashSet<>();
                this.d.put(str, set);
            }
            set.add(lVar);
        }
    }

    public void a(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        this.j = onResolutionRequiredListener;
    }

    public void a(AppDownloadListenerV1 appDownloadListenerV1) {
        this.f = appDownloadListenerV1;
    }

    public void a(AppDownloadListener appDownloadListener) {
        this.e = appDownloadListener;
    }

    public void a(AutoOpenListener autoOpenListener) {
        this.g = autoOpenListener;
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void a(AppDownloadTask appDownloadTask, boolean z) {
        onDownloadDeleted(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void a(AppDownloadTask appDownloadTask) {
        a(AppStatusV1.WAITING_FOR_WIFI, 1000, appDownloadTask);
    }

    public void a() {
        this.j = null;
    }

    private void g(AppDownloadTask appDownloadTask) {
        Context context;
        boolean z;
        if (appDownloadTask == null || appDownloadTask.B() == null) {
            return;
        }
        if (this.g == null || com.huawei.openalliance.ad.download.app.d.a().b() == null || com.huawei.openalliance.ad.download.app.d.a().b().size() == 0) {
            ho.b("AgDe", " no setting auto open Listener");
            context = this.f6717a;
            z = false;
        } else {
            ho.b("AgDe", " setting auto open Listener");
            context = this.f6717a;
            z = true;
        }
        f.a(context, z, String.class);
    }

    private static void g() {
        try {
            for (Method method : c.class.getDeclaredMethods()) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0].isAssignableFrom(AppDownloadTask.class)) {
                    h.put(method.getName(), method);
                }
            }
        } catch (IllegalArgumentException e) {
            ho.a("AgDe", "transport=%s", e.getMessage());
            ho.d("AgDe", "transport=" + e.getClass().getSimpleName());
        }
    }

    private boolean f() {
        String packageName;
        Context context = this.f6717a;
        if (context == null || (packageName = context.getPackageName()) == null) {
            return false;
        }
        return WhiteListPkgList.isHwBrowserPkgName(packageName);
    }

    private void f(AppDownloadTask appDownloadTask) {
        d.a(this.f6717a).b((d) appDownloadTask);
    }

    private void e(AppDownloadTask appDownloadTask) {
        Set<l> a2 = a(appDownloadTask.B());
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        Iterator<l> it = a2.iterator();
        while (it.hasNext()) {
            it.next().b(appDownloadTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ho.b("AgDe", "unRegisterAppInstReceiver");
        this.f6717a.unregisterReceiver(this.n);
    }

    private void d(AppDownloadTask appDownloadTask) {
        Set<l> a2 = a(appDownloadTask.B());
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        Iterator<l> it = a2.iterator();
        while (it.hasNext()) {
            it.next().a(appDownloadTask);
        }
    }

    private void d() {
        ho.b("AgDe", "registerAppInstReceiver");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        ao.a(this.f6717a, this.n, intentFilter);
    }

    private Set<AppDownloadButton.OnResolutionRequiredListener> d(String str) {
        Set<AppDownloadButton.OnResolutionRequiredListener> set;
        synchronized (this) {
            set = this.i.get(str);
        }
        return set;
    }

    private void c(String str) {
        Set<AppDownloadButton.OnResolutionRequiredListener> d = d(str);
        if (!bg.a(d)) {
            for (AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener : d) {
                AppInfo appInfo = new AppInfo();
                appInfo.a(str);
                onResolutionRequiredListener.onResolutionRequired(appInfo, new Bundle());
            }
        }
        if (this.j != null) {
            AppInfo appInfo2 = new AppInfo();
            appInfo2.a(str);
            this.j.onResolutionRequired(appInfo2, new Bundle());
        }
    }

    private void c() {
        bo.a(this.l);
        bo.a(new a(this), this.l, this.k);
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.b("AgDe", " packageName is empty.");
            return;
        }
        Set<l> a2 = a(str);
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        ho.a("AgDe", " findAndRefreshTask list:%s", a2.toString());
        Iterator<l> it = a2.iterator();
        while (it.hasNext()) {
            it.next().c(str);
        }
    }

    private boolean a(AppDownloadTask appDownloadTask, long j) {
        if (appDownloadTask == null || appDownloadTask.B() == null || TextUtils.isEmpty(appDownloadTask.B().getPackageName())) {
            return true;
        }
        if (!TextUtils.isEmpty(this.b)) {
            long j2 = this.c;
            if (j2 != 0 && this.b.equals(appDownloadTask.B().getPackageName()) && j - j2 < 500) {
                this.b = null;
                this.c = 0L;
                ho.b("AgDe", " repeat cancel");
                return false;
            }
        }
        this.b = appDownloadTask.B().getPackageName();
        this.c = j;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Set<l> a2 = a(str2);
        if (a2 == null || a2.size() <= 0 || !"android.intent.action.PACKAGE_ADDED".equals(str)) {
            return;
        }
        for (l lVar : a2) {
            if (lVar != null) {
                lVar.a(str2);
            }
        }
    }

    private void a(AppStatusV1 appStatusV1, int i, AppDownloadTask appDownloadTask) {
        AppDownloadListenerV1 appDownloadListenerV1 = this.f;
        if (appDownloadListenerV1 != null) {
            appDownloadListenerV1.onNewStatusChanged(appStatusV1, i, appDownloadTask.B());
        }
    }

    private void a(AppStatus appStatus, AppDownloadTask appDownloadTask) {
        AppDownloadListener appDownloadListener = this.e;
        if (appDownloadListener != null) {
            appDownloadListener.onStatusChanged(appStatus, appDownloadTask.B());
        }
    }

    private void a(AppDownloadTask appDownloadTask, Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        appDownloadTask.a(com.huawei.openalliance.ad.download.e.a(safeIntent.getIntExtra(UpdateKey.MARKET_DLD_STATUS, com.huawei.openalliance.ad.download.e.IDLE.a())));
        ho.a("AgDe", "download status=%s", appDownloadTask.i());
        appDownloadTask.b(safeIntent.getIntExtra("downloadProgress", 0));
        int intExtra = safeIntent.getIntExtra("pauseReason", DownloadTask.c.NONE.a());
        ho.a("AgDe", " pauseReason=%d", Integer.valueOf(intExtra));
        appDownloadTask.a(DownloadTask.c.a(intExtra));
        a(appDownloadTask, appDownloadTask.l());
    }

    private void a(AppDownloadTask appDownloadTask, int i) {
        appDownloadTask.b((appDownloadTask.f() * i) / 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, String str) {
        try {
            Intent intent2 = new Intent(intent);
            if (!"huawei.intent.action.DOWNLOAD".equals(str)) {
                if (!"huawei.intent.action.OPEN".equals(str)) {
                    if ("huawei.intent.action.PENDINGINTENT".equals(str)) {
                        ho.b("AgDe", " request intent");
                        a(intent2);
                        return;
                    }
                    return;
                }
                String stringExtra = intent2.getStringExtra("appPackageName");
                AppDownloadListener appDownloadListener = this.e;
                if (appDownloadListener != null) {
                    appDownloadListener.onAppOpen(stringExtra);
                    return;
                }
                return;
            }
            String stringExtra2 = intent2.getStringExtra("appPackageName");
            AppDownloadTask a2 = d.a(this.f6717a).a(stringExtra2);
            if (a2 == null) {
                ho.b("AgDe", " task is null, pkg=" + stringExtra2);
                b(stringExtra2);
                return;
            }
            String stringExtra3 = intent2.getStringExtra("appInfo");
            if (!TextUtils.isEmpty(stringExtra3)) {
                AppInfo appInfo = (AppInfo) be.b(stringExtra3, AppInfo.class, new Class[0]);
                AppInfo B = a2.B();
                if (appInfo != null && B != null) {
                    ho.b("AgDe", "update appInfo from remote task.");
                    B.s(appInfo.getUniqueId());
                }
            }
            a2.f(intent2.getIntExtra("agFailedReason", -1000));
            a(a2, intent2);
            String stringExtra4 = intent2.getStringExtra("appDownloadMethod");
            if (TextUtils.isEmpty(stringExtra4)) {
                return;
            }
            if (stringExtra4.equals("onDownloadDeleted")) {
                d.a(this.f6717a).c((d) a2);
                return;
            }
            if (stringExtra4.equals("onDownloadStart")) {
                String stringExtra5 = intent2.getStringExtra("agd_event_reason");
                String stringExtra6 = intent2.getStringExtra("agd_install_type");
                if (!cz.b(stringExtra5)) {
                    a2.o(stringExtra5);
                }
                if (!cz.b(stringExtra6)) {
                    a2.p(stringExtra6);
                }
            }
            Method method = h.get(stringExtra4);
            if (method != null) {
                try {
                    ho.b("AgDe", "methodName:%s", stringExtra4);
                    method.invoke(this, a2);
                } catch (IllegalAccessException unused) {
                    ho.c("AgDe", "ilex=%s", stringExtra4);
                } catch (InvocationTargetException unused2) {
                    ho.c("AgDe", "itex=%s", stringExtra4);
                } catch (Exception e) {
                    ho.c("AgDe", "Exception=%s", e.getClass().getSimpleName());
                }
            }
        } catch (Throwable th) {
            ho.c("AgDe", "notifyListener Err: " + th.getClass().getSimpleName());
        }
    }

    private void a(Intent intent) {
        String str;
        int i;
        String str2;
        try {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(BaseGmsClient.KEY_PENDING_INTENT);
            if (pendingIntent != null) {
                Intent intent2 = new Intent();
                intent2.setClass(this.f6717a, AgProtocolActivity.class);
                intent2.putExtra(BaseGmsClient.KEY_PENDING_INTENT, pendingIntent);
                i = intent.getIntExtra("pendingIntent.type", 6);
                intent2.putExtra("pendingIntent.type", i);
                str = intent.getStringExtra("task.pkg");
                intent2.putExtra("task.pkg", str);
                str2 = intent.getStringExtra(MapKeyNames.AG_ACTION_NAME);
                intent2.putExtra(MapKeyNames.AG_ACTION_NAME, str2);
                intent2.setClipData(Constants.CLIP_DATA);
                Activity curActivity = HiAd.getInstance(this.f6717a).getCurActivity();
                if (curActivity == null || curActivity.isFinishing()) {
                    intent2.addFlags(268435456);
                    this.f6717a.startActivity(intent2);
                } else {
                    curActivity.startActivity(intent2);
                }
                c(str);
            } else {
                str = null;
                i = -1;
                str2 = null;
            }
            a(this.f6717a, i, str, str2);
        } catch (Throwable unused) {
            ho.b("AgDe", " requestAgProtocol error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.ag.c.5
            @Override // java.lang.Runnable
            public void run() {
                AppDownloadTask a2 = d.a(context).a(str);
                if (a2 == null || a2.L() == AppDownloadTask.b.DOWN_LOAD_MODE_FROM_SELF) {
                    return;
                }
                d.a(c.this.f6717a).b((d) a2);
            }
        });
    }

    private void a(final Context context, final int i, final String str, final String str2) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.ag.c.7
            @Override // java.lang.Runnable
            public void run() {
                qq C;
                AppDownloadTask a2 = d.a(context).a(str);
                new com.huawei.openalliance.ad.analysis.c(context).a(str, (a2 == null || (C = a2.C()) == null) ? null : C.a(), i, str2, "reqAgPendingIntent");
            }
        });
    }

    private Set<l> a(String str) {
        Set<l> set;
        synchronized (this) {
            set = this.d.get(str);
        }
        return set;
    }

    private Set<l> a(AppInfo appInfo) {
        synchronized (this) {
            if (appInfo != null) {
                if (!TextUtils.isEmpty(appInfo.getPackageName())) {
                    return a(appInfo.getPackageName());
                }
            }
            return null;
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<c> f6727a;

        @Override // java.lang.Runnable
        public void run() {
            c cVar = this.f6727a.get();
            if (cVar == null) {
                return;
            }
            cVar.e();
        }

        public a(c cVar) {
            this.f6727a = new WeakReference<>(cVar);
        }
    }

    public c(Context context) {
        String str;
        this.f6717a = context.getApplicationContext();
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("huawei.intent.action.DOWNLOAD");
            intentFilter.addAction("huawei.intent.action.OPEN");
            intentFilter.addAction("huawei.intent.action.PENDINGINTENT");
            ao.a(this.f6717a, this.m, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
            IntentFilter intentFilter2 = new IntentFilter("android.intent.action.PACKAGE_DATA_CLEARED");
            intentFilter2.addDataScheme("package");
            ao.a(this.f6717a, this.o, intentFilter2);
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("huawei.intent.action.CHECK_AUTO_OPEN_APP");
            ao.a(this.f6717a, this.q, intentFilter3, Constants.PERMISSION_PPS_DOWNLOAD, null);
            ao.a(this.f6717a, this.p, new IntentFilter("huawei.intent.action.APP.REMOVE"), Constants.PERMISSION_PPS_DOWNLOAD, null);
            ll.a(context).a();
            g();
        } catch (IllegalStateException unused) {
            str = "registerReceiver IllegalStateException";
            ho.c("AgDe", str);
        } catch (Exception unused2) {
            str = "registerReceiver Exception";
            ho.c("AgDe", str);
        }
    }
}

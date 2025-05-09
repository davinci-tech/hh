package com.huawei.openalliance.ad.download.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.i;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.sn;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cw;
import com.huawei.openalliance.ad.utils.dc;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class e extends com.huawei.openalliance.ad.download.b<AppDownloadTask> {
    private static final byte[] f = new byte[0];
    private static e g;
    private a h;
    private AppDownloadListenerV1 i;
    private AutoOpenListener j;
    private b k;
    private BroadcastReceiver l;

    public void k() {
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).d();
    }

    public void j() {
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).c();
    }

    public AppDownloadListenerV1 i() {
        return this.i;
    }

    public String e(AppInfo appInfo) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = (String) dc.b(new Callable<String>() { // from class: com.huawei.openalliance.ad.download.app.e.5
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String call() {
                    return e.c(e.this.f6803a);
                }
            });
        }
        return this.b + File.separator + "tmp" + File.separator + appInfo.getPackageName() + ".apk";
    }

    public boolean d(AppDownloadTask appDownloadTask) {
        String H = appDownloadTask.H();
        if (!g(appDownloadTask)) {
            return false;
        }
        a(H, appDownloadTask);
        return c(appDownloadTask);
    }

    public String d(AppInfo appInfo) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = (String) dc.b(new Callable<String>() { // from class: com.huawei.openalliance.ad.download.app.e.4
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String call() {
                    return e.c(e.this.f6803a);
                }
            });
        }
        return this.b + File.separator + appInfo.getPackageName() + ".apk";
    }

    /* renamed from: c, reason: avoid collision after fix types in other method */
    public boolean c2(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return false;
        }
        if (!appDownloadTask.K()) {
            return a((e) appDownloadTask, false, true, true);
        }
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b((com.huawei.openalliance.ad.download.ag.d) appDownloadTask);
        a((e) appDownloadTask, false, true, false);
        return true;
    }

    public AppDownloadTask c(final AppInfo appInfo) {
        if (!h(appInfo)) {
            if (f(appInfo)) {
                return null;
            }
            AppDownloadTask a2 = a(appInfo.getPackageName());
            return a2 == null ? (AppDownloadTask) dc.b(new Callable<AppDownloadTask>() { // from class: com.huawei.openalliance.ad.download.app.e.3
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public AppDownloadTask call() {
                    return e.this.i(appInfo);
                }
            }) : a2;
        }
        AppDownloadTask b = com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b(appInfo);
        if (b == null && "11".equals(appInfo.getPriorInstallWay())) {
            b = a(appInfo.getPackageName());
        }
        if (b != null) {
            a(appInfo, b);
            b.a(appInfo);
        }
        return b;
    }

    public boolean b(AppInfo appInfo) {
        return a(appInfo, true);
    }

    public void b(AppInfo appInfo, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName()) || onResolutionRequiredListener == null) {
            return;
        }
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b(appInfo.getPackageName(), onResolutionRequiredListener);
    }

    public void b(AppInfo appInfo, com.huawei.openalliance.ad.download.l lVar) {
        if (appInfo != null) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b(appInfo.getPackageName(), lVar);
            this.k.b(appInfo.getPackageName(), lVar);
        }
        if (com.huawei.openalliance.ad.utils.j.a(appInfo)) {
            com.huawei.openalliance.ad.download.ag.e.a(this.f6803a).b(appInfo.H(), lVar);
        }
    }

    public void b(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return;
        }
        if (appDownloadTask.K()) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b(appDownloadTask);
            return;
        }
        if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING || appDownloadTask.i() == com.huawei.openalliance.ad.download.e.DOWNLOADED) {
            ho.b("AppDownloadManager", " can not pause, status=" + appDownloadTask.i());
        } else {
            if (e(appDownloadTask)) {
                i.a().a(appDownloadTask);
            }
            a((e) appDownloadTask, DownloadTask.c.USER_CLICK);
        }
    }

    @Override // com.huawei.openalliance.ad.download.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public AppDownloadTask a(String str) {
        DownloadTask a2 = super.a(str);
        return a2 instanceof AppDownloadTask ? (AppDownloadTask) a2 : com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(str);
    }

    public boolean a(AppInfo appInfo) {
        return a(appInfo, false);
    }

    @Override // com.huawei.openalliance.ad.download.b
    public boolean a(AppDownloadTask appDownloadTask, boolean z) {
        if (appDownloadTask == null) {
            return false;
        }
        if (e(appDownloadTask)) {
            i.a().b(appDownloadTask);
            return true;
        }
        if (appDownloadTask.K()) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).c(appDownloadTask);
            a((e) appDownloadTask, z, true, false);
            return true;
        }
        if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING) {
            ho.b("AppDownloadManager", appDownloadTask.B().getPackageName() + " is installing!");
            return false;
        }
        if (appDownloadTask.i() != com.huawei.openalliance.ad.download.e.DOWNLOADED || !ae.b(appDownloadTask.d())) {
            return super.a((e) appDownloadTask, z);
        }
        ho.b("AppDownloadManager", appDownloadTask.B().getPackageName() + " is downloaded!");
        f(appDownloadTask);
        return true;
    }

    @Override // com.huawei.openalliance.ad.download.b
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean c(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            ho.c("AppDownloadManager", "cannot add task, task is null");
            return false;
        }
        if (ho.a()) {
            ho.a("AppDownloadManager", "addTask, package:%s", appDownloadTask.n());
        }
        if (e(appDownloadTask)) {
            b((e) appDownloadTask);
            i.a().a(appDownloadTask, (i.b) null);
            return true;
        }
        if (appDownloadTask.K()) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appDownloadTask);
            a((e) appDownloadTask, false, true, false);
            return true;
        }
        if (appDownloadTask.I()) {
            AppInfo B = appDownloadTask.B();
            if (B != null && !TextUtils.isEmpty(B.r()) && h(appDownloadTask)) {
                return true;
            }
            ho.b("AppDownloadManager", "can not open Ag detail");
            return d(appDownloadTask);
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.e.2
            @Override // java.lang.Runnable
            public void run() {
                e.this.m();
            }
        });
        if (f(appDownloadTask.B())) {
            return d(appDownloadTask);
        }
        if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING) {
            ho.b("AppDownloadManager", appDownloadTask.B().getPackageName() + " is installing.");
            return true;
        }
        if (appDownloadTask.i() != com.huawei.openalliance.ad.download.e.DOWNLOADED || !ae.b(appDownloadTask.d())) {
            return super.c((e) appDownloadTask);
        }
        ho.b("AppDownloadManager", appDownloadTask.B().getPackageName() + " is downloaded.");
        f(appDownloadTask);
        return true;
    }

    public void a(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(onResolutionRequiredListener);
    }

    public void a(AppDownloadListenerV1 appDownloadListenerV1) {
        this.i = appDownloadListenerV1;
        this.k.a(appDownloadListenerV1);
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appDownloadListenerV1);
    }

    public void a(AppDownloadListener appDownloadListener) {
        this.k.a(appDownloadListener);
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appDownloadListener);
    }

    public void a(AppInfo appInfo, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName()) || onResolutionRequiredListener == null) {
            return;
        }
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appInfo.getPackageName(), onResolutionRequiredListener);
    }

    public void a(AppInfo appInfo, com.huawei.openalliance.ad.download.l lVar) {
        if (appInfo != null) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appInfo.getPackageName(), lVar);
        }
        if (!f(appInfo)) {
            this.k.a(appInfo.getPackageName(), lVar);
        }
        if (com.huawei.openalliance.ad.utils.j.a(appInfo)) {
            com.huawei.openalliance.ad.download.ag.e.a(this.f6803a).a(appInfo.H(), lVar);
        }
    }

    public void a(AutoOpenListener autoOpenListener) {
        this.j = autoOpenListener;
        this.k.a(autoOpenListener);
        com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(this.j);
    }

    protected void a(DownloadTask.c cVar) {
        List<AppDownloadTask> d = this.e.d();
        if (ho.a()) {
            ho.a("AppDownloadManager", "pauseAllTask.begin, task.size:%d", Integer.valueOf(d.size()));
        }
        for (AppDownloadTask appDownloadTask : d) {
            if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING || appDownloadTask.i() == com.huawei.openalliance.ad.download.e.DOWNLOADED) {
                ho.b("AppDownloadManager", " can not pause task, status=" + appDownloadTask.i());
            } else {
                a((e) appDownloadTask, cVar);
            }
        }
        if (ho.a()) {
            ho.a("AppDownloadManager", "pauseAllTask.end, task.size:%d", Integer.valueOf(d.size()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        String str;
        try {
            String str2 = this.b + File.separator + "tmp" + File.separator;
            String[] list = new File(str2).list();
            if (list != null && list.length > 0) {
                a(str2, list);
            }
        } catch (RuntimeException unused) {
            str = "deleteTimeoutFile RuntimeException";
            ho.c("AppDownloadManager", str);
        } catch (Exception unused2) {
            str = "deleteTimeoutFile exception";
            ho.c("AppDownloadManager", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        List<AppDownloadTask> c = this.e.c();
        if (ho.a()) {
            ho.a("AppDownloadManager", "resumeAllTask, task.size:%d", Integer.valueOf(c.size()));
        }
        if (c.size() <= 0) {
            return;
        }
        a(c);
    }

    private AppDownloadTask j(AppInfo appInfo) {
        return new AppDownloadTask.a().a(false).a(appInfo).a(d(appInfo)).b(e(appInfo)).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppDownloadTask i(AppInfo appInfo) {
        AppDownloadTask j;
        boolean z;
        if (appInfo == null || (j = j(appInfo)) == null) {
            return null;
        }
        File file = new File(j.d());
        if (file.exists()) {
            z = false;
        } else {
            file = new File(j.e());
            if (!file.exists()) {
                return null;
            }
            z = true;
        }
        return a(appInfo, j, file, z);
    }

    private boolean h(AppInfo appInfo) {
        AppDownloadTask a2;
        return (appInfo == null || (a2 = a(appInfo.getPackageName())) == null || !a2.K()) ? false : true;
    }

    private boolean h(AppDownloadTask appDownloadTask) {
        ContentRecord R = appDownloadTask.R();
        if (R != null) {
            return new sn(this.f6803a, R).a();
        }
        return false;
    }

    public static e h() {
        e eVar;
        synchronized (f) {
            eVar = g;
            if (eVar == null) {
                throw new com.huawei.openalliance.ad.exception.b("AppDownloadManager instance is not init!");
            }
        }
        return eVar;
    }

    private static boolean g(AppInfo appInfo) {
        if (appInfo == null) {
            return true;
        }
        return appInfo.isCheckSha256() && TextUtils.isEmpty(appInfo.getSha256());
    }

    private boolean g(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return false;
        }
        while (appDownloadTask.J()) {
            ho.b("AppDownloadManager", "switch next install way succ, curInstallWay:%s", appDownloadTask.H());
            if (!appDownloadTask.K() || ao.c(this.f6803a)) {
                return true;
            }
        }
        ho.b("AppDownloadManager", "switch next install way fail, curInstallWay:%s", appDownloadTask.H());
        return false;
    }

    private static boolean f(AppInfo appInfo) {
        if (appInfo == null) {
            return true;
        }
        if ("11".equals(appInfo.getPriorInstallWay())) {
            return false;
        }
        return TextUtils.isEmpty(appInfo.getPackageName()) || TextUtils.isEmpty(appInfo.getDownloadUrl()) || g(appInfo) || appInfo.getFileSize() <= 0;
    }

    private void f(AppDownloadTask appDownloadTask) {
        b bVar = this.k;
        if (bVar != null) {
            bVar.f(appDownloadTask);
        }
    }

    private boolean e(AppDownloadTask appDownloadTask) {
        return "11".equals(appDownloadTask.H());
    }

    private String d(String str) {
        int indexOf = str.indexOf(".apk");
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return null;
    }

    private boolean c(String str) {
        return !TextUtils.isEmpty(str) && (str.equals("8") || str.equals("6") || str.equals("5"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Context context) {
        return cw.c(context) + File.separator + Constants.PPS_ROOT_PATH + File.separator + "apk";
    }

    private boolean a(AppInfo appInfo, boolean z) {
        i.a().a(appInfo);
        if (h(appInfo)) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).a(appInfo);
            a((e) a(appInfo.getPackageName()), z, true, false);
            return true;
        }
        if (f(appInfo)) {
            return false;
        }
        return a((e) a(appInfo.getPackageName()), z, true, true);
    }

    private void a(List<AppDownloadTask> list) {
        Collections.sort(list);
        for (AppDownloadTask appDownloadTask : list) {
            ho.b("AppDownloadManager", " task pause reason:" + appDownloadTask.p());
            if (appDownloadTask.p() == DownloadTask.c.NETWORK_CHANGED || appDownloadTask.p() == DownloadTask.c.WAITING_WIFI_DOWNLOAD) {
                a(appDownloadTask, false);
            }
        }
    }

    private void a(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str2.endsWith(".apk")) {
                File file = new File(str + str2);
                if (!file.isDirectory() && System.currentTimeMillis() - file.lastModified() > Constants.ANALYSIS_EVENT_KEEP_TIME) {
                    ho.b("AppDownloadManager", "remove timeout file");
                    AppDownloadTask a2 = a(d(str2));
                    if (a2 != null) {
                        c2(a2);
                    } else {
                        ae.e(file);
                    }
                }
            }
        }
    }

    private void a(String str, AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return;
        }
        if (c(str)) {
            com.huawei.openalliance.ad.download.ag.d.a(this.f6803a).b((com.huawei.openalliance.ad.download.ag.d) appDownloadTask);
        } else {
            if (f(appDownloadTask.B())) {
                return;
            }
            d((e) appDownloadTask);
        }
    }

    private void a(AppInfo appInfo, AppDownloadTask appDownloadTask) {
        AppInfo B = appDownloadTask.B();
        if (B != null) {
            ho.b("AppDownloadManager", "update appInfo from remote task.");
            appInfo.s(B.getUniqueId());
        }
    }

    public static void a(Context context) {
        synchronized (f) {
            if (g == null) {
                g = new e(context);
            }
        }
    }

    class a {
        private final Context b;
        private ConnectivityManager.NetworkCallback c = new ConnectivityManager.NetworkCallback() { // from class: com.huawei.openalliance.ad.download.app.e.a.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
                final Context applicationContext = a.this.b.getApplicationContext();
                if (ho.a()) {
                    ho.a("AppDownloadManager", "net onLost, active netType: %s, [0:UNKNOWN, 1:ETHERNET, 2:WIFI, 4/5/6/7:2G/3G/4G/5G]", Integer.valueOf(bv.d(applicationContext)));
                }
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.e.a.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                e.this.l();
                            } else {
                                if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                    return;
                                }
                                e.this.a(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("AppDownloadManager", "user info is not enabled");
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
                final Context applicationContext = a.this.b.getApplicationContext();
                if (ho.a()) {
                    ho.a("AppDownloadManager", "net onAvailable, active netType: %s, [0:UNKNOWN, 1:ETHERNET, 2:WIFI, 4/5/6/7:2G/3G/4G/5G]", Integer.valueOf(bv.d(applicationContext)));
                }
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.e.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                e.this.l();
                            } else {
                                if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                    return;
                                }
                                e.this.a(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("AppDownloadManager", "user info is not enabled");
                }
            }
        };

        public void a() {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.b.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0).addTransportType(3).addTransportType(1);
                connectivityManager.registerNetworkCallback(builder.build(), this.c);
            } catch (Throwable unused) {
                ho.c("AppDownloadManager", "register all network callback exception.");
            }
        }

        public a(Context context) {
            this.b = context;
        }
    }

    private AppDownloadTask a(AppInfo appInfo, AppDownloadTask appDownloadTask, File file, boolean z) {
        com.huawei.openalliance.ad.download.e eVar;
        long length = file.length();
        if (appInfo.getFileSize() != length) {
            if (appInfo.getFileSize() > length) {
                appDownloadTask.b((int) ((100 * length) / appInfo.getFileSize()));
                appDownloadTask.b(length);
                if (b((e) appDownloadTask)) {
                    eVar = com.huawei.openalliance.ad.download.e.IDLE;
                    appDownloadTask.a(eVar);
                    return appDownloadTask;
                }
                return null;
            }
            ae.a(file);
            return null;
        }
        if (!appInfo.isCheckSha256() || ae.a(appInfo.getSha256(), file)) {
            if (z && !ae.a(this.f6803a, file, appDownloadTask.d(), "normal")) {
                ae.a(file);
                return null;
            }
            appDownloadTask.b(100);
            appDownloadTask.b(appInfo.getFileSize());
            if (b((e) appDownloadTask)) {
                eVar = com.huawei.openalliance.ad.download.e.DOWNLOADED;
                appDownloadTask.a(eVar);
                return appDownloadTask;
            }
            return null;
        }
        ae.a(file);
        return null;
    }

    private e(final Context context) {
        super(context);
        String str;
        this.l = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.app.e.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (ho.a()) {
                    ho.a("AppDownloadManager", "netReceiver.onReceive, action:%s", intent.getAction());
                }
                final Context applicationContext = context2.getApplicationContext();
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.e.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                e.this.l();
                            } else {
                                if (bv.e(applicationContext) && bv.c(applicationContext)) {
                                    return;
                                }
                                e.this.a(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("AppDownloadManager", "user info is not enabled");
                }
            }
        };
        try {
            super.a();
            b bVar = new b(context);
            this.k = bVar;
            super.a(bVar);
            i.a(context);
            i.a().a(this.k);
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.b = e.c(context);
                }
            });
            a aVar = new a(this.f6803a);
            this.h = aVar;
            aVar.a();
        } catch (IllegalStateException unused) {
            str = "init IllegalStateException";
            ho.c("AppDownloadManager", str);
        } catch (Exception unused2) {
            str = "init exception";
            ho.c("AppDownloadManager", str);
        }
    }
}

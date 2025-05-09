package com.huawei.openalliance.ad.download.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.openalliance.ad.activity.PPSFullScreenNotifyActivity;
import com.huawei.openalliance.ad.activity.PPSInstallAuthorActivity;
import com.huawei.openalliance.ad.beans.inner.DownloadBlockInfo;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.i;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.lf;
import com.huawei.openalliance.ad.ll;
import com.huawei.openalliance.ad.mt;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ow;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bb;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bo;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class b implements com.huawei.openalliance.ad.download.a<AppDownloadTask>, i.c {
    private static Map<DownloadTask.b, Integer> k;

    /* renamed from: a, reason: collision with root package name */
    private Context f6743a;
    private ow b;
    private AppDownloadListener d;
    private AppDownloadListenerV1 f;
    private long g;
    private AutoOpenListener h;
    private a i;
    private Map<String, WeakHashMap<com.huawei.openalliance.ad.download.l, Object>> c = new ConcurrentHashMap();
    private String e = Constants.TIMEOUT_TASK_ID + hashCode();
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.download.app.b.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb;
            if (intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                ho.b("AppDownloadDelegate", "itRe action: %s", action);
                String dataString = intent.getDataString();
                if (TextUtils.isEmpty(dataString)) {
                    ho.c("AppDownloadDelegate", "itRe dataString is empty, " + action);
                } else {
                    final String substring = dataString.substring(8);
                    b.this.a(action, substring);
                    if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                b.this.p(e.h().a(substring));
                            }
                        });
                    }
                }
            } catch (IllegalStateException e) {
                e = e;
                sb = new StringBuilder("itRe:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AppDownloadDelegate", sb.toString());
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("itRe:");
                sb.append(e.getClass().getSimpleName());
                ho.c("AppDownloadDelegate", sb.toString());
            }
        }
    };

    @Override // com.huawei.openalliance.ad.download.app.i.c
    public void l(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return;
        }
        n(appDownloadTask);
        onDownloadProgress(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.app.i.c
    public void k(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null || appDownloadTask.i() != com.huawei.openalliance.ad.download.e.INSTALLED) {
            return;
        }
        n(appDownloadTask);
        p(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.app.i.c
    public void j(AppDownloadTask appDownloadTask) {
        if (appDownloadTask != null) {
            if (appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING || appDownloadTask.i() == com.huawei.openalliance.ad.download.e.DOWNLOADING) {
                AppStatus appStatus = appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING ? AppStatus.INSTALLING : AppStatus.DOWNLOADING;
                AppStatusV1 appStatusV1 = appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLING ? AppStatusV1.START_INSTALL : AppStatusV1.START_DOWNLOAD;
                n(appDownloadTask);
                if (appStatus == AppStatus.DOWNLOADING) {
                    onDownloadProgress(appDownloadTask);
                }
                a(appStatus, appDownloadTask.B());
                a(appStatusV1, 1000, appDownloadTask.B());
            }
        }
    }

    public boolean i(AppDownloadTask appDownloadTask) {
        return e.h().d(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public void onDownloadFail(final AppDownloadTask appDownloadTask) {
        Runnable runnable;
        qq C = appDownloadTask.C();
        if (C != null) {
            DownloadTask.b j = appDownloadTask.j();
            if (j == null) {
                j = DownloadTask.b.NONE;
            }
            C.b(appDownloadTask.F(), appDownloadTask.E(), j.a(), appDownloadTask.x(), appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        if (i(appDownloadTask)) {
            return;
        }
        n(appDownloadTask);
        if (appDownloadTask.j() != DownloadTask.b.NO_SPACE) {
            if (appDownloadTask.j() == DownloadTask.b.FILE_SIZE_ERROR || appDownloadTask.j() == DownloadTask.b.FILE_SHA256_ERROR) {
                runnable = new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(b.this.f6743a, b.this.f6743a.getString(R.string._2130851078_res_0x7f023506, appDownloadTask.B().getAppName()), 0).show();
                    }
                };
            }
            a(AppStatus.DOWNLOADFAILED, appDownloadTask.B());
            a(AppStatusV1.DOWNLOAD_FAILED, a(appDownloadTask.j()), appDownloadTask.B());
        }
        runnable = new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.5
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(b.this.f6743a, R.string._2130851083_res_0x7f02350b, 0).show();
            }
        };
        dj.a(runnable);
        a(AppStatus.DOWNLOADFAILED, appDownloadTask.B());
        a(AppStatusV1.DOWNLOAD_FAILED, a(appDownloadTask.j()), appDownloadTask.B());
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void b(final AppDownloadTask appDownloadTask) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.4
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(b.this.f6743a, b.this.f6743a.getString(R.string._2130851086_res_0x7f02350e, appDownloadTask.B().getAppName()), 0).show();
            }
        });
    }

    public void f(AppDownloadTask appDownloadTask) {
        ho.a("AppDownloadDelegate", "onAppStartInstall start");
        try {
            qq C = appDownloadTask.C();
            if (C == null) {
                ho.b("AppDownloadDelegate", "eventProcessor is null! continue install.");
                o(appDownloadTask);
                return;
            }
            ContentRecord a2 = C.a();
            if (a2 == null) {
                ho.b("AppDownloadDelegate", "contentRecord is null! continue install.");
                o(appDownloadTask);
                return;
            }
            if (bb.a(appDownloadTask, this.f6743a)) {
                if (dd.o(this.f6743a)) {
                    ho.b("AppDownloadDelegate", "start InstallAuthorActivity!");
                    a(a2);
                    if (a2.ae().C() == 1) {
                        appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                        n(appDownloadTask);
                        return;
                    }
                } else if (os.D(a2.V())) {
                    ho.b("AppDownloadDelegate", "app is background, stop install!");
                    appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                    n(appDownloadTask);
                    return;
                }
            }
            o(appDownloadTask);
        } catch (Throwable th) {
            ho.c("AppDownloadDelegate", "onAppStartInstall err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onDownloadSuccess(AppDownloadTask appDownloadTask) {
        qq C = appDownloadTask.C();
        this.g = fh.b(this.f6743a).f();
        if (C != null) {
            C.b(appDownloadTask.F(), appDownloadTask.E(), appDownloadTask.x(), appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        a();
        a(AppStatus.DOWNLOADED, appDownloadTask.B());
        a(AppStatusV1.DOWNLOAD_SUCCESS, 1000, appDownloadTask.B());
        f(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onDownloadProgress(AppDownloadTask appDownloadTask) {
        m(appDownloadTask);
        AppDownloadListener appDownloadListener = this.d;
        if (appDownloadListener != null) {
            appDownloadListener.onDownloadProgress(appDownloadTask.B(), appDownloadTask.l());
        } else {
            ho.b("AppDownloadDelegate", "progress listener empty");
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void c(AppDownloadTask appDownloadTask, boolean z) {
        AppStatusV1 appStatusV1;
        qq C;
        if (z && (C = appDownloadTask.C()) != null) {
            C.b(appDownloadTask.F(), appDownloadTask.E(), appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        if (z) {
            appStatusV1 = AppStatusV1.USER_RESUME;
        } else {
            r0 = (appDownloadTask.p() == DownloadTask.c.WAITING_WIFI_DOWNLOAD || appDownloadTask.p() == DownloadTask.c.NETWORK_CHANGED) ? 1017 : 1000;
            appStatusV1 = AppStatusV1.RESUME;
        }
        a(appStatusV1, r0, appDownloadTask.B());
        appDownloadTask.a(DownloadTask.c.NONE);
        n(appDownloadTask);
        a(AppStatus.RESUME, appDownloadTask.B());
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onDownloadStart(AppDownloadTask appDownloadTask) {
        qq C = appDownloadTask.C();
        if (C != null && appDownloadTask.g() <= 0) {
            C.a(appDownloadTask.F(), appDownloadTask.E(), appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        n(appDownloadTask);
        a(AppStatus.DOWNLOADING, appDownloadTask.B());
        if (appDownloadTask.l() > 0 || appDownloadTask.g() > 0) {
            return;
        }
        a(AppStatusV1.PRE_CHECK_SUCCESS, 1000, appDownloadTask.B());
        a(AppStatusV1.START_DOWNLOAD, 1000, appDownloadTask.B());
    }

    public void b(String str, com.huawei.openalliance.ad.download.l lVar) {
        synchronized (this) {
            WeakHashMap<com.huawei.openalliance.ad.download.l, Object> weakHashMap = this.c.get(str);
            if (weakHashMap != null && weakHashMap.size() > 0) {
                weakHashMap.remove(lVar);
                if (weakHashMap.size() <= 0) {
                    this.c.remove(str);
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void b(AppDownloadTask appDownloadTask, boolean z) {
        AppStatusV1 appStatusV1;
        int i;
        qq C = appDownloadTask.C();
        DownloadTask.c p = appDownloadTask.p();
        if (C != null) {
            int a2 = p != null ? p.a() : DownloadTask.c.NONE.a();
            DownloadBlockInfo x = appDownloadTask.x();
            if (x != null) {
                x.b(ao.c());
                appDownloadTask.y();
            }
            C.a(appDownloadTask.F(), appDownloadTask.E(), a2, x, appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        n(appDownloadTask);
        a(AppStatus.PAUSE, appDownloadTask.B());
        if (z) {
            appStatusV1 = AppStatusV1.USER_PAUSE;
            i = 1000;
        } else if (p == null || DownloadTask.c.NETWORK_CHANGED != p) {
            appStatusV1 = AppStatusV1.PAUSE;
            i = 1016;
        } else {
            appStatusV1 = AppStatusV1.PAUSE;
            i = 1015;
        }
        a(appStatusV1, i, appDownloadTask.B());
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(AppDownloadTask appDownloadTask) {
        n(appDownloadTask);
        a(AppStatus.WAITING_FOR_WIFI, appDownloadTask.B());
        a(AppStatusV1.WAITING_FOR_WIFI, 1000, appDownloadTask.B());
    }

    public void a(String str, com.huawei.openalliance.ad.download.l lVar) {
        synchronized (this) {
            WeakHashMap<com.huawei.openalliance.ad.download.l, Object> weakHashMap = this.c.get(str);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
                this.c.put(str, weakHashMap);
            }
            weakHashMap.put(lVar, null);
        }
    }

    public void a(AppDownloadListenerV1 appDownloadListenerV1) {
        this.f = appDownloadListenerV1;
    }

    public void a(AppDownloadListener appDownloadListener) {
        this.d = appDownloadListener;
    }

    public void a(AutoOpenListener autoOpenListener) {
        this.h = autoOpenListener;
    }

    @Override // com.huawei.openalliance.ad.download.a
    public void a(AppDownloadTask appDownloadTask, boolean z) {
        qq C;
        boolean z2 = z && appDownloadTask.i() != com.huawei.openalliance.ad.download.e.DOWNLOADED;
        if (z2 && (C = appDownloadTask.C()) != null) {
            DownloadBlockInfo x = appDownloadTask.x();
            if (x != null) {
                x.b(ao.c());
                appDownloadTask.y();
            }
            C.a(appDownloadTask.F(), appDownloadTask.E(), x, appDownloadTask.h().a(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
        appDownloadTask.b(0);
        appDownloadTask.b(0L);
        appDownloadTask.c(0L);
        appDownloadTask.a(com.huawei.openalliance.ad.download.e.FAILED);
        if (z2) {
            a(AppStatus.DOWNLOADFAILED, appDownloadTask.B());
            a(AppStatusV1.CANCEL_DOWNLOAD, 1000, appDownloadTask.B());
        }
        n(appDownloadTask);
    }

    @Override // com.huawei.openalliance.ad.download.a
    /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void onDownloadWaiting(AppDownloadTask appDownloadTask) {
        n(appDownloadTask);
        a(AppStatus.WAITING, appDownloadTask.B());
        a(AppStatusV1.WAITING, 1000, appDownloadTask.B());
    }

    public void a(Context context, ContentRecord contentRecord, AppDownloadTask appDownloadTask) {
        try {
            if (a(contentRecord, appDownloadTask)) {
                b(context, contentRecord, appDownloadTask);
            } else {
                ho.b("AppDownloadDelegate", "not show InstalledNotifyActivity");
            }
        } catch (Throwable th) {
            ho.c("AppDownloadDelegate", "start installed notify activity error: %s", th.getClass().getSimpleName());
        }
    }

    public void a() {
        ho.b("AppDownloadDelegate", "registerAppInstReceiver");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        ao.a(this.f6743a, this.j, intentFilter);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null || appDownloadTask.L() != AppDownloadTask.b.DOWN_LOAD_MODE_FROM_SELF) {
            ho.b("AppDownloadDelegate", "task is empty");
            return;
        }
        appDownloadTask.a(com.huawei.openalliance.ad.download.e.INSTALLED);
        final qq C = appDownloadTask.C();
        if (C != null) {
            C.a(Integer.valueOf(appDownloadTask.D()), appDownloadTask.F(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
            new com.huawei.openalliance.ad.analysis.c(this.f6743a).a(C.a(), (String) null);
        }
        final AppInfo B = appDownloadTask.B();
        if (B == null) {
            return;
        }
        String packageName = B.getPackageName();
        e.h().c2(appDownloadTask);
        final String E = appDownloadTask.E();
        a(packageName, E, B);
        a(C, B);
        ho.b("AppDownloadDelegate", "onAppInstalled, popNotify: %s", Integer.valueOf(B.m()));
        ho.b("AppDownloadDelegate", "onAppInstalled, fullScrnNotify: %s", Integer.valueOf(B.n()));
        if (B.m() == 1 && appDownloadTask.T()) {
            a(appDownloadTask, B);
        }
        if (C != null && B.n() != 0 && bz.b(this.f6743a)) {
            a(this.f6743a, C.a(), appDownloadTask);
        }
        ho.b("AppDownloadDelegate", "auto open app");
        a(packageName, appDownloadTask, B);
        if (C != null) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.7
                @Override // java.lang.Runnable
                public void run() {
                    new com.huawei.openalliance.ad.analysis.c(b.this.f6743a).a(C.a(), B.e(), E);
                }
            });
        }
        a(AppStatus.INSTALLED, appDownloadTask.B());
        a(AppStatusV1.INSTALL_SUCCESS, 1000, appDownloadTask.B());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(final AppDownloadTask appDownloadTask) {
        ho.a("AppDownloadDelegate", "installApp start");
        a(AppStatusV1.START_INSTALL, 1000, appDownloadTask.B());
        this.b.a(appDownloadTask.B(), appDownloadTask, new ow.a() { // from class: com.huawei.openalliance.ad.download.app.b.3
            @Override // com.huawei.openalliance.ad.ow.a
            public void b() {
                ho.c("AppDownloadDelegate", "onSystemInstallStart");
                appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                b.this.n(appDownloadTask);
                b.this.a(AppStatus.INSTALL, appDownloadTask.B());
            }

            @Override // com.huawei.openalliance.ad.ow.a
            public void a(int i) {
                b bVar;
                AppStatus appStatus;
                ho.c("AppDownloadDelegate", "install apk failed, reason: %s", Integer.valueOf(i));
                b.this.a(AppStatusV1.INSTALL_FAILED, -1000, appDownloadTask.B());
                if (i != 1 && b.this.i(appDownloadTask)) {
                    return;
                }
                appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                if (b.this.d != null) {
                    if (ae.b(appDownloadTask.d())) {
                        bVar = b.this;
                        appStatus = AppStatus.INSTALL;
                    } else {
                        appDownloadTask.b(0);
                        appDownloadTask.b(0L);
                        appDownloadTask.c(0L);
                        appDownloadTask.a(com.huawei.openalliance.ad.download.e.FAILED);
                        bVar = b.this;
                        appStatus = AppStatus.DOWNLOAD;
                    }
                    bVar.a(appStatus, appDownloadTask.B());
                }
                b.this.n(appDownloadTask);
            }

            @Override // com.huawei.openalliance.ad.ow.a
            public void a() {
                ho.c("AppDownloadDelegate", "onSilentInstallStart");
                appDownloadTask.a(com.huawei.openalliance.ad.download.e.INSTALLING);
                b.this.n(appDownloadTask);
                b.this.a(AppStatus.INSTALLING, appDownloadTask.B());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(AppDownloadTask appDownloadTask) {
        WeakHashMap<com.huawei.openalliance.ad.download.l, Object> a2 = a(appDownloadTask.B());
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        for (com.huawei.openalliance.ad.download.l lVar : a2.keySet()) {
            if (lVar != null) {
                lVar.b(appDownloadTask);
            }
        }
    }

    private void m(AppDownloadTask appDownloadTask) {
        WeakHashMap<com.huawei.openalliance.ad.download.l, Object> a2 = a(appDownloadTask.B());
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        for (com.huawei.openalliance.ad.download.l lVar : a2.keySet()) {
            if (lVar != null) {
                lVar.a(appDownloadTask);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ho.b("AppDownloadDelegate", "unRegisterAppInstReceiver");
        this.f6743a.unregisterReceiver(this.j);
    }

    private void c() {
        bo.a(this.e);
        bo.a(new RunnableC0179b(this), this.e, this.g);
    }

    private void b(Context context, ContentRecord contentRecord, AppDownloadTask appDownloadTask) {
        Intent intent = new Intent(context, (Class<?>) PPSFullScreenNotifyActivity.class);
        intent.setFlags(HiUserInfo.DATA_CLOUD);
        intent.putExtra(Constants.CONTENT_KEY, be.b(contentRecord));
        intent.putExtra("unique_id", (appDownloadTask == null || appDownloadTask.B() == null || appDownloadTask.B().getUniqueId() == null) ? "" : appDownloadTask.B().getUniqueId());
        if (appDownloadTask != null) {
            intent.putExtra("download_source", appDownloadTask.F());
        }
        intent.setClipData(Constants.CLIP_DATA);
        context.startActivity(intent);
    }

    private void b() {
        this.i = new a();
        ao.a(this.f6743a, this.i, new IntentFilter("com.huawei.hms.pps.action.PPS_APP_OPEN"), Constants.PERMISSION_PPS_DOWNLOAD, null);
    }

    private boolean a(ContentRecord contentRecord, AppDownloadTask appDownloadTask) {
        String str;
        if (appDownloadTask != null && appDownloadTask.D() == 2) {
            ho.b("AppDownloadDelegate", "not show fullScreen dialog: install source is 2");
            return false;
        }
        com.huawei.openalliance.ad.analysis.c cVar = new com.huawei.openalliance.ad.analysis.c(this.f6743a);
        if (!HiAd.getInstance(this.f6743a).isAppInstalledNotify()) {
            ho.b("AppDownloadDelegate", "not show fullScreen dialog: media not allow show dialog");
            cVar.a(contentRecord, "6", (com.huawei.openalliance.ad.analysis.a) null);
            return false;
        }
        if (dd.o(this.f6743a)) {
            str = "show fullScreen dialog: app status is Foreground and media allow show dialog";
        } else {
            if (HiAd.a(this.f6743a).l() == null || !HiAd.a(this.f6743a).l().isForeground()) {
                ho.b("AppDownloadDelegate", "not show fullScreen dialog: app status is not Foreground and Callback is false");
                cVar.a(contentRecord, "7", (com.huawei.openalliance.ad.analysis.a) null);
                return false;
            }
            str = "show fullScreen dialog: get the app status is Foreground Through Callback";
        }
        ho.b("AppDownloadDelegate", str);
        return true;
    }

    private void a(final String str, String str2, final AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.e()) || !"3".equalsIgnoreCase(str2)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.9
            @Override // java.lang.Runnable
            public void run() {
                mt.a(b.this.f6743a).a(str, appInfo.e(), appInfo.f());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        WeakHashMap<com.huawei.openalliance.ad.download.l, Object> a2 = a(str2);
        if (a2 != null && a2.size() > 0) {
            if ("android.intent.action.PACKAGE_ADDED".equals(str)) {
                for (com.huawei.openalliance.ad.download.l lVar : a2.keySet()) {
                    if (lVar != null) {
                        lVar.a(str2);
                    }
                }
            } else if ("android.intent.action.PACKAGE_REMOVED".equals(str)) {
                for (com.huawei.openalliance.ad.download.l lVar2 : a2.keySet()) {
                    if (lVar2 != null) {
                        lVar2.b(str2);
                    }
                }
            }
        }
        if ("android.intent.action.PACKAGE_REMOVED".equals(str)) {
            if (this.d == null) {
                ho.b("AppDownloadDelegate", "listener empty");
                return;
            }
            AppInfo appInfo = new AppInfo();
            appInfo.a(str2);
            this.d.onStatusChanged(AppStatus.DOWNLOAD, appInfo);
        }
    }

    private void a(String str, AppDownloadTask appDownloadTask, AppInfo appInfo) {
        Context context = this.f6743a;
        if (context == null || TextUtils.isEmpty(context.getPackageName()) || !Constants.HW_BROWSER_PACKAGE.equals(this.f6743a.getPackageName())) {
            ho.b("AppDownloadDelegate", "auto open app from other");
        } else {
            ho.b("AppDownloadDelegate", "auto open app from browser");
            AutoOpenListener autoOpenListener = this.h;
            if (autoOpenListener != null && !autoOpenListener.isNeedAutoOpen(appInfo)) {
                return;
            }
        }
        a(this.f6743a, str, appDownloadTask);
    }

    private void a(qq qqVar, AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName()) || qqVar == null || fh.b(this.f6743a).bz() != 1) {
            return;
        }
        dj.a(new AnonymousClass8(appInfo, qqVar), 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppStatusV1 appStatusV1, int i, AppInfo appInfo) {
        if (this.f == null) {
            ho.b("AppDownloadDelegate", "listener v1 empty");
            return;
        }
        ho.b("AppDownloadDelegate", "task status:" + appStatusV1 + ", code:" + i);
        this.f.onNewStatusChanged(appStatusV1, i, appInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppStatus appStatus, AppInfo appInfo) {
        if (this.d == null) {
            ho.b("AppDownloadDelegate", "app status empty");
            return;
        }
        ho.b("AppDownloadDelegate", "task status:" + appStatus);
        this.d.onStatusChanged(appStatus, appInfo);
    }

    private void a(AppDownloadTask appDownloadTask, AppInfo appInfo) {
        if (TextUtils.isEmpty(appInfo.d())) {
            ho.b("AppDownloadDelegate", "popUpAfterInstallText is blank");
        } else {
            new lf(this.f6743a, appDownloadTask).b();
        }
    }

    private void a(ContentRecord contentRecord) {
        ho.b("AppDownloadDelegate", "startInstallAuthorActivity");
        try {
            Intent intent = new Intent(this.f6743a, (Class<?>) PPSInstallAuthorActivity.class);
            intent.setFlags(HiUserInfo.DATA_CLOUD);
            intent.putExtra(Constants.CONTENT_KEY, be.b(contentRecord));
            PPSInstallAuthorActivity.a(new com.huawei.openalliance.ad.views.interfaces.g() { // from class: com.huawei.openalliance.ad.download.app.b.2
                @Override // com.huawei.openalliance.ad.views.interfaces.g
                public void a(AppDownloadTask appDownloadTask) {
                    if (appDownloadTask != null) {
                        b.this.o(appDownloadTask);
                    }
                }
            });
            intent.setClipData(Constants.CLIP_DATA);
            this.f6743a.startActivity(intent);
        } catch (Throwable th) {
            ho.b("AppDownloadDelegate", "startInstallAuthorActivity failed %s", th.getClass().getSimpleName());
        }
    }

    private void a(Context context, String str, AppDownloadTask appDownloadTask) {
        if (str == null || appDownloadTask == null || appDownloadTask.B() == null) {
            ho.c("AppDownloadDelegate", "auto open invalid para.");
            return;
        }
        if (fh.b(context).bX()) {
            ho.b("AppDownloadDelegate", "media forbidden auto open after install.");
            return;
        }
        if (appDownloadTask.B().A() != 1) {
            ho.b("AppDownloadDelegate", "no need auto open.");
            return;
        }
        String packageName = context.getPackageName();
        if (!dd.o(context)) {
            ho.b("AppDownloadDelegate", "no need auto open due to caller background, caller:%s", packageName);
            return;
        }
        f fVar = new f();
        ho.b("AppDownloadDelegate", "sdk auto open app package sdk target %s.", str);
        fVar.a(context, appDownloadTask.B(), appDownloadTask.C(), appDownloadTask.F(), false);
    }

    private WeakHashMap<com.huawei.openalliance.ad.download.l, Object> a(String str) {
        WeakHashMap<com.huawei.openalliance.ad.download.l, Object> weakHashMap;
        synchronized (this) {
            weakHashMap = this.c.get(str);
        }
        return weakHashMap;
    }

    static class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ho.b("AppDownloadDelegate", "onReceive.");
            if (intent == null) {
                return;
            }
            try {
                if ("com.huawei.hms.pps.action.PPS_APP_OPEN".equals(intent.getAction())) {
                    AppInfo appInfo = (AppInfo) be.b(intent.getStringExtra("appInfo"), AppInfo.class, new Class[0]);
                    if (appInfo == null) {
                        ho.b("AppDownloadDelegate", "appInfo is null");
                        return;
                    }
                    d a2 = d.a();
                    if (a2 != null) {
                        a2.onAppOpen(appInfo);
                    }
                }
            } catch (Throwable th) {
                ho.c("AppDownloadDelegate", "exception: %s", th.getClass().getSimpleName());
            }
        }

        private a() {
        }
    }

    private WeakHashMap<com.huawei.openalliance.ad.download.l, Object> a(AppInfo appInfo) {
        synchronized (this) {
            if (appInfo != null) {
                if (!TextUtils.isEmpty(appInfo.getPackageName())) {
                    return a(appInfo.getPackageName());
                }
            }
            return null;
        }
    }

    /* renamed from: com.huawei.openalliance.ad.download.app.b$8, reason: invalid class name */
    class AnonymousClass8 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AppInfo f6752a;
        final /* synthetic */ qq b;

        @Override // java.lang.Runnable
        public void run() {
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.b.8.1
                @Override // java.lang.Runnable
                public void run() {
                    mt.a(b.this.f6743a).a(AnonymousClass8.this.f6752a.getPackageName(), new mt.a() { // from class: com.huawei.openalliance.ad.download.app.b.8.1.1
                        @Override // com.huawei.openalliance.ad.mt.a
                        public void a(String str, String str2) {
                            new com.huawei.openalliance.ad.analysis.c(b.this.f6743a).a(AnonymousClass8.this.b.a(), AnonymousClass8.this.f6752a.e(), str2, AnonymousClass8.this.f6752a.getPackageName());
                        }
                    });
                }
            });
        }

        AnonymousClass8(AppInfo appInfo, qq qqVar) {
            this.f6752a = appInfo;
            this.b = qqVar;
        }
    }

    /* renamed from: com.huawei.openalliance.ad.download.app.b$b, reason: collision with other inner class name */
    static class RunnableC0179b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<b> f6756a;

        @Override // java.lang.Runnable
        public void run() {
            b bVar = this.f6756a.get();
            if (bVar == null) {
                return;
            }
            bVar.d();
        }

        public RunnableC0179b(b bVar) {
            this.f6756a = new WeakReference<>(bVar);
        }
    }

    private int a(DownloadTask.b bVar) {
        Integer num = k.get(bVar);
        if (num == null) {
            return -1000;
        }
        return num.intValue();
    }

    public b(Context context) {
        String str;
        Context applicationContext = context.getApplicationContext();
        this.f6743a = applicationContext;
        try {
            this.b = ow.a(applicationContext);
            ll.a(context).a();
            b();
        } catch (IllegalStateException unused) {
            str = "registerReceiver IllegalStateException";
            ho.c("AppDownloadDelegate", str);
        } catch (Exception unused2) {
            str = "registerReceiver Exception";
            ho.c("AppDownloadDelegate", str);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        k = hashMap;
        hashMap.put(DownloadTask.b.NO_SPACE, 1010);
        k.put(DownloadTask.b.CONN_FAILED, 1011);
        k.put(DownloadTask.b.FILE_SIZE_ERROR, 1012);
        k.put(DownloadTask.b.FILE_SHA256_ERROR, 1013);
        k.put(DownloadTask.b.MOBILE_NETWORK, 1014);
    }
}

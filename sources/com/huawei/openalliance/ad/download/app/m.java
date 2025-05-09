package com.huawei.openalliance.ad.download.app;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.openalliance.ad.Cdo;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.h;
import com.huawei.openalliance.ad.download.app.i;
import com.huawei.openalliance.ad.dq;
import com.huawei.openalliance.ad.es;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.pi;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.su;
import com.huawei.openalliance.ad.tf;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.openalliance.ad.views.PPSPlacementView;

/* loaded from: classes5.dex */
public class m implements com.huawei.openalliance.ad.download.app.interfaces.a {

    /* renamed from: a, reason: collision with root package name */
    private final e f6792a;
    private String d;
    private AppDownloadListenerV1 e;
    private int b = 2;
    private Integer c = 6;
    private final j f = new j();

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public AppStatus f(Context context, IAd iAd) {
        if (!h(context, iAd)) {
            return AppStatus.DOWNLOAD;
        }
        AppInfo appInfo = iAd.getAppInfo();
        if (appInfo == null) {
            ho.c("PPSAppDownloadManager", "appInfo is null.");
            return AppStatus.DOWNLOAD;
        }
        if (com.huawei.openalliance.ad.utils.i.a(context, appInfo.getPackageName())) {
            ho.b("PPSAppDownloadManager", "app installed");
            return AppStatus.INSTALLED;
        }
        if (!b(appInfo)) {
            return AppStatus.DOWNLOAD;
        }
        AppDownloadTask c = this.f6792a.c(appInfo);
        b(context, iAd, c);
        if (c == null) {
            return AppStatus.DOWNLOAD;
        }
        c.m(iAd.getContentId());
        return com.huawei.openalliance.ad.utils.j.a(c);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public int e(Context context, IAd iAd) {
        if (!g(context, iAd)) {
            return 0;
        }
        AppDownloadTask c = this.f6792a.c(iAd.getAppInfo());
        if (c != null) {
            return c.l();
        }
        ho.b("PPSAppDownloadManager", "task is not exist.");
        return 0;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void d(Context context, IAd iAd) {
        Integer num;
        if (g(context, iAd)) {
            if (!this.f.a(context, iAd, false)) {
                ho.b("PPSAppDownloadManager", "cancelDownload has not permission");
                return;
            }
            AppInfo appInfo = iAd.getAppInfo();
            if (appInfo == null) {
                ho.c("PPSAppDownloadManager", "appInfo is null.");
                return;
            }
            AppDownloadTask a2 = this.f6792a.a(appInfo.getPackageName());
            if (a2 == null || a2.i() == com.huawei.openalliance.ad.download.e.INSTALLING) {
                return;
            }
            a(context, iAd, a2);
            a(iAd, a2);
            if (this.f6792a.b(appInfo) && (num = this.c) != null) {
                if (num.intValue() == 14 || this.c.intValue() == 10005) {
                    b(context, appInfo);
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public int c(Context context, IAd iAd) {
        if (!g(context, iAd)) {
            return -1;
        }
        if (m(context, iAd)) {
            ho.b("PPSAppDownloadManager", "open landing page action");
            return -4;
        }
        if (!this.f.a(context, iAd, false)) {
            ho.b("PPSAppDownloadManager", "pauseDownload has not permission");
            return -2;
        }
        AppInfo appInfo = iAd.getAppInfo();
        if (appInfo == null) {
            ho.c("PPSAppDownloadManager", "appInfo is null.");
            return -1;
        }
        AppDownloadTask a2 = this.f6792a.a(appInfo.getPackageName());
        if (ho.a()) {
            Object[] objArr = new Object[1];
            objArr[0] = (a2 == null || a2.B() == null) ? null : a2.B().getUniqueId();
            ho.a("PPSAppDownloadManager", "pauseDownload, taskAppInfoId: %s", objArr);
            ho.a("PPSAppDownloadManager", "pauseDownload, adUniqueId: %s", iAd.getUniqueId());
        }
        b(context, iAd, a2);
        if (a2 != null) {
            a(context, iAd, a2);
            a(iAd, a2);
            this.f6792a.b(a2);
        }
        return 1;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void b() {
        this.f6792a.k();
    }

    public int b(final Context context, jk jkVar, final IAd iAd) {
        AppInfo appInfo = iAd.getAppInfo();
        this.f6792a.a(appInfo);
        ou l = l(context, iAd);
        if (l == null) {
            return -1;
        }
        AppDownloadTask a2 = a(iAd, l);
        if (a2 == null) {
            ho.b("PPSAppDownloadManager", "failed when create task");
            return -1;
        }
        a(iAd, a2);
        if (!c()) {
            g(context, jkVar, iAd);
            a(context, l, appInfo);
        }
        this.f6792a.b((e) a2);
        i.a(context);
        i.a().a(a2, new i.b() { // from class: com.huawei.openalliance.ad.download.app.m.5
            @Override // com.huawei.openalliance.ad.download.app.i.b
            public void a(AppDownloadTask appDownloadTask) {
                if (appDownloadTask == null || appDownloadTask.i() == com.huawei.openalliance.ad.download.e.INSTALLED || !appDownloadTask.J()) {
                    return;
                }
                m.this.a(context, (jk) null, iAd, false);
            }
        });
        return 0;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public int b(Context context, IAd iAd) {
        if (m(context, iAd)) {
            ho.b("PPSAppDownloadManager", "open landing page action");
            return -4;
        }
        int b = b(context, iAd, false);
        return b != 0 ? b : a(context, (jk) null, iAd, true);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public boolean a(Context context, IAd iAd, boolean z) {
        return z ? g(context, iAd) && n(context, iAd) : n(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void a(Integer num) {
        this.c = num;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void a(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        if (onResolutionRequiredListener == null) {
            return;
        }
        this.f6792a.a(onResolutionRequiredListener);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void a(final Context context, final IAd iAd, final int i) {
        if (context == null || iAd == null) {
            ho.c("PPSAppDownloadManager", "param err");
        } else {
            com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.m.8
                @Override // java.lang.Runnable
                public void run() {
                    new com.huawei.openalliance.ad.analysis.c(context).a(context, iAd, i);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void a(int i) {
        this.b = i;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public void a() {
        this.f6792a.j();
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public int a(Context context, jk jkVar, IAd iAd) {
        if (n(context, iAd)) {
            ho.b("PPSAppDownloadManager", "app is installed, open it.");
            return 0;
        }
        int c = c(context, jkVar, iAd);
        if (c == 0) {
            return d(context, jkVar, iAd);
        }
        if (iAd != null) {
            a(AppStatusV1.PRE_CHECK_FAILED, -1000, iAd.getAppInfo());
        }
        return c;
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.a
    public int a(Context context, IAd iAd) {
        if (n(context, iAd)) {
            ho.b("PPSAppDownloadManager", "app is installed, open it.");
            return 0;
        }
        if (m(context, iAd)) {
            ho.b("PPSAppDownloadManager", "open landing page action");
            return -4;
        }
        int b = b(context, iAd, a(iAd));
        if (b == 0) {
            return d(context, null, iAd);
        }
        if (iAd != null) {
            a(AppStatusV1.PRE_CHECK_FAILED, -1000, iAd.getAppInfo());
        }
        return b;
    }

    private boolean n(Context context, IAd iAd) {
        String str;
        if (iAd == null || iAd.getAppInfo() == null) {
            str = "param is empty";
        } else {
            ou l = l(context, iAd);
            if (l == null) {
                str = "event processor failed when open app";
            } else {
                AppInfo appInfo = iAd.getAppInfo();
                if (iAd instanceof com.huawei.openalliance.ad.inter.data.i) {
                    com.huawei.openalliance.ad.inter.data.i iVar = (com.huawei.openalliance.ad.inter.data.i) iAd;
                    if (!TextUtils.isEmpty(iVar.b())) {
                        appInfo.s(iVar.b());
                    }
                }
                if (com.huawei.openalliance.ad.utils.i.a(context, appInfo.getPackageName())) {
                    tf.a aVar = new tf.a();
                    aVar.a(iAd.getContentId()).b(iAd.getTaskId()).a(appInfo);
                    boolean a2 = com.huawei.openalliance.ad.utils.i.a(context, appInfo.getPackageName(), appInfo.getIntentUri(), aVar.a());
                    MaterialClickInfo materialClickInfo = new MaterialClickInfo();
                    materialClickInfo.f(1);
                    b.a aVar2 = new b.a();
                    aVar2.b("app").a((Integer) 6).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(context));
                    if (a2) {
                        a(context, appInfo);
                        l.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null);
                        if (!c()) {
                            l.a(aVar2.a());
                            g(context, null, iAd);
                        }
                        return true;
                    }
                    ho.b("PPSAppDownloadManager", "handleClick, openAppIntent failed");
                    l.a(EventType.INTENTFAIL, (Integer) 1, (Integer) 2);
                    if (com.huawei.openalliance.ad.utils.i.a(context, appInfo.getPackageName(), aVar.a())) {
                        l.a((Integer) 6);
                        a(context, appInfo);
                        if (!c()) {
                            l.a(aVar2.a());
                            g(context, null, iAd);
                        }
                        return true;
                    }
                    str = "handleClick, openAppMainPage failed";
                } else {
                    str = "app not installed, need download";
                }
            }
        }
        ho.b("PPSAppDownloadManager", str);
        return false;
    }

    private boolean m(Context context, IAd iAd) {
        if (context != null && iAd != null) {
            AppInfo appInfo = iAd.getAppInfo();
            if (appInfo == null) {
                ho.b("PPSAppDownloadManager", "failed to get app info when open landing page");
                return false;
            }
            if ("21".equals(appInfo.a())) {
                return new su(context, b(iAd), true, iAd instanceof com.huawei.openalliance.ad.inter.data.e ? ((com.huawei.openalliance.ad.inter.data.e) iAd).ah() : null).a();
            }
        }
        return false;
    }

    private ou l(Context context, IAd iAd) {
        ContentRecord b = b(iAd);
        if (b == null) {
            ho.b("PPSAppDownloadManager", "contentRecord is empty when convert from nativeAd");
            return null;
        }
        ou ouVar = new ou(context, sc.a(context, b.e()));
        ouVar.a(b);
        return ouVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int k(Context context, IAd iAd) {
        if (n(context, iAd)) {
            ho.b("PPSAppDownloadManager", "app is installed, open it.");
            return 0;
        }
        ho.b("PPSAppDownloadManager", "preWifiDownload");
        AppDownloadTask b = b(context, b(iAd), iAd.getAppInfo());
        b(context, iAd, b);
        if (b == null) {
            b = a(iAd, l(context, iAd));
            this.f6792a.b((e) b);
        }
        if (b != null) {
            b.a(DownloadTask.a.WAITING_WIFI_DOWNLOAD);
        }
        AppStatus f = f(context, iAd);
        if (f != AppStatus.DOWNLOAD && f != AppStatus.WAITING_FOR_WIFI && f != AppStatus.PAUSE) {
            return -1;
        }
        this.f6792a.e((e) b);
        return 0;
    }

    private int j(Context context, IAd iAd) {
        AppDownloadTask c = this.f6792a.c(iAd.getAppInfo());
        if (ho.a()) {
            Object[] objArr = new Object[1];
            objArr[0] = (c == null || c.B() == null) ? null : c.B().getUniqueId();
            ho.a("PPSAppDownloadManager", "resumeDownload, taskAppInfoId: %s", objArr);
            ho.a("PPSAppDownloadManager", "resumeDownload, adUniqueId: %s", iAd.getUniqueId());
        }
        b(context, iAd, c);
        if (c == null) {
            ho.b("PPSAppDownloadManager", "app download info is empty, must first invoke startDownload method");
            return -1;
        }
        a(context, iAd, c);
        c.a(iAd.getAppInfo().isAllowedNonWifiNetwork());
        a(iAd, c);
        return this.f6792a.a(c, true) ? 0 : -1;
    }

    private boolean i(Context context, IAd iAd) {
        AppInfo appInfo = iAd.getAppInfo();
        if (appInfo != null) {
            return appInfo.u() && appInfo.isPermPromptForCard() && f(context, iAd) == AppStatus.DOWNLOAD;
        }
        ho.c("PPSAppDownloadManager", "appInfo is null.");
        return false;
    }

    private boolean h(Context context, IAd iAd) {
        String str;
        if (context == null || iAd == null) {
            return false;
        }
        boolean z = iAd instanceof com.huawei.openalliance.ad.inter.data.i;
        if (!(iAd instanceof com.huawei.openalliance.ad.inter.data.e) && !(iAd instanceof com.huawei.openalliance.ad.inter.data.g) && !z) {
            str = "ad is not native or placement ad when start download";
        } else {
            if (iAd.isAdIdInWhiteList() || z) {
                return true;
            }
            str = "download has not permission, please add white list";
        }
        ho.b("PPSAppDownloadManager", str);
        return false;
    }

    private boolean g(Context context, IAd iAd) {
        return h(context, iAd) && b(iAd.getAppInfo());
    }

    private void g(Context context, jk jkVar, IAd iAd) {
        if (jkVar != null) {
            if (jkVar instanceof PPSNativeView) {
                ((PPSNativeView) jkVar).a((Integer) 6);
            }
            if (jkVar instanceof PPSPlacementView) {
                ((PPSPlacementView) jkVar).a((Integer) 6);
                return;
            }
            return;
        }
        if (iAd != null) {
            String showId = iAd instanceof com.huawei.openalliance.ad.inter.data.i ? ((com.huawei.openalliance.ad.inter.data.i) iAd).getShowId() : null;
            if (showId == null || !showId.equals(this.d)) {
                this.d = showId;
                ContentRecord b = b(iAd);
                ou ouVar = new ou(context, sc.a(context, b.e()));
                b.c(iAd.getShowId());
                ouVar.a(b);
                a.C0207a c0207a = new a.C0207a();
                c0207a.a(Long.valueOf(iAd.getMinEffectiveShowTime())).a(Integer.valueOf(iAd.getMinEffectiveShowRatio())).b((Integer) 6).a(com.huawei.openalliance.ad.utils.b.a(context));
                ouVar.a(c0207a.a());
            }
        }
    }

    private int f(Context context, jk jkVar, IAd iAd) {
        AppInfo appInfo = iAd.getAppInfo();
        AppDownloadTask c = this.f6792a.c(appInfo);
        b(context, iAd, c);
        if (c != null) {
            c.a(appInfo.isAllowedNonWifiNetwork());
            a(iAd, c);
            a(context, iAd, c);
            return this.f6792a.a(c, true) ? 0 : -1;
        }
        ou l = l(context, iAd);
        if (l == null) {
            return -1;
        }
        AppDownloadTask a2 = a(iAd, l);
        if (a2 == null) {
            ho.b("PPSAppDownloadManager", "failed when create task");
            return -1;
        }
        a2.a(appInfo.isAllowedNonWifiNetwork());
        a(iAd, a2);
        if (!c()) {
            g(context, jkVar, iAd);
            a(context, l, appInfo);
        }
        return this.f6792a.c(a2) ? 0 : -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(Context context, jk jkVar, IAd iAd) {
        return a(iAd) ? b(context, jkVar, iAd) : a(context, jkVar, iAd, false);
    }

    private int d(final Context context, final jk jkVar, final IAd iAd) {
        if (!i(context, iAd)) {
            return e(context, jkVar, iAd);
        }
        h.a(context, iAd.getAppInfo(), new h.a() { // from class: com.huawei.openalliance.ad.download.app.m.2
            @Override // com.huawei.openalliance.ad.download.app.h.a
            public void a(int i) {
            }

            @Override // com.huawei.openalliance.ad.download.app.h.a
            public void a() {
                m.this.e(context, jkVar, iAd);
            }
        });
        return -3;
    }

    private boolean c(AppInfo appInfo) {
        if (appInfo == null) {
            return false;
        }
        return appInfo.a(this.c);
    }

    private boolean c() {
        return this.c.intValue() == 14 || this.c.intValue() == 10005;
    }

    private int c(IAd iAd) {
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.e) {
            return 3;
        }
        return iAd instanceof com.huawei.openalliance.ad.inter.data.g ? 60 : -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(Context context, jk jkVar, IAd iAd, boolean z) {
        if (!n(context, iAd)) {
            return z ? j(context, iAd) : f(context, jkVar, iAd);
        }
        ho.b("PPSAppDownloadManager", "app is installed, open it.");
        return 0;
    }

    private int c(Context context, jk jkVar, IAd iAd) {
        String str;
        if (context != null && iAd != null && jkVar != null && (jkVar instanceof com.huawei.openalliance.ad.views.interfaces.f)) {
            com.huawei.openalliance.ad.views.interfaces.f fVar = (com.huawei.openalliance.ad.views.interfaces.f) jkVar;
            if (fVar.getAd() == null || fVar.getAd() != iAd) {
                str = "make sure the registered ad of view is same";
            } else {
                if (fVar.d()) {
                    return b(context, iAd, a(iAd));
                }
                str = "make sure ad view is visibility";
            }
            ho.b("PPSAppDownloadManager", str);
        }
        return -1;
    }

    private boolean b(AppInfo appInfo) {
        String str;
        if (com.huawei.openalliance.ad.utils.j.a(appInfo, this.c)) {
            str = "appInfo is invalid";
        } else {
            if (this.f6792a != null) {
                return true;
            }
            str = "download manager is not init";
        }
        ho.b("PPSAppDownloadManager", str);
        return false;
    }

    private void b(final Context context, IAd iAd, AppDownloadTask appDownloadTask) {
        if (context == null || appDownloadTask == null || appDownloadTask.B() == null || !(iAd instanceof com.huawei.openalliance.ad.inter.data.i)) {
            return;
        }
        final com.huawei.openalliance.ad.inter.data.i iVar = (com.huawei.openalliance.ad.inter.data.i) iAd;
        String uniqueId = appDownloadTask.B().getUniqueId();
        if (TextUtils.equals(uniqueId, iVar.b())) {
            return;
        }
        iVar.a(uniqueId);
        ho.b("PPSAppDownloadManager", "update uniqueId according to appInfo.");
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.m.4
            @Override // java.lang.Runnable
            public void run() {
                es.a(context).b(iVar.a());
            }
        });
    }

    public static void b(Context context, final AppInfo appInfo) {
        if (appInfo == null) {
            ho.b("PPSAppDownloadManager", "appInfo is empty.");
        } else {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.m.7
                @Override // java.lang.Runnable
                public void run() {
                    d a2 = d.a();
                    if (a2 != null) {
                        a2.onUserCancel(AppInfo.this);
                    }
                }
            });
        }
    }

    private AppDownloadTask b(Context context, ContentRecord contentRecord, AppInfo appInfo) {
        AppDownloadTask c = this.f6792a.c(appInfo);
        if (c != null) {
            if (contentRecord != null) {
                c.k(contentRecord.l());
                c.l(contentRecord.Y());
                c.m(contentRecord.m());
                c.n(contentRecord.j());
            }
            if (c.C() == null && contentRecord != null) {
                ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
                ouVar.a(contentRecord);
                c.a(ouVar);
            }
        }
        return c;
    }

    private ContentRecord b(IAd iAd) {
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.i) {
            return ((com.huawei.openalliance.ad.inter.data.i) iAd).a();
        }
        int c = c(iAd);
        return c != 3 ? c != 60 ? new ContentRecord() : pi.a((com.huawei.openalliance.ad.inter.data.g) iAd) : pd.a((com.huawei.openalliance.ad.inter.data.e) iAd);
    }

    private int b(final Context context, final jk jkVar, final IAd iAd, final boolean z) {
        AppInfo appInfo = iAd.getAppInfo();
        int f = com.huawei.openalliance.ad.utils.i.f(context, "com.huawei.appmarket");
        if (c(appInfo) && f >= 100300300) {
            return c(context, jkVar, iAd, z);
        }
        ContentRecord b = b(iAd);
        AppDownloadTask b2 = b(context, b, appInfo);
        b(context, iAd, b2);
        if ((b2 != null && b2.o()) || appInfo.isAllowedNonWifiNetwork()) {
            appInfo.setAllowedNonWifiNetwork(true);
            return c(context, jkVar, iAd, z);
        }
        dq dqVar = new dq(context);
        dqVar.a(new Cdo.a() { // from class: com.huawei.openalliance.ad.download.app.m.3
            @Override // com.huawei.openalliance.ad.Cdo.a
            public void c(AppInfo appInfo2) {
                m.this.k(context, iAd);
            }

            @Override // com.huawei.openalliance.ad.Cdo.a
            public void b(AppInfo appInfo2) {
                m.this.a(AppStatusV1.PRE_CHECK_FAILED, 1003, appInfo2);
            }

            @Override // com.huawei.openalliance.ad.Cdo.a
            public void a(AppInfo appInfo2) {
                appInfo2.setAllowedNonWifiNetwork(true);
                m.this.c(context, jkVar, iAd, z);
            }
        });
        dqVar.a(appInfo, b, a(context, b, appInfo));
        return -3;
    }

    private int b(final Context context, IAd iAd, boolean z) {
        if (!z && !bv.e(context)) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.m.1
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(context, R.string._2130851114_res_0x7f02352a, 0).show();
                }
            });
            return -1;
        }
        if (context == null || iAd == null) {
            return -1;
        }
        boolean z2 = iAd instanceof com.huawei.openalliance.ad.inter.data.i;
        if (!(iAd instanceof com.huawei.openalliance.ad.inter.data.e) && !(iAd instanceof com.huawei.openalliance.ad.inter.data.g) && !z2) {
            ho.b("PPSAppDownloadManager", "ad is not native or placement ad when start download");
            return -1;
        }
        if (this.f.a(context, iAd, true)) {
            return !b(iAd.getAppInfo()) ? -1 : 0;
        }
        ho.b("PPSAppDownloadManager", "download has not permission, please add white list");
        return -2;
    }

    private boolean a(IAd iAd) {
        return (iAd == null || iAd.getAppInfo() == null || !"11".equals(iAd.getAppInfo().x())) ? false : true;
    }

    private boolean a(AppInfo appInfo) {
        if (appInfo == null) {
            return false;
        }
        String b = appInfo.b(this.c);
        return (TextUtils.isEmpty(b) || TextUtils.isEmpty(appInfo.getPackageName()) || !b.equals("6")) ? false : true;
    }

    private void a(IAd iAd, AppDownloadTask appDownloadTask) {
        RewardVerifyConfig rewardVerifyConfig = iAd.getRewardVerifyConfig();
        if (rewardVerifyConfig != null) {
            appDownloadTask.g(rewardVerifyConfig.getData());
            appDownloadTask.h(rewardVerifyConfig.getUserId());
            ContentRecord R = appDownloadTask.R();
            if (R != null) {
                R.G(rewardVerifyConfig.getData());
                R.H(rewardVerifyConfig.getUserId());
            }
        }
        appDownloadTask.a(this.c);
        appDownloadTask.b(this.c);
        appDownloadTask.c(Integer.valueOf(this.b));
        appDownloadTask.m(iAd.getContentId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppStatusV1 appStatusV1, int i, AppInfo appInfo) {
        if (this.e != null) {
            ho.b("PPSAppDownloadManager", "task status:" + appStatusV1 + ", code:" + i);
            this.e.onNewStatusChanged(appStatusV1, i, appInfo);
        }
    }

    private void a(Context context, qq qqVar, AppInfo appInfo) {
        if (qqVar == null || appInfo == null) {
            return;
        }
        String str = a(appInfo) ? ClickDestination.AGMINIMARKET : "download";
        if ("11".equals(appInfo.x())) {
            str = "restore";
        }
        MaterialClickInfo materialClickInfo = new MaterialClickInfo();
        materialClickInfo.f(1);
        b.a aVar = new b.a();
        aVar.b(str).a((Integer) 6).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(context));
        qqVar.a(aVar.a());
    }

    private void a(Context context, IAd iAd, AppDownloadTask appDownloadTask) {
        if (context == null || appDownloadTask == null || iAd == null) {
            return;
        }
        qq C = appDownloadTask.C();
        if (C != null) {
            ContentRecord a2 = C.a();
            if (a2 != null) {
                a2.c(iAd.getShowId());
            }
        } else {
            appDownloadTask.a(l(context, iAd));
        }
        appDownloadTask.n(iAd.getShowId());
    }

    public static void a(Context context, final AppInfo appInfo) {
        if (appInfo == null) {
            ho.b("PPSAppDownloadManager", "appInfo is empty.");
        } else {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.m.6
                @Override // java.lang.Runnable
                public void run() {
                    d a2 = d.a();
                    if (a2 != null) {
                        a2.onAppOpen(AppInfo.this);
                    }
                }
            });
        }
    }

    private AppDownloadTask a(IAd iAd, ou ouVar) {
        AppInfo appInfo = iAd.getAppInfo();
        AppDownloadTask a2 = new AppDownloadTask.a().a(true).a(appInfo).a(this.f6792a.d(appInfo)).b(this.f6792a.e(appInfo)).a(ouVar).a();
        if (a2 != null) {
            a2.a(this.c);
            a2.c(Integer.valueOf(this.b));
            ContentRecord a3 = ouVar.a();
            a2.m(iAd.getContentId());
            if (a3 != null) {
                a2.l(a3.Y());
                a2.k(a3.l());
                a2.n(iAd.getShowId());
            }
        }
        return a2;
    }

    private long a(Context context, ContentRecord contentRecord, AppInfo appInfo) {
        if (appInfo == null) {
            return 0L;
        }
        AppDownloadTask b = b(context, contentRecord, appInfo);
        long fileSize = appInfo.getFileSize();
        if (b == null) {
            return fileSize;
        }
        long fileSize2 = appInfo.getFileSize() - b.g();
        return fileSize2 <= 0 ? fileSize : fileSize2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Context context, jk jkVar, IAd iAd, boolean z) {
        return !bv.c(context) ? b(context, jkVar, iAd, z) : c(context, jkVar, iAd, z);
    }

    public m() {
        e h = e.h();
        this.f6792a = h;
        this.e = h.i();
    }
}

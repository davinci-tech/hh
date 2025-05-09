package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.openalliance.ad.beans.metadata.AppDownloadStatus;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.y;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSWebView;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class aw {

    /* renamed from: a, reason: collision with root package name */
    AppStatus f7609a;
    AppDownloadTask b;
    private final AppDownloadButton c;
    private final Context d;
    private ContentRecord e;
    private AppInfo f;
    private final PPSWebView g;
    private String h;
    private com.huawei.openalliance.ad.views.interfaces.z i;
    private String j;
    private String m;
    private boolean k = false;
    private int l = 0;
    private int n = 0;

    @JavascriptInterface
    public String queryInteractionCfg() {
        ContentRecord contentRecord;
        ho.c("IPPSJs", "queryInteractionCfg");
        if (!h() || (contentRecord = this.e) == null) {
            return null;
        }
        return contentRecord.aG();
    }

    @JavascriptInterface
    public String queryDownloadStatus() {
        String str;
        this.n++;
        AppDownloadStatus appDownloadStatus = new AppDownloadStatus();
        if (dd.a(this.d)) {
            if (!b(false)) {
                str = "check permission fail";
            } else {
                if (this.f != null) {
                    AppDownloadButton appDownloadButton = this.c;
                    if (appDownloadButton != null) {
                        AppStatus status = appDownloadButton.getStatus();
                        this.f7609a = status;
                        appDownloadStatus = a(status);
                        String a2 = appDownloadStatus.a();
                        if (!cz.e(this.m, a2)) {
                            this.m = a2;
                            ho.b("IPPSJs", "queryDownloadStatus from js status: %s, times:%s.", a2, Integer.valueOf(this.n));
                        }
                    }
                    return be.b(appDownloadStatus);
                }
                str = "app info is null";
            }
            ho.c("IPPSJs", str);
        } else {
            ho.a("IPPSJs", "isScreenInteractive off, don't queryDownloadStatus.");
            appDownloadStatus = a(this.f7609a);
        }
        return be.b(appDownloadStatus);
    }

    @JavascriptInterface
    public int privacyStyle() {
        return this.l;
    }

    @JavascriptInterface
    public void pause() {
        ho.b("IPPSJs", "call pause from js");
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.3
            @Override // java.lang.Runnable
            public void run() {
                if (!aw.this.b(true)) {
                    ho.c("IPPSJs", "check permission fail");
                    return;
                }
                if (aw.this.e()) {
                    ho.b("IPPSJs", "mini pause download");
                    aw.this.f();
                } else if (aw.this.c != null) {
                    aw awVar = aw.this;
                    awVar.f7609a = awVar.c.getStatus();
                    if (AppStatus.DOWNLOADING == aw.this.f7609a) {
                        aw.this.f();
                    }
                }
            }
        });
    }

    @JavascriptInterface
    public void openApp() {
        ho.b("IPPSJs", "call openApp from js");
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.4
            @Override // java.lang.Runnable
            public void run() {
                if (!aw.this.b(true)) {
                    ho.c("IPPSJs", "check permission fail");
                    return;
                }
                if (aw.this.f == null || aw.this.c == null) {
                    return;
                }
                aw awVar = aw.this;
                awVar.f7609a = awVar.c.getStatus();
                if (AppStatus.INSTALLED == aw.this.f7609a) {
                    aw.this.f();
                }
            }
        });
    }

    @JavascriptInterface
    public boolean isPreload() {
        ho.b("IPPSJs", "isPreload: false");
        return false;
    }

    @JavascriptInterface
    public void download(String str, int i) {
        ho.b("IPPSJs", "call download from js with area:" + i);
        try {
            if (100 == i) {
                if (!c() || this.i == null) {
                    return;
                }
                ho.c("IPPSJs", "allow area 100 download in pps landingPage");
                this.i.a(100);
                return;
            }
            if (i != 0 && 1 != i && 2 != i) {
                ho.c("IPPSJs", "area %s is invalid", Integer.valueOf(i));
                return;
            }
            if (!h() && 1 == i) {
                ho.c("IPPSJs", "only allow area 1 download in pps landingPage");
                b();
                return;
            }
            ContentRecord contentRecord = this.e;
            if (contentRecord == null || cz.b(contentRecord.aG())) {
                if (i != 0 && 1 != i) {
                    ho.c("IPPSJs", "not allow area %s download", Integer.valueOf(i));
                    b();
                    return;
                }
            } else if (!Arrays.asList(this.e.aG().split("\\|")).contains(String.valueOf(i))) {
                ho.c("IPPSJs", "not allow area %s download", Integer.valueOf(i));
                b();
                return;
            }
            a(str);
        } catch (Throwable th) {
            ho.c("IPPSJs", "download for Area: %s err, %s", Integer.valueOf(i), th.getClass().getSimpleName());
        }
    }

    @JavascriptInterface
    public void download(String str) {
        ho.b("IPPSJs", "call download from js");
        a(str);
    }

    @JavascriptInterface
    public void download() {
        ho.b("IPPSJs", "call download from js");
        a((String) null);
    }

    public boolean a() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void a(int i) {
        this.l = i;
    }

    private boolean h() {
        return "2".equals(this.e.ad()) || "1".equals(this.e.ad());
    }

    private boolean g() {
        ContentRecord contentRecord = this.e;
        if (contentRecord == null) {
            return false;
        }
        return os.e(contentRecord.V());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.c != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.5
                @Override // java.lang.Runnable
                public void run() {
                    aw.this.c.performClick();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        AppInfo appInfo = this.f;
        if (appInfo == null) {
            return false;
        }
        String b = appInfo.b((Integer) 4);
        return (TextUtils.isEmpty(b) || TextUtils.isEmpty(this.f.getPackageName()) || !b.equals("6")) ? false : true;
    }

    private boolean d() {
        ContentRecord contentRecord = this.e;
        return (contentRecord == null || contentRecord.ae() == null || this.g.getWebHasShownTime() < this.e.ae().B()) ? false : true;
    }

    private boolean c(boolean z) {
        PPSWebView pPSWebView;
        String currentPageUrl;
        EncryptionField<String> ab;
        if (this.e == null || (pPSWebView = this.g) == null) {
            return false;
        }
        if (z) {
            WebView webView = pPSWebView.getWebView();
            currentPageUrl = webView != null ? webView.getUrl() : null;
        } else {
            currentPageUrl = pPSWebView.getCurrentPageUrl();
        }
        if (TextUtils.isEmpty(this.h) && (ab = this.e.ab()) != null) {
            this.h = ab.a(this.d);
        }
        return cz.d(currentPageUrl, this.h);
    }

    private boolean c() {
        return h() && !this.k && os.A(this.j) && d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(boolean z) {
        String str;
        if (h()) {
            return true;
        }
        if (!g()) {
            str = "h5 download is not enable and is not pps landing page";
        } else {
            if (c(z)) {
                return true;
            }
            str = "page is not in white list";
        }
        ho.c("IPPSJs", str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.huawei.openalliance.ad.views.interfaces.z zVar = this.i;
        if (zVar != null) {
            zVar.a();
        }
    }

    /* loaded from: classes9.dex */
    static class a implements y.a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f7616a;
        private final AppDownloadButton b;
        private final ContentRecord c;
        private final Context d;

        @Override // com.huawei.openalliance.ad.utils.y.a
        public void a(boolean z) {
            new com.huawei.openalliance.ad.analysis.h(this.d).d(this.c);
            AppDownloadButton appDownloadButton = this.b;
            if (appDownloadButton != null) {
                appDownloadButton.f();
            }
        }

        @Override // com.huawei.openalliance.ad.utils.y.a
        public void a() {
            if (this.b != null) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.b.setSource(4);
                        a.this.b.setNeedShowPermision(false);
                        a.this.b.setNeedShowConfirmDialog(false);
                        if (a.this.f7616a) {
                            a.this.b.setAllowedNonWifiNetwork(true);
                            a.this.b.setOnNonWifiDownloadListener(new AppDownloadButton.OnNonWifiDownloadListener() { // from class: com.huawei.openalliance.ad.utils.aw.a.1.1
                                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnNonWifiDownloadListener
                                public boolean onNonWifiDownload(AppInfo appInfo, long j) {
                                    a.this.b.setAllowedNonWifiNetwork(true);
                                    return true;
                                }
                            });
                        }
                        a.this.b.performClick();
                    }
                });
            }
            new com.huawei.openalliance.ad.analysis.h(this.d).f(this.c);
        }

        public a(Context context, boolean z, AppDownloadButton appDownloadButton, ContentRecord contentRecord) {
            this.d = context;
            this.f7616a = z;
            this.b = appDownloadButton;
            this.c = contentRecord;
        }
    }

    private void a(final String str) {
        if (!os.u(this.j)) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.2
                @Override // java.lang.Runnable
                public void run() {
                    if (aw.this.b(true)) {
                        if (aw.this.f == null || i.a(aw.this.d, aw.this.f.getPackageName())) {
                            ho.c("IPPSJs", "app info is null or app is installed");
                            return;
                        }
                        if (aw.this.c == null) {
                            ho.c("IPPSJs", "there is no download button");
                            return;
                        }
                        aw.this.c.setVenusExt(str);
                        if (!aw.this.e()) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    aw.this.f7609a = aw.this.c.getStatus();
                                    if (AppStatus.DOWNLOAD == aw.this.f7609a) {
                                        ho.b("IPPSJs", "start download");
                                        if (!os.g(aw.this.e.V())) {
                                            aw.this.c.setSource(4);
                                            aw.this.c.setNeedShowPermision(false);
                                        } else {
                                            aw.this.k = true;
                                            if (bv.c(aw.this.d)) {
                                                com.huawei.openalliance.ad.download.app.c.a(aw.this.d, new a(aw.this.d, false, aw.this.c, aw.this.e));
                                                return;
                                            } else {
                                                com.huawei.openalliance.ad.download.app.c.b(aw.this.d, new a(aw.this.d, true, aw.this.c, aw.this.e));
                                                return;
                                            }
                                        }
                                    } else if (AppStatus.PAUSE != aw.this.f7609a && AppStatus.INSTALL != aw.this.f7609a && AppStatus.WAITING_FOR_WIFI != aw.this.f7609a) {
                                        return;
                                    } else {
                                        ho.b("IPPSJs", "resume download");
                                    }
                                    aw.this.f();
                                }
                            });
                            return;
                        }
                        ho.b("IPPSJs", "mini download");
                        aw.this.c.setSource(4);
                        aw.this.c.setNeedShowPermision(false);
                        aw.this.f();
                        return;
                    }
                    ho.c("IPPSJs", "check permission fail");
                    aw.this.b();
                }
            });
        } else {
            ho.b("IPPSJs", "js download forbidden");
            b();
        }
    }

    private AppDownloadStatus a(AppStatus appStatus) {
        AppDownloadStatus appDownloadStatus = new AppDownloadStatus();
        if (appStatus != null) {
            AppDownloadTask c = com.huawei.openalliance.ad.download.app.e.h().c(this.f);
            this.b = c;
            int l = c == null ? 0 : c.l();
            appDownloadStatus.a(appStatus);
            appDownloadStatus.a(l);
        }
        return appDownloadStatus;
    }

    public aw(final Context context, AdLandingPageData adLandingPageData, AppDownloadButton appDownloadButton, PPSWebView pPSWebView, com.huawei.openalliance.ad.views.interfaces.z zVar) {
        this.c = appDownloadButton;
        this.d = context;
        this.i = zVar;
        if (adLandingPageData != null) {
            this.e = adLandingPageData.x();
            this.f = adLandingPageData.getAppInfo();
            this.j = adLandingPageData.f();
        }
        this.g = pPSWebView;
        if (this.e != null) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aw.1
                @Override // java.lang.Runnable
                public void run() {
                    EncryptionField<String> ab = aw.this.e.ab();
                    if (ab != null) {
                        aw.this.h = ab.a(context);
                    }
                }
            });
        }
    }
}

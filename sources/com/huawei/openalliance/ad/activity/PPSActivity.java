package com.huawei.openalliance.ad.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.activity.b;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.cb;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.gr;
import com.huawei.openalliance.ad.gs;
import com.huawei.openalliance.ad.gz;
import com.huawei.openalliance.ad.ha;
import com.huawei.openalliance.ad.hf;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.nl;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.pf;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.au;
import com.huawei.openalliance.ad.utils.av;
import com.huawei.openalliance.ad.utils.aw;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dk;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSAppDetailView;
import com.huawei.openalliance.ad.views.PPSExpandButtonDetailView;
import com.huawei.openalliance.ad.views.PPSWebView;
import com.huawei.openalliance.ad.views.VideoView;
import com.huawei.openalliance.ad.views.d;
import com.huawei.openalliance.ad.views.interfaces.aa;
import com.huawei.openalliance.ad.views.interfaces.z;
import com.huawei.openalliance.ad.views.t;
import com.huawei.watchface.videoedit.gles.Constant;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class PPSActivity extends com.huawei.openalliance.ad.activity.b implements d.a {

    /* renamed from: a, reason: collision with root package name */
    gz f6572a;
    private PPSWebView b;
    private gr c;
    private ActionBar d;
    private AdLandingPageData e;
    private ClipboardManager f;
    private gc g;
    private Boolean h;
    private PopupMenu i;
    private PPSAppDetailView j;
    private Integer k;
    private PPSExpandButtonDetailView l;
    private AppInfo m;
    private b.a n;
    private Handler o;
    private av q;
    private t r;
    private AppDownloadButton t;
    private aw u;
    private long v;
    private boolean y;
    private com.huawei.openalliance.ad.linked.view.c z;
    private boolean s = false;
    private z w = new z() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.1
        @Override // com.huawei.openalliance.ad.views.interfaces.z
        public void a(final int i) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.1.2
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 100) {
                        PPSActivity.this.a(false);
                    }
                }
            });
        }

        @Override // com.huawei.openalliance.ad.views.interfaces.z
        public void a() {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    PPSActivity.this.a(true);
                }
            });
        }
    };
    private com.huawei.openalliance.ad.views.interfaces.c x = new com.huawei.openalliance.ad.views.interfaces.c() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.5
        @Override // com.huawei.openalliance.ad.views.interfaces.c
        public void a(com.huawei.openalliance.ad.views.interfaces.b bVar) {
            if (bVar == null || bVar.e() == null) {
                ho.c("PPSActivity", "click action invalid");
                return;
            }
            int intValue = bVar.e().intValue();
            ho.b("PPSActivity", "click action: %d", Integer.valueOf(intValue));
            if (intValue != 1) {
                return;
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.5.1
                @Override // java.lang.Runnable
                public void run() {
                    PPSActivity.this.a(true);
                }
            });
        }
    };

    public interface b {
        void a(boolean z);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onStop() {
        if (ho.a()) {
            ho.a("PPSActivity", "onStop");
        }
        super.onStop();
        PPSWebView pPSWebView = this.b;
        if (pPSWebView != null) {
            pPSWebView.onStop();
        }
        if (u()) {
            ho.b("PPSActivity", "checkFinish true");
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        if (ho.a()) {
            ho.a("PPSActivity", "onResume");
        }
        super.onResume();
        PPSWebView pPSWebView = this.b;
        if (pPSWebView != null) {
            pPSWebView.onResume();
        }
        gr grVar = this.c;
        if (grVar != null) {
            grVar.c();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        ho.a("PPSActivity", "requestPermissions, requestCode=%d, result= %s", Integer.valueOf(i), Arrays.toString(iArr));
        a(i, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onPause() {
        gz a2;
        if (ho.a()) {
            ho.a("PPSActivity", "onPause");
        }
        super.onPause();
        IAd d = dc.d();
        if (d instanceof LinkedSplashAd) {
            LinkedSplashAd linkedSplashAd = (LinkedSplashAd) d;
            if (linkedSplashAd.getVideoInfo() != null && (a2 = ha.a()) != null) {
                linkedSplashAd.getVideoInfo().e(a2.d());
                linkedSplashAd.getVideoInfo().e(a2.c());
                ha.a((gz) null);
            }
            if (linkedSplashAd.getListener() != null) {
                linkedSplashAd.getListener().onAdDetailClosed(linkedSplashAd);
            }
        }
        gr grVar = this.c;
        if (grVar != null) {
            grVar.b();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean a2 = a(menuItem);
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.views.d.a
    public void onMenuBtnClick(View view) {
        a(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        if (ho.a()) {
            ho.a("PPSActivity", "onDestroy");
        }
        super.onDestroy();
        y();
        x();
        c((Context) this);
        com.huawei.openalliance.ad.linked.view.c cVar = this.z;
        if (cVar != null) {
            cVar.d();
            this.z = null;
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater;
        int i;
        MenuItem findItem;
        MenuItem findItem2;
        if (this.e == null) {
            return false;
        }
        AppInfo appInfo = this.m;
        if (appInfo == null || !appInfo.u()) {
            menuInflater = getMenuInflater();
            i = R.menu._2131755010_res_0x7f100002;
        } else {
            menuInflater = getMenuInflater();
            i = R.menu._2131755009_res_0x7f100001;
        }
        menuInflater.inflate(i, menu);
        if (k() && (findItem2 = menu.findItem(R.id.hiad_menu_item_privacy_policy)) != null) {
            findItem2.setVisible(true);
        }
        if (!HiAd.a(getApplicationContext()).k() && (findItem = menu.findItem(R.id.hiad_menu_item_open_in_browser)) != null) {
            findItem.setVisible(false);
        }
        return w() && !v();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // com.huawei.openalliance.ad.activity.b, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            boolean r0 = r4.v()
            if (r0 == 0) goto Lc
            r0 = 2131951940(0x7f130144, float:1.9540309E38)
            r4.setTheme(r0)
        Lc:
            com.huawei.openalliance.ad.utils.dd.h(r4)
            boolean r0 = com.huawei.openalliance.ad.ho.a()
            java.lang.String r1 = "PPSActivity"
            if (r0 == 0) goto L1c
            java.lang.String r0 = "onCreate"
            com.huawei.openalliance.ad.ho.a(r1, r0)
        L1c:
            super.onCreate(r5)
            com.huawei.openalliance.ad.inter.HiAd.getInstance(r4)
            android.content.Intent r5 = r4.getIntent()
            android.app.ActionBar r0 = r4.getActionBar()     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            r4.d = r0     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            java.lang.String r0 = "ad_landing_page_data"
            java.io.Serializable r0 = r5.getSerializableExtra(r0)     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r0 = (com.huawei.openalliance.ad.inter.data.AdLandingPageData) r0     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            r4.e = r0     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            r4.a(r5)     // Catch: java.lang.Throwable -> L3a java.lang.ClassCastException -> L3d
            goto L42
        L3a:
            java.lang.String r5 = "fail to get contentRecord"
            goto L3f
        L3d:
            java.lang.String r5 = "fail to get contentRecord, class cast exception"
        L3f:
            com.huawei.openalliance.ad.ho.d(r1, r5)
        L42:
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r5 = r4.e
            if (r5 != 0) goto L4f
            java.lang.String r5 = "content record null, don't show ad detail web page"
            com.huawei.openalliance.ad.ho.b(r1, r5)
            r4.finish()
            return
        L4f:
            com.huawei.openalliance.ad.inter.data.AppInfo r5 = r5.getAppInfo()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.m = r5     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            com.huawei.openalliance.ad.gc r5 = com.huawei.openalliance.ad.fh.b(r4)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.g = r5     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            android.app.ActionBar r5 = r4.d     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            if (r5 == 0) goto L69
            boolean r5 = r4.a(r4)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            if (r5 == 0) goto L69
            r4.i()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            goto L73
        L69:
            android.app.ActionBar r5 = r4.d     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            if (r5 == 0) goto L73
            r5.hide()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r5 = 0
            r4.d = r5     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
        L73:
            com.huawei.openalliance.ad.utils.w r5 = com.huawei.openalliance.ad.utils.w.a(r4)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            boolean r5 = r5.b()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            if (r5 == 0) goto L82
            android.app.ActionBar r5 = r4.d     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r5.hide()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
        L82:
            java.lang.String r5 = "clipboard"
            java.lang.Object r5 = r4.getSystemService(r5)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            android.content.ClipboardManager r5 = (android.content.ClipboardManager) r5     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.f = r5     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.n()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.d()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r4.b(r4)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            com.huawei.openalliance.ad.views.PPSWebView r5 = r4.b     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            if (r5 == 0) goto Lab
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r5 = r4.e     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            int r5 = r5.getAdType()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r0 = 7
            if (r5 != r0) goto Lab
            com.huawei.openalliance.ad.views.PPSWebView r5 = r4.b     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            r5.setRealOpenTime(r2)     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
        Lab:
            r4.o()     // Catch: java.lang.Throwable -> Laf android.util.AndroidRuntimeException -> Lb8
            goto Ld2
        Laf:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "onCreate: "
            r0.<init>(r2)
            goto Lc0
        Lb8:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "onCreate error: "
            r0.<init>(r2)
        Lc0:
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.huawei.openalliance.ad.ho.c(r1, r5)
        Ld2:
            boolean r5 = com.huawei.openalliance.ad.utils.dd.g()
            if (r5 == 0) goto Lf7
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r5 = r4.e
            if (r5 == 0) goto Lf7
            java.lang.String r0 = "3"
            java.lang.String r5 = r5.n()
            boolean r5 = r0.equalsIgnoreCase(r5)
            if (r5 == 0) goto Lf7
            android.content.Context r5 = r4.getApplicationContext()
            r0 = 2130851170(0x7f023562, float:1.7307682E38)
            r1 = 0
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r1)
            r5.show()
        Lf7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.activity.PPSActivity.onCreate(android.os.Bundle):void");
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        if (configuration == null) {
            return;
        }
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        ho.b("PPSActivity", "currentNightMode=" + i);
        a(32 == i ? 2 : 0);
        a(configuration);
    }

    @Override // com.huawei.openalliance.ad.views.d.a
    public void c() {
        ho.b("PPSActivity", "onClose");
        finish();
    }

    @Override // com.huawei.openalliance.ad.activity.b
    protected void b() {
        Handler handler = this.o;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    ho.b("PPSActivity", "finishSelfDelayed");
                    PPSActivity.this.finish();
                }
            }, 300L);
        }
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setRequestedOrientation(1);
        setContentView(R.layout.hiad_activity_landing_page);
        this.p = (ViewGroup) findViewById(R.id.hiad_landing_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(intent);
    }

    private void y() {
        gr grVar = this.c;
        if (grVar != null) {
            grVar.a();
        }
    }

    private void x() {
        PPSWebView pPSWebView = this.b;
        if (pPSWebView != null) {
            pPSWebView.destroy();
        }
        PPSAppDetailView pPSAppDetailView = this.j;
        if (pPSAppDetailView != null) {
            pPSAppDetailView.d();
            this.j = null;
        }
        PPSExpandButtonDetailView pPSExpandButtonDetailView = this.l;
        if (pPSExpandButtonDetailView != null) {
            pPSExpandButtonDetailView.d();
            this.l = null;
        }
    }

    private boolean w() {
        if (this.h == null) {
            this.h = (Boolean) com.huawei.openalliance.ad.utils.dc.a(new Callable<Boolean>() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.12
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Boolean call() {
                    return Boolean.valueOf(PPSActivity.this.g.aR());
                }
            }, false);
        }
        return this.h.booleanValue();
    }

    private boolean v() {
        return !a((Context) this);
    }

    private boolean u() {
        AdLandingPageData adLandingPageData;
        if (isFinishing() || (adLandingPageData = this.e) == null) {
            return false;
        }
        return os.r(adLandingPageData.f());
    }

    private void t() {
        PPSAppDetailView pPSAppDetailView = (this.y || !g()) ? this.j : this.l;
        a(pPSAppDetailView);
        b(this.t);
        if (this.y) {
            a((PPSAppDetailView) this.l);
            this.l.a(2);
            this.l.setBtnSource(61);
            this.l.setNonBtnSource(61);
            pPSAppDetailView.setBtnSource(59);
            pPSAppDetailView.setNonBtnSource(60);
        }
    }

    private boolean s() {
        AppDownloadButton appDownloadButton;
        if (this.e == null || (appDownloadButton = this.t) == null) {
            return false;
        }
        AppStatus status = appDownloadButton.getStatus();
        if (status == AppStatus.DOWNLOAD || status == AppStatus.INSTALLED) {
            return true;
        }
        ho.a("PPSActivity", "current app status not support scan animation.");
        return false;
    }

    private boolean r() {
        return s() && os.x(this.e.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean q() {
        return s() && !os.w(this.e.f());
    }

    private void p() {
        if (this.r == null) {
            t tVar = new t(this, 0);
            this.r = tVar;
            tVar.setPopUpClickListener(new aa() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.9
                @Override // com.huawei.openalliance.ad.views.interfaces.aa
                public void c() {
                }

                @Override // com.huawei.openalliance.ad.views.interfaces.aa
                public void b() {
                    PPSActivity.this.b("129");
                    PPSActivity.this.t.f();
                    PPSActivity.this.r.b();
                    PPSActivity.this.r = null;
                    PPSActivity.this.s = false;
                }

                @Override // com.huawei.openalliance.ad.views.interfaces.aa
                public void a() {
                    PPSActivity.this.b("128");
                    PPSActivity.this.t.setSource(5);
                    PPSActivity.this.t.a(PPSActivity.this.r.getClickInfo());
                    PPSActivity.this.t.performClick();
                    PPSActivity.this.r.b();
                    PPSActivity.this.r = null;
                    PPSActivity.this.s = false;
                }
            });
            this.r.getDialog().setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.10
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    PPSActivity.this.s = false;
                    PPSActivity.this.r = null;
                    PPSActivity.this.b("130");
                }
            });
        }
    }

    private void o() {
        if (os.B(this.e.f()) != 2) {
            ho.a("PPSActivity", "no need popup strategy %s.", Integer.valueOf(os.B(this.e.f())));
            return;
        }
        if (this.m == null || this.e.n() == null) {
            ho.a("PPSActivity", "app or pageType para error.");
            return;
        }
        if (f()) {
            ho.a("PPSActivity", "no need popup auto download.");
            return;
        }
        if (!"1".equals(this.e.n()) && !"2".equals(this.e.n())) {
            ho.a("PPSActivity", "landing type no need pop.");
            return;
        }
        long B = this.m.B();
        Object[] objArr = new Object[1];
        if (B < 0) {
            objArr[0] = Long.valueOf(B);
            ho.c("PPSActivity", "delay time error:%s", objArr);
        } else {
            objArr[0] = Long.valueOf(B);
            ho.b("PPSActivity", "show app download dialog start delayTime %s", objArr);
            dj.a(new a(this), B);
        }
    }

    private void n() {
        try {
            this.z = new com.huawei.openalliance.ad.linked.view.c(this);
            gs gsVar = new gs(this, this.e, this.f6572a);
            PPSWebView pPSWebView = new PPSWebView(this, this.d, this.e, this, w(), gsVar.k() && !dd.a((Activity) this));
            this.b = pPSWebView;
            pPSWebView.setPPSWebEventCallback(nl.a());
            a(this.b);
            gr grVar = new gr(gsVar, this.z, this.b);
            this.c = grVar;
            grVar.a(new b() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.8
                @Override // com.huawei.openalliance.ad.activity.PPSActivity.b
                public void a(boolean z) {
                }
            });
            ((ViewGroup) findViewById(R.id.hiad_landing_webview_layout)).addView(this.c.a(this));
        } catch (Throwable th) {
            ho.c("PPSActivity", "init webview failed " + th.getClass().getSimpleName());
        }
        boolean h = h();
        this.y = h;
        this.j = h ? this.b.getTopDetailView() : (PPSAppDetailView) findViewById(R.id.hiad_landing_app_detail);
        this.l = (PPSExpandButtonDetailView) findViewById(R.id.hiad_landing_expand_button_detail);
        ho.b("PPSActivity", "ctrlSwitchs:" + this.e.f());
        t();
        Resources resources = getResources();
        if (resources != null) {
            if (resources.getConfiguration() != null) {
                this.k = Integer.valueOf(resources.getConfiguration().screenWidthDp);
            }
            onConfigurationChanged(resources.getConfiguration());
        }
    }

    private void m() {
        PPSWebView pPSWebView = this.b;
        if (pPSWebView != null) {
            pPSWebView.loadPage();
        }
    }

    private void l() {
        ClipData newPlainText = ClipData.newPlainText(Constant.TEXT, this.e.getLandingUrl());
        ClipboardManager clipboardManager = this.f;
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
            Toast.makeText(getApplicationContext(), R.string._2130851108_res_0x7f023524, 1).show();
        }
    }

    private boolean k() {
        AdLandingPageData adLandingPageData = this.e;
        return (adLandingPageData == null || this.m == null || TextUtils.isEmpty(adLandingPageData.p())) ? false : true;
    }

    private String j() {
        return !this.e.isShowPageTitle() ? " " : ao.a(this.e) ? cz.c(this.e.getAppInfo().getAppName()) : getString(R.string._2130851066_res_0x7f0234fa);
    }

    private void i() {
        ActionBar actionBar = this.d;
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle(j());
        cb.a(this).a(this.d, true, null, new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean h() {
        return os.f(this.e.f()) == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return ao.n(this) || os.f(this.e.f()) == 2;
    }

    private boolean f() {
        AdLandingPageData adLandingPageData = this.e;
        return adLandingPageData != null && this.m != null && adLandingPageData.l() && os.h(this.e.f());
    }

    private void e() {
        a(this.l, 15);
        a(this.j, 15);
    }

    private void d() {
        if (!f()) {
            ho.b("PPSActivity", "do not auto download app");
            return;
        }
        ho.b("PPSActivity", "auto download app");
        AppDownloadButton appDownloadButton = this.t;
        if (appDownloadButton == null) {
            ho.c("PPSActivity", "there is no download button");
            return;
        }
        if (AppStatus.DOWNLOAD == appDownloadButton.getStatus()) {
            e();
            this.t.performClick();
        }
    }

    private void c(Context context) {
        b.a aVar = this.n;
        if (aVar != null) {
            context.unregisterReceiver(aVar);
            this.n = null;
        }
    }

    private void b(boolean z) {
        try {
            PPSAppDetailView pPSAppDetailView = this.j;
            if (pPSAppDetailView != null && pPSAppDetailView.getVisibility() == 0) {
                View inflate = getLayoutInflater().inflate(R.layout.hiad_landing_app_detail, (ViewGroup) null, false);
                if (inflate == null) {
                    return;
                }
                ho.a("PPSActivity", "start replace appDetailView");
                a(this.j, inflate);
                AppDownloadButton appDownloadButton = this.j.getAppDownloadButton();
                if (appDownloadButton != null) {
                    a(appDownloadButton, z);
                    a(appDownloadButton);
                }
                this.j.setAdLandingData(this.e);
            }
            PPSExpandButtonDetailView pPSExpandButtonDetailView = this.l;
            if (pPSExpandButtonDetailView == null || pPSExpandButtonDetailView.getVisibility() != 0) {
                return;
            }
            PPSExpandButtonDetailView pPSExpandButtonDetailView2 = new PPSExpandButtonDetailView(this);
            if (this.y) {
                pPSExpandButtonDetailView2.a(2);
            }
            ho.a("PPSActivity", "start replace expandButtonDetailView");
            a(this.l, pPSExpandButtonDetailView2);
            AppDownloadButton appDownloadButton2 = this.l.getAppDownloadButton();
            if (appDownloadButton2 != null) {
                a(appDownloadButton2);
            }
            this.l.setAdLandingData(this.e);
        } catch (Throwable unused) {
            ho.c("PPSActivity", "rebuild failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        ho.b("PPSActivity", "report Type is " + str);
        new h(this).a(this.e, str);
    }

    private void b(AppDownloadButton appDownloadButton) {
        if (this.b != null) {
            aw awVar = new aw(this, this.e, appDownloadButton, this.b, this.w);
            this.u = awVar;
            this.b.addJavascriptInterface(awVar, Constants.PPS_JS_NAME);
            this.b.addJavascriptInterface(new au(this, pf.a(this.e)), Constants.LANDING_JS_NAME);
            av avVar = new av(this, pf.a(this.e), this.b);
            this.q = avVar;
            this.b.addJavascriptInterface(avVar, Constants.APPOINT_JS_NAME);
        }
    }

    private void b(Context context) {
        if (Constants.HW_INTELLIEGNT_PACKAGE.equalsIgnoreCase(context.getPackageName())) {
            this.o = new Handler(Looper.myLooper());
            this.n = new b.a();
            ao.a(context, this.n, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), "android.permission.WRITE_SECURE_SETTINGS", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.hiad_menu_item_refresh) {
            m();
            return true;
        }
        if (itemId == R.id.hiad_menu_item_copy_link) {
            l();
            return true;
        }
        if (itemId == R.id.hiad_menu_item_open_in_browser) {
            a(this.e.getLandingUrl());
            return true;
        }
        if (itemId == R.id.hiad_menu_item_permission) {
            com.huawei.openalliance.ad.download.app.h.a(this, this.m);
            return true;
        }
        if (itemId != R.id.hiad_menu_item_privacy_policy) {
            return false;
        }
        a(this.e.p());
        return false;
    }

    private boolean a(Context context) {
        return ao.a(context) >= 3 || bz.e(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        aw awVar;
        if (z && !os.v(this.e.f())) {
            ho.b("PPSActivity", "not allow confirm");
            return;
        }
        AppDownloadButton appDownloadButton = this.t;
        if (appDownloadButton == null || appDownloadButton.d() || this.s || this.t.getStatus() != AppStatus.DOWNLOAD) {
            return;
        }
        this.s = true;
        if (!z && (awVar = this.u) != null) {
            if (awVar.a()) {
                return;
            } else {
                this.u.a(true);
            }
        }
        p();
        this.r.setAdPopupData(this.e);
        this.r.a();
        b("127");
    }

    private void a(String str) {
        try {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setData(Uri.parse(str + "#" + System.currentTimeMillis()));
            intent.setFlags(268468224);
            startActivity(intent);
        } catch (Throwable th) {
            ho.c("PPSActivity", "openLinkInBrowser " + th.getClass().getSimpleName());
        }
    }

    private void a(PPSWebView pPSWebView) {
        if (pPSWebView == null) {
            return;
        }
        pPSWebView.setScrollListener(new View.OnScrollChangeListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.11
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if (PPSActivity.this.A() || !PPSActivity.this.q()) {
                    ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
                    return;
                }
                if (PPSActivity.this.l != null && PPSActivity.this.g()) {
                    PPSActivity.this.l.c();
                }
                if (PPSActivity.this.j != null && !PPSActivity.this.g()) {
                    PPSActivity.this.j.c();
                }
                ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
            }
        });
    }

    private void a(PPSAppDetailView pPSAppDetailView, int i) {
        if (pPSAppDetailView != null) {
            pPSAppDetailView.setBtnSource(i);
            pPSAppDetailView.setNonBtnSource(i);
        }
    }

    private void a(PPSAppDetailView pPSAppDetailView) {
        pPSAppDetailView.setVisibility(0);
        pPSAppDetailView.setNeedShowDspInfo(true);
        pPSAppDetailView.setNeedPerBeforDownload(true);
        pPSAppDetailView.setAdLandingData(this.e);
        pPSAppDetailView.setDetailViewType(1);
        pPSAppDetailView.setAppDetailClickListener(this.x);
        AppDownloadButton appDownloadButton = pPSAppDetailView.getAppDownloadButton();
        this.t = appDownloadButton;
        if (appDownloadButton != null) {
            appDownloadButton.setSource(2);
        }
        if (this.t == null || !r()) {
            return;
        }
        this.t.setAppDownloadButtonStyle(new com.huawei.openalliance.ad.views.aa(this));
    }

    private void a(AppDownloadButton appDownloadButton, boolean z) {
        int i = z ? R.dimen._2131363325_res_0x7f0a05fd : R.dimen._2131363272_res_0x7f0a05c8;
        Resources resources = getResources();
        if (resources == null) {
            return;
        }
        appDownloadButton.setMaxWidth(resources.getDimensionPixelSize(i));
    }

    private void a(AppDownloadButton appDownloadButton) {
        ViewGroup.LayoutParams layoutParams = appDownloadButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        appDownloadButton.setLayoutParamsSkipSizeReset(layoutParams);
    }

    private void a(View view, View view2) {
        dk.a(view.findViewById(R.id.app_detail_root), view2.findViewById(R.id.app_detail_root));
    }

    private void a(View view) {
        MenuItem findItem;
        MenuInflater menuInflater;
        Menu menu;
        int i;
        if (this.i == null) {
            this.i = new PopupMenu(dd.d(this), view, GravityCompat.END);
            AppInfo appInfo = this.m;
            if (appInfo == null || !appInfo.u()) {
                menuInflater = this.i.getMenuInflater();
                menu = this.i.getMenu();
                i = R.menu._2131755010_res_0x7f100002;
            } else {
                menuInflater = this.i.getMenuInflater();
                menu = this.i.getMenu();
                i = R.menu._2131755009_res_0x7f100001;
            }
            menuInflater.inflate(i, menu);
            this.i.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.7
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem) {
                    boolean a2 = PPSActivity.this.a(menuItem);
                    ViewClickInstrumentation.clickOnMenuItem(menuItem);
                    return a2;
                }
            });
        }
        if (k() && (findItem = this.i.getMenu().findItem(R.id.hiad_menu_item_privacy_policy)) != null) {
            findItem.setVisible(true);
        }
        this.i.show();
    }

    private void a(Configuration configuration) {
        ho.a("PPSActivity", "onConfigurationChanged newConfig.screenWidthDp=" + configuration.screenWidthDp + ", this.screenWidthDp=" + this.k);
        if (this.k == null || configuration.screenWidthDp == this.k.intValue()) {
            return;
        }
        ho.a("PPSActivity", "onConfigurationChanged rebuildDetailView()");
        b(configuration.screenWidthDp < this.k.intValue());
        this.k = Integer.valueOf(configuration.screenWidthDp);
        B();
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        ho.b("PPSActivity", "parseLinkedAdConfig");
        this.f6572a = new gz();
        String stringExtra = safeIntent.getStringExtra(MapKeyNames.LINKED_AD_DATA);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.f6572a = (gz) be.b(stringExtra, gz.class, new Class[0]);
    }

    private void a(int i, int[] iArr) {
        if (i == 11 || i == 12) {
            if (iArr.length > 0 && iArr[0] == 0) {
                av avVar = this.q;
                if (avVar != null) {
                    if (i == 11) {
                        avVar.a(true, true);
                        return;
                    } else {
                        avVar.b(true, true);
                        return;
                    }
                }
                return;
            }
            if (!shouldShowRequestPermissionRationale("android.permission.WRITE_CALENDAR")) {
                a(i, i == 11 ? R.string._2130851024_res_0x7f0234d0 : R.string._2130851025_res_0x7f0234d1);
                return;
            }
            av avVar2 = this.q;
            if (avVar2 != null) {
                if (i == 11) {
                    avVar2.a(false, true);
                } else {
                    avVar2.b(false, true);
                }
            }
        }
    }

    private void a(final int i, int i2) {
        new AlertDialog.Builder(this).setTitle(R.string._2130851026_res_0x7f0234d2).setMessage(i2).setPositiveButton(R.string._2130851027_res_0x7f0234d3, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                PPSActivity.this.z();
                dialogInterface.dismiss();
                if (PPSActivity.this.q != null) {
                    if (i == 11) {
                        PPSActivity.this.q.a(false, false);
                    } else {
                        PPSActivity.this.q.b(false, false);
                    }
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i3);
            }
        }).setNegativeButton(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                dialogInterface.dismiss();
                if (PPSActivity.this.q != null) {
                    if (i == 11) {
                        PPSActivity.this.q.a(false, true);
                    } else {
                        PPSActivity.this.q.b(false, true);
                    }
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i3);
            }
        }).show();
    }

    private void a(int i) {
        PPSWebView pPSWebView;
        WebSettings settings;
        if (Build.VERSION.SDK_INT < 29 || (pPSWebView = this.b) == null || (settings = pPSWebView.getSettings()) == null) {
            return;
        }
        settings.setForceDark(i);
    }

    private VideoView a(hf hfVar, Float f, int i) {
        VideoView videoView = hfVar.getVideoView();
        float f2 = i / 1.7777778f;
        float floatValue = f.floatValue();
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.width = (int) (floatValue * f2);
        layoutParams.height = (int) f2;
        videoView.setLayoutParams(layoutParams);
        return videoView;
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<PPSActivity> f6588a;

        @Override // java.lang.Runnable
        public void run() {
            PPSActivity pPSActivity = this.f6588a.get();
            if (pPSActivity == null) {
                return;
            }
            pPSActivity.a(false);
        }

        public a(PPSActivity pPSActivity) {
            this.f6588a = new WeakReference<>(pPSActivity);
        }
    }

    private void B() {
        String str;
        ho.b("PPSActivity", "resetLinkedNativeVideoView start");
        gz gzVar = this.f6572a;
        if (gzVar == null || gzVar.a() == null || this.f6572a.a().m() == null) {
            ho.b("PPSActivity", "getVideoInfo is null");
            return;
        }
        Float m = this.f6572a.a().m();
        int b2 = com.huawei.openalliance.ad.utils.d.b(this);
        if (m.floatValue() >= 1.0f) {
            if (ho.a()) {
                ho.a("PPSActivity", "no need resetLinkedNativeVideoView");
                return;
            }
            return;
        }
        jk d = this.c.d();
        if (ho.a()) {
            ho.a("PPSActivity", "iView is %s", d.getClass().getSimpleName());
        }
        if (d instanceof com.huawei.openalliance.ad.linked.view.c) {
            com.huawei.openalliance.ad.linked.view.c cVar = (com.huawei.openalliance.ad.linked.view.c) d;
            if (cVar.getLinkedNativeVideoView() != null) {
                hf linkedNativeVideoView = cVar.getLinkedNativeVideoView();
                linkedNativeVideoView.setVideoView(a(linkedNativeVideoView, m, b2));
                this.c.a(linkedNativeVideoView);
                str = "reset LinkedLandView end";
                ho.b("PPSActivity", str);
            }
        }
        if (!(d instanceof com.huawei.openalliance.ad.linked.view.b)) {
            if (ho.a()) {
                ho.a("PPSActivity", "not resetLinkedNativeVideoView");
            }
        } else {
            com.huawei.openalliance.ad.linked.view.b bVar = (com.huawei.openalliance.ad.linked.view.b) d;
            bVar.setVideoView(a((hf) d, m, b2));
            this.c.a(bVar);
            str = "reset ILinkedNativeView end";
            ho.b("PPSActivity", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A() {
        if (System.currentTimeMillis() - this.v < 500) {
            return true;
        }
        this.v = System.currentTimeMillis();
        return false;
    }
}

package com.huawei.openalliance.ad.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.hms.ads.uiengine.IRemoteViewDelegate;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.pp;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class TemplateStubActivity extends f {
    private static AdContentData b;

    /* renamed from: a, reason: collision with root package name */
    private IRemoteViewDelegate f6610a;
    private View c;
    private boolean d = false;

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onStop() {
        super.onStop();
        ho.b("TemplateStubActivity", "onStop");
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                iRemoteViewDelegate.onStop();
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "onStop " + th.getClass().getSimpleName());
        }
        finish();
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                iRemoteViewDelegate.onStart();
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "onStart " + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        super.onResume();
        ho.b("TemplateStubActivity", "onResume, hasPause= %s", Boolean.valueOf(this.d));
        if (this.d) {
            finish();
        }
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                iRemoteViewDelegate.onResume();
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "onResume " + th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onRestart() {
        super.onRestart();
        ho.b("TemplateStubActivity", "onRestart, hasPause= %s", Boolean.valueOf(this.d));
        if (this.d) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onPause() {
        super.onPause();
        ho.b("TemplateStubActivity", "onPause");
        this.d = true;
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                iRemoteViewDelegate.onPause();
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "onPause " + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ho.b("TemplateStubActivity", "onDestroy");
        e();
        pp a2 = pp.a(getApplicationContext());
        AdContentData adContentData = b;
        a2.b(adContentData != null ? adContentData.f() : null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ho.b("TemplateStubActivity", "onCreate");
        SafeIntent safeIntent = new SafeIntent(getIntent());
        String stringExtra = safeIntent.getStringExtra("content");
        a((AdContentData) be.b(stringExtra, AdContentData.class, new Class[0]));
        if (!pp.a(getApplicationContext()).a(b)) {
            ho.b("TemplateStubActivity", "content is null");
            pp.a(getApplicationContext()).a((String) null, 3);
            finish();
        }
        if (dd.b(getApplicationContext())) {
            ho.b("TemplateStubActivity", "screen locked");
            pp a2 = pp.a(getApplicationContext());
            AdContentData adContentData = b;
            a2.a(adContentData != null ? adContentData.f() : null, 1);
            finish();
        }
        IRemoteCreator a3 = com.huawei.openalliance.ad.e.a(getApplicationContext());
        if (a3 == null) {
            ho.b("TemplateStubActivity", "remoteCreator is null");
            pp a4 = pp.a(getApplicationContext());
            AdContentData adContentData2 = b;
            a4.a(adContentData2 != null ? adContentData2.f() : null, 2);
            finish();
            return;
        }
        c();
        a(safeIntent);
        Bundle bundle2 = new Bundle();
        bundle2.putString("filePath", safeIntent.getStringExtra("filePath"));
        bundle2.putString("content", stringExtra);
        try {
            IRemoteViewDelegate newRemoteViewDelegate = a3.newRemoteViewDelegate(ObjectWrapper.wrap(this), safeIntent.getStringExtra(ParamConstants.Param.VIEW_TYPE), null);
            this.f6610a = newRemoteViewDelegate;
            newRemoteViewDelegate.onCreate(bundle2);
            this.f6610a.setCallback(new a(this));
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.TemplateStubActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    TemplateStubActivity.this.d();
                    TemplateStubActivity.this.a("start", null);
                    dd.a(TemplateStubActivity.this.c, TemplateStubActivity.this);
                    TemplateStubActivity.this.c.startAnimation(AnimationUtils.loadAnimation(TemplateStubActivity.this.getApplicationContext(), R.anim._2130772023_res_0x7f010037));
                }
            });
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "create remoteViewDelegate err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim._2130772024_res_0x7f010038);
    }

    private void e() {
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                iRemoteViewDelegate.onDestroy();
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "onDestroy failed: " + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                View view = (View) ObjectWrapper.unwrap(iRemoteViewDelegate.getView());
                this.c = view;
                setContentView(view);
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "plugRemoteView " + th.getClass().getSimpleName());
        }
    }

    private void c() {
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        try {
            if (Build.VERSION.SDK_INT < 28 || 1 != getResources().getConfiguration().orientation) {
                return;
            }
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        } catch (Throwable th) {
            Log.w("TemplateStubActivity", "set CutoutMode error:" + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        finish();
        overridePendingTransition(0, R.anim._2130772024_res_0x7f010038);
    }

    private static void a(AdContentData adContentData) {
        b = adContentData;
    }

    private void a(Intent intent) {
        try {
            if (!intent.getBooleanExtra(ParamConstants.Param.NEED_RESET, false)) {
                ho.c("TemplateStubActivity", "not need reset");
                return;
            }
            Window window = getWindow();
            if (window == null) {
                ho.c("TemplateStubActivity", "window is null");
                return;
            }
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = intent.getIntExtra(ParamConstants.Param.FLAGS, window.getAttributes().flags);
            if (!WhiteListPkgList.isHwBrowserPkgName(getPackageName())) {
                attributes.flags |= AppRouterExtras.COLDSTART;
            }
            if (Build.VERSION.SDK_INT >= 28) {
                attributes.layoutInDisplayCutoutMode = intent.getIntExtra(ParamConstants.Param.LAYOUT_IN_DISPLAY_CUTOUT_MODE, window.getAttributes().layoutInDisplayCutoutMode);
            }
            window.setAttributes(attributes);
            window.setNavigationBarColor(intent.getIntExtra(ParamConstants.Param.NAVIGATION_BAR_COLOR, window.getNavigationBarColor()));
            View decorView = window.getDecorView();
            if (decorView == null) {
                ho.c("TemplateStubActivity", "decorView is null");
            } else {
                decorView.setSystemUiVisibility(intent.getIntExtra(ParamConstants.Param.SYSTEM_UI_VISIBILITY, decorView.getSystemUiVisibility()));
            }
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "inherit err: %s", th.getClass().getSimpleName());
        }
    }

    static class a extends IPPSUiEngineCallback.a {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TemplateStubActivity> f6612a;

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.huawei.hms.ads.uiengine.IPPSUiEngineCallback
        public void onCallResult(String str, Bundle bundle) {
            char c;
            ho.b("TemplateStubActivity", "onCallResult method: %s", str);
            TemplateStubActivity templateStubActivity = this.f6612a.get();
            if (templateStubActivity == null) {
                return;
            }
            str.hashCode();
            switch (str.hashCode()) {
                case -872396787:
                    if (str.equals(ParamConstants.CallbackMethod.ON_EASTER_EGG_CLICK)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -599445191:
                    if (str.equals("complete")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 3135262:
                    if (str.equals(ParamConstants.CallbackMethod.ON_FAIL)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 3529469:
                    if (str.equals(ParamConstants.CallbackMethod.ON_SHOW)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 94750088:
                    if (str.equals("click")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 94756344:
                    if (str.equals("close")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1671672458:
                    if (str.equals(ParamConstants.CallbackMethod.ON_DISMISS)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    if (!pp.a(templateStubActivity.getApplicationContext()).b(templateStubActivity, bundle, templateStubActivity.getClass().getSimpleName(), TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null)) {
                        return;
                    }
                    break;
                case 1:
                    templateStubActivity.b();
                    pp.a(templateStubActivity.getApplicationContext()).a(bundle, TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null);
                    return;
                case 2:
                    templateStubActivity.b();
                    pp.a(templateStubActivity.getApplicationContext()).a(TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null, bundle);
                    return;
                case 3:
                    pp.a(templateStubActivity.getApplicationContext()).a(templateStubActivity.getClass().getSimpleName(), TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null);
                    return;
                case 4:
                    if (!pp.a(templateStubActivity.getApplicationContext()).a(templateStubActivity, bundle, templateStubActivity.getClass().getSimpleName(), TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null)) {
                        return;
                    }
                    break;
                case 5:
                    templateStubActivity.b();
                    pp.a(templateStubActivity.getApplicationContext()).b(bundle, TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null);
                    return;
                case 6:
                    templateStubActivity.b();
                    pp.a(templateStubActivity.getApplicationContext()).b(TemplateStubActivity.b != null ? TemplateStubActivity.b.f() : null);
                    return;
                default:
                    return;
            }
            templateStubActivity.b();
        }

        public a(TemplateStubActivity templateStubActivity) {
            this.f6612a = new WeakReference<>(templateStubActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a(String str, Bundle bundle) {
        try {
            IRemoteViewDelegate iRemoteViewDelegate = this.f6610a;
            if (iRemoteViewDelegate != null) {
                return iRemoteViewDelegate.sendCommand(str, bundle);
            }
            return null;
        } catch (Throwable th) {
            ho.c("TemplateStubActivity", "%s failed: %s ", str, th.getClass().getSimpleName());
            return null;
        }
    }
}

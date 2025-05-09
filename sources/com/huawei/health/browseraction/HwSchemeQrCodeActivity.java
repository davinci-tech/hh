package com.huawei.health.browseraction;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.gop;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes8.dex */
public class HwSchemeQrCodeActivity extends BaseActivity {
    private String d = null;
    private String c = null;
    private String b = null;

    /* renamed from: a, reason: collision with root package name */
    private String f2207a = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri CT_ = CT_();
        CU_(getIntent());
        if (CT_ == null || !CR_(CT_)) {
            e();
            return;
        }
        try {
            CV_(CT_);
            OpAnalyticsUtil.getInstance().init(BaseApplication.getContext());
            CS_(CT_);
        } catch (Exception e) {
            LogUtil.a("Login_HwSchemeQrCodeActivity", "ex=", LogUtil.a(e));
            e();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        CU_(intent);
    }

    private void CU_(Intent intent) {
        if (intent == null) {
            LogUtil.a("Login_HwSchemeQrCodeActivity", "intent == null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.d = extras.getString("pushId");
            this.c = extras.getString("serviceId");
            this.b = extras.getString("notifiUri");
            this.f2207a = extras.getString("messageContent");
            LogUtil.c("Login_HwSchemeQrCodeActivity", "mPushId = ", this.d, "mServiceId =", this.c);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean CR_(Uri uri) {
        char c;
        String path = uri.getPath();
        boolean z = false;
        if (!TextUtils.isEmpty(path)) {
            path.hashCode();
            switch (path.hashCode()) {
                case -2088614330:
                    if (path.equals("/97J6mTfn5S")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1805349104:
                    if (path.equals("/1Lfn1eswP6")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1405805462:
                    if (path.equals("/router/startService")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -479754458:
                    if (path.equals("/messageH5/html/HwHealthQRCode.html")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -354535048:
                    if (path.equals("/HuaweiHealth/startComponent")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 928775054:
                    if (path.equals("/a1mpQudURi")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 931197178:
                    if (path.equals("/router/startPage")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1312804218:
                    if (path.equals("/a1jJk451Uk")) {
                        c = 7;
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
                case 1:
                case 3:
                case 5:
                case 7:
                    z = true;
                    break;
                case 2:
                case 4:
                case 6:
                    break;
                default:
                    Intent intent = new Intent();
                    intent.setPackage(getPackageName());
                    intent.setData(uri);
                    ResolveInfo resolveActivity = getPackageManager().resolveActivity(intent, 131072);
                    if (resolveActivity != null && resolveActivity.activityInfo != null) {
                        z = resolveActivity.activityInfo.name.endsWith(getClass().getSimpleName());
                        break;
                    }
                    break;
            }
        }
        if (!z) {
            ReleaseLogUtil.a("Login_HwSchemeQrCodeActivity", "'", uri, "' is not allow or not supported.");
        }
        return z;
    }

    private void CV_(Uri uri) {
        AppRouter.zi_(uri).b(this, new a(this));
    }

    static class a implements NaviCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<HwSchemeQrCodeActivity> f2208a;

        @Override // com.huawei.haf.router.NaviCallback
        public void onFound(Guidepost guidepost) {
        }

        a(HwSchemeQrCodeActivity hwSchemeQrCodeActivity) {
            this.f2208a = new WeakReference<>(hwSchemeQrCodeActivity);
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onLost(Guidepost guidepost) {
            HwSchemeQrCodeActivity hwSchemeQrCodeActivity = this.f2208a.get();
            if (hwSchemeQrCodeActivity != null) {
                hwSchemeQrCodeActivity.e();
            }
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onArrival(Guidepost guidepost) {
            HwSchemeQrCodeActivity hwSchemeQrCodeActivity = this.f2208a.get();
            if (hwSchemeQrCodeActivity != null) {
                hwSchemeQrCodeActivity.e();
            }
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onInterrupt(Guidepost guidepost) {
            HwSchemeQrCodeActivity hwSchemeQrCodeActivity = this.f2208a.get();
            if (hwSchemeQrCodeActivity != null) {
                hwSchemeQrCodeActivity.e();
            }
        }
    }

    private Uri CT_() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("Login_HwSchemeQrCodeActivity", "intent == null");
            return null;
        }
        Uri data = intent.getData();
        if (data == null) {
            LogUtil.a("Login_HwSchemeQrCodeActivity", "uri == null");
        }
        return data;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        setIntent(null);
        finish();
    }

    private void CS_(Uri uri) {
        String queryParameter = uri.getQueryParameter("from");
        gop.c(gop.aRd_(queryParameter, this.d, this.c, this.f2207a, uri), queryParameter, !TextUtils.isEmpty(this.b));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

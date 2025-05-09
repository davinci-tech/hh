package com.huawei.watchface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.watchface.ap;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.cm;
import com.huawei.watchface.cn;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.ILoginCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;

/* loaded from: classes7.dex */
public class ap {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10896a = new Object();
    private static ap b;
    private InstallWatchFaceBean c;
    private int d;
    private Context e;
    private cm f;
    private OperateWatchFaceCallback g;

    private ap(Context context) {
        if (context instanceof Activity) {
            this.e = context;
        }
    }

    public static ap a(Context context) {
        ap apVar;
        synchronized (f10896a) {
            if (b == null) {
                b = new ap(context);
            }
            apVar = b;
        }
        return apVar;
    }

    public void a(int i, InstallWatchFaceBean installWatchFaceBean) {
        HwLog.i("HwWatchFaceLoginHelper", "dealLogin watchFace should login.");
        if (this.e == null || installWatchFaceBean == null) {
            HwLog.w("HwWatchFaceLoginHelper", "dealLogin error.");
            return;
        }
        this.c = installWatchFaceBean;
        this.d = i;
        ThreadPoolManager.getInstance().tagExecute("HwWatchFaceLoginHelper", new AnonymousClass1());
    }

    /* renamed from: com.huawei.watchface.ap$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            final String commonCountryCode = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode();
            ap.this.a(new View.OnClickListener() { // from class: com.huawei.watchface.ap$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ap.AnonymousClass1.lambda$onClick$hianalytics1(ap.AnonymousClass1.this, commonCountryCode, view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.watchface.ap$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ap.AnonymousClass1.lambda$onClick$hianalytics2(ap.AnonymousClass1.this, view);
                }
            });
        }

        private /* synthetic */ void a(String str, View view) {
            ap.this.a(str);
        }

        private /* synthetic */ void a(View view) {
            ap.this.b();
        }

        static void lambda$onClick$hianalytics2(AnonymousClass1 anonymousClass1, View view) {
            anonymousClass1.a(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        static void lambda$onClick$hianalytics1(AnonymousClass1 anonymousClass1, String str, View view) {
            anonymousClass1.a(str, view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        boolean ifAllowLogin = HwWatchFaceApi.getInstance(this.e).ifAllowLogin();
        HwLog.i("HwWatchFaceLoginHelper", "onPositiveClick allowLogin:" + ifAllowLogin);
        b("-1");
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isHmsLiteEnable()) {
            a(str, ifAllowLogin);
        } else {
            b(str, ifAllowLogin);
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        h();
    }

    private void a(final String str, final boolean z) {
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).loginByHealthHmsLite(this.e, new IBaseResponseCallback() { // from class: com.huawei.watchface.ap.2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                HwLog.i("HwWatchFaceLoginHelper", "loginByHealthHmsLite responseCode: " + i);
                if (i == 0) {
                    HwLog.i("HwWatchFaceLoginHelper", "loginByHealthHmsLite success.");
                    HwWatchFaceApi.getInstance(Environment.getApplicationContext()).setAccountInfoMap(null);
                    ap.this.c(str, z);
                    ap.this.b("1");
                    return;
                }
                HwLog.w("HwWatchFaceLoginHelper", "loginByHealthHmsLite failed.");
                ap.this.b("0");
            }
        });
    }

    private void b(final String str, final boolean z) {
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).loginByHealthHms(this.e, new ILoginCallback() { // from class: com.huawei.watchface.ap.3
            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginSuccess(Object obj) {
                HwLog.i("HwWatchFaceLoginHelper", "loginByHms success");
                HwWatchFaceApi.getInstance(Environment.getApplicationContext()).setAccountInfoMap(null);
                ap.this.c(str, z);
                ap.this.b("1");
            }

            @Override // com.huawei.watchface.utils.callback.ILoginCallback
            public void onLoginFailed(Object obj) {
                HwLog.e("HwWatchFaceLoginHelper", "loginByHms failed");
                ap.this.b("0");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        HwLog.i("HwWatchFaceLoginHelper", "transmitWatchFaceLoginResult result:" + str);
        OperateWatchFaceCallback operateWatchFaceCallback = this.g;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitWatchFaceLoginResult(str);
        } else {
            HwLog.e("HwWatchFaceLoginHelper", "transmitWatchFaceLoginResult, mOperateWatchFaceCallback is null");
        }
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        HwLog.i("HwWatchFaceLoginHelper", "enter setWatchFaceCallback");
        this.g = operateWatchFaceCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, final boolean z) {
        ThreadPoolManager.getInstance().tagExecute("HwWatchFaceLoginHelper", new Runnable() { // from class: com.huawei.watchface.ap.4
            @Override // java.lang.Runnable
            public void run() {
                String commonCountryCode = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode();
                boolean ifAllowLogin = HwWatchFaceApi.getInstance(ap.this.e).ifAllowLogin();
                boolean z2 = z != ifAllowLogin && ifAllowLogin;
                HwLog.i("HwWatchFaceLoginHelper", "beforeCountryCode:" + str + ",nowCountryCode:" + commonCountryCode + "beforeallowLogin:" + z + ",nowAllowLogin:" + ifAllowLogin + ",isLoginChange:" + z2);
                if (!TextUtils.equals(str, commonCountryCode) || z2) {
                    HwLog.i("HwWatchFaceLoginHelper", "loginSuccessAction quitApp");
                    ap.this.g();
                } else {
                    Looper.prepare();
                    ap.this.c();
                    Looper.loop();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HwLog.i("HwWatchFaceLoginHelper", "startOperationAgain, mActionType:" + this.d);
        if (this.c == null) {
            HwLog.w("HwWatchFaceLoginHelper", "startActionAgain, mActionParams is null");
            return;
        }
        int i = this.d;
        if (i == 1) {
            e();
            return;
        }
        if (i == 2) {
            f();
        } else if (i == 3) {
            d();
        } else {
            HwLog.w("HwWatchFaceLoginHelper", "startActionAgain no type");
        }
    }

    private void d() {
        HwLog.i("HwWatchFaceLoginHelper", "refreshPage() enter");
        if (this.c == null) {
            HwLog.i("HwWatchFaceLoginHelper", "refreshPage() mActionParams is null.");
        } else if (this.g != null) {
            HwLog.i("HwWatchFaceLoginHelper", "refreshPage mOperateWatchFaceCallback notifyRefreshPage");
            this.g.notifyRefreshPage(this.c.getActionUrl());
        }
    }

    private void e() {
        InstallWatchFaceBean installWatchFaceBean = this.c;
        if (installWatchFaceBean == null) {
            return;
        }
        String watchFaceHiTopId = installWatchFaceBean.getWatchFaceHiTopId();
        String version = this.c.getVersion();
        String fileUrl = this.c.getFileUrl();
        String fileSize = this.c.getFileSize();
        if (TextUtils.isEmpty(watchFaceHiTopId) || TextUtils.isEmpty(version) || TextUtils.isEmpty(fileUrl) || TextUtils.isEmpty(fileSize)) {
            return;
        }
        HwLog.i("HwWatchFaceLoginHelper", "start install again");
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).installWatchFace(this.c);
    }

    private void f() {
        InstallWatchFaceBean installWatchFaceBean = this.c;
        if (installWatchFaceBean == null) {
            HwLog.i("HwWatchFaceLoginHelper", "startPay() mActionParams is null.");
            return;
        }
        String productId = installWatchFaceBean.getProductId();
        String watchFaceHiTopId = this.c.getWatchFaceHiTopId();
        String version = this.c.getVersion();
        String price = this.c.getPrice();
        String showDialog = this.c.getShowDialog();
        String symbolType = this.c.getSymbolType();
        if (TextUtils.isEmpty(productId) || TextUtils.isEmpty(watchFaceHiTopId) || TextUtils.isEmpty(version)) {
            HwLog.i("HwWatchFaceLoginHelper", "startPay() productId|hiTopId|version is empty.");
        } else {
            if (TextUtils.isEmpty(price) || TextUtils.isEmpty(showDialog) || TextUtils.isEmpty(symbolType)) {
                return;
            }
            HwLog.i("HwWatchFaceLoginHelper", "startPay() again.");
            s.a(this.e).a(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        String stringById;
        String stringById2;
        String stringById3;
        if (!CommonUtils.m(this.e) && !HwWatchFaceApi.getInstance(this.e).isHmsLiteEnable()) {
            stringById = DensityUtil.getStringById(R$string.IDS_hw_watchface_hw_account_install);
            stringById2 = DensityUtil.getStringById(R$string.IDS_hw_watchface_hms_install_remind);
            stringById3 = DensityUtil.getStringById(R$string.IDS_hw_watchface_go_hms_install);
        } else {
            stringById = DensityUtil.getStringById(R$string.IDS_hw_watchface_hw_account_login);
            stringById2 = DensityUtil.getStringById(R$string.IDS_hw_watchface_login_remind);
            stringById3 = DensityUtil.getStringById(R$string.dialog_no_account_title);
        }
        final String str = stringById;
        final String str2 = stringById2;
        final String str3 = stringById3;
        Context context = this.e;
        if (context == null) {
            HwLog.w("HwWatchFaceLoginHelper", "showLoginDialog, mContext is null");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.ap.5
                @Override // java.lang.Runnable
                public void run() {
                    ap apVar = ap.this;
                    apVar.f = new cm.a(apVar.e).a(str).b(str2).b(R$string.cancel, onClickListener2).a(str3, onClickListener).a();
                    if (ap.this.f.isShowing() || ((Activity) ap.this.e).isFinishing()) {
                        return;
                    }
                    HwLog.i("HwWatchFaceLoginHelper", "DialogWithTitle is showing");
                    ap.this.f.setCancelable(false);
                    ap.this.f.show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        HwLog.i("HwWatchFaceLoginHelper", "loginCallback quit app");
        showQuitDialog(new View.OnClickListener() { // from class: com.huawei.watchface.ap.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
                LocalBroadcastManager.getInstance(ap.this.e).sendBroadcast(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void showQuitDialog(final View.OnClickListener onClickListener) {
        Context context = this.e;
        if (context == null) {
            HwLog.w("HwWatchFaceLoginHelper", "showLoginDialog, mContext is null");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.ap.7
                @Override // java.lang.Runnable
                public void run() {
                    cn a2 = new cn.a(ap.this.e).a(R$string.IDS_watchface_quit_app).a(R$string.hava_kown, onClickListener).a();
                    if (a2.isShowing() || ((Activity) ap.this.e).isFinishing()) {
                        return;
                    }
                    HwLog.i("HwWatchFaceLoginHelper", "QuitDialog is showing");
                    a2.setCancelable(false);
                    a2.show();
                }
            });
        }
    }

    private void h() {
        cm cmVar = this.f;
        if (cmVar != null) {
            cmVar.dismiss();
            this.f = null;
        }
    }

    public static void a() {
        synchronized (f10896a) {
            if (b != null) {
                b = null;
            }
        }
    }
}

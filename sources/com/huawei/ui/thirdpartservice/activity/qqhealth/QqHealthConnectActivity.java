package com.huawei.ui.thirdpartservice.activity.qqhealth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.main.api.MainCommonApi;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity;
import com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqHealthInteractors;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLogin;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLoginMgr;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.nrh;
import defpackage.sec;
import defpackage.sha;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class QqHealthConnectActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private e f10552a;
    private CommonDialog21 b = null;
    private QqHealthInteractors d = null;
    private QqLoginMgr c = null;
    private AuthorizeCallback e = new AuthorizeCallback() { // from class: com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity.2
        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void initCallback(boolean z) {
            LogUtil.a("QqHealthConnectActivity", "mAuthorizeCallback.initCallback() isSuccess=", Boolean.valueOf(z));
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void loginCallback(String str, String str2, String str3) {
            if (TextUtils.isEmpty(str) || QqHealthConnectActivity.this.f10552a == null) {
                return;
            }
            QqHealthConnectActivity.this.f10552a.sendMessageDelayed(QqHealthConnectActivity.this.f10552a.obtainMessage(1), 300L);
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void logoutCallback(boolean z) {
            LogUtil.a("QqHealthConnectActivity", "mAuthorizeCallback.logoutCallback() isSuccess=", Boolean.valueOf(z));
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_qq_health_connect);
        this.d = QqHealthInteractors.getInstance();
        this.f10552a = new e(this);
        dXj_(this, this.e);
        findViewById(R.id.qqhealthConnectButton).setOnClickListener(dXi_());
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("isQqTokenValid", false)) {
            return;
        }
        nrh.b(this, R.string._2130844366_res_0x7f021ace);
    }

    private View.OnClickListener dXi_() {
        return new View.OnClickListener() { // from class: sft
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QqHealthConnectActivity.this.dXk_(view);
            }
        };
    }

    public /* synthetic */ void dXk_(View view) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", "1");
        ixx.d().d(this, AnalyticsValue.HEALTH_MINE_SHARE_DATA_CONNECT_2040035.value(), hashMap, 0);
        if ("false".equals(((MainCommonApi) Services.c("Main", MainCommonApi.class)).getPersonalPrivacySettingValue(3))) {
            ((WeChatApi) Services.c("Main", WeChatApi.class)).showSportPrivacySettingDialog(this, R.string._2130841737_res_0x7f021089);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!CommonUtil.aa(this)) {
            LogUtil.a("QqHealthConnectActivity", "Network is not Connected ");
            nrh.e(this, R.string._2130841884_res_0x7f02111c);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (sec.b(this, "com.tencent.mobileqq")) {
            LogUtil.a("QqHealthConnectActivity", "QqHealthConnectActivity onClick() loginQq().");
            a();
        } else {
            LogUtil.a("QqHealthConnectActivity", "System is not install QQ App.");
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(QqLoginMgr.MARKET_URI));
                intent.addFlags(268435456);
                startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("QqHealthConnectActivity", "start qq ActivityNotFoundException");
                sec.d(this, R.string._2130841794_res_0x7f0210c2, R.string._2130842392_res_0x7f021318);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("QqHealthConnectActivity", "QqHealthActivity onResume");
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("QqHealthConnectActivity", "QqHealthActivity onDestroy");
        this.f10552a.removeCallbacksAndMessages(null);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("QqHealthConnectActivity", "showWaitingDialog: mLoadDataDialog ");
        if (this.b != null) {
            return;
        }
        if (isFinishing()) {
            LogUtil.a("QqHealthConnectActivity", "showWaitingDialog: isFinishing...");
            return;
        }
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.b = a2;
        a2.e(getString(R.string._2130841746_res_0x7f021092));
        this.b.setCancelable(false);
        this.b.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("QqHealthConnectActivity", "closeLoadDataDialog: mLoadDataDialog = ", this.b);
        if (isFinishing()) {
            LogUtil.a("QqHealthConnectActivity", "closeLoadDataDialog: isFinishing...");
            return;
        }
        CommonDialog21 commonDialog21 = this.b;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.b = null;
        }
    }

    private void dXj_(Activity activity, AuthorizeCallback authorizeCallback) {
        if (this.c == null) {
            this.c = new QqLoginMgr(activity, authorizeCallback, sha.b(1, 20, PointerIconCompat.TYPE_GRAB, 2020));
        }
    }

    private void a() {
        if (this.c != null) {
            jdx.b(new Runnable() { // from class: sfr
                @Override // java.lang.Runnable
                public final void run() {
                    QqHealthConnectActivity.this.b();
                }
            });
        }
    }

    public /* synthetic */ void b() {
        this.c.login();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final ICloudOperationResult<String> iCloudOperationResult) {
        LogUtil.c("QqHealthConnectActivity", "getUserName");
        jdx.b(new Runnable() { // from class: sfu
            @Override // java.lang.Runnable
            public final void run() {
                QqHealthConnectActivity.this.c(iCloudOperationResult);
            }
        });
    }

    public /* synthetic */ void c(final ICloudOperationResult iCloudOperationResult) {
        this.c.getUserName(this, new QqLogin.QqNameCallback() { // from class: sfv
            @Override // com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLogin.QqNameCallback
            public final void onQqNameCallback(String str) {
                QqHealthConnectActivity.c(ICloudOperationResult.this, str);
            }
        });
    }

    public static /* synthetic */ void c(ICloudOperationResult iCloudOperationResult, String str) {
        LogUtil.c("QqHealthConnectActivity", "userName = ", str);
        if (iCloudOperationResult != null) {
            iCloudOperationResult.operationResult(str, str, true);
        }
    }

    public static class e extends BaseHandler<QqHealthConnectActivity> {
        e(QqHealthConnectActivity qqHealthConnectActivity) {
            super(qqHealthConnectActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXl_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(QqHealthConnectActivity qqHealthConnectActivity, Message message) {
            LogUtil.a("QqHealthConnectActivity", "handleMessage msg is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                c(qqHealthConnectActivity);
                return;
            }
            if (i == 2) {
                e(qqHealthConnectActivity);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                qqHealthConnectActivity.c();
                nrh.b(qqHealthConnectActivity, R.string._2130841413_res_0x7f020f45);
                return;
            }
            qqHealthConnectActivity.c();
            KeyValDbManager.b(BaseApplication.getContext()).e("third_part_service_qq_health_status", "true");
            qqHealthConnectActivity.startActivity(new Intent(qqHealthConnectActivity, (Class<?>) QqHealthActivity.class));
            qqHealthConnectActivity.finish();
        }

        private void c(final QqHealthConnectActivity qqHealthConnectActivity) {
            qqHealthConnectActivity.d();
            qqHealthConnectActivity.a((ICloudOperationResult<String>) new ICloudOperationResult() { // from class: sfz
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    QqHealthConnectActivity.e.c(QqHealthConnectActivity.this, (String) obj, str, z);
                }
            });
        }

        public static /* synthetic */ void c(QqHealthConnectActivity qqHealthConnectActivity, String str, String str2, boolean z) {
            LogUtil.c("QqHealthConnectActivity", "isSuccess = ", Boolean.valueOf(z));
            if (z) {
                if (qqHealthConnectActivity.c != null) {
                    qqHealthConnectActivity.c.saveQqUserInfo();
                }
                qqHealthConnectActivity.f10552a.sendMessageDelayed(qqHealthConnectActivity.f10552a.obtainMessage(2), 300L);
                return;
            }
            qqHealthConnectActivity.f10552a.sendMessageDelayed(qqHealthConnectActivity.f10552a.obtainMessage(4), 300L);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void e(final com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity r6) {
            /*
                r5 = this;
                com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthDb r0 = new com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthDb
                r0.<init>()
                com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthTable r0 = r0.get()
                java.lang.String r1 = "QqHealthConnectActivity"
                if (r0 != 0) goto L29
                java.lang.String r0 = "loginHuaweiCloud qqHealthTable is null"
                java.lang.Object[] r0 = new java.lang.Object[]{r0}
                com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity$e r0 = com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity.d(r6)
                com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity$e r6 = com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity.d(r6)
                r1 = 4
                android.os.Message r6 = r6.obtainMessage(r1)
                r1 = 300(0x12c, double:1.48E-321)
                r0.sendMessageDelayed(r6, r1)
                return
            L29:
                com.huawei.hwcloudmodel.model.ThirdUserToken r2 = new com.huawei.hwcloudmodel.model.ThirdUserToken
                r2.<init>()
                android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                com.huawei.login.ui.login.LoginInit r3 = com.huawei.login.ui.login.LoginInit.getInstance(r3)
                r4 = 1011(0x3f3, float:1.417E-42)
                java.lang.String r3 = r3.getAccountInfo(r4)
                if (r3 == 0) goto L4c
                long r3 = java.lang.Long.parseLong(r3)     // Catch: java.lang.NumberFormatException -> L43
                goto L4e
            L43:
                java.lang.String r3 = "loginHuaweiCloud NumberFormatException"
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
            L4c:
                r3 = 0
            L4e:
                r2.setHuid(r3)
                java.lang.String r3 = r0.getOpenId()
                r2.setThirdAccount(r3)
                java.lang.String r3 = r0.getToken()
                r2.setThirdAccessToken(r3)
                r3 = 7
                r2.setType(r3)
                java.lang.String r0 = r0.getExpireTime()
                if (r0 == 0) goto L77
                int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L6e
                goto L78
            L6e:
                java.lang.String r3 = "loginHuaweiCloud NumberFormatException expireTimeStr = "
                java.lang.Object[] r0 = new java.lang.Object[]{r3, r0}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            L77:
                r0 = 0
            L78:
                r2.setExpireTime(r0)
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                java.lang.String r1 = "thirdUserToken"
                r0.put(r1, r2)
                shc r1 = new shc
                java.lang.Class<java.lang.String> r2 = java.lang.String.class
                java.lang.String r3 = "/profile/third/authorize"
                r1.<init>(r2, r3)
                sfx r2 = new sfx
                r2.<init>()
                r1.c(r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity.e.e(com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthConnectActivity):void");
        }

        public static /* synthetic */ void a(QqHealthConnectActivity qqHealthConnectActivity, String str, String str2, boolean z) {
            LogUtil.a("QqHealthConnectActivity", "HwCloudManagerQQ authorize() isSuccess = ", Boolean.valueOf(z));
            if (z) {
                qqHealthConnectActivity.d.sendQqHealthConnectSuccessBroadcast();
                qqHealthConnectActivity.f10552a.sendMessageDelayed(qqHealthConnectActivity.f10552a.obtainMessage(3), 300L);
            } else {
                qqHealthConnectActivity.f10552a.sendMessageDelayed(qqHealthConnectActivity.f10552a.obtainMessage(4), 300L);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.c.onActivityResult(i, i2, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

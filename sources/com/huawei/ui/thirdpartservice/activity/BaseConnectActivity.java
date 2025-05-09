package com.huawei.ui.thirdpartservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.thirdpartservice.activity.BaseConnectActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferencePerformOauthCallback;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import defpackage.ixx;
import defpackage.jah;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class BaseConnectActivity extends BaseActivity {
    static final String KEY_CODE = "code";
    static final String KEY_ERROR = "error";
    private boolean mIsSupport;
    private CommonDialog21 mLoadDataDialog = null;

    /* loaded from: classes7.dex */
    public interface CheckSupportCallBack {
        void checkResult(boolean z);
    }

    protected abstract void checkConnectStatus();

    protected abstract Intent createDisconnectIntent();

    protected abstract OauthManager createOauthManager();

    protected abstract String getAccountType();

    protected abstract String getAuthorizationUrl();

    protected abstract String getClientId();

    protected abstract String getGrsKey();

    protected abstract String getRedirectUri();

    protected abstract String getTag();

    protected abstract void initView();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oauth_connect);
        initView();
        LogUtil.a(getTag(), "onCreate.");
        showWaitingDialog();
        checkSupport(new CheckSupportCallBack() { // from class: sdv
            @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity.CheckSupportCallBack
            public final void checkResult(boolean z) {
                BaseConnectActivity.this.m865x8433915d(z);
            }
        });
        recordBiEvent();
    }

    /* renamed from: lambda$onCreate$0$com-huawei-ui-thirdpartservice-activity-BaseConnectActivity, reason: not valid java name */
    public /* synthetic */ void m865x8433915d(boolean z) {
        m866xd61f2fc6(getIntent(), z);
    }

    protected void openOauthPage() {
        if (!this.mIsSupport) {
            nrh.b(this, R.string._2130848950_res_0x7f022cb6);
            return;
        }
        if (nsn.o()) {
            return;
        }
        if (!CommonUtil.aa(this)) {
            LogUtil.a(getTag(), "Network is not Connected ");
            nrh.e(this, R.string._2130841393_res_0x7f020f31);
            return;
        }
        LogUtil.a(getTag(), "openOauthPage");
        String e = jah.c().e(getGrsKey());
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(getTag(), "oauth domain is empty!");
            return;
        }
        String e2 = jah.c().e("domain_oauth_redirect_uri");
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h(getTag(), "redirect domain is empty!");
            return;
        }
        String str = e + getAuthorizationUrl();
        String str2 = e2 + getRedirectUri();
        LogUtil.a(getTag(), "getClientId", getClientId());
        OauthManager.openOauthPage(this, String.format(Locale.ENGLISH, str, getClientId(), str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissWaitingDialog() {
        LogUtil.a(getTag(), "closeLoadDataDialog: mLoadDataDialog = ", this.mLoadDataDialog);
        CommonDialog21 commonDialog21 = this.mLoadDataDialog;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.mLoadDataDialog = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a(getTag(), "onNewIntent.");
        showWaitingDialog();
        checkSupport(new CheckSupportCallBack() { // from class: sdu
            @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity.CheckSupportCallBack
            public final void checkResult(boolean z) {
                BaseConnectActivity.this.m866xd61f2fc6(intent, z);
            }
        });
    }

    private void startOauth(Intent intent) {
        if (intent == null) {
            dismissWaitingDialog();
            return;
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (zs_ == null) {
            dismissWaitingDialog();
            return;
        }
        checkConnectStatus();
        LogUtil.a(getTag(), "uri:", zs_.toString());
        String queryParameter = zs_.getQueryParameter("code");
        if (TextUtils.isEmpty(queryParameter)) {
            String queryParameter2 = zs_.getQueryParameter("error");
            if (!TextUtils.isEmpty(queryParameter2)) {
                nrh.c(this, queryParameter2);
            }
            LogUtil.h(getTag(), "startOauth: data is error");
            dismissWaitingDialog();
            return;
        }
        createOauthManager().performOauth(queryParameter, new a(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSupportResult, reason: merged with bridge method [inline-methods] */
    public void m866xd61f2fc6(Intent intent, boolean z) {
        LogUtil.a(getTag(), "checkSupport: ", Boolean.valueOf(z));
        this.mIsSupport = z;
        if (z) {
            startOauth(intent);
        } else {
            nrh.b(this, R.string._2130848950_res_0x7f022cb6);
            dismissWaitingDialog();
        }
    }

    private void recordBiEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, getAccountType());
        LogUtil.a(getTag(), AnalyticsValue.THIRD_CONNECT_ACTIVITY_2041096.value(), getAccountType());
        ixx.d().d(this, AnalyticsValue.THIRD_CONNECT_ACTIVITY_2041096.value(), hashMap, 0);
    }

    private void showWaitingDialog() {
        LogUtil.a(getTag(), "showWaitingDialog: mLoadDataDialog ");
        if (this.mLoadDataDialog != null) {
            return;
        }
        if (isFinishing()) {
            LogUtil.a(getTag(), "showWaitingDialog: isFinishing...");
            return;
        }
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.mLoadDataDialog = a2;
        a2.e(getString(R.string._2130841528_res_0x7f020fb8));
        this.mLoadDataDialog.setCancelable(false);
        this.mLoadDataDialog.a();
        this.mLoadDataDialog.show();
    }

    /* loaded from: classes7.dex */
    public static class a extends ReferencePerformOauthCallback<BaseConnectActivity> {
        a(BaseConnectActivity baseConnectActivity) {
            super(baseConnectActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferencePerformOauthCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void oauthResultWhenReferenceNotNull(final BaseConnectActivity baseConnectActivity, final boolean z, final boolean z2, long j, final String str) {
            baseConnectActivity.runOnUiThread(new Runnable() { // from class: sdx
                @Override // java.lang.Runnable
                public final void run() {
                    BaseConnectActivity.a.a(BaseConnectActivity.this, z, z2, str);
                }
            });
        }

        public static /* synthetic */ void a(BaseConnectActivity baseConnectActivity, boolean z, boolean z2, String str) {
            baseConnectActivity.dismissWaitingDialog();
            if (!z) {
                nrh.b(baseConnectActivity, R.string._2130841884_res_0x7f02111c);
                return;
            }
            if (z2) {
                baseConnectActivity.startActivity(baseConnectActivity.createDisconnectIntent());
                baseConnectActivity.finish();
            } else if (TextUtils.isEmpty(str)) {
                nrh.b(baseConnectActivity, R.string._2130841392_res_0x7f020f30);
            } else {
                nrh.c(baseConnectActivity, str);
            }
        }
    }

    public void checkSupport(CheckSupportCallBack checkSupportCallBack) {
        LogUtil.a(getTag(), "checkSupport: ");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(getTag(), "onDestroy.");
        dismissWaitingDialog();
    }
}

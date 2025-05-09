package com.huawei.ui.thirdpartservice.activity.qqhealth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthActivity;
import com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.QqLoginMgr;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthDb;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr.QqHealthTable;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.sha;
import defpackage.shg;
import health.compact.a.KeyValDbManager;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class QqHealthActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private QqLoginMgr f10551a;
    private Context b;
    private HealthButton c;
    private AuthorizeCallback d = new AuthorizeCallback() { // from class: com.huawei.ui.thirdpartservice.activity.qqhealth.QqHealthActivity.4
        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void initCallback(boolean z) {
            LogUtil.a("QqHealthActivity", "mAuthorizeCallback.initCallback() isSuccess=", Boolean.valueOf(z));
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void loginCallback(String str, String str2, String str3) {
            LogUtil.c("QqHealthActivity", "mAuthorizeCallback.loginCallback() success");
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.AuthorizeCallback
        public void logoutCallback(boolean z) {
            LogUtil.a("QqHealthActivity", "mAuthorizeCallback.logoutCallback() isSuccess=", Boolean.valueOf(z));
        }
    };
    private ImageView e;
    private HealthTextView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        LogUtil.a("QqHealthActivity", "enter onCreate()");
        e();
    }

    private void e() {
        setContentView(R.layout.activity_qq_health);
        this.e = (ImageView) findViewById(R.id.qq_health_head_img);
        this.j = (HealthTextView) findViewById(R.id.qq_health_name);
        HealthButton healthButton = (HealthButton) findViewById(R.id.DisconnectQQHealthButton);
        this.c = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sfm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QqHealthActivity.this.dXh_(view);
            }
        });
        QqHealthTable qqHealthTable = new QqHealthDb().get();
        if (qqHealthTable != null) {
            this.j.setText(qqHealthTable.getNickName());
            nrf.cJF_(Uri.parse(qqHealthTable.getQqLogoPath()), this.e);
            return;
        }
        this.e.setImageResource(R.mipmap._2131821477_res_0x7f1103a5);
    }

    public /* synthetic */ void dXh_(View view) {
        e(AnalyticsValue.HEALTH_MINE_SHARE_DATA_QQ_CONNECT_2040036.value(), "1");
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        LogUtil.a("QqHealthActivity", "enter disconnectQqHealth():");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.b).e(R.string._2130841743_res_0x7f02108f).czC_(R.string._2130841740_res_0x7f02108c, new View.OnClickListener() { // from class: sfn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QqHealthActivity.this.dXg_(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: sfo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QqHealthActivity.dXe_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void dXg_(View view) {
        LogUtil.a("QqHealthActivity", "disconnectQqHealth");
        new QqHealthDb().delete();
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAccountType", "7");
        new shg(Object.class, "/profile/third/cancelAuthorize").d(hashMap, new ICloudOperationResult() { // from class: sfs
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                LogUtil.a("QqHealthActivity", "HwCloudManagerQQ cancelAuthorize() isSuccess = ", Boolean.valueOf(z));
            }
        });
        dXf_(this, this.d);
        QqLoginMgr qqLoginMgr = this.f10551a;
        if (qqLoginMgr != null) {
            qqLoginMgr.logout();
        } else {
            LogUtil.b("QqHealthActivity", "mQqLoginMgr is null! ");
        }
        KeyValDbManager.b(BaseApplication.getContext()).d("third_part_service_qq_health_status", "false", null);
        finish();
        startActivity(new Intent(this.b, (Class<?>) QqHealthConnectActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dXe_(View view) {
        LogUtil.a("QqHealthActivity", "user cancel disconnectQqHealth");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dXf_(Activity activity, AuthorizeCallback authorizeCallback) {
        LogUtil.a("QqHealthActivity", "registerQqLogin enter():");
        if (this.f10551a == null) {
            this.f10551a = new QqLoginMgr(activity, authorizeCallback, sha.b(1, 20, PointerIconCompat.TYPE_GRAB, 2020));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("QqHealthActivity", "enter onResume():");
        super.onResume();
    }

    private void e(String str, String str2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.b, str, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

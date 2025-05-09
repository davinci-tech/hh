package com.huawei.ui.main.stories.me.activity.thirdparty;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.huaweihealth.HuaweiHealthApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.thirdparty.ThirdPartySyncActivity;
import com.huawei.ui.main.stories.me.util.AppSettingUtil;
import com.tencent.open.SocialOperation;
import defpackage.nrh;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class ThirdPartySyncActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CustomProgressDialog f10359a;
    private HealthOpenSDK b;
    private Context c;
    private AppSettingUtil d;
    private CustomProgressDialog.Builder e;
    private d f;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        LogUtil.a("ThirdPartySyncActivity", "onCreate");
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = 0;
        attributes.width = 0;
        getWindow().setAttributes(attributes);
        this.c = this;
        this.f = new d();
        this.d = new AppSettingUtil(BaseApplication.e());
        e();
        if (NetworkUtil.i()) {
            LogUtil.a("ThirdPartySyncActivity", "Network is connected");
            a();
            b();
        } else {
            nrh.b(BaseApplication.e(), R$string.IDS_hw_show_set_about_privacy_connectting_error);
            finish();
        }
    }

    private String b(String str) {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("ThirdPartySyncActivity", "getParamFromIntent intent is null");
            return "";
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (intent.hasExtra(str) || zs_ == null) {
            return intent.getStringExtra(str);
        }
        return zs_.getQueryParameter(str);
    }

    private void a() {
        int i;
        if (this.f10359a == null) {
            try {
                i = Integer.parseInt(b("extraColor"));
            } catch (NumberFormatException e) {
                LogUtil.b("ThirdPartySyncActivity", "NumberFormatException: ", e.getMessage());
                i = 0;
            }
            if (i == getColor(R.color._2131297808_res_0x7f090610)) {
                i = 0;
            }
            CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.c);
            this.e = builder;
            builder.d(getResources().getString(R$string.IDS_third_party_sync_dialog_title));
            CustomProgressDialog e2 = this.e.e();
            this.f10359a = e2;
            e2.setCanceledOnTouchOutside(false);
            this.f10359a.setCancelable(false);
            HealthProgressBar healthProgressBar = (HealthProgressBar) this.f10359a.findViewById(R.id.custom_progress_dialog_progressbar);
            if (i == 0) {
                healthProgressBar.d(this.c, -1, R.color._2131296927_res_0x7f09029f);
            } else {
                healthProgressBar.setProgressTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{i}));
            }
            HealthTextView healthTextView = new HealthTextView(this.c);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = nsn.c(this.c, 16.0f);
            layoutParams.gravity = 1;
            healthTextView.setLayoutParams(layoutParams);
            healthTextView.setText(R$string.cancel);
            if (i == 0) {
                i = ContextCompat.getColor(this.c, R.color._2131296927_res_0x7f09029f);
            }
            healthTextView.setTextColor(i);
            healthTextView.setTextSize(16.0f);
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: rhf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ThirdPartySyncActivity.this.dOg_(view);
                }
            });
            ((LinearLayout) this.f10359a.findViewById(R.id.AppUpdateDialog_progress_layout)).addView(healthTextView);
            this.f10359a.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: rhd
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    ThirdPartySyncActivity.this.dOh_(dialogInterface);
                }
            });
        }
        this.f10359a.show();
        LogUtil.a("ThirdPartySyncActivity", "Dialog show");
    }

    public /* synthetic */ void dOg_(View view) {
        ReleaseLogUtil.e("ThirdPartySyncActivity", "Canceling data synchronization");
        HiHealthNativeApi.a(BaseApplication.e()).synCloudCancel();
        this.f10359a.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dOh_(DialogInterface dialogInterface) {
        LogUtil.a("ThirdPartySyncActivity", "Dialog dismiss");
        finish();
    }

    private void b() {
        HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
        this.b = healthOpenSDK;
        healthOpenSDK.initSDK(BaseApplication.e(), new IExecuteResult() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.ThirdPartySyncActivity.2
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                LogUtil.a("ThirdPartySyncActivity", "healthOpenSdk.initSDK onSuccess");
                ThirdPartySyncActivity.this.i();
                ThirdPartySyncActivity.this.d.c(ThirdPartySyncActivity.this.b, (HealthDownLoadWidget) null, (String) null);
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                LogUtil.b("ThirdPartySyncActivity", "healthOpenSdk.initSDK onFailed");
                ThirdPartySyncActivity.this.f();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                LogUtil.b("ThirdPartySyncActivity", "healthOpenSdk.initSDK onServiceException");
                ThirdPartySyncActivity.this.f();
            }
        }, "HuaweiHealth");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Intent intent = new Intent();
        intent.setAction("sync_cloud_data_action");
        intent.putExtra("sync_cloud_data_status", "sync_cloud_data_setting_flag");
        BroadcastManagerUtil.bFI_(BaseApplication.e(), intent);
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this.f, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.c("ThirdPartySyncActivity", "finishDataSync sync done");
        Date date = new Date();
        long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.e("ThirdPartySyncActivity", "finishDataSync formatDate:", new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMdd")).format(date), " formatTime:", nsj.c(BaseApplication.e(), currentTimeMillis, 1));
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10000), "last_sync_time", String.valueOf(currentTimeMillis), new StorageParams());
        ((HuaweiHealthApi) Services.c("HuaweiHealth", HuaweiHealthApi.class)).updateSyncFinishTime(currentTimeMillis);
        nrh.b(BaseApplication.e(), R$string.IDS_hw_show_synchronous_success);
        h();
        c();
    }

    private void c() {
        CustomProgressDialog customProgressDialog = this.f10359a;
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
        }
    }

    private void h() {
        ReleaseLogUtil.e("ThirdPartySyncActivity", "enter responseRedirection");
        String b = b("appId");
        String b2 = b(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL);
        String b3 = b(SocialOperation.GAME_SIGNATURE);
        if (TextUtils.isEmpty(b2)) {
            ReleaseLogUtil.e("ThirdPartySyncActivity", "redirectUrl is null, no response redirection required");
            return;
        }
        if (((HmsAuthApi) Services.c("HealthKit", HmsAuthApi.class)).isValidRedirectUrl(b, b3, b2)) {
            try {
                startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(b(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL))));
                return;
            } catch (ActivityNotFoundException e) {
                LogUtil.b("ThirdPartySyncActivity", "responseRedirection ActivityNotFoundException: ", e.getMessage());
                return;
            }
        }
        AppRouter.b("/home/main").e(Constants.HOME_TAB_NAME, Constants.HOME).c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        CustomProgressDialog.Builder builder = this.e;
        if (builder != null) {
            builder.d(i);
            this.e.c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        nrh.b(BaseApplication.e(), R$string.IDS_hw_show_me_sync_fail);
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("ThirdPartySyncActivity", "onDestroy");
        LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(this.f);
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK != null) {
            healthOpenSDK.destorySDK();
            this.b = null;
        }
    }

    /* loaded from: classes8.dex */
    class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("ThirdPartySyncActivity", "onReceive() to enter");
            if (intent == null) {
                LogUtil.h("ThirdPartySyncActivity", "onReceive intent is null");
                return;
            }
            if (!"com.huawei.hihealth.action_sync".equals(intent.getAction())) {
                LogUtil.h("ThirdPartySyncActivity", "onReceive action is not action_sync");
                return;
            }
            try {
                int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
                if (intExtra == 0) {
                    LogUtil.c("ThirdPartySyncActivity", "updateSyncView Sync BEGIN");
                } else if (intExtra == 1) {
                    int doubleExtra = (int) intent.getDoubleExtra("com.huawei.hihealth.action_sync_process", 0.0d);
                    LogUtil.a("ThirdPartySyncActivity", "updateSyncView process = ", Integer.valueOf(doubleExtra));
                    ThirdPartySyncActivity.this.d(doubleExtra);
                } else if (intExtra == 2) {
                    ThirdPartySyncActivity.this.d();
                } else if (intExtra == 3) {
                    ThirdPartySyncActivity.this.f();
                } else {
                    LogUtil.a("ThirdPartySyncActivity", "unknown message.what");
                }
            } catch (Exception unused) {
                LogUtil.b("ThirdPartySyncActivity", "getDataFromIntent Exception");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

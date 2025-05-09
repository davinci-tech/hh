package com.huawei.appgallery.coreservice.internal.service.installhiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.appgallery.coreservice.api.ConnectConfig;
import com.huawei.appgallery.marketinstallerservice.api.FailResultParam;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.InstallerApi;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.secure.android.common.intent.SafeIntent;
import defpackage.afu;
import defpackage.afw;

/* loaded from: classes2.dex */
public class GuideInstallAppGallery extends Activity {
    private boolean c;
    private AlertDialog d = null;
    private ConnectConfig e;

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.d;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.d.dismiss();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        afu.e("GuideInstallAppGallery", "onCreate");
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.c = safeIntent.getBooleanExtra("is_app_market_exist", false);
        this.e = (ConnectConfig) safeIntent.getSerializableExtra("vendor_app_connect_config");
        boolean z = this.c;
        int i = z ? R.string._2130850813_res_0x7f0233fd : R.string._2130850812_res_0x7f0233fc;
        int i2 = z ? R.string._2130850764_res_0x7f0233cc : R.string._2130850763_res_0x7f0233cb;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) null);
        String string = getResources().getString(R.string._2130850811_res_0x7f0233fb);
        ConnectConfig connectConfig = this.e;
        if (connectConfig != null) {
            string = connectConfig.getInstallAppName();
        }
        builder.setMessage(getResources().getString(i, string));
        builder.setPositiveButton(getResources().getText(i2), new e());
        builder.setNegativeButton(getResources().getText(R.string._2130850762_res_0x7f0233ca), new c());
        builder.setOnKeyListener(new a());
        AlertDialog create = builder.create();
        this.d = create;
        create.setCanceledOnTouchOutside(false);
        if (isDestroyed() || isFinishing()) {
            return;
        }
        this.d.show();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        return new SafeIntent(super.getIntent());
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
            overridePendingTransition(0, 0);
        } catch (Exception e2) {
            afu.a("GuideInstallAppGallery", "activity finish exception:" + e2.getMessage());
        }
    }

    class b implements InstallCallback {
        @Override // com.huawei.appgallery.marketinstallerservice.api.InstallCallback
        public void onSuccess(MarketInfo marketInfo) {
            afu.e("GuideInstallAppGallery", "download market succeed!");
            GuideInstallAppGallery.this.setResult(-1);
            GuideInstallAppGallery.this.finish();
        }

        @Override // com.huawei.appgallery.marketinstallerservice.api.InstallCallback
        public void onFailed(FailResultParam failResultParam) {
            if (failResultParam != null) {
                afu.e("GuideInstallAppGallery", "can not download market: result:" + failResultParam.getResult() + ", reason:" + failResultParam.getReason() + ", responseCode:" + failResultParam.getResponseCode() + ", rtnCode:" + failResultParam.getRtnCode());
                GuideInstallAppGallery.this.setResult(0);
            }
            GuideInstallAppGallery.this.finish();
        }

        b() {
        }
    }

    class a implements DialogInterface.OnKeyListener {
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (4 != i || keyEvent.getAction() != 1) {
                return false;
            }
            afu.e("GuideInstallAppGallery", "use cancel download market by KEYCODE_BACK!");
            GuideInstallAppGallery.this.setResult(0);
            GuideInstallAppGallery.this.finish();
            return true;
        }

        a() {
        }
    }

    class c implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            afu.e("GuideInstallAppGallery", "use cancel download market by NegativeButton!");
            GuideInstallAppGallery.this.setResult(0);
            GuideInstallAppGallery.this.finish();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        c() {
        }
    }

    class e implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            String a2 = afw.a(GuideInstallAppGallery.this, "STORE");
            InstallParamSpec installParamSpec = new InstallParamSpec();
            installParamSpec.setServerUrl(a2);
            installParamSpec.setSubsystem("agdsdk");
            installParamSpec.setFailResultPromptType(2);
            installParamSpec.setUpdate(GuideInstallAppGallery.this.c);
            if (GuideInstallAppGallery.this.e != null) {
                installParamSpec.setMarketPkg(GuideInstallAppGallery.this.e.getConnectAppPkg());
            }
            GuideInstallAppGallery guideInstallAppGallery = GuideInstallAppGallery.this;
            InstallerApi.installMarket(guideInstallAppGallery, installParamSpec, guideInstallAppGallery.new b());
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        e() {
        }
    }

    public static PendingIntent gM_(Context context, ConnectConfig connectConfig, String str) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, (Class<?>) GuideInstallAppGallery.class);
        intent.putExtra("is_app_market_exist", !TextUtils.isEmpty(str));
        intent.putExtra("vendor_app_connect_config", connectConfig);
        return PendingIntent.getActivity(context, SmartMsgConstant.MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND, intent, Build.VERSION.SDK_INT >= 30 ? 1140850688 : 1073741824);
    }

    public static PendingIntent gL_(Context context) {
        if (context == null) {
            return null;
        }
        String a2 = com.huawei.appgallery.coreservice.e.a(context);
        Intent intent = new Intent(context, (Class<?>) GuideInstallAppGallery.class);
        intent.putExtra("is_app_market_exist", !TextUtils.isEmpty(a2));
        return PendingIntent.getActivity(context, SmartMsgConstant.MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND, intent, Build.VERSION.SDK_INT >= 30 ? 1140850688 : 1073741824);
    }
}

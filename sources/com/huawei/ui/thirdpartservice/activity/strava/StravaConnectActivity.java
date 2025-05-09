package com.huawei.ui.thirdpartservice.activity.strava;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.thirdpartservice.activity.BaseConnectActivity;
import com.huawei.ui.thirdpartservice.activity.strava.StravaConnectActivity;
import com.huawei.ui.thirdpartservice.syncdata.constants.StravaInfo;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig;
import defpackage.sgd;
import defpackage.sjm;
import defpackage.sjo;

/* loaded from: classes2.dex */
public class StravaConnectActivity extends BaseConnectActivity {
    private StravaConfig c = new StravaConfig();
    private StravaInfo e;

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = sjm.b(LoginInit.getInstance(this).getAccountInfo(1009));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkConnectStatus() {
        sjo.d().checkConnectStatus(new sgd(this));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void initView() {
        ((CustomTitleBar) findViewById(R.id.title_bar)).setTitleText(getString(R.string._2130850494_res_0x7f0232be));
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821105_res_0x7f110231);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connect_content);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.sport_connect_content1);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.sport_connect_content2);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.sport_connect_content3);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.sport_connect_content4);
        HealthTextView healthTextView6 = (HealthTextView) findViewById(R.id.sport_connect_content5);
        if (String.valueOf(7).equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009))) {
            healthTextView.setText(getString(R.string._2130848944_res_0x7f022cb0));
            healthTextView2.setText(getString(R.string._2130848945_res_0x7f022cb1, new Object[]{1}));
            healthTextView3.setText(getString(R.string._2130848946_res_0x7f022cb2, new Object[]{2, 49, 1}));
            healthTextView4.setText(getString(R.string._2130848947_res_0x7f022cb3, new Object[]{3}));
            healthTextView5.setText(getString(R.string._2130848948_res_0x7f022cb4, new Object[]{4}));
            healthTextView6.setText(getString(R.string._2130848949_res_0x7f022cb5, new Object[]{5}));
        } else {
            healthTextView.setText(getString(R.string._2130848915_res_0x7f022c93));
            healthTextView2.setText(getString(R.string._2130848920_res_0x7f022c98, new Object[]{1}));
            healthTextView3.setText(getString(R.string._2130848921_res_0x7f022c99, new Object[]{2}));
            healthTextView4.setText(getString(R.string._2130848922_res_0x7f022c9a, new Object[]{3}));
            healthTextView5.setText(getString(R.string._2130848923_res_0x7f022c9b, new Object[]{4}));
            healthTextView6.setText(getString(R.string._2130848924_res_0x7f022c9c, new Object[]{5}));
        }
        healthTextView6.setVisibility(0);
        findViewById(R.id.sport_connect_tip).setVisibility(0);
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_connect_button);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap._2131820615_res_0x7f110047);
        if (drawable != null) {
            ViewGroup.LayoutParams layoutParams = healthButton.getLayoutParams();
            layoutParams.height = drawable.getIntrinsicHeight();
            layoutParams.width = drawable.getIntrinsicWidth();
            healthButton.setLayoutParams(layoutParams);
            healthButton.setBackground(drawable);
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sge
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StravaConnectActivity.this.dXo_(view);
            }
        });
    }

    public /* synthetic */ void dXo_(View view) {
        openOauthPage();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getClientId() {
        return this.e.getClientId();
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public OauthManager createOauthManager() {
        return sjo.d();
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public Intent createDisconnectIntent() {
        return new Intent(this, (Class<?>) StravaHealthActivity.class);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkSupport(final BaseConnectActivity.CheckSupportCallBack checkSupportCallBack) {
        super.checkSupport(checkSupportCallBack);
        this.c.dYg_(new Handler(Looper.getMainLooper()), LoginInit.getInstance(this).getAccountInfo(1010), new StravaConfig.StravaCountryCallback() { // from class: sgc
            @Override // com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig.StravaCountryCallback
            public final void onStravaCountryCallback(boolean z) {
                StravaConnectActivity.a(BaseConnectActivity.CheckSupportCallBack.this, z);
            }
        });
    }

    public static /* synthetic */ void a(BaseConnectActivity.CheckSupportCallBack checkSupportCallBack, boolean z) {
        LogUtil.a("StravaConnectActivity", "mStravaConfig isSupport ", Boolean.valueOf(z));
        checkSupportCallBack.checkResult(z);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.c.b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getTag() {
        return "StravaConnectActivity";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getRedirectUri() {
        return StravaInfo.STRAVA_REDIRECT_URI;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getGrsKey() {
        return "domain_strava_oauth";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAuthorizationUrl() {
        return StravaInfo.STRAVA_AUTHORIZATION_URL;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAccountType() {
        return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA;
    }
}

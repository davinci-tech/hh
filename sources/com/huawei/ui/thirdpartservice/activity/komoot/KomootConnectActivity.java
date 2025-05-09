package com.huawei.ui.thirdpartservice.activity.komoot;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.thirdpartservice.activity.BaseConnectActivity;
import com.huawei.ui.thirdpartservice.activity.komoot.KomootConnectActivity;
import com.huawei.ui.thirdpartservice.syncdata.constants.KomootOauthConstant;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import defpackage.sfj;
import defpackage.sir;

/* loaded from: classes2.dex */
public class KomootConnectActivity extends BaseConnectActivity {
    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void initView() {
        String string = getString(R.string._2130850492_res_0x7f0232bc);
        ((CustomTitleBar) findViewById(R.id.title_bar)).setTitleText(string);
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821102_res_0x7f11022e);
        ((HealthTextView) findViewById(R.id.sport_connect_content)).setText(getString(R.string._2130845138_res_0x7f021dd2, new Object[]{string, string, string}));
        ((HealthTextView) findViewById(R.id.sport_connect_content1)).setText(getString(R.string._2130844411_res_0x7f021afb, new Object[]{1, string}));
        ((HealthTextView) findViewById(R.id.sport_connect_content2)).setText(getString(R.string._2130844714_res_0x7f021c2a, new Object[]{2, string, string}));
        ((HealthTextView) findViewById(R.id.sport_connect_content3)).setText(getString(R.string._2130845139_res_0x7f021dd3, new Object[]{3, string}));
        ((HealthTextView) findViewById(R.id.sport_connect_content4)).setText(getString(R.string._2130844412_res_0x7f021afc, new Object[]{4, string}));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connect_content5);
        healthTextView.setVisibility(0);
        healthTextView.setText(getString(R.string._2130844413_res_0x7f021afd, new Object[]{5, string}));
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_connect_button);
        healthButton.setText(R.string._2130844407_res_0x7f021af7);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sfp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KomootConnectActivity.this.dXc_(view);
            }
        });
    }

    public /* synthetic */ void dXc_(View view) {
        openOauthPage();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public OauthManager createOauthManager() {
        return sir.c();
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkConnectStatus() {
        sir.c().checkConnectStatus(new sfj(this));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public Intent createDisconnectIntent() {
        return new Intent(this, (Class<?>) KomootHealthActivity.class);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkSupport(BaseConnectActivity.CheckSupportCallBack checkSupportCallBack) {
        super.checkSupport(checkSupportCallBack);
        checkSupportCallBack.checkResult(String.valueOf(7).equals(LoginInit.getInstance(this).getAccountInfo(1009)));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getTag() {
        return "KomootConnectActivity";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getRedirectUri() {
        return KomootOauthConstant.KOMOOT_REDIRECT_URI;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getGrsKey() {
        return "domain_komoot_oauth";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getClientId() {
        return KomootOauthConstant.KOMOOT_CLIENT_ID;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAuthorizationUrl() {
        return KomootOauthConstant.KOMOOT_AUTHORIZATION_URL;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAccountType() {
        return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT;
    }
}

package com.huawei.ui.thirdpartservice.activity.runtastic;

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
import com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticConnectActivity;
import com.huawei.ui.thirdpartservice.syncdata.constants.RuntasticOauthConstant;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import defpackage.sfy;
import defpackage.sjd;

/* loaded from: classes2.dex */
public class RuntasticConnectActivity extends BaseConnectActivity {
    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkConnectStatus() {
        sjd.d().checkConnectStatus(new sfy(this));
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void initView() {
        String string = getString(R.string._2130850493_res_0x7f0232bd);
        ((CustomTitleBar) findViewById(R.id.title_bar)).setTitleText(string);
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821104_res_0x7f110230);
        ((HealthTextView) findViewById(R.id.sport_connect_content)).setText(getString(R.string._2130844479_res_0x7f021b3f));
        ((HealthTextView) findViewById(R.id.sport_connect_content1)).setText(getString(R.string._2130848943_res_0x7f022caf, new Object[]{1}));
        ((HealthTextView) findViewById(R.id.sport_connect_content2)).setText(getString(R.string._2130844480_res_0x7f021b40, new Object[]{2}));
        ((HealthTextView) findViewById(R.id.sport_connect_content3)).setText(getString(R.string._2130844714_res_0x7f021c2a, new Object[]{3, getString(R.string._2130849022_res_0x7f022cfe), string}));
        ((HealthTextView) findViewById(R.id.sport_connect_content4)).setText(getString(R.string._2130844412_res_0x7f021afc, new Object[]{4, string}));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connect_content5);
        healthTextView.setVisibility(0);
        healthTextView.setText(getString(R.string._2130844481_res_0x7f021b41, new Object[]{5}));
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_connect_button);
        healthButton.setText(R.string._2130844406_res_0x7f021af6);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sfw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RuntasticConnectActivity.this.dXm_(view);
            }
        });
    }

    public /* synthetic */ void dXm_(View view) {
        openOauthPage();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public OauthManager createOauthManager() {
        return sjd.d();
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public Intent createDisconnectIntent() {
        return new Intent(this, (Class<?>) RuntasticHealthActivity.class);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public void checkSupport(BaseConnectActivity.CheckSupportCallBack checkSupportCallBack) {
        super.checkSupport(checkSupportCallBack);
        String accountInfo = LoginInit.getInstance(this).getAccountInfo(1009);
        checkSupportCallBack.checkResult(String.valueOf(5).equals(accountInfo) || String.valueOf(7).equals(accountInfo));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getTag() {
        return "RuntasticConnectActivity";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getRedirectUri() {
        return RuntasticOauthConstant.RUNTASTIC_REDIRECT_URI;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getGrsKey() {
        return "domain_runtastic_oauth";
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getClientId() {
        return RuntasticOauthConstant.RUNTASTIC_CLIENT_ID;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAuthorizationUrl() {
        return RuntasticOauthConstant.RUNTASTIC_AUTHORIZATION_URL;
    }

    @Override // com.huawei.ui.thirdpartservice.activity.BaseConnectActivity
    public String getAccountType() {
        return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC;
    }
}

package com.huawei.operation.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes9.dex */
public class AppMarketSettingsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private static final int GET_APP_MARKET_SEVERCES_STATUS = 8;
    private static final String TAG = "AppMarketSettingsActivity";
    private PluginOperationAdapter mAdapter;
    private String mAppMarketType;
    private LinearLayout mCancelAppMarketService;
    private Context mContext;
    private ImageView mStopServiceImage;
    private HealthSwitchButton mSwicthWifiUpdate;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_market_setings);
        getWindow().clearFlags(1024);
        PluginBaseAdapter adapter = PluginOperation.getInstance(BaseApplication.getContext()).getAdapter();
        this.mAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : null;
        this.mContext = this;
        initView();
    }

    private void initView() {
        this.mSwicthWifiUpdate = (HealthSwitchButton) findViewById(R.id.switch_wifi_update);
        this.mCancelAppMarketService = (LinearLayout) findViewById(R.id.cancel_app_market_service);
        this.mStopServiceImage = (ImageView) findViewById(R.id.stop_service_image);
        if (LanguageUtil.bc(this.mContext)) {
            this.mStopServiceImage.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.mStopServiceImage.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.mSwicthWifiUpdate.setOnCheckedChangeListener(this);
        this.mCancelAppMarketService.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.mAppMarketType = intent.getStringExtra("app_type");
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        LogUtil.a(TAG, "onCheckedChanged");
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.cancel_app_market_service) {
            showMarketPrivacyDialog();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showMarketPrivacyDialog() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(R$string.IDS_cancel_app_market_service);
        if (!TextUtils.isEmpty(this.mAppMarketType) && "smart_type".equals(this.mAppMarketType)) {
            builder.d(R$string.IDS_apps_stop_service);
            builder.cyU_(R$string.IDS_ota_update_know, new View.OnClickListener() { // from class: com.huawei.operation.activity.AppMarketSettingsActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(AppMarketSettingsActivity.TAG, "showMarketPrivacyDialog click button known");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            builder.d(R$string.IDS_cancel_app_market_service_description);
            builder.cyR_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: com.huawei.operation.activity.AppMarketSettingsActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(AppMarketSettingsActivity.TAG, "showMarketPrivacyDialog setNegativeButton");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.cyU_(R$string.IDS_application_market_confirm, new View.OnClickListener() { // from class: com.huawei.operation.activity.AppMarketSettingsActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(AppMarketSettingsActivity.TAG, "showMarketPrivacyDialog is true");
                    if (AppMarketSettingsActivity.this.mAdapter != null) {
                        AppMarketSettingsActivity.this.mAdapter.setAppMarketParameter(8, false);
                    }
                    SharedPreferenceManager.a(BaseApplication.getContext(), false);
                    AppMarketSettingsActivity.this.setResult(Constants.REBACK_MARKET_RESULT_CODE);
                    AppMarketSettingsActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

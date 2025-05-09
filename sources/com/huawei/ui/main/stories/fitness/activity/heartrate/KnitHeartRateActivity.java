package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.KnitHeartRateActivity;
import com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateZoneSettingActivity;
import defpackage.nsf;
import defpackage.rud;

/* loaded from: classes6.dex */
public class KnitHeartRateActivity extends KnitHealthDetailActivity {
    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 9;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        return null;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_main_heart_health_string));
        customTitleBar.setRightSoftkeyBackground(ContextCompat.getDrawable(this, R.drawable._2131428277_res_0x7f0b03b5), nsf.h(R$string.IDS_main_btn_state_settings));
        customTitleBar.setRightSoftkeyVisibility(0);
        customTitleBar.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: prm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitHeartRateActivity.this.dss_(view);
            }
        });
        customTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        rud.e(this, customTitleBar, 102);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: prq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitHeartRateActivity.this.dst_(view);
            }
        });
    }

    public /* synthetic */ void dss_(View view) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: pro
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.h("KnitHeartRateActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
                }
            }, null);
        } else {
            startActivity(new Intent(this, (Class<?>) HeartRateZoneSettingActivity.class));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dst_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        Intent intent = getIntent();
        if (intent == null) {
            return true;
        }
        bundle.putLong("key_bundle_health_last_data_time", intent.getLongExtra("key_bundle_health_last_data_time", 0L));
        return true;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        hideEmptyFragment();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.HeartRateDayDetail;
        }
        if (i == 1) {
            return DataInfos.HeartRateWeekDetail;
        }
        if (i == 2) {
            return DataInfos.HeartRateMonthDetail;
        }
        if (i == 3) {
            return DataInfos.HeartRateYearDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "KnitHeartRateActivity";
    }
}

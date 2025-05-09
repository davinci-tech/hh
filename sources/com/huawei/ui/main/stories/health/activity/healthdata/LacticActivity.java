package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.LacticActivity;
import defpackage.ruf;

/* loaded from: classes.dex */
public class LacticActivity extends KnitHealthDetailActivity {
    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 28;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        return null;
    }

    public static void c(Context context, long j) {
        if (j <= 0) {
            j = 0;
        }
        Intent intent = new Intent(context, (Class<?>) LacticActivity.class);
        intent.putExtra("key_bundle_health_last_data_time", j);
        context.startActivity(intent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_running_lactate_threshold));
        ruf.d(this, customTitleBar, "LACTATE_THRESHOLD");
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qer
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LacticActivity.this.dBi_(view);
            }
        });
    }

    public /* synthetic */ void dBi_(View view) {
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
            return DataInfos.LactateThresholdMonthDetail;
        }
        if (i == 1) {
            return DataInfos.LactateThresholdYearDetail;
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
        return "LacticActivity";
    }
}

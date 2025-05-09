package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.AchievementPredictionFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.nsn;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class AchievementPredictionActivity extends BaseActivity {
    private CustomTitleBar d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_achievement_prediction);
        d();
        e();
        b();
    }

    private void d() {
        this.d = (CustomTitleBar) nsy.cMc_(this, R.id.achievements_detail_titlebar);
        HealthSubHeader healthSubHeader = (HealthSubHeader) nsy.cMc_(this, R.id.achievements_what_title_text);
        this.d.setTitleText(getString(R$string.IDS_hwh_health_vo2max_record_forecast));
        this.d.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
    }

    private void e() {
        Intent intent = getIntent();
        int i = 0;
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("running_level_data");
            r3 = parcelableExtra instanceof RunningStateIndexData ? (RunningStateIndexData) parcelableExtra : null;
            i = intent.getIntExtra("vo2max_value", 0);
        }
        AchievementPredictionFragment achievementPredictionFragment = new AchievementPredictionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("running_level_data", r3);
        bundle.putInt("vo2max_value", i);
        achievementPredictionFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, achievementPredictionFragment).commit();
    }

    private void b() {
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AchievementPredictionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.a(500)) {
                    LogUtil.h("AchievementPredictionActivity", "click title bar left button too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    AchievementPredictionActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.d != null) {
            this.d = null;
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

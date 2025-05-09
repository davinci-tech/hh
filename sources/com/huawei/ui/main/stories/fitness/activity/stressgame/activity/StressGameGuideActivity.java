package com.huawei.ui.main.stories.fitness.activity.stressgame.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameGuideActivity;
import defpackage.cun;
import defpackage.cvs;
import defpackage.gge;
import defpackage.nsn;
import defpackage.pvv;
import defpackage.pvw;
import defpackage.sdk;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes6.dex */
public class StressGameGuideActivity extends AppCompatActivity {
    private long c = 0;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        requestWindowFeature(1);
        window.setFlags(1024, 1024);
        setContentView(R.layout.activity_stress_game_guide);
        this.c = System.currentTimeMillis();
        c();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        pvv.duv_(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.a("StressGameGuideActivity", "onBackPressed");
        finish();
    }

    private void c() {
        View findViewById = findViewById(R.id.start_pressure_game_layout);
        findViewById.setPadding(findViewById.getPaddingStart(), findViewById.getPaddingTop(), findViewById.getPaddingEnd(), ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
        String quantityString = getResources().getQuantityString(R.plurals._2130903041_res_0x7f030001, 2, 3);
        String format = String.format(Locale.ENGLISH, getString(R$string.Stress_game_guide_info_game), quantityString);
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, getString(R$string.Stress_game_guide_game_info_new), quantityString);
        }
        ((HealthTextView) findViewById(R.id.tv_guide_info_keep_break)).setText(format);
        ((HealthButton) findViewById(R.id.btn_guide_start)).setOnClickListener(new View.OnClickListener() { // from class: pvp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StressGameGuideActivity.this.duq_(view);
            }
        });
    }

    public /* synthetic */ void duq_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("StressGameGuideActivity", "start StressGame");
        d();
        if (cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "StressGameGuideActivity") == null) {
            Toast.makeText(this, getResources().getString(R$string.Stress_game_guide_info_unbind_bluetooth), 0).show();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        boolean isSupportPressAutoMonitor = cvs.d().isSupportPressAutoMonitor();
        LogUtil.a("StressGameGuideActivity", "Adjust isSupportPressAutoMonitor() = ", Boolean.valueOf(isSupportPressAutoMonitor));
        if (!isSupportPressAutoMonitor) {
            new pvw().a();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        sdk.c().c(false);
        sdk.c().c("");
        Intent intent = new Intent(this, (Class<?>) StressGameMainActivity.class);
        intent.putExtra("STRESSGAME_STARTTIME", this.c);
        startActivity(intent);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_START_2160019.value(), hashMap);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

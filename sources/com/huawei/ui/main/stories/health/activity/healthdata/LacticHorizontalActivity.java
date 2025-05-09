package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView;
import defpackage.nsy;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class LacticHorizontalActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private DataInfos f10076a = DataInfos.LactateThresholdMonthDetail;
    private HealthCalendar b;
    private CustomTitleBar c;
    private LacticChartHolderView d;
    private long e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_lactic_horizontal_layout);
        b();
        a();
    }

    private void b() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        Serializable serializable = extras.getSerializable("key_bundle_health_line_chart_data_infos");
        if (serializable instanceof DataInfos) {
            DataInfos dataInfos = (DataInfos) serializable;
            this.f10076a = dataInfos;
            if (!dataInfos.isMonthData() && !this.f10076a.isYearData()) {
                finish();
                return;
            }
        }
        long j = extras.getLong("key_bundle_health_last_data_time", 0L);
        this.e = j;
        if (j == 0) {
            this.e = System.currentTimeMillis();
        }
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.titlebar);
        this.c = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_running_lactate_threshold));
        this.c.setRightButtonVisibility(8);
        this.c.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.LacticHorizontalActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LacticHorizontalActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        d();
    }

    private void d() {
        this.d = (LacticChartHolderView) findViewById(R.id.chart_horizontal_view);
        e();
        this.d.e(this.f10076a, this.e);
    }

    private void e() {
        this.d.setStartCalendarViewListener(new LacticChartHolderView.StartCalendarViewListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.LacticHorizontalActivity.5
            @Override // com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView.StartCalendarViewListener
            public void onStartCalendarView() {
                LacticHorizontalActivity lacticHorizontalActivity = LacticHorizontalActivity.this;
                HealthCalendarActivity.cxn_(lacticHorizontalActivity, lacticHorizontalActivity.b);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
            this.b = healthCalendar;
            this.d.e(healthCalendar.transformCalendar().getTimeInMillis(), this.b);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

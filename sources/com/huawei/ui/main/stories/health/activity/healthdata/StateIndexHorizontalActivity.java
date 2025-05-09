package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.StateIndexHorizontalActivity;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineHorizontalChartHolderView;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class StateIndexHorizontalActivity extends BaseActivity {
    private RqLineHorizontalChartHolderView c;
    private Context d;
    private HealthCalendar e;
    private CustomTitleBar h;
    private int b = 1;
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10085a = false;
    private long f = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_state_index_horizontal_layout);
        this.d = BaseApplication.getContext();
        e();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.state_detail_titlebar);
        this.h = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_hwh_motiontrack_detail_chart_title_three, new Object[]{getString(R$string.IDS_data_state_index_title), getString(R$string.IDS_data_fitness_condition), getString(R$string.IDS_data_fatigue_condition)}));
        if (nsn.v(this.d)) {
            if (LanguageUtil.bc(this.d)) {
                this.h.setLeftButtonDrawable(ContextCompat.getDrawable(this.d, R.drawable._2131429734_res_0x7f0b0966), nsf.h(R$string.accessibility_go_back));
            } else {
                this.h.setLeftButtonDrawable(ContextCompat.getDrawable(this.d, R.drawable._2131429733_res_0x7f0b0965), nsf.h(R$string.accessibility_go_back));
            }
        }
        this.h.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.StateIndexHorizontalActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StateIndexHorizontalActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getIntExtra("ChartType", this.b);
            this.f = intent.getLongExtra("rq_jump_to_horizontal_time", System.currentTimeMillis());
            b();
        }
    }

    private void b() {
        this.h.setRightButtonDrawable(ContextCompat.getDrawable(this.d, R.drawable._2131430373_res_0x7f0b0be5), nsf.h(R$string.IDS_data_fatigue_condition));
        this.h.setRightButtonClickable(true);
        this.h.setRightButtonVisibility(0);
        this.h.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qev
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StateIndexHorizontalActivity.this.dBk_(view);
            }
        });
        this.h.setRightSoftkeyBackground(ContextCompat.getDrawable(this.d, R.drawable._2131430375_res_0x7f0b0be7), nsf.h(R$string.IDS_data_fitness_condition));
        this.h.setRightSoftkeyVisibility(0);
        this.h.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.StateIndexHorizontalActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StateIndexHorizontalActivity.this.j = !r0.j;
                if (StateIndexHorizontalActivity.this.j) {
                    StateIndexHorizontalActivity.this.h.setRightSoftkeyBackground(ContextCompat.getDrawable(StateIndexHorizontalActivity.this.d, R.drawable._2131430376_res_0x7f0b0be8), nsf.h(R$string.IDS_data_fitness_condition));
                } else {
                    StateIndexHorizontalActivity.this.h.setRightSoftkeyBackground(ContextCompat.getDrawable(StateIndexHorizontalActivity.this.d, R.drawable._2131430375_res_0x7f0b0be7), nsf.h(R$string.IDS_data_fitness_condition));
                }
                StateIndexHorizontalActivity.this.c.b(StateIndexHorizontalActivity.this.j);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a();
    }

    public /* synthetic */ void dBk_(View view) {
        boolean z = !this.f10085a;
        this.f10085a = z;
        if (z) {
            this.h.setRightButtonDrawable(ContextCompat.getDrawable(this.d, R.drawable._2131430374_res_0x7f0b0be6), nsf.h(R$string.IDS_data_fatigue_condition));
        } else {
            this.h.setRightButtonDrawable(ContextCompat.getDrawable(this.d, R.drawable._2131430373_res_0x7f0b0be5), nsf.h(R$string.IDS_data_fatigue_condition));
        }
        this.c.e(this.f10085a);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        RqLineHorizontalChartHolderView rqLineHorizontalChartHolderView = (RqLineHorizontalChartHolderView) findViewById(R.id.rq_chart_horizontal_view);
        this.c = rqLineHorizontalChartHolderView;
        rqLineHorizontalChartHolderView.setWidthMatchParent();
        d();
        if (this.b == 2) {
            this.c.e(DataInfos.StateIndexMonthDetail, 1, 0, this.f);
        } else {
            this.c.e(DataInfos.StateIndexWeekDetail, 1, 0, this.f);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
        super.onResume();
    }

    private void d() {
        this.c.setStartCalendarViewListener(new RqLineChartHolderView.StartCalendarViewListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.StateIndexHorizontalActivity.5
            @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView.StartCalendarViewListener
            public void onStartCalendarView() {
                StateIndexHorizontalActivity stateIndexHorizontalActivity = StateIndexHorizontalActivity.this;
                HealthCalendarActivity.cxn_(stateIndexHorizontalActivity, stateIndexHorizontalActivity.e);
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
        LogUtil.a("StateIndexHorizontalActivity", "obj=" + serializableExtra);
        if (serializableExtra instanceof HealthCalendar) {
            HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
            this.e = healthCalendar;
            this.c.a(healthCalendar.transformCalendar().getTimeInMillis(), this.e);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (z) {
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

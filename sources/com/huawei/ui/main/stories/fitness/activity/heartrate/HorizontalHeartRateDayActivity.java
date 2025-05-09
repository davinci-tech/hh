package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HorizontalHeartRateDayActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayLineHorizontalClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.heartrate.DayHeartRateDoubleViewHorizontalDataObserverView;
import com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverBradycardiaAlarmView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverHRView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverRestHRView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverSportHRView;
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverWarningHRView;
import defpackage.koq;
import defpackage.nom;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class HorizontalHeartRateDayActivity extends BaseActivity {
    private HealthCalendar b;
    private View c;
    private Context d;
    private ObserveredClassifiedView e;
    private String f;
    private DayHeartRateDoubleViewHorizontalDataObserverView g;
    private String i;
    private CustomTitleBar j;
    private HeartRateLineChartHolder k;
    private ViewStub l;
    private long m;
    private DataInfos o;

    /* renamed from: a, reason: collision with root package name */
    private int f9848a = 0;
    private final List<Integer> h = new ArrayList(4);
    private List<String> n = new ArrayList(4);

    public static /* synthetic */ boolean a(DataInfos dataInfos) {
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(0);
        setContentView(R.layout.layout_horizontal_linechart);
        this.d = this;
        d();
        j();
        if (f()) {
            return;
        }
        g();
        a(true);
    }

    private void j() {
        b();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.table_chart_container);
        this.l = (ViewStub) findViewById(R.id.horizontal_heart_rate_linechart_tips);
        ObserveredClassifiedView i = i();
        linearLayout.addView(i, new LinearLayout.LayoutParams(-1, -1));
        i.init(this.k);
        if (koq.b(this.h, this.f9848a)) {
            return;
        }
        this.g.setCurrentItem(this.f9848a);
        i.setJumpTableChartTimeId((int) TimeUnit.MILLISECONDS.toMinutes(nom.b(this.m)));
    }

    private void c() {
        String[] d = d(this.h, this);
        HealthSpinner titleSpinner = this.j.getTitleSpinner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.health_commonui_spinner_item_appbar, d);
        arrayAdapter.setDropDownViewResource(R.layout.hwspinner_dropdown_item);
        if (titleSpinner != null) {
            titleSpinner.setListShadowEnabled(true);
            titleSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            titleSpinner.setSelection(this.f9848a, true);
            titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HorizontalHeartRateDayActivity.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    HorizontalHeartRateDayActivity.this.f9848a = i;
                    HorizontalHeartRateDayActivity.this.g.setCurrentItem(HorizontalHeartRateDayActivity.this.f9848a);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            });
        }
    }

    private String[] d(List<Integer> list, Context context) {
        if (context == null || koq.b(list)) {
            return new String[]{""};
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            String string = context.getResources().getString(intValue);
            if (intValue != 0 && string != null) {
                arrayList.add(string);
            }
        }
        if (koq.b(arrayList)) {
            return new String[]{""};
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    private void b() {
        this.j = (CustomTitleBar) findViewById(R.id.heart_rate_day_titlebar);
        c();
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Health_HeartRateHorizontalActivity", "the HorizontalHeartRateDayActivityNew intent is null,pls check");
            finish();
            return;
        }
        a();
        this.m = intent.getLongExtra(ObserveredClassifiedView.JUMP_TIME_ID, System.currentTimeMillis());
        String stringExtra = intent.getStringExtra(ObserveredClassifiedView.JUMP_DATA_LAYER_ID);
        Serializable serializableExtra = intent.getSerializableExtra(ObserveredClassifiedView.JUMP_DATA_TYPE);
        if (serializableExtra instanceof DataInfos) {
            this.o = (DataInfos) serializableExtra;
        }
        a(stringExtra);
        HeartRateLineChartHolder heartRateLineChartHolder = new HeartRateLineChartHolder(this.d);
        this.k = heartRateLineChartHolder;
        heartRateLineChartHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: prh
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return HorizontalHeartRateDayActivity.a(dataInfos);
            }
        }, this.f);
        DataInfos dataInfos = this.o;
        if (dataInfos == null || !dataInfos.isSupportHorzontal()) {
            finish();
        }
    }

    private void a(String str) {
        if ("default".equals(str)) {
            this.f9848a = 0;
            return;
        }
        Iterator<String> it = this.n.iterator();
        while (it.hasNext() && !it.next().equals(str)) {
            this.f9848a++;
        }
        if (this.f9848a >= 4) {
            this.f9848a = 0;
        }
    }

    private void a() {
        this.h.clear();
        this.h.add(Integer.valueOf(R$string.IDS_main_watch_heart_rate_string));
        this.h.add(Integer.valueOf(R$string.IDS_hw_health_show_healthdata_resting_heart_bmp));
        this.h.add(Integer.valueOf(R$string.IDS_heartrate_raise_alarm));
        this.h.add(Integer.valueOf(R$string.IDS_heartrate_bradycardia_alarm));
        this.n = Arrays.asList(HwHealthChartHolder.LAYER_ID_NORMAL_HR, HwHealthChartHolder.LAYER_ID_REST_HR, HwHealthChartHolder.LAYER_ID_WARNING_HR, HwHealthChartHolder.LAYER_ID_BRADYCARDIA);
        this.f = getString(R$string.IDS_main_watch_heart_rate_unit_string);
        this.i = getString(R$string.IDS_main_watch_heart_rate_string);
    }

    private ObserveredClassifiedView i() {
        DayLineHorizontalClassifiedView dayLineHorizontalClassifiedView = new DayLineHorizontalClassifiedView(this) { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HorizontalHeartRateDayActivity.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                return UnitUtil.e((int) f, 1, 0);
            }
        };
        dayLineHorizontalClassifiedView.setStepDatatype(DataInfos.query(e(), DateType.DATE_DAY));
        dayLineHorizontalClassifiedView.setHighlightedEntryParser(this.k);
        dayLineHorizontalClassifiedView.selectDataLayerId(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        dayLineHorizontalClassifiedView.initLandscapeCalendarView(this, this.b, new HealthDataMarkDateTrigger(46019, new int[]{47055, 47005}), true);
        this.e = dayLineHorizontalClassifiedView;
        b((HorizontalHeartRateDayActivity) dayLineHorizontalClassifiedView, true);
        return dayLineHorizontalClassifiedView;
    }

    private <T extends ObserveredClassifiedView> void b(T t, boolean z) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.horizontal_popup_view, (ViewGroup) null);
        this.c = inflate;
        DayHeartRateDoubleViewHorizontalDataObserverView dayHeartRateDoubleViewHorizontalDataObserverView = (DayHeartRateDoubleViewHorizontalDataObserverView) inflate.findViewById(R.id.day_heartrate_muti_data_view);
        this.g = dayHeartRateDoubleViewHorizontalDataObserverView;
        dayHeartRateDoubleViewHorizontalDataObserverView.setHost(t);
        this.g.a(a((HorizontalHeartRateDayActivity) t), b(t), z);
        t.enableObserverView(this.g);
    }

    private <T extends ObserveredClassifiedView> List<ScrollChartHorizontalObserverHRView> a(T t) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new ScrollChartHorizontalObserverSportHRView(this, t, this.i));
        ScrollChartHorizontalObserverRestHRView scrollChartHorizontalObserverRestHRView = new ScrollChartHorizontalObserverRestHRView(this, t, getString(R$string.IDS_hw_health_show_healthdata_resting_heart_bmp));
        scrollChartHorizontalObserverRestHRView.setAvgCalculator(this.k.h());
        arrayList.add(scrollChartHorizontalObserverRestHRView);
        ScrollChartHorizontalObserverWarningHRView scrollChartHorizontalObserverWarningHRView = new ScrollChartHorizontalObserverWarningHRView(this, t, getString(R$string.IDS_heartrate_raise_alarm));
        scrollChartHorizontalObserverWarningHRView.setScrollChartVisitor(this.k);
        arrayList.add(scrollChartHorizontalObserverWarningHRView);
        ScrollChartHorizontalObserverBradycardiaAlarmView scrollChartHorizontalObserverBradycardiaAlarmView = new ScrollChartHorizontalObserverBradycardiaAlarmView(this, t, getString(R$string.IDS_heartrate_bradycardia_alarm));
        scrollChartHorizontalObserverBradycardiaAlarmView.goneDivider();
        scrollChartHorizontalObserverBradycardiaAlarmView.setScrollChartVisitor(this.k);
        arrayList.add(scrollChartHorizontalObserverBradycardiaAlarmView);
        return arrayList;
    }

    private <T extends ObserveredClassifiedView> List<HwHealthChartHolder.b> b(T t) {
        HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
        bVar.e(t.getStepDataType());
        bVar.e(HwHealthChartHolder.LAYER_ID_REST_HR);
        HwHealthChartHolder.b bVar2 = new HwHealthChartHolder.b();
        bVar2.e(t.getStepDataType());
        bVar2.e(HwHealthChartHolder.LAYER_ID_WARNING_HR);
        HwHealthChartHolder.b bVar3 = new HwHealthChartHolder.b();
        bVar3.e(t.getStepDataType());
        bVar3.e(HwHealthChartHolder.LAYER_ID_BRADYCARDIA);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(MultiViewHorizontalDataObserverView.d);
        arrayList.add(bVar);
        arrayList.add(bVar2);
        arrayList.add(bVar3);
        return arrayList;
    }

    public ClassType e() {
        return ClassType.TYPE_HEART_RATE;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ObserveredClassifiedView observeredClassifiedView;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (!(serializableExtra instanceof HealthCalendar) || (observeredClassifiedView = this.e) == null) {
            return;
        }
        HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
        this.b = healthCalendar;
        observeredClassifiedView.processCalendarSelect(healthCalendar);
    }

    private boolean f() {
        LogUtil.c("Health_HeartRateHorizontalActivity", "isTipsHasShown ", 10006, " ", "heart_rate_horizontal_chart_tips_shown");
        String b = SharedPreferenceManager.b(this.d, String.valueOf(10006), "heart_rate_horizontal_chart_tips_shown");
        if (!TextUtils.isEmpty(b)) {
            LogUtil.a("Health_HeartRateHorizontalActivity", "isTipsHasShown bFlag:", Boolean.valueOf(Boolean.parseBoolean(b)));
            return Boolean.parseBoolean(b);
        }
        LogUtil.a("Health_HeartRateHorizontalActivity", "isTipsHasShown return default");
        return false;
    }

    private void g() {
        ViewStub viewStub = this.l;
        if (viewStub != null) {
            final View inflate = viewStub.inflate();
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.horizontal_linechart_relativelayout);
            if (relativeLayout != null) {
                relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: prl
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HorizontalHeartRateDayActivity.dsr_(inflate, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void dsr_(View view, View view2) {
        view.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void a(boolean z) {
        LogUtil.a("Health_HeartRateHorizontalActivity", "saveTipsHasShown bFlag:", Boolean.valueOf(z));
        LogUtil.c("Health_HeartRateHorizontalActivity", "saveTipsHasShown ", 10006, " ", "heart_rate_horizontal_chart_tips_shown");
        SharedPreferenceManager.e(this.d, String.valueOf(10006), "heart_rate_horizontal_chart_tips_shown", Boolean.toString(z), new StorageParams());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}

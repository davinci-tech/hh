package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.dashboard.BloodSugarDashboardView;
import com.huawei.ui.commonui.linechart.view.dashboard.DashboardRingView;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriod;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity;
import com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureDetailFragment;
import defpackage.qjv;
import defpackage.qkg;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarMeasureDetailFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private BloodSugarDeviceMeasureActivity f10165a;
    private BloodSugarDashboardView b;
    private Calendar c;
    private HealthButton d;
    private HealthTextView e;
    private HealthTextView f;
    private BloodSugarTimePeriodView g;
    private HealthTextView h;
    private long i;
    private int j;
    private float k;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof BloodSugarDeviceMeasureActivity) {
            this.f10165a = (BloodSugarDeviceMeasureActivity) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_blood_sugar_measure_detail, (ViewGroup) null, false);
        dDR_(inflate);
        if ("jump_from_blood_sugar_history".equals(this.f10165a.e()) || "jump_from_blood_sugar_feedback".equals(this.f10165a.e()) || "jump_from_blood_sugar_home".equals(this.f10165a.e())) {
            dDS_(inflate, 0);
            dDT_(inflate, 8);
        } else {
            dDS_(inflate, 8);
            dDT_(inflate, 0);
        }
        return inflate;
    }

    private void dDR_(View view) {
        this.b = (BloodSugarDashboardView) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_dashboard_view);
        BloodSugarTimePeriodView bloodSugarTimePeriodView = (BloodSugarTimePeriodView) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_time_period);
        this.g = bloodSugarTimePeriodView;
        bloodSugarTimePeriodView.setOnTimePeriodItemChangedListener(new BloodSugarTimePeriodView.OnTimePeriodItemChangedListener() { // from class: qih
            @Override // com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView.OnTimePeriodItemChangedListener
            public final void onTimePeriodItemChanged(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
                BloodSugarMeasureDetailFragment.this.a(i, bloodSugarTimePeriod);
            }
        });
        this.c = Calendar.getInstance();
    }

    public /* synthetic */ void a(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
        this.j = bloodSugarTimePeriod.getCode();
        e();
        b(true);
        BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity = this.f10165a;
        if (bloodSugarDeviceMeasureActivity == null || bloodSugarDeviceMeasureActivity.d() == null || this.j == BigDecimal.valueOf(this.f10165a.d().o()).intValue()) {
            return;
        }
        d();
    }

    private void dDT_(View view, int i) {
        this.h = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_top_date);
        this.d = (HealthButton) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_bt_done);
        this.h.setVisibility(i);
        this.d.setVisibility(i);
        if (i == 0) {
            this.d.setOnClickListener(new View.OnClickListener() { // from class: qie
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BloodSugarMeasureDetailFragment.this.dDX_(view2);
                }
            });
        }
    }

    public /* synthetic */ void dDX_(View view) {
        this.f10165a.b(this.j, this.c.getTimeInMillis());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dDS_(View view, int i) {
        view.findViewById(R.id.hw_show_blood_sugar_measure_detail_date_layout).setVisibility(i);
        if (i != 0) {
            return;
        }
        view.findViewById(R.id.hw_show_blood_sugar_measure_detail_date_ll).setOnClickListener(new View.OnClickListener() { // from class: qik
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarMeasureDetailFragment.this.dDU_(view2);
            }
        });
        this.e = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_date);
        view.findViewById(R.id.hw_show_blood_sugar_measure_detail_time_ll).setOnClickListener(new View.OnClickListener() { // from class: qii
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarMeasureDetailFragment.this.dDV_(view2);
            }
        });
        this.f = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_measure_detail_time);
        this.f10165a.h().setRightButtonOnClickListener(new View.OnClickListener() { // from class: qig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarMeasureDetailFragment.this.dDW_(view2);
            }
        });
    }

    public /* synthetic */ void dDU_(View view) {
        b();
        d(9);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dDV_(View view) {
        a();
        d(10);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dDW_(View view) {
        this.f10165a.b(this.j, this.c.getTimeInMillis());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        char c;
        HashMap hashMap;
        String e = this.f10165a.e();
        e.hashCode();
        int hashCode = e.hashCode();
        if (hashCode == -824305694) {
            if (e.equals("jump_from_blood_sugar_history")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -758500271) {
            if (hashCode == 1397670459 && e.equals("jump_from_blood_sugar_notify")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (e.equals("jump_from_blood_sugar_home")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            hashMap = new HashMap();
            hashMap.put("type", 3);
        } else if (c != 1) {
            if (c == 2 && this.f10165a.f()) {
                hashMap = new HashMap();
                hashMap.put("type", 1);
            } else {
                hashMap = null;
            }
        } else {
            hashMap = new HashMap();
            hashMap.put("type", 2);
        }
        if (hashMap != null) {
            String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_CONFIRMED_2030071.value();
            hashMap.put("click", "1");
            hashMap.put(FunctionSetBeanReader.BI_ELEMENT, 1);
            this.f10165a.c(value, hashMap);
        }
    }

    private void d(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, Integer.valueOf(i));
        this.f10165a.c(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), hashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        h();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        h();
    }

    private void h() {
        qkg d = this.f10165a.d();
        if (d == null) {
            return;
        }
        this.j = BigDecimal.valueOf(d.o()).intValue();
        this.k = BigDecimal.valueOf(d.m()).floatValue();
        long h = d.h();
        this.i = h;
        this.c.setTimeInMillis(h);
        if ("jump_from_blood_sugar_history".equals(this.f10165a.e()) || "jump_from_blood_sugar_feedback".equals(this.f10165a.e()) || "jump_from_blood_sugar_home".equals(this.f10165a.e())) {
            e();
            c();
            d(this.i);
            this.g.d(this.i, this.j);
            b(true);
            return;
        }
        if ("jump_from_blood_sugar_notify".equals(this.f10165a.e()) && this.f10165a.f()) {
            i();
            e();
            c();
            this.g.d(this.i, this.j);
            b(true);
            return;
        }
        i();
        c();
        this.g.e(this.i);
        b(false);
    }

    private void i() {
        this.h.setText(new SimpleDateFormat("yyyy/M/d HH:mm").format(Long.valueOf(this.i)));
    }

    private void c() {
        this.b.setCurrentValue(this.k);
        this.b.c();
    }

    private void e() {
        ArrayList arrayList = new ArrayList();
        List<Float> a2 = qjv.a(this.j);
        int i = 0;
        int i2 = 0;
        while (i2 < a2.size() && this.k > a2.get(i2).floatValue()) {
            i2++;
        }
        int[] a3 = qjv.a(getContext());
        int length = (a3.length / 2) + i2;
        float f = 0.0f;
        while (i < a2.size()) {
            float floatValue = a2.get(i).floatValue();
            int i3 = i == i2 ? length : i;
            if (i3 < a3.length) {
                arrayList.add(new DashboardRingView.b(f, floatValue, a3[i3]));
                f = floatValue;
            }
            i++;
        }
        this.b.setRingAreas(1.0f, 33.0f, arrayList);
        String valueOf = String.valueOf(qjv.a(getContext(), this.j, this.k).get("HEALTH_BLOOD_SUGAR_LEVEL_DESC"));
        if (length < a3.length) {
            this.b.setStatusText(valueOf, a3[length]);
            this.b.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j) {
        String[] split = new SimpleDateFormat("yyyy/M/d HH:mm").format(Long.valueOf(j)).split(" ");
        this.e.setText(split[0]);
        this.f.setText(split[1]);
        this.g.e(j);
    }

    private void b() {
        new HealthDatePickerDialog(this.f10165a, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureDetailFragment.3
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                BloodSugarMeasureDetailFragment.this.c.set(i, i2, i3);
                long timeInMillis = BloodSugarMeasureDetailFragment.this.c.getTimeInMillis();
                BloodSugarMeasureDetailFragment.this.d(timeInMillis);
                BloodSugarMeasureDetailFragment.this.g.e(timeInMillis);
                BloodSugarMeasureDetailFragment.this.b(false);
            }
        }, new GregorianCalendar(this.c.get(1), this.c.get(2), this.c.get(5), this.c.get(11), this.c.get(12))).show();
    }

    private void a() {
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this.f10165a, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureDetailFragment.5
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                BloodSugarMeasureDetailFragment.this.c.set(11, i);
                BloodSugarMeasureDetailFragment.this.c.set(12, i2);
                long timeInMillis = BloodSugarMeasureDetailFragment.this.c.getTimeInMillis();
                BloodSugarMeasureDetailFragment.this.d(timeInMillis);
                BloodSugarMeasureDetailFragment.this.g.e(timeInMillis);
                BloodSugarMeasureDetailFragment.this.b(false);
            }
        });
        healthTimePickerDialog.b(getString(R$string.IDS_hw_health_show_healthdata_measure_time));
        healthTimePickerDialog.e().d(this.c.get(1), this.c.get(2), this.c.get(5), this.c.get(11), this.c.get(12));
        healthTimePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        HealthButton healthButton = this.d;
        if (healthButton == null) {
            this.f10165a.h().setRightButtonClickable(z);
        } else if (z) {
            healthButton.setClickable(true);
            this.d.setBackgroundResource(R.drawable.button_background_emphasize);
        } else {
            healthButton.setClickable(false);
            this.d.setBackgroundResource(R.drawable.button_background_emphasize_disable);
        }
    }
}

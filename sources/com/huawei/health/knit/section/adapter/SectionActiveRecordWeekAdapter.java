package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionActiveRecordWeekAdapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eeb;
import defpackage.koq;
import defpackage.nmz;
import defpackage.nnc;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionActiveRecordWeekAdapter extends BaseRecyclerAdapter<eeb> {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2558a;
    private final Context c;
    private OnClickSectionListener d;
    private final int e;

    public SectionActiveRecordWeekAdapter() {
        super(new ArrayList(), R.layout.section_active_record_week_item);
        Context e = BaseApplication.e();
        this.c = e;
        this.e = HiDateUtil.c(System.currentTimeMillis());
        this.f2558a = LanguageUtil.bc(e);
    }

    public void e(List<eeb> list) {
        refreshDataChange(list);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, final int i, eeb eebVar) {
        HwHealthBarChart hwHealthBarChart;
        if (recyclerHolder == null || eebVar == null) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeekAdapter", "convert holder ", recyclerHolder, " itemData ", eebVar);
            return;
        }
        HealthImageView healthImageView = (HealthImageView) recyclerHolder.cwK_(R.id.section_active_record_week_item_arrow);
        healthImageView.setImageResource(this.f2558a ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
        healthImageView.setOnClickListener(new View.OnClickListener() { // from class: ebo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SectionActiveRecordWeekAdapter.this.adi_(i, view);
            }
        });
        HealthImageView healthImageView2 = (HealthImageView) recyclerHolder.cwK_(R.id.section_active_record_week_item_icon);
        int i2 = eebVar.i();
        if (this.f2558a) {
            Context context = this.c;
            healthImageView2.setImageDrawable(nrz.cKm_(context, ContextCompat.getDrawable(context, i2)));
        } else {
            healthImageView2.setImageResource(i2);
        }
        healthImageView2.setOnClickListener(new View.OnClickListener() { // from class: ebl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SectionActiveRecordWeekAdapter.this.adj_(i, view);
            }
        });
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.section_active_record_week_item_title);
        healthTextView.setText(eebVar.o());
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: ebp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SectionActiveRecordWeekAdapter.this.adk_(i, view);
            }
        });
        int g = eebVar.g();
        Drawable cKq_ = nsf.cKq_(g);
        HealthImageView healthImageView3 = (HealthImageView) recyclerHolder.cwK_(R.id.section_active_record_week_item_icon_tip);
        if (cKq_ == null) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeekAdapter", "convert drawable is null iconTipResourceId ", Integer.valueOf(g));
            healthImageView3.setVisibility(8);
        } else {
            healthImageView3.setVisibility(0);
            healthImageView3.setImageDrawable(cKq_);
            healthImageView3.setOnClickListener(new View.OnClickListener() { // from class: ebr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionActiveRecordWeekAdapter.this.adl_(view);
                }
            });
        }
        recyclerHolder.c(R.id.section_active_record_week_item_tip, eebVar.j());
        LinearLayout linearLayout = (LinearLayout) recyclerHolder.cwK_(R.id.section_active_record_week_item_event);
        if (eebVar.f() == 1) {
            linearLayout.setVisibility(0);
            recyclerHolder.cwP_(R.id.section_active_record_week_item_event_button, new View.OnClickListener() { // from class: ebu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionActiveRecordWeekAdapter.this.adm_(view);
                }
            });
        } else {
            linearLayout.setVisibility(8);
        }
        recyclerHolder.b(R.id.section_active_record_week_item_compare_day_title, nsf.h(HiDateUtil.c(eebVar.b()) == this.e ? R$string.IDS_hwh_home_group_today : R$string.IDS_same_day));
        recyclerHolder.c(R.id.section_active_record_week_item_compare_day_value, eebVar.d());
        recyclerHolder.c(R.id.section_active_record_week_item_compare_week_value, eebVar.l());
        if (new HealthColumnSystem(this.c, 0).e()) {
            hwHealthBarChart = (HwHealthBarChart) recyclerHolder.cwK_(R.id.section_active_record_week_item_bar_chart);
            recyclerHolder.a(R.id.section_active_record_week_item_bar_chart_widescreen, 8);
        } else {
            hwHealthBarChart = (HwHealthBarChart) recyclerHolder.cwK_(R.id.section_active_record_week_item_bar_chart_widescreen);
            recyclerHolder.a(R.id.section_active_record_week_item_bar_chart, 8);
        }
        d(hwHealthBarChart, eebVar);
    }

    public /* synthetic */ void adi_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.d;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void adj_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.d;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void adk_(int i, View view) {
        OnClickSectionListener onClickSectionListener = this.d;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void adl_(View view) {
        OnClickSectionListener onClickSectionListener = this.d;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick("RIGHT_ICON_CLICK_EVENT");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void adm_(View view) {
        OnClickSectionListener onClickSectionListener = this.d;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick("GOAL_BUTTON");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(HwHealthBarChart hwHealthBarChart, eeb eebVar) {
        List<Integer> n = eebVar.n();
        if (koq.b(n)) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeekAdapter", "setBarChart valueList ", n);
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = n.size();
        int e = e(eebVar.b());
        int a2 = eebVar.a();
        int e2 = eebVar.e();
        int i = 0;
        while (i < size) {
            arrayList.add(new HwHealthBarEntry(i, new nnc(n.get(i).intValue(), i == e ? e2 : a2, e2)));
            i++;
        }
        e(hwHealthBarChart);
        c(hwHealthBarChart, a(arrayList, eebVar));
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(arrayList, "", "", "");
        hwHealthBarDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        hwHealthBarDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
        hwHealthBarDataSet.setBarDrawWidth(0.3f);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(hwHealthBarDataSet);
        hwHealthBarChart.setData(new nmz(arrayList2));
        int h = eebVar.h();
        hwHealthBarChart.setManualReferenceLine(h, eebVar.c(), nsf.b(R$string.IDS_current_target, UnitUtil.e(h, 1, 0)));
        hwHealthBarChart.setHorizontalAxisFocusLabelIndex(e);
        hwHealthBarChart.refresh();
    }

    private int e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.setTimeInMillis(j);
        int i = calendar.get(7);
        if (i == 1) {
            return 6;
        }
        return i - 2;
    }

    private void e(HwHealthBarChart hwHealthBarChart) {
        hwHealthBarChart.setVisibility(0);
        hwHealthBarChart.makeReverse(true);
        hwHealthBarChart.setTouchEnabled(false);
        hwHealthBarChart.b(true, false);
        hwHealthBarChart.enableSpacePreserveForDataOverlay(true);
        hwHealthBarChart.enableManualFloatLineText(ContextCompat.getColor(this.c, R.color._2131299241_res_0x7f090ba9));
        hwHealthBarChart.fillChartBackground(ContextCompat.getColor(this.c, R.color._2131296666_res_0x7f09019a), ContextCompat.getColor(this.c, R.color._2131296666_res_0x7f09019a));
    }

    private void c(HwHealthBarChart hwHealthBarChart, float f) {
        HwHealthYAxis axisFirstParty = hwHealthBarChart.getAxisFirstParty();
        if (axisFirstParty != null) {
            axisFirstParty.setEnabled(true);
            axisFirstParty.setDrawLabels(false);
            axisFirstParty.setDrawAxisLine(false);
            axisFirstParty.setDrawGridLines(false);
            axisFirstParty.setAxisMinimum(0.0f);
            axisFirstParty.setAxisMaximum(f);
        }
        HwHealthYAxis axisSecondParty = hwHealthBarChart.getAxisSecondParty();
        if (axisSecondParty != null) {
            axisSecondParty.setEnabled(true);
            axisSecondParty.setDrawLabels(false);
            axisSecondParty.setDrawAxisLine(false);
            axisSecondParty.setDrawGridLines(true);
            axisSecondParty.disableGridDashedLine();
            axisSecondParty.setGridColor(ContextCompat.getColor(this.c, R.color._2131296448_res_0x7f0900c0));
            axisSecondParty.setAxisMinimum(0.0f);
            axisSecondParty.setAxisMaximum(f);
        }
        XAxis xAxis = hwHealthBarChart.getXAxis();
        if (xAxis == null) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeekAdapter", "initAxis xAxis is null");
            return;
        }
        xAxis.setTextSize(10.0f);
        xAxis.setEnabled(true);
        xAxis.setDrawLabels(true);
        final List<String> a2 = a();
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.health.knit.section.adapter.SectionActiveRecordWeekAdapter.2
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f2, AxisBase axisBase) {
                int i = (int) f2;
                return koq.b(a2, i) ? "" : (String) a2.get(i);
            }
        });
    }

    private List<String> a() {
        ArrayList arrayList = new ArrayList(7);
        arrayList.add(nsf.h(R$string.IDS_report_monday));
        arrayList.add(nsf.h(R$string.IDS_report_thuesday));
        arrayList.add(nsf.h(R$string.IDS_report_wedesday));
        arrayList.add(nsf.h(R$string.IDS_report_thursday));
        arrayList.add(nsf.h(R$string.IDS_report_friday));
        arrayList.add(nsf.h(R$string.IDS_report_saturday));
        arrayList.add(nsf.h(R$string.IDS_report_sunday));
        return arrayList;
    }

    private float a(List<HwHealthBarEntry> list, eeb eebVar) {
        if (koq.b(list) || eebVar == null) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeekAdapter", "getVerticalAxisMaximum list ", list, " bean ", eebVar);
            return 0.0f;
        }
        float h = eebVar.h();
        float f = h;
        for (HwHealthBarEntry hwHealthBarEntry : list) {
            if (hwHealthBarEntry != null) {
                f = Math.max(f, hwHealthBarEntry.getY());
            }
        }
        float f2 = h * 1.2f;
        return Float.compare(f, f2) == 1 ? f : f2;
    }

    public void b(OnClickSectionListener onClickSectionListener) {
        this.d = onClickSectionListener;
    }
}

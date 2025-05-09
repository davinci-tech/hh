package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.HwHealthBloodPressureLineChart;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import defpackage.edq;
import defpackage.eeu;
import defpackage.efb;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionMultiLineChart<ChartT extends HwHealthBaseScrollBarLineChart> extends BaseSectionLineChart<ChartT> {

    /* renamed from: a, reason: collision with root package name */
    protected View f2704a;
    protected Typeface b;
    protected HealthTextView c;
    protected View d;
    protected HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private RelativeLayout h;
    private HealthTextView i;
    private List<Object> j;
    private HealthTextView l;
    private HealthTextView m;
    private RelativeLayout o;

    public SectionMultiLineChart(Context context) {
        super(context);
        this.b = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
    }

    public SectionMultiLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
    }

    public SectionMultiLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void initSectionLineChart() {
        super.initSectionLineChart();
        setIntervalLegendLayout(this.mainView);
        d();
        this.c = (HealthTextView) this.mainView.findViewById(R.id.third_layer_cursor_state_nextline);
        this.h = (RelativeLayout) this.mainView.findViewById(R.id.forth_layer_layout);
        this.i = (HealthTextView) this.mainView.findViewById(R.id.forth_layer_cursor_text);
        this.f = (HealthTextView) this.mainView.findViewById(R.id.forth_layer_cursor_value);
        this.g = (HealthTextView) this.mainView.findViewById(R.id.forth_layer_cursor_unit);
        this.i.setAutoTextInfo(8, 1, 2);
        this.f.setTypeface(this.b);
        this.o = (RelativeLayout) this.mainView.findViewById(R.id.legend_group_layout);
        this.m = (HealthTextView) this.mainView.findViewById(R.id.left_bottom_legend);
        this.l = (HealthTextView) this.mainView.findViewById(R.id.right_bottom_legend);
        this.m = (HealthTextView) this.d.findViewById(R.id.left_bottom_legend);
        this.l = (HealthTextView) this.d.findViewById(R.id.right_bottom_legend);
        this.h.setVisibility(0);
        setLeftAndRightTextStatus(this.m);
        setLeftAndRightTextStatus(this.l);
        if (efb.g()) {
            getKnitFragment().setRecyclerViewDescendantFocusability(393216);
        }
    }

    private void setLeftAndRightTextStatus(HealthTextView healthTextView) {
        if (LanguageUtil.bq(this.mContext)) {
            setTextGlobalLayoutListener(healthTextView);
        }
    }

    private void setTextGlobalLayoutListener(final HealthTextView healthTextView) {
        healthTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionMultiLineChart.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int lineCount = healthTextView.getLayout().getLineCount();
                LogUtil.a("OnGlobalLayoutListener,", Integer.valueOf(lineCount));
                if (lineCount >= 1) {
                    healthTextView.setTextSize(1, 9.0f);
                    healthTextView.setMaxLines(1);
                    healthTextView.setSingleLine();
                    healthTextView.setEllipsize(TextUtils.TruncateAt.END);
                }
                healthTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void d() {
        if (this.mThirdLayerUnitLayoutView != null) {
            this.f2704a = this.mThirdLayerUnitLayoutView.findViewById(R.id.linechart_cursor_verticalline);
            this.e = (HealthTextView) this.mainView.findViewById(R.id.third_layer_cursor_state);
        }
    }

    private void e() {
        if (this.mIsEnglish || LanguageUtil.v(this.mContext)) {
            this.f2704a.setVisibility(8);
        } else {
            this.f2704a.setVisibility(0);
        }
    }

    private void setIntervalLegendLayout(View view) {
        if (nsn.s()) {
            ajm_(view, R.id.linechart_view_legend_trable, R.id.linechart_view_legend_trable_inflated);
        } else {
            ajm_(view, R.id.linechart_view_legend, R.id.linechart_view_legend_inflated);
        }
        View view2 = this.d;
        if (view2 != null) {
            this.m = (HealthTextView) view2.findViewById(R.id.left_bottom_legend);
            this.l = (HealthTextView) this.d.findViewById(R.id.right_bottom_legend);
        }
    }

    private void ajm_(View view, int i, int i2) {
        if (this.mViewStub == null) {
            this.mViewStub = (ViewStub) view.findViewById(i);
        }
        if (this.mViewStub.getParent() != null) {
            this.d = this.mViewStub.inflate();
        } else {
            this.d = view.findViewById(i2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected ChartT acquireCurrentChart(int i) {
        return new HwHealthBloodPressureLineChart(this.mContext.getApplicationContext());
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart, com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        super.bindParamsToView(hashMap);
        this.j = (List) nru.c(hashMap, "CURSOR_DOWN_AVERAGE_TEXT", List.class, null);
        String str = (String) nru.c(hashMap, "CURSOR_DOWN_AVERAGE_UNIT", String.class, null);
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        nsy.cMl_(this.m, (Drawable) nru.c(hashMap, "LEFT_BOTTOM_LEGEND_SIGN", Drawable.class, null));
        nsy.cMr_(this.m, (CharSequence) nru.c(hashMap, "LEFT_BOTTOM_LEGEND_TEXT", String.class, null));
        nsy.cMl_(this.l, (Drawable) nru.c(hashMap, "RIGHT_BOTTOM_LEGEND_SIGN", Drawable.class, null));
        nsy.cMr_(this.l, (CharSequence) nru.c(hashMap, "RIGHT_BOTTOM_LEGEND_TEXT", String.class, null));
        nsy.cMA_(this.o, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected DataInfos getJudgeDataInfos() {
        return DataInfos.BloodPressureDayDetail;
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void initLayer() {
        super.initLayer();
        List<Object> list = this.j;
        if (list != null && list.size() > 1 && (this.j.get(1) instanceof String)) {
            this.i.setText(String.valueOf(this.j.get(1)));
        }
        this.g.setVisibility(0);
        this.f.setText("--");
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected String parseEntry(HwHealthBaseEntry hwHealthBaseEntry) {
        LogUtil.a("SectionMultiLineChart", "parse multiLineChart entry");
        return (hwHealthBaseEntry == null || "".equals(a(hwHealthBaseEntry))) ? "--" : a(hwHealthBaseEntry);
    }

    protected String c(HwHealthBaseEntry hwHealthBaseEntry) {
        LogUtil.a("SectionMultiLineChart", "parse multiLineChart extra data");
        if (hwHealthBaseEntry == null || !(hwHealthBaseEntry.getData() instanceof edq) || this.mThirdLayerCursorTextList == null || this.mThirdLayerCursorTextList.size() <= 1 || this.mThirdLayerCursorText == null || this.i == null) {
            return "--";
        }
        edq edqVar = (edq) hwHealthBaseEntry.getData();
        if (edqVar != null && edqVar.d() != 1) {
            if (this.mThirdLayerCursorTextList.get(1) instanceof String) {
                this.mThirdLayerCursorText.setText(String.valueOf(this.mThirdLayerCursorTextList.get(1)));
                this.i.setText(String.valueOf(this.j.get(1)));
            }
        } else if (this.mThirdLayerCursorTextList.get(0) instanceof String) {
            this.mThirdLayerCursorText.setText(String.valueOf(this.mThirdLayerCursorTextList.get(0)));
            this.i.setText(String.valueOf(this.j.get(0)));
        }
        if (edqVar == null) {
            return "--";
        }
        String e = UnitUtil.e(edqVar.e(), 1, 1);
        if (e.contains(".")) {
            e = e.substring(0, e.indexOf("."));
        }
        return e.contains(",") ? e.substring(0, e.indexOf(",")) : e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void notifyView(List<HwHealthMarkerView.a> list) {
        String c;
        super.notifyView(list);
        String valueOf = String.valueOf(this.mThirdLayerCursorValue.getText());
        if (koq.b(list)) {
            LogUtil.h("SectionMultiLineChart", "notifyView datas is empty");
            c = "--";
        } else {
            c = c(list.get(list.size() - 1).b);
        }
        if ("--".equals(c) || "0".equals(c)) {
            List<Object> list2 = this.j;
            if (list2 != null && list2.size() > 1 && (this.j.get(1) instanceof String)) {
                this.i.setText(String.valueOf(this.j.get(1)));
            }
            this.f.setText("--");
        } else {
            this.f.setText(c);
        }
        if ("--".equals(valueOf)) {
            this.f2704a.setVisibility(8);
            this.e.setVisibility(8);
            this.c.setVisibility(8);
            if (this.mIsEnglish) {
                this.mCursorHavedataUnitLayout.setVisibility(8);
                this.mCursorNodataUnit.setVisibility(0);
                return;
            }
            return;
        }
        if (this.mIsEnglish) {
            this.mCursorNodataUnit.setVisibility(8);
            this.mCursorHavedataUnitLayout.setVisibility(0);
        }
        e();
        if (LanguageUtil.v(this.mContext)) {
            return;
        }
        this.e.setVisibility(0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void updateDateAndTime(String str, List<HwHealthMarkerView.a> list) {
        notifyCursorDateAndTime(str, list);
    }

    private String a(HwHealthBaseEntry hwHealthBaseEntry) {
        if (!(hwHealthBaseEntry.getData() instanceof edq)) {
            return "";
        }
        edq edqVar = (edq) hwHealthBaseEntry.getData();
        if (edqVar.b() == 0.0f || edqVar.c() == 0.0f) {
            return "--";
        }
        int c = (int) edqVar.c();
        int b = (int) edqVar.b();
        String e = UnitUtil.e(c, 1, 1);
        String e2 = UnitUtil.e(b, 1, 1);
        if (e.contains(".")) {
            e = e.substring(0, e.indexOf("."));
        }
        if (e2.contains(".")) {
            e2 = e2.substring(0, e2.indexOf("."));
        }
        if (e.contains(",")) {
            e = e.substring(0, e.indexOf(","));
        }
        if (e2.contains(",")) {
            e2 = e2.substring(0, e2.indexOf(","));
        }
        e(eeu.d(eeu.c(c, b)), eeu.c(eeu.c(c, b)));
        return e + "/" + e2;
    }

    private void e(int i, String str) {
        e();
        if (LanguageUtil.v(this.mContext)) {
            this.e.setVisibility(8);
            this.c.setVisibility(0);
            this.c.setText(str);
            if (i != -1) {
                this.c.setTextColor(i);
                return;
            }
            return;
        }
        this.c.setVisibility(8);
        this.e.setVisibility(0);
        this.e.setText(str);
        if (i != -1) {
            this.e.setTextColor(i);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionMultiLineChart";
    }
}

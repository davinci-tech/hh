package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.LacticLineChart;
import com.huawei.health.knit.section.utils.CardSelectedInterface;
import com.huawei.health.servicesui.R$string;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eal;
import defpackage.edw;
import defpackage.nqo;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionLacticLineChart<ChartT extends HwHealthBaseScrollBarLineChart> extends BaseSectionLineChart<ChartT> {
    private int b;
    private Observer c;
    private boolean d;
    private String e;

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected DataInfos getJudgeDataInfos() {
        return null;
    }

    public SectionLacticLineChart(Context context) {
        super(context);
        this.e = "lthrHr";
        this.d = false;
        this.c = new Observer() { // from class: com.huawei.health.knit.section.view.SectionLacticLineChart.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (!SectionLacticLineChart.this.getObserverLabel().equals(str) || objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                SectionLacticLineChart.this.e = intValue == 0 ? "lthrHr" : "lthrPace";
                SectionLacticLineChart.this.d();
            }
        };
    }

    public SectionLacticLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = "lthrHr";
        this.d = false;
        this.c = new Observer() { // from class: com.huawei.health.knit.section.view.SectionLacticLineChart.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (!SectionLacticLineChart.this.getObserverLabel().equals(str) || objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                SectionLacticLineChart.this.e = intValue == 0 ? "lthrHr" : "lthrPace";
                SectionLacticLineChart.this.d();
            }
        };
    }

    public SectionLacticLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = "lthrHr";
        this.d = false;
        this.c = new Observer() { // from class: com.huawei.health.knit.section.view.SectionLacticLineChart.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                if (!SectionLacticLineChart.this.getObserverLabel().equals(str) || objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                SectionLacticLineChart.this.e = intValue == 0 ? "lthrHr" : "lthrPace";
                SectionLacticLineChart.this.d();
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected ChartT acquireCurrentChart(int i) {
        LacticLineChart lacticLineChart = new LacticLineChart(this.mContext);
        lacticLineChart.setMarkViewCenteredWhenEmpty(false);
        return lacticLineChart;
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected String parseEntry(HwHealthBaseEntry hwHealthBaseEntry) {
        if (hwHealthBaseEntry == null || !(hwHealthBaseEntry.getData() instanceof edw)) {
            return "--";
        }
        int y = (int) hwHealthBaseEntry.getY();
        if (c()) {
            float f = y;
            if (UnitUtil.h()) {
                f = (float) UnitUtil.d(y, 3);
            }
            return nqo.d(f);
        }
        return UnitUtil.e(y, 1, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void updateDateAndTime(String str, List<HwHealthMarkerView.a> list) {
        if (this.mSecondLayerDateTime != null) {
            this.mSecondLayerDateTime.setText(str);
        }
        notifyCursorDateAndTime(str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.mChartHolder instanceof CardSelectedInterface) {
            ((CardSelectedInterface) this.mChartHolder).setShowDataType(this.e);
            this.mCurrentChart.clearValues();
            this.mChartHolder.addDataLayer((IChartLayerHolder) this.mCurrentChart, this.mDataInfos);
            this.mCurrentChart.setWillNotDraw(false);
            HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.mCurrentChart.acquireScrollAdapter();
            acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
            this.mCurrentChart.resetYaxisAnimateValue();
            this.mCurrentChart.refresh();
            a();
        }
    }

    private boolean c() {
        return "lthrPace".equals(this.e);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart, com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        int d = nru.d((Map) hashMap, "CURSOR_UP_AVERAGE_UNIT", -1);
        if (d != -1) {
            this.b = d;
        }
        a();
        super.bindParamsToView(hashMap);
        nsy.cMp_(this.mHorizontalBtn, nru.d((Map) hashMap, "HORIZONTAL_BTN", R.drawable._2131430277_res_0x7f0b0b85));
        this.mHorizontalBtn.setVisibility(0);
        if (!this.d) {
            ObserverManagerUtil.d(this.c, getObserverLabel());
            this.d = true;
        }
        this.mHorizontalBtn.setOnClickListener((OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null));
    }

    private void a() {
        String string = getResources().getString(this.b);
        if (c()) {
            string = getResources().getString(R$string.IDS_pace_unit);
            if (UnitUtil.h()) {
                string = getResources().getString(R$string.IDS_pace_unit_mi);
            }
        }
        this.mThirdLayerCursorUnit.setText(string);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.third_layer_layout);
        if (LanguageUtil.bp(getContext()) && c()) {
            relativeLayout.setLayoutDirection(0);
        } else {
            relativeLayout.setLayoutDirection(3);
        }
    }

    protected String getObserverLabel() {
        return eal.e(this.mDataInfos);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        super.clear();
        ObserverManagerUtil.e(this.c, getObserverLabel());
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void refreshChart(ChartT chartt) {
        chartt.reflesh(this.mStartTimeStampMillis);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionLacticLineChart";
    }
}

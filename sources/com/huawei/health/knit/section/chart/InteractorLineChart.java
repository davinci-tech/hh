package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import defpackage.ecv;
import defpackage.koq;
import defpackage.nom;
import defpackage.now;
import defpackage.nox;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class InteractorLineChart extends HwHealthLineChart {
    private static final float EXTRA_BOTTOM_OFFSET = 7.0f;
    private static final float EXTRA_TOP_OFFSET = 5.0f;
    public static final int NUMBER_TEN = 10;
    private static final int ONE_MINUTE = 60;
    private static final String TAG = "InteractorLineChart";
    protected Context mContext;
    protected a mOnScrollPagerAnimateMgr;

    protected abstract ecv getHealthLineDataSet(HwHealthBaseBarLineData hwHealthBaseBarLineData);

    protected abstract nox getLineChartRender(Context context, DataInfos dataInfos);

    protected abstract void initCursorMode();

    protected abstract void initXAxis();

    protected abstract void initYRender();

    public InteractorLineChart(Context context) {
        super(context);
        this.mOnScrollPagerAnimateMgr = new a();
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, null);
        initStyle();
    }

    public InteractorLineChart(Context context, DataInfos dataInfos) {
        super(context);
        this.mOnScrollPagerAnimateMgr = new a();
        this.mContext = null;
        LogUtil.c(TAG, "construct chart");
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, dataInfos);
        initStyle();
    }

    public InteractorLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnScrollPagerAnimateMgr = new a();
        this.mContext = null;
    }

    public InteractorLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOnScrollPagerAnimateMgr = new a();
        this.mContext = null;
    }

    private void initStyle() {
        this.mLegend.setEnabled(false);
        getDescription().setEnabled(false);
        initXAxis();
        initYRender();
        setExtraTopOffset(5.0f);
        setExtraBottomOffset(EXTRA_BOTTOM_OFFSET);
        initCursorMode();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void setMarkerViewPosition(HwHealthBaseBarLineChart.a aVar) {
        this.mMarkerViewPosition = aVar;
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart
    public void customAxisShow() {
        if (koq.c(((now) this.mData).getDataSets())) {
            super.customAxisShow();
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(false);
        this.mAxisThirdParty.setEnabled(false);
        this.mAxisFirstParty.setDrawLabels(true);
        this.mAxisSecondParty.setDrawLabels(false);
        this.mAxisThirdParty.setDrawLabels(false);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public void adsorbMarkerViewToSelectedDataByDataArea(boolean z, HwHealthBaseScrollBarLineChart<now>.e eVar) {
        if (eVar == null) {
            LogUtil.b(TAG, "adsorbMarkerViewToSelectedDataByDataArea scrollPagerAnimateListener == null");
            return;
        }
        this.mOnScrollPagerAnimateMgr.c(eVar);
        if (this.mMarkerViewPosition == MARKER_VIEW_POSITION_UNSET) {
            eVar.d();
            return;
        }
        ecv healthLineDataSet = getHealthLineDataSet((HwHealthBaseBarLineData) getData());
        if (healthLineDataSet == null) {
            eVar.d();
            return;
        }
        DataInfos e2 = healthLineDataSet.e();
        if ((e2 == null || !e2.isDayData()) && this.mMarkerViewPosition != null) {
            attachDataView(eVar, healthLineDataSet);
        }
    }

    private void attachDataView(HwHealthBaseScrollBarLineChart<now>.e eVar, ecv ecvVar) {
        HwEntrys.HwDataEntry hwDataEntry;
        float[] fArr = {fetchMarkViewXValPx(), 0.0f};
        getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).pixelsToValue(fArr);
        if (this.mMarkerViewPosition == MARKER_VIEW_POSITION_UNSET) {
            eVar.d();
            return;
        }
        this.mMarkerViewPosition.c(fArr[0]);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(new Highlight(this.mMarkerViewPosition.d(), 0, -1));
        Entry entryForXValue = ((now) this.mData).getEntryForXValue(this.mIndicesToHighlight[0].getX(), this);
        if (!(entryForXValue instanceof HwEntrys)) {
            LogUtil.h(TAG, "adsorb2SelectedDataByDataArea:entryForXValue must not be null");
            return;
        }
        if (arrayList.size() == 0) {
            eVar.d();
            return;
        }
        float[] fArr2 = {((Highlight) arrayList.get(0)).getX(), 0.0f};
        this.mFirstAxisTransformer.pointValuesToPixel(fArr2);
        if (!this.mViewPortHandler.isInBoundsX(fArr2[0])) {
            eVar.d();
            return;
        }
        Iterator<HwEntrys.HwDataEntry> it = ((HwEntrys) entryForXValue).getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                hwDataEntry = null;
                break;
            } else {
                hwDataEntry = it.next();
                if (hwDataEntry.getDataSet() == ecvVar) {
                    break;
                }
            }
        }
        if (hwDataEntry == null) {
            eVar.d();
        } else {
            markerScrollToGoalPosition(eVar, ecvVar);
        }
    }

    private void markerScrollToGoalPosition(HwHealthBaseScrollBarLineChart<now>.e eVar, ecv ecvVar) {
        int d = (int) this.mMarkerViewPosition.d();
        if (d >= ((int) this.mRangeBoardMax) - 10) {
            d -= 10;
        }
        if (d <= this.mRangeBoardMin + 10.0f) {
            d += 10;
        }
        scrollMarkerViewToUnixTime(true, nom.f(ecvVar.b(nom.h(d))), eVar);
    }

    protected static class a {
        private List<HwHealthBaseScrollBarLineChart<now>.e> d = new ArrayList(16);

        protected a() {
        }

        public void c(HwHealthBaseScrollBarLineChart<now>.e eVar) {
            if (this.d.contains(eVar)) {
                return;
            }
            this.d.add(eVar);
        }
    }

    public static class e implements IAxisValueFormatter {
        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            return decimalFormat.format(((int) f) / 60) + ":" + decimalFormat.format(r5 - (r0 * 60));
        }
    }
}

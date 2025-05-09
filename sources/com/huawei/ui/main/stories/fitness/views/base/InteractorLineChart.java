package com.huawei.ui.main.stories.fitness.views.base;

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
import defpackage.koq;
import defpackage.nom;
import defpackage.now;
import defpackage.nox;
import defpackage.pyd;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class InteractorLineChart extends HwHealthLineChart {
    private static final float EXTRA_BOTTOM_OFFSET = 7.0f;
    private static final float EXTRA_TOP_OFFSET = 5.0f;
    private static final int ONE_MINUTE = 60;
    private static final String TAG = "InteractorLineChart";
    public Context mContext;
    protected e mOnScrollPagerAnimateMgr;

    protected abstract pyd getHealthLineDataSet(HwHealthBaseBarLineData hwHealthBaseBarLineData);

    protected abstract nox getLineChartRender(Context context, DataInfos dataInfos);

    protected abstract void initCursorMode();

    protected abstract void initXAxis();

    protected abstract void initYRender();

    public InteractorLineChart(Context context) {
        super(context);
        this.mOnScrollPagerAnimateMgr = new e();
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, null);
        initStyle();
    }

    public InteractorLineChart(Context context, DataInfos dataInfos) {
        super(context);
        this.mOnScrollPagerAnimateMgr = new e();
        this.mContext = null;
        LogUtil.c(TAG, "construct chart");
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, dataInfos);
        initStyle();
    }

    public InteractorLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnScrollPagerAnimateMgr = new e();
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, null);
        initStyle();
    }

    public InteractorLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOnScrollPagerAnimateMgr = new e();
        this.mContext = context;
        this.mRenderer = getLineChartRender(context, null);
        initStyle();
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
        this.mOnScrollPagerAnimateMgr.a(eVar);
        if (this.mMarkerViewPosition == MARKER_VIEW_POSITION_UNSET) {
            eVar.d();
            return;
        }
        pyd healthLineDataSet = getHealthLineDataSet((HwHealthBaseBarLineData) getData());
        if (healthLineDataSet == null) {
            eVar.d();
            return;
        }
        DataInfos e2 = healthLineDataSet.e();
        if ((e2 == null || !e2.isDayData()) && this.mMarkerViewPosition != null) {
            attachDataView(eVar, healthLineDataSet);
        }
    }

    private void attachDataView(HwHealthBaseScrollBarLineChart<now>.e eVar, pyd pydVar) {
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
                if (hwDataEntry.getDataSet() == pydVar) {
                    break;
                }
            }
        }
        if (hwDataEntry == null) {
            eVar.d();
        } else {
            markerScrollToGoalPosition(eVar, pydVar);
        }
    }

    private void markerScrollToGoalPosition(HwHealthBaseScrollBarLineChart<now>.e eVar, pyd pydVar) {
        int d2 = (int) this.mMarkerViewPosition.d();
        if (d2 >= ((int) this.mRangeBoardMax) - 10) {
            d2 -= 10;
        }
        if (d2 <= this.mRangeBoardMin + 10.0f) {
            d2 += 10;
        }
        scrollMarkerViewToUnixTime(true, nom.f(pydVar.c(nom.h(d2))), eVar);
    }

    protected static class e {
        private List<HwHealthBaseScrollBarLineChart<now>.e> c = new ArrayList(16);

        protected e() {
        }

        public void a(HwHealthBaseScrollBarLineChart<now>.e eVar) {
            if (this.c.contains(eVar)) {
                return;
            }
            this.c.add(eVar);
        }
    }

    public static class d implements IAxisValueFormatter {
        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            return decimalFormat.format(((int) f) / 60) + ":" + decimalFormat.format(r5 - (r0 * 60));
        }
    }
}

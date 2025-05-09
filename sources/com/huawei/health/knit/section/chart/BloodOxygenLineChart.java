package com.huawei.health.knit.section.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.InteractorLineChart;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthXAxisRenderer;
import defpackage.eca;
import defpackage.ecf;
import defpackage.eci;
import defpackage.eco;
import defpackage.ecs;
import defpackage.ect;
import defpackage.koq;
import defpackage.nox;
import defpackage.nrn;
import java.util.List;

/* loaded from: classes3.dex */
public class BloodOxygenLineChart extends InteractorLineChart {
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public int getAnimateTime() {
        return 100;
    }

    public BloodOxygenLineChart(Context context, DataInfos dataInfos) {
        super(context, dataInfos);
        injectDataContainer(new HwHealthBaseBarLineChart.HwHealthDataContainerGenerator() { // from class: com.huawei.health.knit.section.chart.BloodOxygenLineChart.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            public boolean typeOf(Class cls) {
                Class<eca> cls2 = eca.class;
                while (true) {
                    if (cls2 == null) {
                        return false;
                    }
                    if (cls2.equals(cls)) {
                        return true;
                    }
                    for (Class<?> cls3 : cls2.getInterfaces()) {
                        if (cls3.equals(cls)) {
                            return true;
                        }
                    }
                    cls2 = cls2.getSuperclass();
                }
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public eca newDataContainer() {
                return new eca(BloodOxygenLineChart.this);
            }
        });
    }

    public BloodOxygenLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BloodOxygenLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.huawei.health.knit.section.chart.InteractorLineChart
    protected nox getLineChartRender(Context context, DataInfos dataInfos) {
        if (dataInfos == DataInfos.BloodOxygenAltitudeDayDetail) {
            return new eci(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
        }
        return new ecs(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
    }

    @Override // com.huawei.health.knit.section.chart.InteractorLineChart
    protected void initXAxis() {
        XAxis xAxis = getXAxis();
        xAxis.setValueFormatter(new InteractorLineChart.e());
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(1440.0f);
    }

    @Override // com.huawei.health.knit.section.chart.InteractorLineChart
    protected void initCursorMode() {
        acquireLayout().l(Utils.convertDpToPixel(30.0f));
        setBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296657_res_0x7f090191));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void enableMarkerView(boolean z) {
        this.mIsMarkerViewEnable = z;
        if (!(this.mXAxisRenderer instanceof HwHealthXAxisRenderer)) {
            LogUtil.h("BloodOxygenLineChart", "enableMarkerView mXAxisRenderer not instanceof HwHealthXAxisRenderer");
            return;
        }
        if (z) {
            getXAxis().setYOffset(Utils.convertPixelsToDp(this.mLayout.g() + this.mLayout.j()));
            setExtraBottomOffset(Utils.convertPixelsToDp(this.mLayout.e()) / 2.0f);
            ((HwHealthXAxisRenderer) this.mXAxisRenderer).c(HwHealthXAxisRenderer.Sorption.Y_OFFSET_FLOOR);
            setBackgroundColor(nrn.d(getContext(), R$color.health_chart_extend_background_color));
            fillChartBackground(nrn.d(getContext(), R$color.colorBackground), nrn.d(getContext(), R$color.colorBackground));
            return;
        }
        ((HwHealthXAxisRenderer) this.mXAxisRenderer).c(HwHealthXAxisRenderer.Sorption.Y_OFFSET_CEIL);
        float f = this.mLayout.f();
        float h = this.mLayout.h();
        setExtraBottomOffset(Utils.convertPixelsToDp(f + h + this.mLayout.b() + this.mLayout.d()));
        setBackgroundColor(0);
        fillChartBackground(0, 0);
    }

    @Override // com.huawei.health.knit.section.chart.InteractorLineChart
    protected void initYRender() {
        this.mAxisRendererFirstParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void clipDataArea(Canvas canvas) {
        RectF rectF = new RectF(this.mViewPortHandler.getContentRect());
        rectF.bottom += this.mXAxis.getYOffset();
        rectF.top -= 12.0f;
        canvas.clipRect(rectF);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        LogUtil.c("BloodOxygenLineChart", "refresh chart");
        super.refresh();
    }

    public void setNewRenderer(DataInfos dataInfos, Context context) {
        if (dataInfos == DataInfos.BloodOxygenAltitudeDayDetail || dataInfos == DataInfos.BloodOxygenAltitudeDayHorizontal) {
            this.mRenderer = new eci(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
            this.mAxisRendererFirstParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
            this.mAxisRendererSecondParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
            this.mAxisRendererThirdParty = new ecf(this.mContext, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
            return;
        }
        this.mRenderer = new ecs(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
        this.mAxisRendererFirstParty = new ect(this.mContext, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this, dataInfos);
        this.mAxisRendererSecondParty = new ect(this.mContext, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this, dataInfos);
        this.mAxisRendererThirdParty = new ect(this.mContext, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this, dataInfos);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public float computeTipsCenterHeightPx() {
        return (this.mAxisFirstParty.mEntries[1] + this.mAxisFirstParty.mEntries[2]) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.section.chart.InteractorLineChart
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public eco getHealthLineDataSet(HwHealthBaseBarLineData hwHealthBaseBarLineData) {
        if (hwHealthBaseBarLineData == null) {
            LogUtil.h("BloodOxygenLineChart", "getBloodOxygenHealthLineDataSet data = null");
            return null;
        }
        List<T> dataSets = hwHealthBaseBarLineData.getDataSets();
        if (koq.b(dataSets)) {
            LogUtil.h("BloodOxygenLineChart", "getBloodOxygenHealthLineDataSet dataSets = null");
            return null;
        }
        for (T t : dataSets) {
            if (t instanceof eco) {
                return (eco) t;
            }
        }
        return null;
    }
}

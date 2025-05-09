package com.huawei.ui.commonui.linechart.common.touch;

import android.view.MotionEvent;
import android.view.View;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.touch.TouchModeDelegateMgr;

/* loaded from: classes6.dex */
public class HwHealthBarLineChartTouchListener extends ChartTouchListener<HwHealthBaseBarLineChart<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {

    /* renamed from: a, reason: collision with root package name */
    public HwHealthBaseScrollBarLineChart.ChartShowMode f8882a;
    private OnScaleListener b;
    private boolean c;
    private TouchModeDelegateMgr.TouchModeDelegate d;
    private TouchModeDelegateMgr e;

    public interface OnScaleListener {
        void onScale(float f);
    }

    public HwHealthBarLineChartTouchListener(HwHealthBaseBarLineChart<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler) {
        super(hwHealthBaseBarLineChart);
        this.f8882a = HwHealthBaseScrollBarLineChart.ChartShowMode.NORMAL;
        this.e = null;
        this.d = null;
        this.c = true;
        this.b = null;
        TouchModeDelegateMgr touchModeDelegateMgr = new TouchModeDelegateMgr(this, (HwHealthBaseBarLineChart) this.mChart, viewPortHandler);
        this.e = touchModeDelegateMgr;
        this.d = touchModeDelegateMgr.e();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        OnScaleListener onScaleListener;
        float scaleX = ((HwHealthBaseBarLineChart) this.mChart).getViewPortHandler().getScaleX();
        if (motionEvent == null) {
            return false;
        }
        this.d = this.d.monitor(motionEvent);
        if (scaleX != ((HwHealthBaseBarLineChart) this.mChart).getViewPortHandler().getScaleX() && (onScaleListener = this.b) != null) {
            onScaleListener.onScale(((HwHealthBaseBarLineChart) this.mChart).getViewPortHandler().getScaleX());
        }
        if (this.d != this.e.e()) {
            if (this.c) {
                ((HwHealthBaseBarLineChart) this.mChart).disableScroll();
                this.c = false;
                return true;
            }
            if (motionEvent.getAction() == 2) {
                ((HwHealthBaseBarLineChart) this.mChart).invalidate();
            }
        } else if (!this.c) {
            ((HwHealthBaseBarLineChart) this.mChart).enableScroll();
            this.c = true;
        }
        if (motionEvent.getAction() == 1) {
            ((HwHealthBaseBarLineChart) this.mChart).invalidate();
        }
        return true;
    }

    public void d() {
        this.f8882a = HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE;
    }

    public void b() {
        this.f8882a = HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_SCALE_MODE;
    }

    public void a(OnScaleListener onScaleListener) {
        this.b = onScaleListener;
    }
}

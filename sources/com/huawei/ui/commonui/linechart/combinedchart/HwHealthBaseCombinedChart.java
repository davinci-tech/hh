package com.huawei.ui.commonui.linechart.combinedchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener;
import com.huawei.ui.commonui.linechart.icommon.HwHealthCombinedDataProvider;
import com.huawei.ui.commonui.linechart.utils.CustomChartTitleBar;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import defpackage.koq;
import defpackage.nmr;
import defpackage.nms;
import defpackage.nmu;
import defpackage.nmx;
import defpackage.nmz;
import defpackage.nnd;
import defpackage.nnf;
import defpackage.nng;
import defpackage.nns;
import defpackage.nos;
import defpackage.now;
import defpackage.npg;
import defpackage.nrn;
import defpackage.nrs;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class HwHealthBaseCombinedChart extends HwHealthBaseScrollBarLineChart<nnd> implements HwHealthCombinedDataProvider {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f8854a;
    private CustomChartTitleBar b;
    protected DrawOrder[] c;
    private DataParser d;
    private Context e;
    private long f;
    private boolean g;
    private DataParser h;
    private boolean i;
    private boolean j;
    private int k;
    private TimeValueMode l;
    private int m;
    private boolean n;
    private ViewTreeObserver.OnGlobalLayoutListener o;
    private OnDoubleTapListener p;
    private OnSingleTapConfirmedListener r;
    private HwHealthBarLineChartTouchListener.OnScaleListener s;
    private int t;

    public interface DataParser {
        boolean isSupportOverlaying();

        boolean isSupportSampling();

        void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet);

        int onSampling(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet);
    }

    public enum DrawOrder {
        BAR,
        LINE,
        POINT
    }

    /* loaded from: classes9.dex */
    public interface OnDoubleTapListener {
        boolean isOnDoubleTap(MotionEvent motionEvent);
    }

    public interface OnSingleTapConfirmedListener {
        boolean isOnSingleTapConfirmed(MotionEvent motionEvent);
    }

    public enum TimeValueMode {
        DEFAULT,
        MINUTES,
        METERS,
        KILOMETERS
    }

    public HwHealthBaseCombinedChart(Context context) {
        super(context);
        this.f8854a = false;
        this.f = 0L;
        this.t = 0;
        this.b = null;
        this.l = TimeValueMode.DEFAULT;
        this.k = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.m = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.j = true;
        this.i = false;
        this.p = null;
        this.r = null;
        this.g = false;
        d dVar = new d();
        this.h = dVar;
        this.n = true;
        this.d = dVar;
        this.o = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthBaseCombinedChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HwHealthBaseCombinedChart.this.e == null) {
                            return;
                        }
                        HwHealthBaseCombinedChart.this.t = (int) (nrs.c(HwHealthBaseCombinedChart.this.e) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f));
                        HwHealthBaseCombinedChart.this.c(HwHealthBaseCombinedChart.this.t);
                        HwHealthBaseCombinedChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthBaseCombinedChart.this.o);
                    }
                });
            }
        };
        this.s = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.3
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "width:", Integer.valueOf(HwHealthBaseCombinedChart.this.getWidth()), " scaleX:", Float.valueOf(f));
                if (HwHealthBaseCombinedChart.this.e != null) {
                    HwHealthBaseCombinedChart.this.c((int) ((nrs.c(HwHealthBaseCombinedChart.this.e) * f) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f)));
                    return;
                }
                LogUtil.b("HealthChart_HwHealthBaseCombinedChart", "mContext is null,pls check");
            }
        };
        c();
    }

    public HwHealthBaseCombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8854a = false;
        this.f = 0L;
        this.t = 0;
        this.b = null;
        this.l = TimeValueMode.DEFAULT;
        this.k = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.m = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.j = true;
        this.i = false;
        this.p = null;
        this.r = null;
        this.g = false;
        d dVar = new d();
        this.h = dVar;
        this.n = true;
        this.d = dVar;
        this.o = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthBaseCombinedChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HwHealthBaseCombinedChart.this.e == null) {
                            return;
                        }
                        HwHealthBaseCombinedChart.this.t = (int) (nrs.c(HwHealthBaseCombinedChart.this.e) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f));
                        HwHealthBaseCombinedChart.this.c(HwHealthBaseCombinedChart.this.t);
                        HwHealthBaseCombinedChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthBaseCombinedChart.this.o);
                    }
                });
            }
        };
        this.s = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.3
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "width:", Integer.valueOf(HwHealthBaseCombinedChart.this.getWidth()), " scaleX:", Float.valueOf(f));
                if (HwHealthBaseCombinedChart.this.e != null) {
                    HwHealthBaseCombinedChart.this.c((int) ((nrs.c(HwHealthBaseCombinedChart.this.e) * f) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f)));
                    return;
                }
                LogUtil.b("HealthChart_HwHealthBaseCombinedChart", "mContext is null,pls check");
            }
        };
        c();
    }

    public HwHealthBaseCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8854a = false;
        this.f = 0L;
        this.t = 0;
        this.b = null;
        this.l = TimeValueMode.DEFAULT;
        this.k = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.m = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.j = true;
        this.i = false;
        this.p = null;
        this.r = null;
        this.g = false;
        d dVar = new d();
        this.h = dVar;
        this.n = true;
        this.d = dVar;
        this.o = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthBaseCombinedChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HwHealthBaseCombinedChart.this.e == null) {
                            return;
                        }
                        HwHealthBaseCombinedChart.this.t = (int) (nrs.c(HwHealthBaseCombinedChart.this.e) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f));
                        HwHealthBaseCombinedChart.this.c(HwHealthBaseCombinedChart.this.t);
                        HwHealthBaseCombinedChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthBaseCombinedChart.this.o);
                    }
                });
            }
        };
        this.s = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.3
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "width:", Integer.valueOf(HwHealthBaseCombinedChart.this.getWidth()), " scaleX:", Float.valueOf(f));
                if (HwHealthBaseCombinedChart.this.e != null) {
                    HwHealthBaseCombinedChart.this.c((int) ((nrs.c(HwHealthBaseCombinedChart.this.e) * f) / (HwHealthBaseCombinedChart.this.e.getResources().getDisplayMetrics().density * 3.0f)));
                    return;
                }
                LogUtil.b("HealthChart_HwHealthBaseCombinedChart", "mContext is null,pls check");
            }
        };
        c();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.c = new DrawOrder[]{DrawOrder.BAR, DrawOrder.LINE, DrawOrder.POINT};
        setHighlighter(new nnf(this, this));
        setHighlightFullBarEnabled(true);
        this.mRenderer = new HwHealthBaseCombinedChartRenderer(this, this.mAnimator, this.mViewPortHandler);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.n) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setXAxisValueFormatter(IAxisValueFormatter iAxisValueFormatter) {
        XAxis xAxis = getXAxis();
        if (xAxis == null) {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "setValueFormatter failed, cause");
        } else {
            xAxis.setValueFormatter(iAxisValueFormatter);
            invalidate();
        }
    }

    private void c() {
        this.e = getContext();
        setLayerType(1, null);
        this.mLegend.setXOffset(0.0f);
        setTouchEnabled(true);
        setDragEnabled(true);
        setScaleEnabled(true);
        setScaleYEnabled(false);
        setDoubleTapToZoomEnabled(false);
        setMaximumScaleX(4.0f);
        setDrawGridBackground(true);
        setGridBackgroundColor(Color.argb(0, 0, 0, 0));
        nos.a(getContext(), getLegend());
        this.mLegendRenderer = new npg(this.e, this, this.mViewPortHandler, this.mLegend);
        setDrawBorders(false);
        i();
        setExtraTopOffset(13.0f);
        setMaxHighlightDistance(1000.0f);
        e();
        if (this.mChartTouchListener instanceof HwHealthBarLineChartTouchListener) {
            ((HwHealthBarLineChartTouchListener) this.mChartTouchListener).a(this.s);
        }
        b();
        XAxis xAxis = getXAxis();
        xAxis.setAxisLineWidth(0.5f);
        xAxis.setAxisLineColor(-7829368);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(this.k);
        xAxis.setTextSize(10.0f);
        if (this.l == TimeValueMode.MINUTES) {
            xAxis.setValueFormatter(new nmx());
        } else if (this.l == TimeValueMode.METERS) {
            xAxis.setValueFormatter(new nmu());
        } else if (this.l == TimeValueMode.KILOMETERS) {
            xAxis.setValueFormatter(new nms());
        } else {
            xAxis.setValueFormatter(new nmr());
        }
        xAxis.setAxisMinimum(0.0f);
        HwHealthMarkerView hwHealthMarkerView = new HwHealthMarkerView(this.e, R.layout.custom_marker_view, this);
        hwHealthMarkerView.setChartView(this);
        setMarker(hwHealthMarkerView);
        getLegend().setEnabled(true);
        setVisibility(8);
        acquireLayout().l(Utils.convertDpToPixel(20.0f)).a(Utils.convertDpToPixel(24.0f)).j(Utils.convertDpToPixel(0.0f));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void clipDataArea(Canvas canvas) {
        RectF rectF = new RectF(this.mViewPortHandler.getContentRect());
        rectF.top -= this.mXAxis.getYOffset();
        rectF.bottom += this.mXAxis.getYOffset();
        rectF.left -= Utils.convertDpToPixel(5.0f);
        rectF.right += Utils.convertDpToPixel(5.0f);
        canvas.clipRect(rectF);
    }

    private void i() {
        getDescription().setEnabled(true);
        getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_time));
        if (!nng.d(this.e)) {
            getDescription().setTextAlign(Paint.Align.RIGHT);
        } else {
            getDescription().setTextAlign(Paint.Align.LEFT);
        }
        getDescription().setTextSize(10.0f);
        getDescription().setTextColor(this.k);
    }

    protected void e() {
        final GestureDetector gestureDetector = new GestureDetector(this.e, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.4
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (HwHealthBaseCombinedChart.this.p == null) {
                    return false;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (Math.abs(elapsedRealtime - HwHealthBaseCombinedChart.this.f) < 2300) {
                    return false;
                }
                HwHealthBaseCombinedChart.this.f = elapsedRealtime;
                return HwHealthBaseCombinedChart.this.p.isOnDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (HwHealthBaseCombinedChart.this.r == null) {
                    return false;
                }
                return HwHealthBaseCombinedChart.this.r.isOnSingleTapConfirmed(motionEvent);
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!HwHealthBaseCombinedChart.this.mTouchEnabled) {
                    gestureDetector.onTouchEvent(motionEvent);
                    return true;
                }
                return HwHealthBaseCombinedChart.this.onTouchEvent(motionEvent);
            }
        });
    }

    public void setMaximumScaleX(float f) {
        this.mViewPortHandler.setMaximumScaleX(f);
    }

    public void setAvoidFirstLastClipping(boolean z) {
        this.mXAxis.setAvoidFirstLastClipping(z);
    }

    public void setLabelColor(int i) {
        this.k = Color.argb(128, Color.red(i), Color.green(i), Color.blue(i));
        getDescription().setTextColor(this.k);
        getXAxis().setTextColor(this.k);
        this.mAxisFirstParty.setTextColor(this.k);
        this.mAxisSecondParty.setTextColor(this.k);
        this.mLegend.setTextColor(this.k);
        this.m = Color.argb(51, Color.red(i), Color.green(i), Color.blue(i));
        this.mAxisFirstParty.setGridColor(this.m);
    }

    private void b() {
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setAxisLineWidth(0.5f);
        this.mAxisFirstParty.setAxisLineColor(Color.argb(127, 0, 0, 0));
        this.mAxisFirstParty.setDrawGridLines(true);
        this.mAxisFirstParty.setGridColor(this.m);
        this.mAxisFirstParty.setGridLineWidth(0.5f);
        this.mAxisFirstParty.setTextColor(this.k);
        this.mAxisFirstParty.setTextSize(10.0f);
        this.mAxisFirstParty.setLabelCount(5, true);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setAxisLineWidth(0.5f);
        this.mAxisSecondParty.setAxisLineColor(-7829368);
        this.mAxisSecondParty.setDrawGridLines(false);
        this.mAxisSecondParty.setTextColor(this.k);
        this.mAxisSecondParty.setTextSize(10.0f);
        this.mAxisSecondParty.setLabelCount(5, true);
    }

    public void setYRenderFillArea(List<nns> list) {
        this.mAxisRendererFirstParty.e(list);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthCombinedDataProvider
    public nnd getCombinedData() {
        return (nnd) this.mData;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void setData(nnd nndVar) {
        super.setData((HwHealthBaseCombinedChart) nndVar);
        setHighlighter(new nnf(this, this));
        if (this.mRenderer instanceof HwHealthBaseCombinedChartRenderer) {
            ((HwHealthBaseCombinedChartRenderer) this.mRenderer).e();
            this.mRenderer.initBuffers();
        }
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public Highlight getHighlightByTouchPoint(float f, float f2) {
        if (this.mData == 0) {
            LogUtil.b(Chart.LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        Highlight highlight = getHighlighter().getHighlight(f, f2);
        return (highlight == null || !isHighlightFullBarEnabled()) ? highlight : new Highlight(highlight.getX(), highlight.getY(), highlight.getXPx(), highlight.getYPx(), highlight.getDataSetIndex(), -1, highlight.getAxis());
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public void drawDescription(Canvas canvas) {
        float convertDpToPixel;
        if (this.mDescription == null || !this.mDescription.isEnabled()) {
            return;
        }
        this.mDescPaint.setTypeface(this.mDescription.getTypeface());
        this.mDescPaint.setTextSize(this.mDescription.getTextSize());
        this.mDescPaint.setColor(this.mDescription.getTextColor());
        this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
        if (!nng.d(getContext())) {
            this.mDescPaint.setTextAlign(Paint.Align.RIGHT);
            convertDpToPixel = getWidth() - Utils.convertDpToPixel(1.0f);
        } else {
            this.mDescPaint.setTextAlign(Paint.Align.LEFT);
            convertDpToPixel = Utils.convertDpToPixel(1.0f);
        }
        canvas.drawText(this.mDescription.getText(), convertDpToPixel, this.mChartAnchor.cBm_().top + (-this.mDescPaint.getFontMetrics().top), this.mDescPaint);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider
    public now getPointData() {
        if (this.mData == 0) {
            return null;
        }
        return ((nnd) this.mData).h();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider
    public now getLineData() {
        if (this.mData == 0) {
            return null;
        }
        return ((nnd) this.mData).f();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public nmz getBarData() {
        if (this.mData == 0) {
            return null;
        }
        return ((nnd) this.mData).j();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawBarShadowEnabled() {
        return this.i;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawValueAboveBarEnabled() {
        return this.j;
    }

    public void setHighlightFullBarEnabled(boolean z) {
        this.f8854a = z;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isHighlightFullBarEnabled() {
        return this.f8854a;
    }

    public DrawOrder[] getDrawOrder() {
        if (getData() != null && ((nnd) getData()).i() != null) {
            return ((nnd) getData()).i();
        }
        return (DrawOrder[]) this.c.clone();
    }

    public void setTimeValueMode(TimeValueMode timeValueMode) {
        this.l = timeValueMode;
        XAxis xAxis = getXAxis();
        if (this.l == TimeValueMode.MINUTES) {
            xAxis.setValueFormatter(new nmx());
            getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_time_min));
            return;
        }
        if (this.l == TimeValueMode.METERS) {
            xAxis.setValueFormatter(new nmu());
            if (UnitUtil.h()) {
                getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_foot));
                return;
            } else {
                getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_swim_distance_meter));
                return;
            }
        }
        if (this.l == TimeValueMode.KILOMETERS) {
            xAxis.setValueFormatter(new nms());
            if (UnitUtil.h()) {
                getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_mi));
                return;
            } else {
                getDescription().setText(getResources().getString(R$string.IDS_route_alt_chart_x_label));
                return;
            }
        }
        xAxis.setValueFormatter(new nmr());
        getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_time));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        if (getWidth() == 0) {
            setVisibility(0);
            getViewTreeObserver().addOnGlobalLayoutListener(this.o);
            return;
        }
        int i = this.t;
        if (i == 0) {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "mOneScreenShowCounts null,width not null");
        } else {
            c(i);
        }
    }

    public void c(int i) {
        if (this.mData == 0) {
            return;
        }
        if (this.mRenderer instanceof HwHealthBaseCombinedChartRenderer) {
            ((HwHealthBaseCombinedChartRenderer) this.mRenderer).e();
            this.mRenderer.initBuffers();
        }
        ((nnd) this.mData).c();
        d(i);
        notifyDataSetChanged();
        invalidate();
        for (T t : ((nnd) this.mData).getDataSets()) {
            if (t != null) {
                if (t instanceof IHwHealthLineDataSet) {
                    ((IHwHealthLineDataSet) t).checkIfNeedReload();
                } else {
                    t.setValues(t.acquireEntryVals());
                    t.makeDataCalculated(true);
                }
            }
        }
    }

    private void d(int i) {
        if (this.mData == 0) {
            return;
        }
        a();
        List<T> dataSets = ((nnd) this.mData).getDataSets();
        LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "fillOriginalData mData size = ", Integer.valueOf(dataSets.size()), " counts ", Integer.valueOf(i));
        if (dataSets.size() == 0) {
            return;
        }
        Iterator it = dataSets.iterator();
        while (it.hasNext()) {
            if (koq.b(((HwHealthBaseBarLineDataSet) it.next()).acquireEntryVals())) {
                return;
            }
        }
        boolean z = true;
        for (T t : dataSets) {
            if (t instanceof HwHealthLineDataSet) {
                if (z) {
                    LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "firData setDrawFilled true");
                    ((HwHealthLineDataSet) t).setDrawFilled(true);
                } else {
                    LogUtil.a("HealthChart_HwHealthBaseCombinedChart", "not firData setDrawFilled false");
                    ((HwHealthLineDataSet) t).setDrawFilled(false);
                }
            }
            if (z) {
                z = false;
            }
        }
        a(i);
        d();
    }

    private void d() {
        if (this.b == null) {
            return;
        }
        List<T> dataSets = ((nnd) this.mData).getDataSets();
        if (koq.b(dataSets)) {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "setTitle not find data,lineDataSets size zero,return");
            return;
        }
        ArrayList arrayList = new ArrayList(dataSets.size());
        for (T t : dataSets) {
            if (t instanceof HwHealthLineDataSet) {
                arrayList.add(((HwHealthLineDataSet) t).c().d());
            } else if (t instanceof HwHealthBarDataSet) {
                arrayList.add(((HwHealthBarDataSet) t).d().d());
            } else {
                LogUtil.b("HealthChart_HwHealthBaseCombinedChart", "dataSet type is error. pls check");
            }
        }
        int size = arrayList.size();
        String str = "";
        if (size == 1) {
            str = "" + ((String) arrayList.get(0));
        } else if (size == 2) {
            str = "" + String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_title_two), arrayList.get(0), arrayList.get(1));
        } else if (size == 3) {
            str = "" + String.format(Locale.ENGLISH, this.e.getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_title_three), arrayList.get(0), arrayList.get(1), arrayList.get(2));
        } else {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "lineDataSets.size not support:", Integer.valueOf(arrayList.size()));
        }
        this.b.setTitle(str);
    }

    protected void a() {
        List<T> dataSets = ((nnd) this.mData).getDataSets();
        if (dataSets.size() == 0) {
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(true);
        this.mAxisThirdParty.setEnabled(true);
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisThirdParty.setDrawAxisLine(false);
        if (dataSets.size() == 1) {
            this.mAxisFirstParty.setDrawLabels(true);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
        }
        if (dataSets.size() == 2) {
            this.mAxisFirstParty.setDrawLabels(true);
            this.mAxisSecondParty.setDrawLabels(true);
            this.mAxisThirdParty.setDrawLabels(false);
        }
        if (dataSets.size() == 3) {
            this.mAxisFirstParty.setDrawLabels(false);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
        }
        if (this.g) {
            this.mAxisFirstParty.setDrawLabels(false);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
        }
    }

    private void a(int i) {
        if (i == 0) {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "fillOriginalData showCounts zero,why?,return");
            return;
        }
        List<T> dataSets = ((nnd) this.mData).getDataSets();
        if (dataSets.size() > 3) {
            LogUtil.h("HealthChart_HwHealthBaseCombinedChart", "only support 3 layers");
            return;
        }
        for (T t : dataSets) {
            if (t != null) {
                if (!(t instanceof HwHealthLineDataSet)) {
                    t.makeDataCalculated(true);
                } else {
                    HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) t;
                    List<T> acquireEntryVals = hwHealthLineDataSet.acquireEntryVals();
                    ArrayList arrayList = new ArrayList(acquireEntryVals.size());
                    for (T t2 : acquireEntryVals) {
                        if (t2 != null) {
                            arrayList.add(new HwHealthBaseEntry(t2.getX(), t2.getY(), t2.getData()));
                        }
                    }
                    if (this.d.isSupportOverlaying()) {
                        this.d.onOverlaying(arrayList, hwHealthLineDataSet);
                        hwHealthLineDataSet.j();
                    }
                    if (this.d.isSupportSampling()) {
                        hwHealthLineDataSet.e(this.d.onSampling(arrayList, i, hwHealthLineDataSet));
                    }
                    hwHealthLineDataSet.setValues(arrayList);
                    t.makeDataCalculated(true);
                }
            }
        }
    }

    public void setOnSingleTapConfirmedListener(OnSingleTapConfirmedListener onSingleTapConfirmedListener) {
        this.r = onSingleTapConfirmedListener;
    }

    public void d(DataParser dataParser) {
        if (dataParser == null) {
            this.d = this.h;
        } else {
            this.d = dataParser;
        }
    }

    public void setIsTouchEventConsumption(boolean z) {
        this.n = z;
    }

    /* loaded from: classes9.dex */
    static class d implements DataParser {
        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public boolean isSupportOverlaying() {
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public boolean isSupportSampling() {
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet) {
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public int onSampling(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet) {
            return 0;
        }

        private d() {
        }
    }
}

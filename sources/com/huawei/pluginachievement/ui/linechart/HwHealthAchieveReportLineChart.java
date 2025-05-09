package com.huawei.pluginachievement.ui.linechart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.gvv;
import defpackage.mjr;
import defpackage.mjw;
import defpackage.mjy;
import defpackage.mjz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class HwHealthAchieveReportLineChart extends LineChart {

    /* renamed from: a, reason: collision with root package name */
    private float f8450a;
    private boolean b;
    private WeakReference<Bitmap> c;
    private mjy d;
    private float e;
    private Canvas f;
    private GradientDrawable g;
    private int h;
    private HwHealthAchieveReportMarkView i;
    private int j;
    private int k;
    private RectF l;
    private OnSingleTapListener m;
    private int n;
    private float[] o;
    private float p;
    private float q;

    public interface OnSingleTapListener {
        void onSingleTap(int i);
    }

    public HwHealthAchieveReportLineChart(Context context) {
        super(context);
        this.k = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.h = Color.parseColor("#FFFDB290");
        this.j = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = new mjy();
        this.q = 0.0f;
        this.f8450a = 0.0f;
        this.n = 1;
        this.b = true;
        this.p = 0.0f;
        this.e = 0.0f;
        this.c = null;
        this.l = new RectF();
        e();
    }

    public HwHealthAchieveReportLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.h = Color.parseColor("#FFFDB290");
        this.j = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = new mjy();
        this.q = 0.0f;
        this.f8450a = 0.0f;
        this.n = 1;
        this.b = true;
        this.p = 0.0f;
        this.e = 0.0f;
        this.c = null;
        this.l = new RectF();
        e();
    }

    public HwHealthAchieveReportLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.h = Color.parseColor("#FFFDB290");
        this.j = Color.parseColor(Constants.CHOOSE_TEXT_COLOR);
        this.d = new mjy();
        this.q = 0.0f;
        this.f8450a = 0.0f;
        this.n = 1;
        this.b = true;
        this.p = 0.0f;
        this.e = 0.0f;
        this.c = null;
        this.l = new RectF();
        e();
    }

    @Override // com.github.mikephil.charting.charts.LineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        if (getContext() == null) {
            LogUtil.a("event", "context is null");
        }
        this.mXAxisRenderer = new mjz(this.mViewPortHandler, getXAxis(), this.mLeftAxisTransformer);
        this.mRenderer = new mjw(this, this.mAnimator, this.mViewPortHandler);
    }

    public void setMyMarkView(HwHealthAchieveReportMarkView hwHealthAchieveReportMarkView) {
        this.i = hwHealthAchieveReportMarkView;
        setMarker(hwHealthAchieveReportMarkView);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
            float f = this.p;
            if (rawX < f) {
                d(f);
                this.i.e(this.p);
                float[] fArr = this.o;
                if (fArr != null && fArr.length > 1) {
                    c(fArr[0]);
                }
            } else {
                float f2 = this.e;
                if (rawX > f2) {
                    d(f2);
                    this.i.e(this.e);
                    float[] fArr2 = this.o;
                    if (fArr2 != null && fArr2.length > 1) {
                        c(fArr2[fArr2.length - 2]);
                    }
                } else {
                    d(rawX);
                    int b = b(rawX) * 2;
                    float[] fArr3 = this.o;
                    if (fArr3 != null && b < fArr3.length) {
                        c(fArr3[b]);
                    }
                    this.i.e(rawX);
                }
            }
            this.m.onSingleTap(b(rawX));
            if (this.mChartTouchListener == null || this.mData == 0 || !this.mTouchEnabled) {
                return false;
            }
            return this.mChartTouchListener.onTouch(this, motionEvent);
        }
        if (motionEvent.getAction() == 1 && rawX > this.p && rawX < this.e) {
            this.q = this.i.getmMarkDrawX();
            int b2 = b(rawX) * 2;
            float[] fArr4 = this.o;
            if (fArr4 != null && b2 < fArr4.length) {
                this.f8450a = fArr4[b2];
                b();
            }
        }
        return false;
    }

    private void getRange() {
        if (this.mRenderer instanceof mjw) {
            this.p = ((mjw) this.mRenderer).b();
            this.e = ((mjw) this.mRenderer).d() == 0.0f ? this.p : ((mjw) this.mRenderer).d();
            LogUtil.a("PLGACHIEVE_HwHealthAchieveReportLineChart", "startPoint = ", Float.valueOf(this.p), "endPoint=", Float.valueOf(this.e));
        }
    }

    private int b(float f) {
        float f2 = this.p;
        float f3 = this.e;
        if (f2 == f3) {
            return 0;
        }
        if (f >= f3) {
            return this.n;
        }
        return (int) (((f - f2) / ((f3 - f2) / this.n)) + 0.5f);
    }

    private void a() {
        this.o = new float[this.mXAxis.mEntryCount * 2];
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i = 0;
        while (true) {
            float[] fArr = this.o;
            if (i >= fArr.length) {
                this.mLeftAxisTransformer.pointValuesToPixel(this.o);
                return;
            }
            if (isCenterAxisLabelsEnabled) {
                fArr[i] = this.mXAxis.mCenteredEntries[i / 2];
            } else {
                fArr[i] = this.mXAxis.mEntries[i / 2];
            }
            i += 2;
        }
    }

    void c() {
        if (this.b) {
            getRange();
            this.b = false;
            if (LanguageUtil.bc(getContext())) {
                d(this.p);
                this.i.e(this.p);
            } else {
                d(this.e);
                this.i.e(this.e);
            }
        }
    }

    private void e() {
        d();
    }

    private void d() {
        getDescription().setEnabled(false);
        setPinchZoom(false);
        setDrawBorders(false);
        setTouchEnabled(true);
        setDragEnabled(true);
        setScaleEnabled(false);
        setLogEnabled(false);
        getLegend().setEnabled(false);
        d(BaseApplication.getContext().getResources().getColor(R.color._2131296657_res_0x7f090191), BaseApplication.getContext().getResources().getColor(R.color._2131296657_res_0x7f090191));
    }

    public void c(int i, int i2, int i3) {
        this.k = i;
        this.h = i2;
        this.j = i3;
    }

    public void setChartData(Context context, ArrayList<String> arrayList, ArrayList<Entry> arrayList2, boolean z, boolean z2) {
        if (arrayList == null || arrayList2 == null) {
            return;
        }
        if (z && !z2) {
            this.mLeftAxisTransformer = new mjr(this.mViewPortHandler, this);
            this.mXAxisRenderer = new mjz(this.mViewPortHandler, getXAxis(), this.mLeftAxisTransformer);
        }
        this.n = arrayList2.size() - 1 == 0 ? 1 : arrayList2.size() - 1;
        XAxis xAxis = getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGridLineWidth(0.5f);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setTextColor(context.getResources().getColor(R.color._2131299244_res_0x7f090bac));
        xAxis.setTextSize(10.0f);
        xAxis.setDrawAxisLine(false);
        xAxis.setYOffset(10.0f);
        getAxisRight().setEnabled(false);
        YAxis axisLeft = getAxisLeft();
        axisLeft.setEnabled(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setStartAtZero(false);
        axisLeft.setSpaceTop(12.0f);
        axisLeft.setDrawGridLines(false);
        ArrayList<String> arrayList3 = new ArrayList<>(16);
        d(arrayList3, arrayList);
        d(xAxis, arrayList3);
        b(context, arrayList2, z, z2);
    }

    private static void d(XAxis xAxis, final List<String> list) {
        if (list == null) {
            return;
        }
        final int size = list.size();
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart.4
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                int i = (int) f;
                return (i >= 0 && i < size) ? (String) list.get(i) : "";
            }
        });
    }

    private void b(Context context, ArrayList<Entry> arrayList, boolean z, boolean z2) {
        ArrayList arrayList2 = new ArrayList(16);
        LineDataSet b = b(context, 1, arrayList, z, z2);
        if (b != null) {
            arrayList2.add(b);
        }
        LogUtil.a("dataSets", "size=", Integer.valueOf(arrayList2.size()));
        setData(new LineData(arrayList2));
        highlightValues(new Highlight[]{getHighlightByTouchPoint(919.50006f, 864.0f)});
    }

    private LineDataSet b(Context context, int i, List<Entry> list, boolean z, final boolean z2) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        LineDataSet lineDataSet = new LineDataSet(list, "");
        lineDataSet.disableDashedLine();
        if (z) {
            lineDataSet.setValueFormatter(new IValueFormatter() { // from class: com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart.3
                @Override // com.github.mikephil.charting.formatter.IValueFormatter
                public String getFormattedValue(float f, Entry entry, int i2, ViewPortHandler viewPortHandler) {
                    if (z2) {
                        return UnitUtil.e(f, 1, 2);
                    }
                    return gvv.a(f);
                }
            });
        } else {
            lineDataSet.setValueFormatter(new IValueFormatter() { // from class: com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart.1
                @Override // com.github.mikephil.charting.formatter.IValueFormatter
                public String getFormattedValue(float f, Entry entry, int i2, ViewPortHandler viewPortHandler) {
                    return UnitUtil.e(f, 1, 2);
                }
            });
        }
        return c(context, lineDataSet, i);
    }

    private LineDataSet c(Context context, LineDataSet lineDataSet, int i) {
        if (i == 1) {
            lineDataSet.setLineWidth(2.0f);
            lineDataSet.setColor(this.k);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleColor(this.k);
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setValueTextSize(10.0f);
            lineDataSet.setValueTextColor(context.getResources().getColor(R.color._2131299244_res_0x7f090bac));
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(context.getResources().getDrawable(R.drawable._2131431184_res_0x7f0b0f10));
        } else if (i == 2) {
            lineDataSet.setColor(this.h);
            lineDataSet.setLineWidth(3.0f);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleColor(this.h);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(10.0f);
            lineDataSet.setValueTextColor(this.h);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(context.getResources().getDrawable(R.drawable._2131431184_res_0x7f0b0f10));
        } else if (i == 3) {
            lineDataSet.setColor(this.j);
            lineDataSet.setLineWidth(1.0f);
            lineDataSet.enableDashedLine(10.0f, 10.0f, 0.0f);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleColor(this.j);
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setValueTextSize(10.0f);
            lineDataSet.setValueTextColor(context.getResources().getColor(R.color._2131299244_res_0x7f090bac));
        }
        return lineDataSet;
    }

    private void d(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        arrayList.addAll(arrayList2);
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        calculateLegendOffsets(this.l);
        float convertDpToPixel = Utils.convertDpToPixel(16.0f) + this.l.left;
        float convertDpToPixel2 = Utils.convertDpToPixel(50.0f) + this.l.top;
        float convertDpToPixel3 = Utils.convertDpToPixel(16.0f) + this.l.right;
        float convertDpToPixel4 = Utils.convertDpToPixel(50.0f) + this.l.bottom;
        if (this.mAxisLeft.needsOffset()) {
            convertDpToPixel += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
        }
        if (this.mAxisRight.needsOffset()) {
            convertDpToPixel3 += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
        }
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float yOffset = this.mXAxis.mLabelHeight + this.mXAxis.getYOffset();
            if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                convertDpToPixel4 += yOffset;
            } else {
                if (this.mXAxis.getPosition() != XAxis.XAxisPosition.TOP) {
                    if (this.mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                        convertDpToPixel4 += yOffset;
                    } else {
                        LogUtil.c("PLGACHIEVE_HwHealthAchieveReportLineChart", "calculateOffsets position is not matching");
                    }
                }
                convertDpToPixel2 += yOffset;
            }
        }
        float extraTopOffset = getExtraTopOffset();
        float extraRightOffset = getExtraRightOffset();
        float extraBottomOffset = getExtraBottomOffset();
        float extraLeftOffset = getExtraLeftOffset();
        float convertDpToPixel5 = Utils.convertDpToPixel(this.mMinOffset);
        this.mViewPortHandler.restrainViewPort(Math.max(convertDpToPixel5, convertDpToPixel + extraLeftOffset), Math.max(convertDpToPixel5, convertDpToPixel2 + extraTopOffset), Math.max(convertDpToPixel5, convertDpToPixel3 + extraRightOffset), Math.max(convertDpToPixel5, convertDpToPixel4 + extraBottomOffset));
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    private boolean cjg_(Bitmap bitmap, int i, int i2) {
        return bitmap != null && bitmap.getWidth() == i && bitmap.getHeight() == i2;
    }

    @Override // com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        LogUtil.a("PLGACHIEVE_HwHealthAchieveReportLineChart", "line onDraw");
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.c;
        Bitmap bitmap = weakReference != null ? weakReference.get() : null;
        if (!cjg_(bitmap, chartWidth, chartHeight)) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmap = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_8888);
            this.c = new WeakReference<>(bitmap);
            this.f = new Canvas(bitmap);
        }
        if (bitmap == null) {
            return;
        }
        bitmap.eraseColor(0);
        cji_(this.f);
        canvas.drawBitmap(this.c.get(), 0.0f, 0.0f, new Paint());
    }

    private void cji_(Canvas canvas) {
        if (this.b && this.mData != 0) {
            float[] fArr = {((LineData) this.mData).getXMax(), 0.0f};
            this.mLeftAxisTransformer.pointValuesToPixel(fArr);
            d(fArr[0]);
            c(fArr[0]);
            a();
        }
        if (this.mData == 0) {
            return;
        }
        calculateOffsets();
        if (this.mData == 0) {
            return;
        }
        drawGridBackground(canvas);
        cjh_(canvas);
        if (this.mAutoScaleMinMaxEnabled) {
            autoScale();
        }
        if (this.mAxisLeft.isEnabled()) {
            this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
        }
        if (this.mAxisRight.isEnabled()) {
            this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
        }
        if (this.mXAxis.isEnabled()) {
            this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
        }
        this.mXAxisRenderer.renderAxisLine(canvas);
        this.mAxisRendererLeft.renderAxisLine(canvas);
        this.mAxisRendererRight.renderAxisLine(canvas);
        this.mXAxisRenderer.renderGridLines(canvas);
        this.mAxisRendererLeft.renderGridLines(canvas);
        this.mAxisRendererRight.renderGridLines(canvas);
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
            this.mXAxisRenderer.renderLimitLines(canvas);
        }
        if (this.mAxisLeft.isEnabled() && this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
            this.mAxisRendererLeft.renderLimitLines(canvas);
        }
        if (this.mAxisRight.isEnabled() && this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
            this.mAxisRendererRight.renderLimitLines(canvas);
        }
        cjj_(canvas);
    }

    private void cjj_(Canvas canvas) {
        int save = canvas.save();
        canvas.clipRect(this.mViewPortHandler.getContentRect());
        this.mRenderer.drawData(canvas);
        if (valuesToHighlight()) {
            this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
        }
        canvas.restoreToCount(save);
        this.mRenderer.drawExtras(canvas);
        if (this.mXAxis.isEnabled() && !this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
            this.mXAxisRenderer.renderLimitLines(canvas);
        }
        if (this.mAxisLeft.isEnabled() && !this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
            this.mAxisRendererLeft.renderLimitLines(canvas);
        }
        if (this.mAxisRight.isEnabled() && !this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
            this.mAxisRendererRight.renderLimitLines(canvas);
        }
        this.mXAxisRenderer.renderAxisLabels(canvas);
        this.mAxisRendererLeft.renderAxisLabels(canvas);
        this.mAxisRendererRight.renderAxisLabels(canvas);
        if (isClipValuesToContentEnabled()) {
            int save2 = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            this.mRenderer.drawValues(canvas);
            canvas.restoreToCount(save2);
        } else {
            this.mRenderer.drawValues(canvas);
        }
        this.mLegendRenderer.renderLegend(canvas);
        drawDescription(canvas);
        drawMarkers(canvas);
    }

    public void setOnSingleTapListener(OnSingleTapListener onSingleTapListener) {
        this.m = onSingleTapListener;
    }

    private void cjh_(Canvas canvas) {
        GradientDrawable gradientDrawable = this.g;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setBounds(0, 0, (int) this.mViewPortHandler.getChartWidth(), (int) (this.mViewPortHandler.contentBottom() + this.mXAxis.getYOffset() + Utils.convertDpToPixel(40.0f)));
        this.g.draw(canvas);
    }

    private void d(int i, int i2) {
        this.g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i2});
    }

    public void b() {
        this.d.cjf_(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float a2 = HwHealthAchieveReportLineChart.this.d.a();
                HwHealthAchieveReportLineChart hwHealthAchieveReportLineChart = HwHealthAchieveReportLineChart.this;
                hwHealthAchieveReportLineChart.d(hwHealthAchieveReportLineChart.q + ((HwHealthAchieveReportLineChart.this.f8450a - HwHealthAchieveReportLineChart.this.q) * a2));
            }
        }, 500);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f) {
        d(f);
        this.i.e(f);
        invalidate();
    }

    private void d(double d) {
        if (this.mXAxisRenderer instanceof mjz) {
            ((mjz) this.mXAxisRenderer).c(d);
        }
    }

    private void c(float f) {
        if (this.mXAxisRenderer instanceof mjz) {
            ((mjz) this.mXAxisRenderer).c(f);
        }
    }
}

package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes9.dex */
public class pke extends nox {

    /* renamed from: a, reason: collision with root package name */
    private float f16165a;
    private DataInfos b;
    private int c;
    private int e;
    private Path g;
    private int h;
    private int k;
    private int l;
    private boolean m;
    private int n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private Paint x;

    private int e(float f) {
        if (f >= 90.0f) {
            return 1;
        }
        return f >= 70.0f ? 2 : 4;
    }

    public pke(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.n = 1;
        this.g = new Path();
        this.m = false;
        this.x = new Paint();
        this.r = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296511_res_0x7f0900ff);
        this.k = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296509_res_0x7f0900fd);
        this.c = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296505_res_0x7f0900f9);
        this.q = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.p = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.s = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        this.e = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131298145_res_0x7f090761);
        this.l = 0;
        this.h = 0;
        LogUtil.a("BloodOxygenLineChartRender", "BloodOxygenLineChartRender");
        g();
        this.b = dataInfos;
    }

    private void g() {
        this.x.setColor(this.e);
        this.x.setAntiAlias(true);
        this.x.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.x.setStyle(Paint.Style.STROKE);
    }

    private Set<Float> a(IHwHealthLineDataSet iHwHealthLineDataSet) {
        HashSet hashSet = new HashSet(16);
        List<HwHealthBaseEntry> acquireEntryVals = iHwHealthLineDataSet.acquireEntryVals();
        if (acquireEntryVals == null || this.o) {
            LogUtil.h("BloodOxygenLineChartRender", "getRemindSet baseEntries is null or is horizontal");
            return hashSet;
        }
        LogUtil.a("BloodOxygenLineChartRender", "drawLinear baseEntries.size = ", Integer.valueOf(acquireEntryVals.size()));
        for (HwHealthBaseEntry hwHealthBaseEntry : acquireEntryVals) {
            if (hwHealthBaseEntry instanceof HwHealthLineEntry) {
                HwHealthLineEntry hwHealthLineEntry = (HwHealthLineEntry) hwHealthBaseEntry;
                if ((hwHealthLineEntry.acquireModel() instanceof pka) && ((pka) hwHealthLineEntry.acquireModel()).b()) {
                    hashSet.add(Float.valueOf(hwHealthBaseEntry.getX()));
                }
            }
        }
        return hashSet;
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [com.github.mikephil.charting.data.Entry] */
    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            this.o = this.b == DataInfos.BloodOxygenDayHorizontal;
            float phaseY = this.mAnimator.getPhaseY();
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            T entryForIndex = iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min);
            ArrayList arrayList = new ArrayList(16);
            if (entryForIndex == 0) {
                LogUtil.h("BloodOxygenLineChartRender", "entryMin is null");
                return;
            }
            for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min; i++) {
                ?? entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i);
                if (entryForIndex2 != 0 && this.n != 0) {
                    arrayList.add(new PointF(entryForIndex2.getX(), entryForIndex2.getY() * phaseY));
                }
            }
            if (this.n == 0 || arrayList.size() <= 0) {
                return;
            }
            if (this.b == DataInfos.BloodOxygenDayDetail || this.b == DataInfos.BloodOxygenDayHorizontal) {
                dqm_(canvas2, arrayList, iHwHealthLineDataSet, a(iHwHealthLineDataSet));
            } else {
                LogUtil.h("BloodOxygenLineChartRender", "drawLinear other dataInfos");
            }
        }
    }

    private Paint dqp_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20.0f);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private Paint dqr_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private void dqm_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, Set<Float> set) {
        Paint dqp_ = dqp_();
        Paint dqr_ = dqr_();
        int size = list.size() * 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[list.size()];
        HashSet hashSet = new HashSet(list.size());
        float f = Float.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (PointF pointF : list) {
            int i4 = i + 1;
            fArr[i] = pointF.x;
            if (set.contains(Float.valueOf(pointF.x))) {
                hashSet.add(Integer.valueOf(i2));
            }
            if (pointF.y == 100.0f) {
                i += 2;
                fArr[i4] = pointF.y - 0.0f;
            } else if (pointF.y == 99.0f) {
                i += 2;
                fArr[i4] = pointF.y - 0.0f;
            } else if (pointF.y == 98.0f) {
                i += 2;
                fArr[i4] = pointF.y - 0.0f;
            } else {
                i += 2;
                fArr[i4] = pointF.y;
            }
            if (pointF.y < f) {
                f = pointF.y;
            }
            i2++;
            fArr2[i3] = e(pointF.y);
            i3++;
        }
        c(iHwHealthLineDataSet, fArr);
        PointF pointF2 = new PointF();
        if (a(pjm.c(f))) {
            return;
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            float f2 = fArr[i5] + this.t;
            float f3 = fArr[i5 + 1];
            LinearGradient dqs_ = dqs_(f2, f3, dqq_(dqp_, fArr2[i6]), new float[]{0.0f, 1.0f});
            dqr_.setShader(dqs_);
            canvas.drawRect(b(f2), this.mViewPortHandler.contentBottom(), f2, f3, dqr_);
            i5 += 2;
            pointF2.set(f2, f3);
            dqo_(canvas, hashSet, i6, pointF2, dqs_);
            float f4 = this.t;
            canvas.drawCircle(f2 - f4, f3, f4, dqp_);
            i6++;
            size = size;
            dqr_ = dqr_;
        }
    }

    private float b(float f) {
        return f - (this.t * 2);
    }

    private void c(IHwHealthLineDataSet iHwHealthLineDataSet, float[] fArr) {
        float[] fArr2 = {0.0f, 100.0f};
        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(fArr2);
        this.f16165a = fArr2[1];
        this.t = nrr.e(BaseApplication.getContext(), b());
    }

    private LinearGradient dqs_(float f, float f2, int[] iArr, float[] fArr) {
        return new LinearGradient(f - (this.t * 2), this.mViewPortHandler.contentBottom(), f, f2, iArr, fArr, Shader.TileMode.CLAMP);
    }

    private boolean a(float f) {
        float axisMinimum = (!(this.mChart instanceof HwHealthLineChart) || ((HwHealthLineChart) this.mChart).getAxisFirstParty() == null) ? 0.0f : ((HwHealthLineChart) this.mChart).getAxisFirstParty().getAxisMinimum();
        LogUtil.c("BloodOxygenLineChartRender", "isOutOfChart min = ", Float.valueOf(f), " axisMin = ", Float.valueOf(axisMinimum));
        return f < axisMinimum;
    }

    private void dqo_(Canvas canvas, Set<Integer> set, int i, PointF pointF, LinearGradient linearGradient) {
        if (!set.contains(Integer.valueOf(i)) || this.o) {
            return;
        }
        Path path = new Path();
        float sqrt = (float) Math.sqrt(3.0d);
        path.moveTo(pointF.x - (r1 * 2), this.f16165a - (this.t * sqrt));
        path.lineTo(pointF.x, this.f16165a - (this.t * sqrt));
        path.lineTo(pointF.x - this.t, this.f16165a);
        path.close();
        Paint paint = new Paint();
        paint.setColor(this.k);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        Paint dqr_ = dqr_();
        dqr_.setAlpha(20);
        dqr_.setShader(linearGradient);
        canvas.drawRect(pointF.x - (this.t * 2), this.f16165a, pointF.x, pointF.y, dqr_);
    }

    private float b() {
        return this.o ? 1.0f : 2.0f;
    }

    private int[] dqq_(Paint paint, float f) {
        int[] iArr = new int[2];
        if (f == 1.0f) {
            int i = this.c;
            iArr[0] = i;
            iArr[1] = i;
            paint.setColor(i);
        } else if (f == 2.0f) {
            int i2 = this.k;
            iArr[0] = i2;
            iArr[1] = i2;
            paint.setColor(i2);
        } else {
            int i3 = this.r;
            iArr[0] = i3;
            iArr[1] = i3;
            paint.setColor(i3);
            LogUtil.h("R_BloodOxygen_BloodOxygenLineChartRender", "grade no match");
        }
        return iArr;
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        LogUtil.a("BloodOxygenLineChartRender", "set.getHighlightLineWidth() = ", Float.valueOf(iLineScatterCandleRadarDataSet.getHighlightLineWidth()));
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(Color.argb(0, 0, 0, 0));
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.g.reset();
            this.g.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.g.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.g, this.mHighlightPaint);
        }
        if (z2) {
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(6.0f), this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(this.l);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(4.0f), this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.h);
        float f3 = this.t;
        canvas.drawCircle(f - f3, f2, f3, this.mHighlightPaint);
        canvas.drawRect(f - (r10 * 2), f2, f, this.mViewPortHandler.contentBottom(), this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length == 0 || lineData == null) {
            return;
        }
        this.o = this.b == DataInfos.BloodOxygenDayHorizontal;
        this.t = nrr.e(BaseApplication.getContext(), b());
        dqn_(lineData, canvas, highlightArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void dqn_(defpackage.now r15, android.graphics.Canvas r16, com.github.mikephil.charting.highlight.Highlight[] r17) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pke.dqn_(now, android.graphics.Canvas, com.github.mikephil.charting.highlight.Highlight[]):void");
    }

    private MPPointD c(List<IHwHealthLineDataSet> list, Entry entry, float f) {
        return this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(entry.getX(), f * this.mAnimator.getPhaseY());
    }

    private void dql_(List<IHwHealthLineDataSet> list, Highlight highlight, Canvas canvas, int i) {
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            List<T> entriesForXValue = iHwHealthLineDataSet.getEntriesForXValue(highlight.getX());
            if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                Entry entry = (Entry) entriesForXValue.get(0);
                MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                if (this.b == DataInfos.BloodOxygenDayDetail || this.b == DataInfos.BloodOxygenDayHorizontal) {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, false, i);
                } else {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    private void c(Entry entry) {
        LogUtil.a("BloodOxygenLineChartRender", "setHighLightDotColor");
        int e = e(entry.getY());
        if (e == 1) {
            this.l = this.c;
            this.h = this.s;
        } else if (e == 2) {
            this.l = this.k;
            this.h = this.p;
        } else {
            this.l = this.r;
            this.h = this.q;
            LogUtil.h("R_BloodOxygen_BloodOxygenLineChartRender", "high light grade no match");
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        LogUtil.a("BloodOxygenLineChartRender", "usePaintAsBackground");
        this.m = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        LogUtil.a("BloodOxygenLineChartRender", "isUsePaintAsBackground");
        return this.m;
    }
}

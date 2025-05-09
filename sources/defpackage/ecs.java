package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import health.compact.a.SystemProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class ecs extends nox {

    /* renamed from: a, reason: collision with root package name */
    private float f11955a;
    private int b;
    private DataInfos c;
    private int e;
    private int g;
    private Path h;
    private boolean k;
    private int l;
    private float m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private float r;
    private int s;
    private int t;
    private int v;
    private int w;
    private Paint x;
    private int y;

    public static int d(float f) {
        if (f <= 100.0f && f < 90.0f) {
            return f >= 80.0f ? 70 : 60;
        }
        return 85;
    }

    public ecs(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.p = 1;
        this.h = new Path();
        this.n = false;
        this.o = "tablet".equals(SystemProperties.b("ro.build.characteristics"));
        this.x = new Paint();
        this.q = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.l = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.e = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        this.y = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.w = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.v = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        this.b = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131298145_res_0x7f090761);
        this.s = 0;
        this.g = 0;
        LogUtil.a("BloodOxygenLineChartRender", "BloodOxygenLineChartRender");
        i();
        this.c = dataInfos;
    }

    private void i() {
        this.x.setColor(this.b);
        this.x.setAntiAlias(true);
        this.x.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.x.setStyle(Paint.Style.STROKE);
    }

    private Set<Float> b(IHwHealthLineDataSet iHwHealthLineDataSet) {
        HashSet hashSet = new HashSet(16);
        List<HwHealthBaseEntry> acquireEntryVals = iHwHealthLineDataSet.acquireEntryVals();
        if (acquireEntryVals == null || this.k) {
            LogUtil.h("BloodOxygenLineChartRender", "getRemindSet baseEntries is null or is horizontal");
            return hashSet;
        }
        LogUtil.a("BloodOxygenLineChartRender", "drawLinear baseEntries.size = ", Integer.valueOf(acquireEntryVals.size()));
        for (HwHealthBaseEntry hwHealthBaseEntry : acquireEntryVals) {
            if (hwHealthBaseEntry instanceof HwHealthLineEntry) {
                HwHealthLineEntry hwHealthLineEntry = (HwHealthLineEntry) hwHealthBaseEntry;
                if ((hwHealthLineEntry.acquireModel() instanceof ecm) && ((ecm) hwHealthLineEntry.acquireModel()).b()) {
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
            this.k = this.c == DataInfos.BloodOxygenDayHorizontal;
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
                if (entryForIndex2 != 0 && entryForIndex2.getY() != 0.0f && this.p != 0) {
                    arrayList.add(new PointF(entryForIndex2.getX(), entryForIndex2.getY() * phaseY));
                }
            }
            if (this.p == 0 || arrayList.size() <= 0) {
                return;
            }
            if (this.c == DataInfos.BloodOxygenDayDetail || this.c == DataInfos.BloodOxygenDayHorizontal) {
                aeY_(canvas2, arrayList, iHwHealthLineDataSet, b(iHwHealthLineDataSet));
            } else {
                LogUtil.h("BloodOxygenLineChartRender", "drawLinear other dataInfos");
            }
        }
    }

    private Paint afb_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20.0f);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(153);
        return paint;
    }

    private Paint afd_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(153);
        return paint;
    }

    private void aeY_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, Set<Float> set) {
        LinearGradient linearGradient;
        Paint paint;
        float[] fArr;
        float[] fArr2;
        Rect rect;
        Paint afb_ = afb_();
        Paint afd_ = afd_();
        int size = list.size() * 2;
        float[] fArr3 = new float[size];
        float[] fArr4 = new float[list.size()];
        HashSet hashSet = new HashSet(list.size());
        b(list, iHwHealthLineDataSet);
        Iterator<PointF> it = list.iterator();
        float f = Float.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            PointF next = it.next();
            int i4 = i + 1;
            fArr3[i] = next.x;
            Iterator<PointF> it2 = it;
            if (set.contains(Float.valueOf(next.x))) {
                hashSet.add(Integer.valueOf(i2));
            }
            if (next.y == 100.0f) {
                i += 2;
                fArr3[i4] = next.y - 0.0f;
            } else if (next.y == 99.0f) {
                i += 2;
                fArr3[i4] = next.y - 0.0f;
            } else if (next.y == 98.0f) {
                i += 2;
                fArr3[i4] = next.y - 0.0f;
            } else {
                i += 2;
                fArr3[i4] = next.y;
            }
            if (next.y < f) {
                f = next.y;
            }
            i2++;
            fArr4[i3] = a(next.y, true);
            i3++;
            it = it2;
        }
        a(iHwHealthLineDataSet, fArr3);
        PointF pointF = new PointF();
        ChartTouchHelper accessibilityHelper = ((HwHealthBaseBarLineChart) this.mChart).getAccessibilityHelper();
        if (accessibilityHelper != null) {
            accessibilityHelper.e().d();
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            float f2 = fArr3[i5] + this.t;
            int i7 = i5 / 2;
            float f3 = list.get(i7).y;
            int i8 = i5;
            int i9 = size;
            LinearGradient afe_ = afe_(f2, f3, afc_(afb_, fArr4[i6]), new float[]{0.0f, 1.0f});
            afd_.setShader(afe_);
            aeZ_(canvas, afd_, f2, f3);
            if (accessibilityHelper != null) {
                AbstractTouchNode a2 = accessibilityHelper.e().a(i7);
                if (this.o && this.k) {
                    linearGradient = afe_;
                    paint = afd_;
                    fArr = fArr3;
                    fArr2 = fArr4;
                    rect = new Rect((int) b(f2), (int) f3, (int) (f2 + 4.0f), (int) this.mViewPortHandler.contentBottom());
                } else {
                    linearGradient = afe_;
                    paint = afd_;
                    fArr = fArr3;
                    fArr2 = fArr4;
                    rect = new Rect((int) b(f2), (int) f3, (int) f2, (int) this.mViewPortHandler.contentBottom());
                }
                a2.setRect(rect);
                a2.setDescription(d(iHwHealthLineDataSet, list.get(i7).x));
            } else {
                linearGradient = afe_;
                paint = afd_;
                fArr = fArr3;
                fArr2 = fArr4;
            }
            pointF.set(f2, f3);
            afa_(canvas, hashSet, i6, pointF, linearGradient);
            i6++;
            afb_.setAlpha(153);
            afb_.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            aeX_(canvas, afb_, f2, f3);
            i5 = i8 + 2;
            size = i9;
            hashSet = hashSet;
            fArr3 = fArr;
            afd_ = paint;
            fArr4 = fArr2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String d(IHwHealthLineDataSet iHwHealthLineDataSet, float f) {
        String formattedValue;
        HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) iHwHealthLineDataSet.getEntryForXValue(f, 0.0f);
        if (hwHealthBaseEntry == null) {
            LogUtil.h("BloodOxygenLineChartRender", "entry is null");
            return "";
        }
        IAxisValueFormatter valueFormatter = ((HwHealthBaseScrollBarLineChart) this.mChart).getXAxis().getValueFormatter();
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            formattedValue = ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(hwHealthBaseEntry.getX(), this.mChart.getXAxis());
        } else {
            formattedValue = valueFormatter.getFormattedValue(hwHealthBaseEntry.getX(), this.mChart.getXAxis());
        }
        String h = nsf.h(R$string.IDS_mean_blood_oxygen);
        if (this.c == DataInfos.BloodOxygenDayDetail) {
            formattedValue = nsf.b(com.huawei.ui.commonui.R$string.IDS_two_parts, formattedValue, h);
        }
        return nsf.b(com.huawei.ui.commonui.R$string.IDS_two_parts, formattedValue, jed.b(hwHealthBaseEntry.getY(), 2, 0));
    }

    private void aeZ_(Canvas canvas, Paint paint, float f, float f2) {
        if (this.o && this.k) {
            canvas.drawRect(b(f), this.mViewPortHandler.contentBottom(), f + 4.0f, f2, paint);
        } else {
            canvas.drawRect(b(f), this.mViewPortHandler.contentBottom(), f, f2, paint);
        }
    }

    private void aeX_(Canvas canvas, Paint paint, float f, float f2) {
        if (this.o && this.k) {
            canvas.drawCircle((f + 2.0f) - this.t, f2, r0 + 2, paint);
        } else {
            float f3 = this.t;
            canvas.drawCircle(f - f3, f2, f3, paint);
        }
    }

    private void b(List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Integer[] c = ecl.c(iHwHealthLineDataSet.getYMin(), iHwHealthLineDataSet.getYMax());
        if (!koq.e(c, 0)) {
            LogUtil.a("BloodOxygenLineChartRender", "null args!");
            return;
        }
        int intValue = c[0].intValue();
        int intValue2 = c[1].intValue();
        float f = this.mChart.getContentRect().bottom;
        float e = this.mChart.getContentRect().top + nrr.e(BaseApplication.getContext(), e());
        float f2 = (f - e) / (intValue - intValue2);
        this.r = f2;
        this.m = e - (f2 * intValue2);
        for (PointF pointF : list) {
            pointF.set(pointF.x, (((int) pointF.y) * this.r) + this.m);
        }
    }

    private float b(float f) {
        return f - (this.t * 2);
    }

    private void a(IHwHealthLineDataSet iHwHealthLineDataSet, float[] fArr) {
        float[] fArr2 = {0.0f, 100.0f};
        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(fArr2);
        this.f11955a = fArr2[1];
        this.t = nrr.e(BaseApplication.getContext(), e());
    }

    private LinearGradient afe_(float f, float f2, int[] iArr, float[] fArr) {
        return new LinearGradient(f - (this.t * 2), this.mViewPortHandler.contentBottom(), f, f2, iArr, fArr, Shader.TileMode.CLAMP);
    }

    private boolean a(float f) {
        float axisMinimum = (!(this.mChart instanceof HwHealthLineChart) || ((HwHealthLineChart) this.mChart).getAxisFirstParty() == null) ? 0.0f : ((HwHealthLineChart) this.mChart).getAxisFirstParty().getAxisMinimum();
        LogUtil.c("BloodOxygenLineChartRender", "isOutOfChart min = ", Float.valueOf(f), " axisMin = ", Float.valueOf(axisMinimum));
        return f < axisMinimum;
    }

    private void afa_(Canvas canvas, Set<Integer> set, int i, PointF pointF, LinearGradient linearGradient) {
        if (!set.contains(Integer.valueOf(i)) || this.k) {
            return;
        }
        Path path = new Path();
        float sqrt = (float) Math.sqrt(3.0d);
        float f = this.mChart.getContentRect().top;
        float f2 = this.t;
        float f3 = f + f2 + (f2 * sqrt);
        path.moveTo(pointF.x - (r2 * 2), f3 - (this.t * sqrt));
        path.lineTo(pointF.x, f3 - (this.t * sqrt));
        path.lineTo(pointF.x - this.t, f3);
        path.close();
        Paint paint = new Paint();
        paint.setColor(this.l);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        Paint afd_ = afd_();
        afd_.setAlpha(20);
        afd_.setShader(linearGradient);
        canvas.drawRect(pointF.x - (r14 * 2), f3 - (this.t * sqrt), pointF.x, pointF.y, afd_);
    }

    private float e() {
        return this.k ? 0.2f : 2.0f;
    }

    private int[] afc_(Paint paint, float f) {
        int[] iArr = new int[2];
        if (f == 1.0f) {
            int i = this.e;
            iArr[0] = i;
            iArr[1] = i;
            paint.setColor(i);
        } else if (f == 2.0f) {
            int i2 = this.l;
            iArr[0] = i2;
            iArr[1] = i2;
            paint.setColor(i2);
        } else {
            int i3 = this.q;
            iArr[0] = i3;
            iArr[1] = i3;
            paint.setColor(i3);
            LogUtil.h("R_BloodOxygen_BloodOxygenLineChartRender", "grade no match");
        }
        return iArr;
    }

    private int a(float f, boolean z) {
        if (z && (this.c == DataInfos.BloodOxygenAltitudeDayDetail || this.c == DataInfos.BloodOxygenDayDetail || this.c == DataInfos.BloodOxygenDayHorizontal)) {
            f = (f - this.m) / this.r;
        }
        if (f > 89.0f) {
            return 1;
        }
        return f >= 70.0f ? 2 : 4;
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        LogUtil.a("BloodOxygenLineChartRender", "set.getHighlightLineWidth() = ", Float.valueOf(iLineScatterCandleRadarDataSet.getHighlightLineWidth()));
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(Color.argb(0, 0, 0, 0));
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.h.reset();
            this.h.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.h.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.h, this.mHighlightPaint);
        }
        if (z2) {
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(6.0f), this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(this.s);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(4.0f), this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.g);
        float f3 = this.t;
        canvas.drawCircle(f - f3, f2, f3, this.mHighlightPaint);
        canvas.drawRect(f - (r10 * 2), f2, f, this.mViewPortHandler.contentBottom(), this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now lineData = this.mChart.getLineData();
        if (c(highlightArr, lineData)) {
            return;
        }
        int i = 1;
        int i2 = 0;
        this.k = this.c == DataInfos.BloodOxygenDayHorizontal;
        this.t = nrr.e(BaseApplication.getContext(), e());
        int length = highlightArr.length;
        int i3 = 0;
        while (i3 < length) {
            Highlight highlight = highlightArr[i3];
            List<T> dataSets = lineData.getDataSets();
            if (!e((List<IHwHealthLineDataSet>) dataSets)) {
                int argb = Color.argb(i2, i2, i2, i2);
                if (dataSets.size() == i) {
                    List<T> entriesForXValue = ((IHwHealthLineDataSet) dataSets.get(i2)).getEntriesForXValue(highlight.getX());
                    if (!c((List<HwHealthBaseEntry>) entriesForXValue)) {
                        Entry entry = (Entry) entriesForXValue.get(i2);
                        if (entry.getY() == 0.0f) {
                            return;
                        }
                        c(entry);
                        Entry c = c(highlight, entry);
                        float e = e(c);
                        if (!a(d(c.getY()))) {
                            MPPointD b = b(dataSets, c, e);
                            float b2 = b(e, 0.0f);
                            b.x += this.t;
                            highlight.setDraw((float) b.x, (float) b.y);
                            aff_(canvas, dataSets, argb, b, b2);
                        }
                    }
                } else {
                    aeW_(dataSets, highlight, canvas, argb);
                }
                i3++;
                i = 1;
                i2 = 0;
            }
            i3++;
            i = 1;
            i2 = 0;
        }
    }

    private boolean c(List<HwHealthBaseEntry> list) {
        return list == null || list.size() == 0;
    }

    private boolean e(List<IHwHealthLineDataSet> list) {
        return list == null || list.size() == 0;
    }

    private boolean c(Highlight[] highlightArr, now nowVar) {
        return highlightArr == null || highlightArr.length <= 0 || nowVar == null;
    }

    private float b(float f, float f2) {
        float f3 = this.r;
        if (f3 == 0.0f) {
            return f2;
        }
        float f4 = this.m;
        return f4 != 0.0f ? (f * f3) + f4 : f2;
    }

    private Entry c(Highlight highlight, Entry entry) {
        return (this.n && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) ? new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue()) : entry;
    }

    private void aff_(Canvas canvas, List<IHwHealthLineDataSet> list, int i, MPPointD mPPointD, float f) {
        if (this.c == DataInfos.BloodOxygenDayDetail || this.c == DataInfos.BloodOxygenDayHorizontal) {
            dwf_(canvas, (float) mPPointD.x, f, list.get(0), true, false, i);
        } else {
            dwf_(canvas, (float) mPPointD.x, f, list.get(0), true, true, i);
        }
    }

    private float e(Entry entry) {
        float y;
        if (entry.getY() == 100.0f) {
            y = entry.getY();
        } else if (entry.getY() == 99.0f) {
            y = entry.getY();
        } else if (entry.getY() == 98.0f) {
            y = entry.getY();
        } else {
            return entry.getY();
        }
        return y - 0.0f;
    }

    private MPPointD b(List<IHwHealthLineDataSet> list, Entry entry, float f) {
        return this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(entry.getX(), f * this.mAnimator.getPhaseY());
    }

    private void aeW_(List<IHwHealthLineDataSet> list, Highlight highlight, Canvas canvas, int i) {
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            List<T> entriesForXValue = iHwHealthLineDataSet.getEntriesForXValue(highlight.getX());
            if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                Entry entry = (Entry) entriesForXValue.get(0);
                MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                if (this.c == DataInfos.BloodOxygenDayDetail || this.c == DataInfos.BloodOxygenDayHorizontal) {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, false, i);
                } else {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    private void c(Entry entry) {
        LogUtil.a("BloodOxygenLineChartRender", "setHighLightDotColor");
        int a2 = a(entry.getY(), false);
        if (a2 == 1) {
            this.s = this.e;
            this.g = this.v;
        } else if (a2 == 2) {
            this.s = this.l;
            this.g = this.w;
        } else {
            this.s = this.q;
            this.g = this.y;
            LogUtil.h("R_BloodOxygen_BloodOxygenLineChartRender", "high light grade no match");
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        LogUtil.a("BloodOxygenLineChartRender", "usePaintAsBackground");
        this.n = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        LogUtil.a("BloodOxygenLineChartRender", "isUsePaintAsBackground");
        return this.n;
    }
}

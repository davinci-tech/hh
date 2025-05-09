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
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;
import health.compact.a.SystemProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class eci extends nox {

    /* renamed from: a, reason: collision with root package name */
    private int f11952a;
    private Paint ab;
    private float ad;
    boolean b;
    private npj c;
    public DataInfos e;
    private int g;
    private float h;
    private Path k;
    private int l;
    private boolean m;
    private boolean n;
    private HwHealthRenderMode o;
    private int p;
    private int q;
    private int r;
    private float s;
    private float t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;

    private int a(float f) {
        if (f >= 90.0f) {
            return 1;
        }
        return f >= 70.0f ? 2 : 4;
    }

    public static int e(float f) {
        if (f <= 100.0f && f < 90.0f) {
            return f >= 80.0f ? 70 : 60;
        }
        return 85;
    }

    public eci(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.r = 1;
        this.k = new Path();
        this.m = false;
        this.b = "tablet".equals(SystemProperties.b("ro.build.characteristics"));
        this.ab = new Paint();
        this.x = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.q = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.f11952a = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        this.v = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296512_res_0x7f090100);
        this.w = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296510_res_0x7f0900fe);
        this.y = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296506_res_0x7f0900fa);
        this.g = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131298145_res_0x7f090761);
        this.p = 0;
        this.l = 0;
        this.o = null;
        this.c = null;
        this.t = 0.0f;
        this.s = 0.0f;
        LogUtil.a("BloodOxygenLineChartRender", "BloodOxygenLineChartRender");
        i();
        this.e = dataInfos;
        if (this.mChart instanceof HwHealthBaseBarLineChart) {
            this.o = new HwHealthRenderMode((HwHealthBaseBarLineChart) this.mChart, this.mViewPortHandler);
            this.c = new nph(this.o, this.e);
        }
    }

    private void i() {
        this.ab.setColor(this.g);
        this.ab.setAntiAlias(true);
        this.ab.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.ab.setStyle(Paint.Style.STROKE);
    }

    private Set<Float> b(IHwHealthLineDataSet iHwHealthLineDataSet) {
        HashSet hashSet = new HashSet(16);
        List<HwHealthBaseEntry> acquireEntryVals = iHwHealthLineDataSet.acquireEntryVals();
        if (acquireEntryVals == null || this.n) {
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

    /* JADX WARN: Type inference failed for: r5v5, types: [com.github.mikephil.charting.data.Entry] */
    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        ?? entryForIndex;
        if (iHwHealthLineDataSet.isDataCalculated()) {
            if (this.e == DataInfos.BloodOxygenAltitudeDayDetail || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal) {
                aem_(canvas, iHwHealthLineDataSet);
            }
            this.n = this.e == DataInfos.BloodOxygenDayHorizontal || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal;
            float phaseY = this.mAnimator.getPhaseY();
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            T entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min);
            ArrayList arrayList = new ArrayList(16);
            if (entryForIndex2 == 0) {
                LogUtil.h("BloodOxygenLineChartRender", "entryMin is null");
                return;
            }
            for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min + 1; i++) {
                if (i < iHwHealthLineDataSet.getEntryCount() && (entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i)) != 0 && entryForIndex.getY() != 0.0f) {
                    if (entryForIndex.getX() <= this.mChart.getHighestVisibleX() && this.r != 0) {
                        arrayList.add(new PointF(entryForIndex.getX(), entryForIndex.getY() * phaseY));
                    }
                }
            }
            if (this.r == 0 || arrayList.size() <= 0) {
                return;
            }
            if (this.e == DataInfos.BloodOxygenDayDetail || this.e == DataInfos.BloodOxygenDayHorizontal || this.e == DataInfos.BloodOxygenAltitudeDayDetail || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal) {
                aep_(canvas2, arrayList, iHwHealthLineDataSet, b(iHwHealthLineDataSet));
            } else {
                LogUtil.h("BloodOxygenLineChartRender", "drawLinear other dataInfos");
            }
        }
    }

    private void aem_(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (!(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            LogUtil.h("BloodOxygenLineChartRender", "drawLinear !(dataSetArg instanceof  HwHealthLineDataSet)");
            return;
        }
        HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) iHwHealthLineDataSet;
        hwHealthLineDataSet.setColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296501_res_0x7f0900f5));
        hwHealthLineDataSet.b(Color.argb(102, 0, 199, a.A), Color.argb(0, 0, 199, a.A), true);
        hwHealthLineDataSet.setFillAlpha(51);
        Canvas canvas2 = this.mBitmapCanvas;
        if (hwHealthLineDataSet.isDataCalculated()) {
            this.o.d(hwHealthLineDataSet);
            this.o.d();
            HwHealthRenderMode.IRenderMode a2 = this.o.a();
            nnj axisDataRenderArg = this.mChart.getAxisDataRenderArg(hwHealthLineDataSet.getAxisDependencyExt());
            if (axisDataRenderArg != null && axisDataRenderArg.a()) {
                if (!hwHealthLineDataSet.h()) {
                    throw new RuntimeException("drawLadderLinear needs displayIntervalSeted");
                }
                a2.setLadderMode(hwHealthLineDataSet.a());
            } else {
                a2.setStandMode();
            }
            if (this.mChart instanceof HwHealthBaseBarLineChart) {
                IHwHealthDatasContainer cacheDataContainer = hwHealthLineDataSet.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
                if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                    this.c.cDs_(canvas2, (IHwHealthLineDatasContainer) cacheDataContainer, this.mChart.getTransformer(hwHealthLineDataSet.getAxisDependencyExt()));
                }
            }
        }
    }

    public void aeD_(Canvas canvas, Highlight[] highlightArr) {
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            if (highlight != null) {
                List<IHwHealthLineDataSet> dataSets = lineData.getDataSets();
                if (!koq.b(dataSets)) {
                    aez_(canvas, highlight, dataSets, Color.argb(0, 0, 0, 0));
                }
            }
        }
    }

    private void aez_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i) {
        if (list.size() == 1) {
            aeB_(canvas, highlight, list, i);
        } else {
            aeA_(canvas, highlight, list, i);
        }
    }

    private void aeA_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i) {
        MPPointD pixelForValues = this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(highlight.getX(), 0.0f);
        aeC_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, list.get(0), true, false, i);
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            if (!(this.mChart instanceof HwHealthBaseBarLineChart)) {
                return;
            }
            IHwHealthDatasContainer cacheDataContainer = iHwHealthLineDataSet.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
            if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                List<HwHealthBaseEntry> searchEntryForXValue = ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(highlight.getX());
                if (!koq.b(searchEntryForXValue)) {
                    HwHealthBaseEntry hwHealthBaseEntry = searchEntryForXValue.get(0);
                    MPPointD pixelForValues2 = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues2.x, (float) pixelForValues2.y);
                    aeC_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    private void aeB_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i) {
        HwHealthBaseEntry hwHealthBaseEntry;
        boolean z;
        ecm ecmVar;
        List<HwHealthBaseEntry> b = b(highlight, list);
        if (b == null) {
            return;
        }
        HwHealthBaseEntry hwHealthBaseEntry2 = b.get(0);
        List<Entry> arrayList = new ArrayList();
        if (list.get(0) instanceof HwHealthBaseBarLineDataSet) {
            arrayList = ((HwHealthBaseBarLineDataSet) list.get(0)).getValues();
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        for (Entry entry : arrayList) {
            if ((entry.getData() instanceof ecm) && (ecmVar = (ecm) entry.getData()) != null && ecmVar.d() != Integer.MAX_VALUE) {
                int d = ecmVar.d();
                if (d > i2) {
                    i2 = d;
                }
                if (d < i3) {
                    i3 = d;
                }
            }
        }
        int i4 = i3 % 50;
        int i5 = i4 == 0 ? i3 - 50 : i3 - i4;
        int i6 = i2 % 50;
        int i7 = i2 + 50;
        if (i6 != 0) {
            i7 -= i6;
        }
        float f = (i7 - i5) / 20.5f;
        float f2 = i5;
        if ((this.o.a() instanceof HwHealthRenderMode.a) && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
            z = false;
            hwHealthBaseEntry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
        } else {
            hwHealthBaseEntry = hwHealthBaseEntry2;
            z = true;
        }
        aen_(canvas, highlight, list, i, hwHealthBaseEntry, f, f2 - (80.5f * f), z);
    }

    private void aen_(Canvas canvas, Highlight highlight, List<IHwHealthLineDataSet> list, int i, Entry entry, float f, float f2, boolean z) {
        if (entry.getData() instanceof ecm) {
            if (((ecm) entry.getData()).d() == Integer.MAX_VALUE) {
                return;
            }
            MPPointD pixelForValues = this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(entry.getX(), ((r1.d() * this.mAnimator.getPhaseY()) - f2) / f);
            highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
            aeC_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, list.get(0), true, z, i);
        }
    }

    private List<HwHealthBaseEntry> b(Highlight highlight, List<IHwHealthLineDataSet> list) {
        if (!(this.mChart instanceof HwHealthBaseBarLineChart)) {
            return null;
        }
        IHwHealthDatasContainer cacheDataContainer = list.get(0).cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
        if (!(cacheDataContainer instanceof eca)) {
            return null;
        }
        List<HwHealthBaseEntry> a2 = ((eca) cacheDataContainer).a(highlight.getX());
        if (koq.b(a2)) {
            return null;
        }
        return a2;
    }

    protected void aeC_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.k.reset();
            this.k.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.k.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.k, this.mHighlightPaint);
        }
        if (z2) {
            float convertDpToPixel = Utils.convertDpToPixel(4.0f);
            float convertDpToPixel2 = Utils.convertDpToPixel(6.0f);
            if (this.mChart instanceof HwHealthCombinedChart) {
                if (j() != 0.0f) {
                    convertDpToPixel = j();
                }
                convertDpToPixel2 = 1.5f * convertDpToPixel;
            }
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(nrn.d(R$color.colorBackground));
            canvas.drawCircle(f, f2, convertDpToPixel2, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296501_res_0x7f0900f5));
            canvas.drawCircle(f, f2, convertDpToPixel, this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private float j() {
        List<T> dataSets = this.mChart.getData().getDataSets();
        if (!koq.c(dataSets) || !(dataSets.get(0) instanceof HwHealthBarDataSet) || ((HwHealthCombinedChart) this.mChart).getBarData() == null) {
            return 0.0f;
        }
        float[] fArr = {0.0f, 0.0f, ((HwHealthCombinedChart) this.mChart).getBarData().d(), 0.0f};
        this.mChart.getTransformer(((HwHealthCombinedChart) this.mChart).getAxisFirstParty().e()).pointValuesToPixel(fArr);
        return (fArr[2] - fArr[0]) / 2.0f;
    }

    private Paint aeu_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20.0f);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(153);
        return paint;
    }

    private Paint aex_() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(153);
        return paint;
    }

    private void aep_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, Set<Float> set) {
        List<PointF> list2 = list;
        Paint aeu_ = aeu_();
        Paint aex_ = aex_();
        int size = list.size() * 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[list.size()];
        HashSet hashSet = new HashSet(list.size());
        e(list2, iHwHealthLineDataSet, fArr2);
        Iterator<PointF> it = list.iterator();
        float f = Float.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            PointF next = it.next();
            int i3 = i + 1;
            fArr[i] = next.x;
            Iterator<PointF> it2 = it;
            if (set.contains(Float.valueOf(next.x))) {
                hashSet.add(Integer.valueOf(i2));
            }
            if (next.y == 100.0f) {
                i += 2;
                fArr[i3] = next.y - 0.0f;
            } else if (next.y == 99.0f) {
                i += 2;
                fArr[i3] = next.y - 0.0f;
            } else if (next.y == 98.0f) {
                i += 2;
                fArr[i3] = next.y - 0.0f;
            } else {
                i += 2;
                fArr[i3] = next.y;
            }
            if (next.y < f) {
                f = next.y;
            }
            i2++;
            it = it2;
        }
        a(iHwHealthLineDataSet, fArr);
        PointF pointF = new PointF();
        if (c(e(f))) {
            return;
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < size) {
            float f2 = fArr[i4] + this.u;
            float f3 = list2.get(i4 / 2).y;
            if (Float.isNaN(f3)) {
                return;
            }
            LinearGradient aey_ = aey_(f2, f3, aev_(aeu_, fArr2[i5]), new float[]{0.0f, 1.0f});
            aex_.setShader(aey_);
            aes_(canvas, aex_, f2, f3);
            pointF.set(f2, f3);
            aet_(canvas, hashSet, i5, pointF, aey_);
            aeu_.setAlpha(153);
            aeu_.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            aeo_(canvas, aeu_, f2, f3);
            i5++;
            i4 += 2;
            list2 = list;
            aex_ = aex_;
        }
    }

    private void aes_(Canvas canvas, Paint paint, float f, float f2) {
        if (this.b && this.n) {
            canvas.drawRect(b(f), this.mViewPortHandler.contentBottom(), f + 4.0f, f2, paint);
        } else {
            canvas.drawRect(b(f), this.mViewPortHandler.contentBottom(), f, f2, paint);
        }
    }

    private void aeo_(Canvas canvas, Paint paint, float f, float f2) {
        if (this.b && this.n) {
            canvas.drawCircle((f + 2.0f) - this.u, f2, r0 + 2, paint);
        } else {
            float f3 = this.u;
            canvas.drawCircle(f - f3, f2, f3, paint);
        }
    }

    private void e(List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, float[] fArr) {
        Integer[] c = ecl.c(iHwHealthLineDataSet.getYMin(), iHwHealthLineDataSet.getYMax());
        int i = 0;
        if (!koq.e(c, 0)) {
            LogUtil.a("BloodOxygenLineChartRender", "null args!");
            return;
        }
        int intValue = c[0].intValue();
        int intValue2 = c[1].intValue();
        float f = (this.mChart.getContentRect().bottom - this.mChart.getContentRect().top) / 4.0f;
        float f2 = this.mChart.getContentRect().bottom;
        float f3 = this.mChart.getContentRect().bottom - (f * 2.0f);
        this.ad = f3;
        float f4 = (f2 - f3) / (intValue - intValue2);
        this.t = f4;
        this.s = f3 - (f4 * intValue2);
        for (PointF pointF : list) {
            fArr[i] = a(pointF.y);
            pointF.set(pointF.x, (((int) pointF.y) * this.t) + this.s);
            i++;
        }
    }

    private float b(float f) {
        return f - (this.u * 2);
    }

    private void a(IHwHealthLineDataSet iHwHealthLineDataSet, float[] fArr) {
        float[] fArr2 = {0.0f, 100.0f};
        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(fArr);
        transformer.pointValuesToPixel(fArr2);
        this.h = fArr2[1];
        this.u = nrr.e(BaseApplication.getContext(), e());
    }

    private LinearGradient aey_(float f, float f2, int[] iArr, float[] fArr) {
        return new LinearGradient(f - (this.u * 2), this.mViewPortHandler.contentBottom(), f, f2, iArr, fArr, Shader.TileMode.CLAMP);
    }

    private boolean c(float f) {
        float axisMinimum = (!(this.mChart instanceof HwHealthLineChart) || ((HwHealthLineChart) this.mChart).getAxisFirstParty() == null) ? 0.0f : ((HwHealthLineChart) this.mChart).getAxisFirstParty().getAxisMinimum();
        LogUtil.c("BloodOxygenLineChartRender", "isOutOfChart min = ", Float.valueOf(f), " axisMin = ", Float.valueOf(axisMinimum));
        return f < axisMinimum;
    }

    private void aet_(Canvas canvas, Set<Integer> set, int i, PointF pointF, LinearGradient linearGradient) {
        if (!set.contains(Integer.valueOf(i)) || this.n) {
            return;
        }
        Path path = new Path();
        float sqrt = (float) Math.sqrt(3.0d);
        float f = this.ad + (this.u * sqrt);
        path.moveTo(pointF.x - (r2 * 2), f - (this.u * sqrt));
        path.lineTo(pointF.x, f - (this.u * sqrt));
        path.lineTo(pointF.x - this.u, f);
        path.close();
        Paint paint = new Paint();
        paint.setColor(this.q);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        Paint aex_ = aex_();
        aex_.setAlpha(20);
        aex_.setShader(linearGradient);
        canvas.drawRect(pointF.x - (r14 * 2), f - (this.u * sqrt), pointF.x, pointF.y, aex_);
    }

    private float e() {
        return (this.e == DataInfos.BloodOxygenDayHorizontal || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal) ? 0.2f : 2.0f;
    }

    private int[] aev_(Paint paint, float f) {
        int[] iArr = new int[2];
        if (f == 1.0f) {
            int i = this.f11952a;
            iArr[0] = i;
            iArr[1] = i;
            paint.setColor(i);
        } else if (f == 2.0f) {
            int i2 = this.q;
            iArr[0] = i2;
            iArr[1] = i2;
            paint.setColor(i2);
        } else {
            int i3 = this.x;
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
            this.k.reset();
            this.k.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.k.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.k, this.mHighlightPaint);
        }
        if (z2) {
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(6.0f), this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(this.p);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(4.0f), this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.l);
        float f3 = this.u;
        canvas.drawCircle(f - f3, f2, f3, this.mHighlightPaint);
        canvas.drawRect(f - (r10 * 2), f2, f, this.mViewPortHandler.contentBottom(), this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now aew_ = aew_(canvas, highlightArr);
        if (aew_ == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = aew_.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0) {
                int argb = Color.argb(0, 0, 0, 0);
                if (dataSets.size() == 1) {
                    aeq_(canvas, highlight, dataSets, argb);
                } else {
                    ael_(dataSets, highlight, canvas, argb);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void aeq_(android.graphics.Canvas r11, com.github.mikephil.charting.highlight.Highlight r12, java.util.List<com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet> r13, int r14) {
        /*
            r10 = this;
            com.github.mikephil.charting.data.Entry r0 = r10.a(r12, r13)
            if (r0 != 0) goto L7
            return
        L7:
            r10.d(r0)
            boolean r1 = r10.m
            if (r1 == 0) goto L2a
            com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider r1 = r10.mChart
            com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart r1 = (com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart) r1
            boolean r1 = r1.isManualReferenceLineEnabled()
            if (r1 == 0) goto L2a
            float r0 = r12.getX()
            com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider r1 = r10.mChart
            com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart r1 = (com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart) r1
            com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry r2 = new com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry
            float r1 = r1.getManualReferenceLineValue()
            r2.<init>(r0, r1)
            r0 = r2
        L2a:
            float r1 = r0.getY()
            r2 = 1120403456(0x42c80000, float:100.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r2 = 0
            if (r1 != 0) goto L3a
            float r1 = r0.getY()
            goto L57
        L3a:
            float r1 = r0.getY()
            r3 = 1120272384(0x42c60000, float:99.0)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L49
            float r1 = r0.getY()
            goto L57
        L49:
            float r1 = r0.getY()
            r3 = 1120141312(0x42c40000, float:98.0)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L59
            float r1 = r0.getY()
        L57:
            float r1 = r1 - r2
            goto L5d
        L59:
            float r1 = r0.getY()
        L5d:
            float r3 = r0.getY()
            int r3 = e(r3)
            float r3 = (float) r3
            boolean r3 = r10.c(r3)
            if (r3 == 0) goto L6d
            return
        L6d:
            com.github.mikephil.charting.utils.MPPointD r8 = r10.b(r13, r0, r1)
            float r0 = r10.t
            int r3 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r3 == 0) goto L80
            float r3 = r10.s
            int r4 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r4 == 0) goto L80
            float r1 = r1 * r0
            float r2 = r1 + r3
        L80:
            r9 = r2
            double r0 = r8.x
            int r2 = r10.u
            double r2 = (double) r2
            double r0 = r0 + r2
            r8.x = r0
            double r0 = r8.x
            float r0 = (float) r0
            double r1 = r8.y
            float r1 = (float) r1
            r12.setDraw(r0, r1)
            r4 = r10
            r5 = r11
            r6 = r13
            r7 = r14
            r4.aer_(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eci.aeq_(android.graphics.Canvas, com.github.mikephil.charting.highlight.Highlight, java.util.List, int):void");
    }

    private void aer_(Canvas canvas, List<IHwHealthLineDataSet> list, int i, MPPointD mPPointD, float f) {
        if (this.e == DataInfos.BloodOxygenDayDetail || this.e == DataInfos.BloodOxygenDayHorizontal || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal) {
            dwf_(canvas, (float) mPPointD.x, f, list.get(0), true, false, i);
        } else if (this.e == DataInfos.BloodOxygenAltitudeDayDetail) {
            dwf_(canvas, (float) mPPointD.x, f, list.get(0), true, false, i);
        } else {
            dwf_(canvas, (float) mPPointD.x, (float) mPPointD.y, list.get(0), true, true, i);
        }
    }

    private Entry a(Highlight highlight, List<IHwHealthLineDataSet> list) {
        List<T> entriesForXValue = list.get(0).getEntriesForXValue(highlight.getX());
        if (entriesForXValue == 0 || entriesForXValue.size() == 0) {
            return null;
        }
        Entry entry = new Entry();
        boolean z = true;
        for (T t : entriesForXValue) {
            if (t.getY() != 0.0f) {
                z = false;
                entry = t;
            }
        }
        if (z) {
            return null;
        }
        return entry;
    }

    private now aew_(Canvas canvas, Highlight[] highlightArr) {
        if (this.e == DataInfos.BloodOxygenAltitudeDayDetail || this.e == DataInfos.BloodOxygenAltitudeDayHorizontal) {
            aeD_(canvas, highlightArr);
        }
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null) {
            return null;
        }
        this.n = this.e == DataInfos.BloodOxygenDayHorizontal;
        this.u = nrr.e(BaseApplication.getContext(), e());
        return lineData;
    }

    private MPPointD b(List<IHwHealthLineDataSet> list, Entry entry, float f) {
        return this.mChart.getTransformer(list.get(0).getAxisDependencyExt()).getPixelForValues(entry.getX(), f * this.mAnimator.getPhaseY());
    }

    private void ael_(List<IHwHealthLineDataSet> list, Highlight highlight, Canvas canvas, int i) {
        for (IHwHealthLineDataSet iHwHealthLineDataSet : list) {
            List<T> entriesForXValue = iHwHealthLineDataSet.getEntriesForXValue(highlight.getX());
            if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                Entry entry = (Entry) entriesForXValue.get(0);
                MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                if (this.e == DataInfos.BloodOxygenDayDetail || this.e == DataInfos.BloodOxygenDayHorizontal) {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, false, i);
                } else {
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, true, i);
                }
            }
        }
    }

    private void d(Entry entry) {
        LogUtil.a("BloodOxygenLineChartRender", "setHighLightDotColor");
        int a2 = a(entry.getY());
        if (a2 == 1) {
            this.p = this.f11952a;
            this.l = this.y;
        } else if (a2 == 2) {
            this.p = this.q;
            this.l = this.w;
        } else {
            this.p = this.x;
            this.l = this.v;
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

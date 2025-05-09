package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class nnb extends BarLineScatterCandleBubbleRenderer implements IHwHealthDataRender {

    /* renamed from: a, reason: collision with root package name */
    protected not[] f15395a;
    protected Paint b;
    protected RectF c;
    protected RectF d;
    public HwHealthBarDataProvider e;
    private boolean f;
    private RectF g;
    private boolean h;
    private boolean i;
    protected Paint j;

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return false;
    }

    public nnb(HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.d = new RectF();
        this.c = new RectF();
        this.h = false;
        this.f = true;
        this.i = true;
        this.g = new RectF();
        if (hwHealthBarDataProvider == null) {
            LogUtil.h("HwHealthBarChartRenderer", "chart == null");
            return;
        }
        this.e = hwHealthBarDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.b = paint2;
        paint2.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("HwHealthBarChartRenderer", "initBuffers() barData is null!");
            return;
        }
        this.f15395a = new not[barData.getDataSetCount()];
        for (int i = 0; i < this.f15395a.length; i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            this.f15395a[i] = new not(iHwHealthBarDataSet.getEntryCount() * 4 * (iHwHealthBarDataSet.isStacked() ? iHwHealthBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iHwHealthBarDataSet.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("HwHealthBarChartRenderer", "drawData() barData is null!");
            return;
        }
        ArrayList<IHwHealthBarDataSet> arrayList = new ArrayList();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            if (iHwHealthBarDataSet.isVisible()) {
                arrayList.add(iHwHealthBarDataSet);
            }
        }
        if (arrayList.size() < 2) {
            for (IHwHealthBarDataSet iHwHealthBarDataSet2 : arrayList) {
                dGJ_(canvas, iHwHealthBarDataSet2, barData.getDataSets().indexOf(iHwHealthBarDataSet2));
            }
            return;
        }
        usePaintAsBackground(true);
        dGJ_(canvas, (IHwHealthBarDataSet) arrayList.get(0), barData.getDataSets().indexOf(arrayList.get(0)));
        usePaintAsBackground(false);
        for (int i2 = 1; i2 < arrayList.size(); i2++) {
            dGJ_(canvas, (IHwHealthBarDataSet) arrayList.get(i2), i2);
        }
    }

    protected void dGJ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, int i) {
        if (canvas == null || iHwHealthBarDataSet == null) {
            LogUtil.h("HwHealthBarChartRenderer", "canvas == null||dataSet ==null");
            return;
        }
        this.b.setColor(iHwHealthBarDataSet.getBarBorderColor());
        this.b.setStrokeWidth(Utils.convertDpToPixel(iHwHealthBarDataSet.getBarBorderWidth()));
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.h("HwHealthBarChartRenderer", "drawDataSet() barData is null!");
            return;
        }
        HwHealthTransformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
        float d = IHwHealthBarDataSet.BarWidthMode.DEFAULT_WIDTH.equals(iHwHealthBarDataSet.getBarDrawWidthMode()) ? barData.d() : iHwHealthBarDataSet.getBarDrawWidth(transformer);
        float phaseX = this.mAnimator.getPhaseX();
        cBp_(canvas, iHwHealthBarDataSet, transformer, d / 2.0f, phaseX);
        not[] notVarArr = this.f15395a;
        if (notVarArr == null || notVarArr.length == 0) {
            LogUtil.h("HwHealthBarChartRenderer", "drawDataSet mBarBuffers == null || mBarBuffers.length == 0");
            return;
        }
        not notVar = notVarArr.length > i ? notVarArr[i] : null;
        if (notVar == null) {
            LogUtil.b("HwHealthBarChartRenderer", "drawData() buffer is null !");
            return;
        }
        notVar.setPhases(phaseX, this.mAnimator.getPhaseY());
        notVar.setDataSet(i);
        cBs_(canvas, iHwHealthBarDataSet, transformer, notVar, d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cBp_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, Transformer transformer, float f, float f2) {
        if (this.e.isDrawBarShadowEnabled()) {
            this.j.setColor(iHwHealthBarDataSet.getBarShadowColor());
            int min = Math.min((int) Math.ceil(iHwHealthBarDataSet.getEntryCount() * f2), iHwHealthBarDataSet.getEntryCount());
            for (int i = 0; i < min; i++) {
                float x = ((HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i)).getX();
                this.g.left = x - f;
                this.g.right = x + f;
                transformer.rectValueToPixel(this.g);
                if (this.mViewPortHandler.isInBoundsLeft(this.g.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.g.left)) {
                        return;
                    }
                    this.g.top = this.mViewPortHandler.contentTop();
                    this.g.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.g, this.j);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cBs_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, Transformer transformer, not notVar, float f) {
        ChartTouchHelper chartTouchHelper;
        notVar.setInverted(false);
        notVar.setBarWidth(f);
        notVar.b(iHwHealthBarDataSet);
        transformer.pointValuesToPixel(notVar.buffer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(b(iHwHealthBarDataSet));
        }
        BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider = this.e;
        if (barLineScatterCandleBubbleDataProvider instanceof HwHealthBaseScrollBarLineChart) {
            chartTouchHelper = ((HwHealthBaseScrollBarLineChart) barLineScatterCandleBubbleDataProvider).getAccessibilityHelper();
            if (chartTouchHelper != null) {
                chartTouchHelper.e().d();
            }
        } else {
            chartTouchHelper = null;
        }
        for (int i = 0; i < notVar.size(); i += 4) {
            float f2 = notVar.buffer[i];
            float f3 = notVar.buffer[i + 2];
            float f4 = f3 - f2;
            if (this.mViewPortHandler.isInBoundsX((f2 + f3) / 2.0f)) {
                float f5 = this.mViewPortHandler.getContentRect().left;
                float f6 = this.mViewPortHandler.getContentRect().right;
                if (!this.mViewPortHandler.isInBoundsLeft(f2)) {
                    f3 = f5 + f4;
                    f2 = f5;
                }
                if (this.mViewPortHandler.isInBoundsRight(f3)) {
                    f6 = f3;
                } else {
                    f2 = f6 - f4;
                }
                if (!z || HwHealthBarDataSet.DrawColorMode.DATA_COLOR.equals(iHwHealthBarDataSet.getDrawColorMode())) {
                    this.mRenderPaint.setColor(iHwHealthBarDataSet.getColor(i / 4));
                }
                this.c.set(f2, notVar.buffer[i + 1], f6, notVar.buffer[i + 3]);
                cBo_(canvas, this.c, this.mRenderPaint, transformer);
                if (chartTouchHelper != null) {
                    int i2 = i / 4;
                    AbstractTouchNode a2 = chartTouchHelper.e().a(i2);
                    Rect rect = new Rect();
                    this.c.round(rect);
                    a2.setRect(rect);
                    a(a2, (HwHealthBaseScrollBarLineChart) this.e, (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i2), (HwEntrys.MarkerViewFormatProvider) iHwHealthBarDataSet);
                }
            }
        }
    }

    private void a(AbstractTouchNode abstractTouchNode, HwHealthBaseScrollBarLineChart<?> hwHealthBaseScrollBarLineChart, HwHealthBarEntry hwHealthBarEntry, HwEntrys.MarkerViewFormatProvider markerViewFormatProvider) {
        String formattedValue;
        String str;
        IAxisValueFormatter valueFormatter = hwHealthBaseScrollBarLineChart.getXAxis().getValueFormatter();
        Object parseTool = hwHealthBaseScrollBarLineChart.getParseTool();
        if (abstractTouchNode == null || valueFormatter == null || parseTool == null) {
            ReleaseLogUtil.d("HwHealthBarChartRenderer", "cant setAccessibilityDescription,", abstractTouchNode, valueFormatter, parseTool);
            return;
        }
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            formattedValue = ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(hwHealthBarEntry.getX(), hwHealthBaseScrollBarLineChart.getXAxis());
        } else {
            formattedValue = valueFormatter.getFormattedValue(hwHealthBarEntry.getX(), hwHealthBaseScrollBarLineChart.getXAxis());
        }
        try {
            str = (String) Class.forName("com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView").getDeclaredMethod("parse", HwHealthBaseEntry.class).invoke(parseTool, hwHealthBarEntry);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            ReleaseLogUtil.c("HwHealthBarChartRenderer", "reflect error");
            str = "";
        }
        String acquireUnit = markerViewFormatProvider.acquireUnit();
        if (!TextUtils.isEmpty(str)) {
            if (Pattern.compile("([0-9]+(.[0-9]+)?)\\s?-\\s?([0-9]+(.[0-9]+)?)").matcher(str).matches()) {
                String[] split = str.split(Constants.LINK);
                if (split.length == 2) {
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    str = nsf.b(R$string.accessibility_range, split[0], split[1]);
                }
            }
            formattedValue = nsf.b(R$string.IDS_two_parts, formattedValue, str);
        }
        if (!TextUtils.isEmpty(acquireUnit) && !TextUtils.isEmpty(str)) {
            formattedValue = nsf.b(R$string.IDS_two_parts, formattedValue, acquireUnit);
        }
        abstractTouchNode.setDescription(formattedValue);
    }

    private void cBo_(Canvas canvas, RectF rectF, Paint paint, Transformer transformer) {
        float[] fArr = {0.0f, 0.0f};
        transformer.pointValuesToPixel(fArr);
        float f = fArr[1];
        float abs = Math.abs(rectF.top - rectF.bottom);
        float abs2 = Math.abs(rectF.right - rectF.left);
        float cBt_ = cBt_(rectF, abs, abs2, f);
        if (cBt_ < 1.0E-7f) {
            return;
        }
        float f2 = this.f ? 0.0f + (abs2 / 2.0f) : 0.0f;
        if (this.i) {
            f2 += abs2 / 2.0f;
        }
        if (cBt_ < f2) {
            float f3 = (rectF.top + rectF.bottom) / 2.0f;
            float f4 = f2 / 2.0f;
            rectF.top = f3 - f4;
            rectF.bottom = f3 + f4;
            if (rectF.bottom > f) {
                float f5 = rectF.bottom - f;
                rectF.bottom -= f5;
                rectF.top -= f5;
            }
        }
        cBx_(canvas, rectF, paint, rectF.bottom - rectF.top, abs2);
    }

    protected float cBt_(RectF rectF, float f, float f2, float f3) {
        if (f >= 1.0E-7f || rectF.top != f3) {
            return f;
        }
        return 0.0f;
    }

    protected void cBx_(Canvas canvas, RectF rectF, Paint paint, float f, float f2) {
        float f3;
        float f4;
        float f5;
        if (f <= 0.0f || !this.f) {
            f3 = f;
            f4 = 0.0f;
        } else {
            canvas.save();
            float f6 = f2 / 2.0f;
            canvas.clipRect(rectF.left, rectF.top, rectF.right, rectF.top + f6 + 1.0f);
            canvas.drawRoundRect(new RectF(rectF.left, rectF.top, rectF.right, rectF.top + f2), f6, f6, paint);
            f3 = f - f6;
            f4 = f6 + 0.0f;
            canvas.restore();
        }
        if (f3 <= 0.0f || !this.i) {
            f5 = 0.0f;
        } else {
            canvas.save();
            float f7 = f2 / 2.0f;
            canvas.clipRect(rectF.left, (rectF.bottom - f7) - 1.0f, rectF.right, rectF.bottom);
            canvas.drawRoundRect(new RectF(rectF.left, rectF.bottom - f2, rectF.right, rectF.bottom), f7, f7, paint);
            f3 -= f7;
            f5 = f7 + 0.0f;
            canvas.restore();
        }
        if (f3 > 0.0f) {
            canvas.drawRect(rectF.left, rectF.top + f4, rectF.right, rectF.bottom - f5, paint);
        }
    }

    protected void b(float f, float f2, float f3, float f4, Transformer transformer) {
        this.d.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.d, this.mAnimator.getPhaseY());
    }

    private boolean d() {
        HwHealthBarDataProvider hwHealthBarDataProvider = this.e;
        if (hwHealthBarDataProvider == null || hwHealthBarDataProvider.getBarData() == null || !isDrawingValuesAllowed(this.e)) {
            return false;
        }
        not[] notVarArr = this.f15395a;
        if (notVarArr != null && notVarArr.length != 0) {
            return true;
        }
        LogUtil.h("HwHealthBarChartRenderer", "drawValues mBarBuffers == null || mBarBuffers.length == 0");
        return false;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (d()) {
            List<T> dataSets = this.e.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.e.isDrawValueAboveBarEnabled();
            float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
            float f = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
            float f2 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
            for (int i = 0; i < this.e.getBarData().getDataSetCount(); i++) {
                IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) dataSets.get(i);
                if (shouldDrawValues(iHwHealthBarDataSet)) {
                    applyValueTextStyle(iHwHealthBarDataSet);
                    MPPointF mPPointF = MPPointF.getInstance(iHwHealthBarDataSet.getIconsOffset());
                    mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                    mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                    cBr_(canvas, iHwHealthBarDataSet, this.f15395a[i], mPPointF, f, f2);
                    MPPointF.recycleInstance(mPPointF);
                }
            }
        }
    }

    private void cBr_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF, float f, float f2) {
        if (!iHwHealthBarDataSet.isStacked()) {
            for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
                float f3 = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
                if (!this.mViewPortHandler.isInBoundsRight(f3)) {
                    return;
                }
                int i2 = i + 1;
                if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f3)) {
                    int i3 = i / 4;
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                    float f4 = hwHealthBarEntry.getY() >= 0.0f ? barBuffer.buffer[i2] + f : barBuffer.buffer[i + 3] + f2;
                    if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                        cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler), f3, f4, iHwHealthBarDataSet.getValueTextColor(i3));
                    }
                    if (iHwHealthBarDataSet.isDrawIconsEnabled()) {
                        cBq_(canvas, hwHealthBarEntry.getIcon(), mPPointF, f3, f4);
                    }
                }
            }
            return;
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < iHwHealthBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
            float f5 = (barBuffer.buffer[i5] + barBuffer.buffer[i5 + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                return;
            }
            int i6 = i5 + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i6]) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                HwHealthBarEntry hwHealthBarEntry2 = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i4);
                float f6 = barBuffer.buffer[i6] + (hwHealthBarEntry2.getY() >= 0.0f ? f : f2);
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry2.getY(), hwHealthBarEntry2, i4, this.mViewPortHandler), f5, f6, iHwHealthBarDataSet.getValueTextColor(i4));
                }
                if (iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    cBq_(canvas, hwHealthBarEntry2.getIcon(), mPPointF, f5, f6);
                }
                i5 += 4;
                i4++;
            }
        }
    }

    private void cBq_(Canvas canvas, Drawable drawable, MPPointF mPPointF, float f, float f2) {
        if (drawable == null) {
            return;
        }
        Utils.drawImage(canvas, drawable, (int) (f + mPPointF.x), (int) (f2 + mPPointF.y), drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        IHwHealthBarDataSet iHwHealthBarDataSet;
        if (this.h) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.h("HwHealthBarChartRenderer", "drawHighlighted() barData is null!");
            return;
        }
        for (Highlight highlight : highlightArr) {
            if (barData.getDataSets() != null && (iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSets().get(barData.getDataSets().size() - 1)) != null && iHwHealthBarDataSet.isHighlightEnabled()) {
                List<HwHealthBarEntry> entriesForXValue = iHwHealthBarDataSet.getEntriesForXValue(highlight.getX(), this.e);
                if (koq.b(entriesForXValue)) {
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet)) {
                    this.mHighlightPaint.setColor(b(hwHealthBarEntry, iHwHealthBarDataSet));
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    b(hwHealthBarEntry.getX(), noy.c(hwHealthBarEntry.acquireModel()), noy.a(hwHealthBarEntry.acquireModel()), (IHwHealthBarDataSet.BarWidthMode.DEFAULT_WIDTH.equals(iHwHealthBarDataSet.getBarDrawWidthMode()) ? barData.d() : iHwHealthBarDataSet.getBarDrawWidth(transformer)) / 2.0f, transformer);
                    dGK_(highlight, this.d);
                    cBo_(canvas, this.d, this.mHighlightPaint, transformer);
                }
            }
        }
    }

    protected void dGK_(Highlight highlight, RectF rectF) {
        if (highlight == null || rectF == null) {
            LogUtil.h("HwHealthBarChartRenderer", "high == null||bar == null");
        } else {
            highlight.setDraw(rectF.centerX(), rectF.top);
        }
    }

    public HwHealthBarDataProvider b() {
        return this.e;
    }

    public void usePaintAsBackground(boolean z) {
        this.h = z;
    }

    public boolean isUsePaintAsBackground() {
        return this.h;
    }

    private int b(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.h) {
            return nrn.d(BaseApplication.getContext(), R$color.health_chart_extend_background_color);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int b(HwHealthBarEntry hwHealthBarEntry, IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.h) {
            return nrn.d(BaseApplication.getContext(), R$color.health_chart_extend_background_color);
        }
        return iHwHealthBarDataSet.acquireFocusColor(hwHealthBarEntry);
    }

    public boolean hasData() {
        nmz barData = this.e.getBarData();
        return barData != null && koq.c(barData.getDataSets());
    }

    public void d(boolean z, boolean z2) {
        this.f = z;
        this.i = z2;
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void focusArea(List<nnl> list) {
        throw new RuntimeException("pls not call bar's focusArea,no impl");
    }

    @Override // com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void disableFocusArea() {
        throw new RuntimeException("pls not call bar's disableFocusArea,no impl");
    }

    public void cBv_(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }
}

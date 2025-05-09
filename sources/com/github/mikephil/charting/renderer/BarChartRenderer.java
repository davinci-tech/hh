package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect;
    private RectF mBarShadowRectBuffer;
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mBarRect = new RectF();
        this.mBarShadowRectBuffer = new RectF();
        this.mChart = barDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint = new Paint(1);
        this.mShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mBarBorderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        if (this.mBarBuffers == null) {
            initBuffers();
        }
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        int i2 = 0;
        boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
            for (int i3 = 0; i3 < min; i3++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i3)).getX();
                this.mBarShadowRectBuffer.left = x - barWidth;
                this.mBarShadowRectBuffer.right = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z2 = (iBarDataSet.getFills() == null || iBarDataSet.getFills().isEmpty()) ? false : true;
        boolean z3 = iBarDataSet.getColors().size() == 1;
        boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
        if (z3) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        int i4 = 0;
        while (i2 < barBuffer.size()) {
            int i5 = i2 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i5])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i2])) {
                    return;
                }
                if (!z3) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i4));
                }
                if (z2) {
                    iBarDataSet.getFill(i4).fillRect(canvas, this.mRenderPaint, barBuffer.buffer[i2], barBuffer.buffer[i2 + 1], barBuffer.buffer[i5], barBuffer.buffer[i2 + 3], isInverted ? Fill.Direction.DOWN : Fill.Direction.UP);
                } else {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i2 + 1], barBuffer.buffer[i5], barBuffer.buffer[i2 + 3], this.mRenderPaint);
                }
                if (z) {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i2 + 1], barBuffer.buffer[i5], barBuffer.buffer[i2 + 3], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            i4++;
        }
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        float f;
        boolean z;
        MPPointF mPPointF;
        int i;
        float f2;
        boolean z2;
        float[] fArr;
        Transformer transformer;
        int i2;
        float[] fArr2;
        int i3;
        float f3;
        float f4;
        float f5;
        float f6;
        int i4;
        MPPointF mPPointF2;
        List list2;
        BarBuffer barBuffer;
        float f7;
        float f8;
        float f9;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i5 = 0;
            while (i5 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i5);
                if (iBarDataSet.getEntryCount() != 0 && shouldDrawValues(iBarDataSet)) {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
                    float f10 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    float f11 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                    if (isInverted) {
                        f10 = (-f10) - calcTextHeight;
                        f11 = (-f11) - calcTextHeight;
                    }
                    float f12 = f10;
                    float f13 = f11;
                    BarBuffer barBuffer2 = this.mBarBuffers[i5];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        mPPointF = mPPointF3;
                        list = dataSets;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i6 = 0;
                        int i7 = 0;
                        while (i6 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i6);
                            float[] yVals = barEntry.getYVals();
                            float f14 = (barBuffer2.buffer[i7] + barBuffer2.buffer[i7 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i6);
                            if (yVals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(f14)) {
                                    break;
                                }
                                int i8 = i7 + 1;
                                if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i8]) && this.mViewPortHandler.isInBoundsLeft(f14)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f6 = f14;
                                        f2 = convertDpToPixel;
                                        fArr = yVals;
                                        i = i6;
                                        z2 = isDrawValueAboveBarEnabled;
                                        transformer = transformer2;
                                        drawValue(canvas, iBarDataSet.getValueFormatter(), barEntry.getY(), barEntry, i5, f6, barBuffer2.buffer[i8] + (barEntry.getY() >= 0.0f ? f12 : f13), valueTextColor);
                                    } else {
                                        f6 = f14;
                                        i = i6;
                                        f2 = convertDpToPixel;
                                        z2 = isDrawValueAboveBarEnabled;
                                        fArr = yVals;
                                        transformer = transformer2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        Utils.drawImage(canvas, icon, (int) (f6 + mPPointF.x), (int) (barBuffer2.buffer[i8] + (barEntry.getY() >= 0.0f ? f12 : f13) + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                } else {
                                    transformer2 = transformer2;
                                    isDrawValueAboveBarEnabled = isDrawValueAboveBarEnabled;
                                    convertDpToPixel = convertDpToPixel;
                                    i6 = i6;
                                }
                            } else {
                                i = i6;
                                f2 = convertDpToPixel;
                                z2 = isDrawValueAboveBarEnabled;
                                fArr = yVals;
                                transformer = transformer2;
                                float f15 = f14;
                                int length = fArr.length * 2;
                                float[] fArr3 = new float[length];
                                float f16 = -barEntry.getNegativeSum();
                                float f17 = 0.0f;
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < length) {
                                    float f18 = fArr[i10];
                                    if (f18 == 0.0f && (f17 == 0.0f || f16 == 0.0f)) {
                                        float f19 = f16;
                                        f16 = f18;
                                        f5 = f19;
                                    } else if (f18 >= 0.0f) {
                                        f17 += f18;
                                        f5 = f16;
                                        f16 = f17;
                                    } else {
                                        f5 = f16 - f18;
                                    }
                                    fArr3[i9 + 1] = f16 * phaseY;
                                    i9 += 2;
                                    i10++;
                                    f16 = f5;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i11 = 0;
                                while (i11 < length) {
                                    int i12 = i11 / 2;
                                    float f20 = fArr[i12];
                                    float f21 = fArr3[i11 + 1] + (((f20 > 0.0f ? 1 : (f20 == 0.0f ? 0 : -1)) == 0 && (f16 > 0.0f ? 1 : (f16 == 0.0f ? 0 : -1)) == 0 && (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) > 0) || (f20 > 0.0f ? 1 : (f20 == 0.0f ? 0 : -1)) < 0 ? f13 : f12);
                                    if (!this.mViewPortHandler.isInBoundsRight(f15)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(f21) && this.mViewPortHandler.isInBoundsLeft(f15)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f4 = f21;
                                            i2 = i11;
                                            fArr2 = fArr3;
                                            i3 = length;
                                            f3 = f15;
                                            drawValue(canvas, iBarDataSet.getValueFormatter(), fArr[i12], barEntry, i5, f15, f4, valueTextColor);
                                        } else {
                                            f4 = f21;
                                            i2 = i11;
                                            fArr2 = fArr3;
                                            i3 = length;
                                            f3 = f15;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (f3 + mPPointF.x), (int) (f4 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i2 = i11;
                                        fArr2 = fArr3;
                                        i3 = length;
                                        f3 = f15;
                                    }
                                    i11 = i2 + 2;
                                    fArr3 = fArr2;
                                    length = i3;
                                    f15 = f3;
                                }
                            }
                            i7 = fArr == null ? i7 + 4 : i7 + (fArr.length * 4);
                            i6 = i + 1;
                            transformer2 = transformer;
                            isDrawValueAboveBarEnabled = z2;
                            convertDpToPixel = f2;
                        }
                    } else {
                        int i13 = 0;
                        while (i13 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            float f22 = (barBuffer2.buffer[i13] + barBuffer2.buffer[i13 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f22)) {
                                break;
                            }
                            int i14 = i13 + 1;
                            if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i14]) && this.mViewPortHandler.isInBoundsLeft(f22)) {
                                int i15 = i13 / 4;
                                Entry entry = (BarEntry) iBarDataSet.getEntryForIndex(i15);
                                float y = entry.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                                    if (y >= 0.0f) {
                                        f9 = barBuffer2.buffer[i14] + f12;
                                    } else {
                                        f9 = barBuffer2.buffer[i13 + 3] + f13;
                                    }
                                    f7 = f22;
                                    i4 = i13;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    drawValue(canvas, valueFormatter, y, entry, i5, f7, f9, iBarDataSet.getValueTextColor(i15));
                                } else {
                                    f7 = f22;
                                    i4 = i13;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                }
                                if (entry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = entry.getIcon();
                                    if (y >= 0.0f) {
                                        f8 = barBuffer.buffer[i14] + f12;
                                    } else {
                                        f8 = barBuffer.buffer[i4 + 3] + f13;
                                    }
                                    Utils.drawImage(canvas, icon3, (int) (f7 + mPPointF2.x), (int) (f8 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i4 = i13;
                                mPPointF2 = mPPointF3;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i13 = i4 + 4;
                            barBuffer2 = barBuffer;
                            mPPointF3 = mPPointF2;
                            dataSets = list2;
                        }
                        mPPointF = mPPointF3;
                        list = dataSets;
                    }
                    f = convertDpToPixel;
                    z = isDrawValueAboveBarEnabled;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f = convertDpToPixel;
                    z = isDrawValueAboveBarEnabled;
                }
                i5++;
                dataSets = list;
                isDrawValueAboveBarEnabled = z;
                convertDpToPixel = f;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float y;
        float f;
        float f2;
        float f3;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (highlight.getStackIndex() >= 0 && barEntry.isStacked()) {
                        if (this.mChart.isHighlightFullBarEnabled()) {
                            y = barEntry.getPositiveSum();
                            f = -barEntry.getNegativeSum();
                        } else {
                            Range range = barEntry.getRanges()[highlight.getStackIndex()];
                            f3 = range.from;
                            f2 = range.to;
                            prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                            setHighlightDrawPos(highlight, this.mBarRect);
                            canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                        }
                    } else {
                        y = barEntry.getY();
                        f = 0.0f;
                    }
                    f2 = f;
                    f3 = y;
                    prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }
}

package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
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
                this.mBarShadowRectBuffer.top = x - barWidth;
                this.mBarShadowRectBuffer.bottom = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
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
            int i5 = i2 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i5])) {
                return;
            }
            int i6 = i2 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i6])) {
                if (!z3) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i2 / 4));
                }
                if (z2) {
                    iBarDataSet.getFill(i4).fillRect(canvas, this.mRenderPaint, barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], isInverted ? Fill.Direction.LEFT : Fill.Direction.RIGHT);
                } else {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], this.mRenderPaint);
                }
                if (z) {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i6], barBuffer.buffer[i2 + 2], barBuffer.buffer[i5], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            i4++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        float f;
        MPPointF mPPointF;
        int i;
        float[] fArr;
        int i2;
        float[] fArr2;
        float f2;
        float f3;
        float f4;
        BarEntry barEntry;
        int i3;
        List list2;
        float f5;
        float f6;
        MPPointF mPPointF2;
        BarBuffer barBuffer;
        IValueFormatter iValueFormatter;
        float f7;
        float f8;
        float f9;
        float f10;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i4 = 0;
            while (i4 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i4);
                if (iBarDataSet.getEntryCount() != 0 && shouldDrawValues(iBarDataSet)) {
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    applyValueTextStyle(iBarDataSet);
                    float f11 = 2.0f;
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "10") / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer2 = this.mBarBuffers[i4];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        list = dataSets;
                        f = convertDpToPixel;
                        mPPointF = mPPointF3;
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i5 = 0;
                        int i6 = 0;
                        while (i5 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i5);
                            int valueTextColor = iBarDataSet.getValueTextColor(i5);
                            float[] yVals = barEntry2.getYVals();
                            if (yVals == null) {
                                int i7 = i6 + 1;
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i7])) {
                                    break;
                                }
                                if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i6]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i7])) {
                                    String formattedValue = valueFormatter.getFormattedValue(barEntry2.getY(), barEntry2, i4, this.mViewPortHandler);
                                    float calcTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    float f12 = isDrawValueAboveBarEnabled ? f : -(calcTextWidth + f);
                                    float f13 = isDrawValueAboveBarEnabled ? -(calcTextWidth + f) : f;
                                    if (isInverted) {
                                        f12 = (-f12) - calcTextWidth;
                                        f13 = (-f13) - calcTextWidth;
                                    }
                                    float f14 = f12;
                                    float f15 = f13;
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        i = i5;
                                        fArr = yVals;
                                        barEntry = barEntry2;
                                        drawValue(canvas, formattedValue, barBuffer2.buffer[i6 + 2] + (barEntry2.getY() >= 0.0f ? f14 : f15), barBuffer2.buffer[i7] + calcTextHeight, valueTextColor);
                                    } else {
                                        barEntry = barEntry2;
                                        i = i5;
                                        fArr = yVals;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        float f16 = barBuffer2.buffer[i6 + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f14 = f15;
                                        }
                                        Utils.drawImage(canvas, icon, (int) (f16 + f14 + mPPointF.x), (int) (barBuffer2.buffer[i7] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                i = i5;
                                fArr = yVals;
                                int length = fArr.length * 2;
                                float[] fArr3 = new float[length];
                                float f17 = -barEntry2.getNegativeSum();
                                float f18 = 0.0f;
                                int i8 = 0;
                                int i9 = 0;
                                while (i8 < length) {
                                    float f19 = fArr[i9];
                                    if (f19 == 0.0f && (f18 == 0.0f || f17 == 0.0f)) {
                                        float f20 = f17;
                                        f17 = f19;
                                        f4 = f20;
                                    } else if (f19 >= 0.0f) {
                                        f18 += f19;
                                        f4 = f17;
                                        f17 = f18;
                                    } else {
                                        f4 = f17 - f19;
                                    }
                                    fArr3[i8] = f17 * phaseY;
                                    i8 += 2;
                                    i9++;
                                    f17 = f4;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i10 = 0;
                                while (i10 < length) {
                                    float f21 = fArr[i10 / 2];
                                    String formattedValue2 = valueFormatter.getFormattedValue(f21, barEntry2, i4, this.mViewPortHandler);
                                    float calcTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float f22 = isDrawValueAboveBarEnabled ? f : -(calcTextWidth2 + f);
                                    int i11 = length;
                                    float f23 = isDrawValueAboveBarEnabled ? -(calcTextWidth2 + f) : f;
                                    if (isInverted) {
                                        f22 = (-f22) - calcTextWidth2;
                                        f23 = (-f23) - calcTextWidth2;
                                    }
                                    boolean z = (f21 == 0.0f && f17 == 0.0f && f18 > 0.0f) || f21 < 0.0f;
                                    float f24 = fArr3[i10];
                                    if (z) {
                                        f22 = f23;
                                    }
                                    float f25 = f24 + f22;
                                    float f26 = (barBuffer2.buffer[i6 + 1] + barBuffer2.buffer[i6 + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f26)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(f25) && this.mViewPortHandler.isInBoundsBottom(f26)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f2 = f26;
                                            i2 = i10;
                                            fArr2 = fArr3;
                                            f3 = f25;
                                            drawValue(canvas, formattedValue2, f25, f26 + calcTextHeight, valueTextColor);
                                        } else {
                                            f2 = f26;
                                            i2 = i10;
                                            fArr2 = fArr3;
                                            f3 = f25;
                                        }
                                        if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry2.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (f3 + mPPointF.x), (int) (f2 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i2 = i10;
                                        fArr2 = fArr3;
                                    }
                                    i10 = i2 + 2;
                                    length = i11;
                                    fArr3 = fArr2;
                                }
                            }
                            i6 = fArr == null ? i6 + 4 : i6 + (fArr.length * 4);
                            i5 = i + 1;
                        }
                    } else {
                        int i12 = 0;
                        while (i12 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            int i13 = i12 + 1;
                            float f27 = (barBuffer2.buffer[i13] + barBuffer2.buffer[i12 + 3]) / f11;
                            if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i13])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i12]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i13])) {
                                BarEntry barEntry3 = (BarEntry) iBarDataSet.getEntryForIndex(i12 / 4);
                                float y = barEntry3.getY();
                                String formattedValue3 = valueFormatter.getFormattedValue(y, barEntry3, i4, this.mViewPortHandler);
                                MPPointF mPPointF4 = mPPointF3;
                                float calcTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                float f28 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth3 + convertDpToPixel);
                                IValueFormatter iValueFormatter2 = valueFormatter;
                                if (isDrawValueAboveBarEnabled) {
                                    f7 = -(calcTextWidth3 + convertDpToPixel);
                                    list2 = dataSets;
                                } else {
                                    list2 = dataSets;
                                    f7 = convertDpToPixel;
                                }
                                int i14 = i12 + 2;
                                f5 = convertDpToPixel;
                                float f29 = f7 - (barBuffer2.buffer[i14] - barBuffer2.buffer[i12]);
                                if (isInverted) {
                                    f28 = (-f28) - calcTextWidth3;
                                    f29 = (-f29) - calcTextWidth3;
                                }
                                float f30 = f28;
                                float f31 = f29;
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    float f32 = f27 + calcTextHeight;
                                    f8 = y;
                                    i3 = i12;
                                    f9 = f30;
                                    mPPointF2 = mPPointF4;
                                    f10 = f31;
                                    barBuffer = barBuffer2;
                                    f6 = calcTextHeight;
                                    iValueFormatter = iValueFormatter2;
                                    drawValue(canvas, formattedValue3, (y >= 0.0f ? f30 : f31) + barBuffer2.buffer[i14], f32, iBarDataSet.getValueTextColor(i12 / 2));
                                } else {
                                    f8 = y;
                                    i3 = i12;
                                    f9 = f30;
                                    f6 = calcTextHeight;
                                    mPPointF2 = mPPointF4;
                                    iValueFormatter = iValueFormatter2;
                                    f10 = f31;
                                    barBuffer = barBuffer2;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = barEntry3.getIcon();
                                    float f33 = barBuffer.buffer[i14];
                                    if (f8 < 0.0f) {
                                        f9 = f10;
                                    }
                                    Utils.drawImage(canvas, icon3, (int) (f33 + f9 + mPPointF2.x), (int) (f27 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i3 = i12;
                                list2 = dataSets;
                                f5 = convertDpToPixel;
                                f6 = calcTextHeight;
                                mPPointF2 = mPPointF3;
                                barBuffer = barBuffer2;
                                iValueFormatter = valueFormatter;
                            }
                            i12 = i3 + 4;
                            mPPointF3 = mPPointF2;
                            barBuffer2 = barBuffer;
                            valueFormatter = iValueFormatter;
                            dataSets = list2;
                            convertDpToPixel = f5;
                            calcTextHeight = f6;
                            f11 = 2.0f;
                        }
                        list = dataSets;
                        f = convertDpToPixel;
                        mPPointF = mPPointF3;
                    }
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f = convertDpToPixel;
                }
                i4++;
                dataSets = list;
                convertDpToPixel = f;
            }
        }
    }

    protected void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f2, f - f4, f3, f + f4);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    protected boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}

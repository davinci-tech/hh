package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import androidx.core.internal.view.SupportMenu;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes7.dex */
public class RadarChartRenderer extends LineRadarRenderer {
    private Path innerArea;
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer;
    protected Path mDrawHighlightCirclePathBuffer;
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;
    private Paint paint;
    private Path previousPath;
    private Path temp;

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.paint = new Paint(1);
        this.previousPath = new Path();
        this.innerArea = new Path();
        this.temp = new Path();
        this.mDrawDataSetSurfacePathBuffer = new Path();
        this.mDrawHighlightCirclePathBuffer = new Path();
        this.mChart = radarChart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        Paint paint = new Paint(1);
        this.mWebPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int entryCount = radarData.getMaxEntryCountSet().getEntryCount();
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets()) {
            if (iRadarDataSet.isVisible()) {
                drawDataSet(canvas, iRadarDataSet, entryCount);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawDataSet(Canvas canvas, IRadarDataSet iRadarDataSet, int i) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        Path path = this.mDrawDataSetSurfacePathBuffer;
        path.reset();
        boolean z = false;
        for (int i2 = 0; i2 < iRadarDataSet.getEntryCount(); i2++) {
            this.mRenderPaint.setColor(iRadarDataSet.getColor(i2));
            Utils.getPosition(centerOffsets, (((RadarEntry) iRadarDataSet.getEntryForIndex(i2)).getY() - this.mChart.getYChartMin()) * factor * phaseY, (i2 * sliceAngle * phaseX) + this.mChart.getRotationAngle(), mPPointF);
            if (!Float.isNaN(mPPointF.x)) {
                if (!z) {
                    path.moveTo(mPPointF.x, mPPointF.y);
                    z = true;
                } else {
                    path.lineTo(mPPointF.x, mPPointF.y);
                }
            }
        }
        if (iRadarDataSet.getEntryCount() > i) {
            path.lineTo(centerOffsets.x, centerOffsets.y);
        }
        path.close();
        if (iRadarDataSet.isDrawFilledEnabled()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable();
            if (fillDrawable != null) {
                drawFilledPath(canvas, path, fillDrawable);
            } else {
                drawFilledPath(canvas, path, iRadarDataSet.getFillColor(), iRadarDataSet.getFillAlpha());
            }
        }
        this.mRenderPaint.setStrokeWidth(iRadarDataSet.getLineWidth());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled() || iRadarDataSet.getFillAlpha() < 255) {
            canvas.drawPath(path, this.mRenderPaint);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i;
        float f;
        float f2;
        MPPointF mPPointF;
        int i2;
        IRadarDataSet iRadarDataSet;
        int i3;
        float f3;
        float f4;
        MPPointF mPPointF2;
        MPPointF mPPointF3;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF mPPointF4 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF mPPointF5 = MPPointF.getInstance(0.0f, 0.0f);
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i4 = 0;
        while (i4 < ((RadarData) this.mChart.getData()).getDataSetCount()) {
            IRadarDataSet dataSetByIndex = ((RadarData) this.mChart.getData()).getDataSetByIndex(i4);
            if (dataSetByIndex.getEntryCount() != 0 && shouldDrawValues(dataSetByIndex)) {
                applyValueTextStyle(dataSetByIndex);
                MPPointF mPPointF6 = MPPointF.getInstance(dataSetByIndex.getIconsOffset());
                mPPointF6.x = Utils.convertDpToPixel(mPPointF6.x);
                mPPointF6.y = Utils.convertDpToPixel(mPPointF6.y);
                int i5 = 0;
                while (i5 < dataSetByIndex.getEntryCount()) {
                    RadarEntry radarEntry = (RadarEntry) dataSetByIndex.getEntryForIndex(i5);
                    float f5 = i5 * sliceAngle * phaseX;
                    Utils.getPosition(centerOffsets, (radarEntry.getY() - this.mChart.getYChartMin()) * factor * phaseY, f5 + this.mChart.getRotationAngle(), mPPointF4);
                    if (dataSetByIndex.isDrawValuesEnabled()) {
                        i2 = i5;
                        f3 = phaseX;
                        mPPointF2 = mPPointF6;
                        iRadarDataSet = dataSetByIndex;
                        i3 = i4;
                        f4 = sliceAngle;
                        mPPointF3 = mPPointF5;
                        drawValue(canvas, dataSetByIndex.getValueFormatter(), radarEntry.getY(), radarEntry, i4, mPPointF4.x, mPPointF4.y - convertDpToPixel, dataSetByIndex.getValueTextColor(i5));
                    } else {
                        i2 = i5;
                        iRadarDataSet = dataSetByIndex;
                        i3 = i4;
                        f3 = phaseX;
                        f4 = sliceAngle;
                        mPPointF2 = mPPointF6;
                        mPPointF3 = mPPointF5;
                    }
                    if (radarEntry.getIcon() != null && iRadarDataSet.isDrawIconsEnabled()) {
                        Drawable icon = radarEntry.getIcon();
                        Utils.getPosition(centerOffsets, (radarEntry.getY() * factor * phaseY) + mPPointF2.y, f5 + this.mChart.getRotationAngle(), mPPointF3);
                        mPPointF3.y += mPPointF2.x;
                        Utils.drawImage(canvas, icon, (int) mPPointF3.x, (int) mPPointF3.y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i5 = i2 + 1;
                    mPPointF6 = mPPointF2;
                    mPPointF5 = mPPointF3;
                    sliceAngle = f4;
                    i4 = i3;
                    phaseX = f3;
                    dataSetByIndex = iRadarDataSet;
                }
                i = i4;
                f = phaseX;
                f2 = sliceAngle;
                mPPointF = mPPointF5;
                MPPointF.recycleInstance(mPPointF6);
            } else {
                i = i4;
                f = phaseX;
                f2 = sliceAngle;
                mPPointF = mPPointF5;
            }
            i4 = i + 1;
            mPPointF5 = mPPointF;
            sliceAngle = f2;
            phaseX = f;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF4);
        MPPointF.recycleInstance(mPPointF5);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        drawWeb(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawWeb(Canvas canvas) {
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationAngle = this.mChart.getRotationAngle();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int skipWebLineCount = this.mChart.getSkipWebLineCount();
        int entryCount = ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < entryCount; i += skipWebLineCount + 1) {
            Utils.getPosition(centerOffsets, this.mChart.getYRange() * factor, (i * sliceAngle) + rotationAngle, mPPointF);
            canvas.drawLine(centerOffsets.x, centerOffsets.y, mPPointF.x, mPPointF.y, this.mWebPaint);
        }
        MPPointF.recycleInstance(mPPointF);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int i2 = this.mChart.getYAxis().mEntryCount;
        MPPointF mPPointF2 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF mPPointF3 = MPPointF.getInstance(0.0f, 0.0f);
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mChart.isCustomLayerColorEnable()) {
                this.innerArea.rewind();
                this.paint.setColor(this.mChart.getLayerColorList().get(i3).intValue());
            }
            int i4 = 0;
            while (i4 < ((RadarData) this.mChart.getData()).getEntryCount()) {
                float yChartMin = (this.mChart.getYAxis().mEntries[i3] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(centerOffsets, yChartMin, (i4 * sliceAngle) + rotationAngle, mPPointF2);
                int i5 = i4 + 1;
                Utils.getPosition(centerOffsets, yChartMin, (i5 * sliceAngle) + rotationAngle, mPPointF3);
                canvas.drawLine(mPPointF2.x, mPPointF2.y, mPPointF3.x, mPPointF3.y, this.mWebPaint);
                if (this.mChart.isCustomLayerColorEnable() && mPPointF2.x != mPPointF3.x) {
                    if (i4 == 0) {
                        this.innerArea.moveTo(mPPointF2.x, mPPointF2.y);
                    } else {
                        this.innerArea.lineTo(mPPointF2.x, mPPointF2.y);
                    }
                    this.innerArea.lineTo(mPPointF3.x, mPPointF3.y);
                }
                i4 = i5;
            }
            if (this.mChart.isCustomLayerColorEnable()) {
                this.temp.set(this.innerArea);
                if (!this.innerArea.isEmpty() && this.innerArea.op(this.previousPath, Path.Op.DIFFERENCE)) {
                    canvas.drawPath(this.innerArea, this.paint);
                }
                this.previousPath.set(this.temp);
            }
        }
        MPPointF.recycleInstance(mPPointF2);
        MPPointF.recycleInstance(mPPointF3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i;
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = highlightArr.length;
        for (int i2 = 0; i2 < length; i2 = i + 1) {
            Highlight highlight = highlightArr[i2];
            IRadarDataSet dataSetByIndex = radarData.getDataSetByIndex(highlight.getDataSetIndex());
            if (dataSetByIndex != null && dataSetByIndex.isHighlightEnabled()) {
                Entry entry = (RadarEntry) dataSetByIndex.getEntryForIndex((int) highlight.getX());
                if (isInBoundsX(entry, dataSetByIndex)) {
                    float y = entry.getY();
                    float yChartMin = this.mChart.getYChartMin();
                    float phaseY = this.mAnimator.getPhaseY();
                    float x = highlight.getX();
                    i = i2;
                    Utils.getPosition(centerOffsets, (y - yChartMin) * factor * phaseY, (x * sliceAngle * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), mPPointF);
                    highlight.setDraw(mPPointF.x, mPPointF.y);
                    drawHighlightLines(canvas, mPPointF.x, mPPointF.y, dataSetByIndex);
                    if (dataSetByIndex.isDrawHighlightCircleEnabled() && !Float.isNaN(mPPointF.x) && !Float.isNaN(mPPointF.y)) {
                        int highlightCircleStrokeColor = dataSetByIndex.getHighlightCircleStrokeColor();
                        if (highlightCircleStrokeColor == 1122867) {
                            highlightCircleStrokeColor = dataSetByIndex.getColor(0);
                        }
                        if (dataSetByIndex.getHighlightCircleStrokeAlpha() < 255) {
                            highlightCircleStrokeColor = ColorTemplate.colorWithAlpha(highlightCircleStrokeColor, dataSetByIndex.getHighlightCircleStrokeAlpha());
                        }
                        drawHighlightCircle(canvas, mPPointF, dataSetByIndex.getHighlightCircleInnerRadius(), dataSetByIndex.getHighlightCircleOuterRadius(), dataSetByIndex.getHighlightCircleFillColor(), highlightCircleStrokeColor, dataSetByIndex.getHighlightCircleStrokeWidth());
                    }
                }
            }
            i = i2;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }

    public void drawHighlightCircle(Canvas canvas, MPPointF mPPointF, float f, float f2, int i, int i2, float f3) {
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(f2);
        float convertDpToPixel2 = Utils.convertDpToPixel(f);
        if (i != 1122867) {
            Path path = this.mDrawHighlightCirclePathBuffer;
            path.reset();
            path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel, Path.Direction.CW);
            if (convertDpToPixel2 > 0.0f) {
                path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(i);
            this.mHighlightCirclePaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, this.mHighlightCirclePaint);
        }
        if (i2 != 1122867) {
            this.mHighlightCirclePaint.setColor(i2);
            this.mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(f3));
            canvas.drawCircle(mPPointF.x, mPPointF.y, convertDpToPixel, this.mHighlightCirclePaint);
        }
        canvas.restore();
    }
}

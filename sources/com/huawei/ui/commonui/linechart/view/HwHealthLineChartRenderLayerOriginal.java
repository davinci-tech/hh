package com.huawei.ui.commonui.linechart.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.renderer.LineRadarRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet;
import defpackage.now;
import defpackage.nrn;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthLineChartRenderLayerOriginal extends LineRadarRenderer {
    private static final int HALF = 2;
    private static final String TAG = "HealthChart_HwHealthLineChartRenderLayerOriginal";
    private Paint mBitmapBuffPaint;
    protected Canvas mBitmapCanvas;
    protected Bitmap.Config mBitmapConfig;
    protected HwHealthLineDataProvider mChart;
    protected Paint mCirclePaintInner;
    protected Path mCubicFillPath;
    protected Path mCubicPath;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mGenerateFilledPathBuffer;

    protected abstract void drawCubicBezier(IHwHealthLineDataSet iHwHealthLineDataSet);

    protected void drawCubicFill() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public abstract void drawHighlighted(Canvas canvas, Highlight[] highlightArr);

    protected abstract void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet);

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return true;
    }

    public HwHealthLineChartRenderLayerOriginal(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mGenerateFilledPathBuffer = new Path();
        this.mBitmapConfig = Bitmap.Config.ARGB_8888;
        this.mCubicPath = new Path();
        this.mCubicFillPath = new Path();
        this.mBitmapBuffPaint = new Paint();
        this.mChart = hwHealthLineDataProvider;
        Paint paint = new Paint(1);
        this.mCirclePaintInner = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mCirclePaintInner.setColor(-1);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        Bitmap bitmap = weakReference != null ? weakReference.get() : null;
        if (bitmap == null || !checkBitmapValidate(bitmap, chartWidth, chartHeight)) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            bitmap = Bitmap.createBitmap(chartWidth, chartHeight, this.mBitmapConfig);
            this.mDrawBitmap = new WeakReference<>(bitmap);
            this.mBitmapCanvas = new Canvas(this.mDrawBitmap.get());
        }
        bitmap.eraseColor(0);
        now dataset = getDataset();
        if (dataset == null) {
            return;
        }
        for (T t : dataset.getDataSets()) {
            if (t.isVisible()) {
                drawDataSet(canvas, t);
            }
        }
        this.mBitmapBuffPaint.reset();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mBitmapBuffPaint);
    }

    public now getDataset() {
        return this.mChart.getLineData();
    }

    private boolean checkBitmapValidate(Bitmap bitmap, int i, int i2) {
        return bitmap != null && bitmap.getWidth() == i && bitmap.getHeight() == i2;
    }

    protected void drawDataSet(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.getEntryCount() < 1) {
            return;
        }
        this.mRenderPaint.setStrokeWidth(iHwHealthLineDataSet.getLineWidth());
        this.mRenderPaint.setPathEffect(iHwHealthLineDataSet.getDashPathEffect());
        int i = AnonymousClass1.d[iHwHealthLineDataSet.getMode().ordinal()];
        if (i == 1 || i == 2) {
            drawLinear(canvas, iHwHealthLineDataSet);
        } else if (i == 3) {
            drawCubicBezier(iHwHealthLineDataSet);
        } else if (i == 4) {
            drawHorizontalBezier(iHwHealthLineDataSet);
        }
        this.mRenderPaint.setPathEffect(null);
    }

    /* renamed from: com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HwHealthBaseLineDataSet.Mode.values().length];
            d = iArr;
            try {
                iArr[HwHealthBaseLineDataSet.Mode.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HwHealthBaseLineDataSet.Mode.STEPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HwHealthBaseLineDataSet.Mode.CUBIC_BEZIER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[HwHealthBaseLineDataSet.Mode.HORIZONTAL_BEZIER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v8, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawHorizontalBezier(IHwHealthLineDataSet iHwHealthLineDataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
        if (transformer == null) {
            LogUtil.h(TAG, "drawHorizontalBezier trans == null");
            return;
        }
        this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
        this.mCubicPath.reset();
        if (this.mXBounds.range >= 1) {
            ?? entryForIndex = iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min);
            HwHealthLineDataSet hwHealthLineDataSet = iHwHealthLineDataSet instanceof HwHealthLineDataSet ? (HwHealthLineDataSet) iHwHealthLineDataSet : null;
            this.mCubicPath.moveTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
            int i = this.mXBounds.min + 1;
            Entry entry = entryForIndex;
            while (i <= this.mXBounds.range + this.mXBounds.min) {
                ?? entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i);
                if (hwHealthLineDataSet != null && !hwHealthLineDataSet.b((int) entry.getX(), (int) entryForIndex2.getX())) {
                    this.mCubicPath.moveTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                } else {
                    float x = entry.getX() + ((entryForIndex2.getX() - entry.getX()) / 2.0f);
                    this.mCubicPath.cubicTo(x, entry.getY() * phaseY, x, entryForIndex2.getY() * phaseY, entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                }
                i++;
                entry = entryForIndex2;
            }
        }
        if (iHwHealthLineDataSet.isDrawFilledEnabled()) {
            this.mCubicFillPath.reset();
            this.mCubicFillPath.addPath(this.mCubicPath);
            drawCubicFill();
        }
        this.mRenderPaint.setColor(iHwHealthLineDataSet.getColor());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        transformer.pathValueToPixel(this.mCubicPath);
        this.mBitmapCanvas.drawPath(this.mCubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getData().getDataSets();
            if (dataSets == 0) {
                LogUtil.h(TAG, "drawValues dataSets == null");
                return;
            }
            for (int i = 0; i < dataSets.size(); i++) {
                Object obj = dataSets.get(i);
                if (obj instanceof IHwHealthLineDataSet) {
                    IHwHealthLineDataSet iHwHealthLineDataSet = (IHwHealthLineDataSet) obj;
                    if (shouldDrawValues(iHwHealthLineDataSet) || iHwHealthLineDataSet.isDrawStartEndValue()) {
                        applyValueTextStyle(iHwHealthLineDataSet);
                        HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
                        int circleRadius = (int) (iHwHealthLineDataSet.getCircleRadius() * 1.75f);
                        if (!iHwHealthLineDataSet.isDrawCirclesEnabled()) {
                            circleRadius /= 2;
                        }
                        if (this.mXBounds != null) {
                            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
                            float[] e = transformer.e(iHwHealthLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                            MPPointF mPPointF = MPPointF.getInstance(iHwHealthLineDataSet.getIconsOffset());
                            mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                            mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                            drawDataValue(e, iHwHealthLineDataSet, canvas, mPPointF, circleRadius);
                            MPPointF.recycleInstance(mPPointF);
                        }
                    }
                }
            }
        }
    }

    private void drawDataValue(float[] fArr, IHwHealthLineDataSet iHwHealthLineDataSet, Canvas canvas, MPPointF mPPointF, int i) {
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f = fArr[i2];
            float f2 = fArr[i2 + 1];
            if (!this.mViewPortHandler.isInBoundsRight(f)) {
                return;
            }
            if (this.mViewPortHandler.isInBoundsLeft(f) && this.mViewPortHandler.isInBoundsY(f2)) {
                int i3 = i2 / 2;
                int i4 = i3 + this.mXBounds.min;
                Entry entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i4);
                if (iHwHealthLineDataSet.isDrawValuesEnabled()) {
                    drawValue(canvas, iHwHealthLineDataSet.getValueFormatter().getFormattedValue(entryForIndex.getY(), entryForIndex, i4, this.mViewPortHandler), f, f2 - i, iHwHealthLineDataSet.getValueTextColor(i3));
                }
                if (entryForIndex.getIcon() != null && iHwHealthLineDataSet.isDrawIconsEnabled()) {
                    Drawable icon = entryForIndex.getIcon();
                    Utils.drawImage(canvas, icon, (int) (mPPointF.x + f), (int) (mPPointF.y + f2), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
                if (iHwHealthLineDataSet.isDrawStartEndValue() && (i2 == 0 || i2 == fArr.length - 2)) {
                    drawValue(canvas, iHwHealthLineDataSet.getValueFormatter().getFormattedValue(entryForIndex.getY(), entryForIndex, i4, this.mViewPortHandler), f, f2 - i, nrn.d(R$color.textColorSecondary));
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }
}

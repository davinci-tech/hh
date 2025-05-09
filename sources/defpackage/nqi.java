package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$color;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class nqi extends LineChartRenderer {
    private HashMap<IDataSet, d> b;
    private float[] e;

    public nqi(LineDataProvider lineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(lineDataProvider, chartAnimator, viewPortHandler);
        this.e = new float[2];
        this.b = new HashMap<>();
    }

    @Override // com.github.mikephil.charting.renderer.LineChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getLineData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i);
                if (shouldDrawValues(iLineDataSet) && iLineDataSet.getEntryCount() != 0) {
                    applyValueTextStyle(iLineDataSet);
                    Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                    int circleRadius = (int) (iLineDataSet.getCircleRadius() * 1.75f);
                    if (!iLineDataSet.isDrawCirclesEnabled()) {
                        int i2 = circleRadius / 2;
                    }
                    this.mXBounds.set(this.mChart, iLineDataSet);
                    float[] generateTransformedValuesLine = transformer.generateTransformedValuesLine(iLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    MPPointF mPPointF = MPPointF.getInstance(iLineDataSet.getIconsOffset());
                    mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                    mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                    cFc_(canvas, iLineDataSet, generateTransformedValuesLine, mPPointF);
                    MPPointF.recycleInstance(mPPointF);
                }
            }
        }
    }

    private void cFc_(Canvas canvas, ILineDataSet iLineDataSet, float[] fArr, MPPointF mPPointF) {
        for (int i = 0; i < fArr.length; i += 2) {
            float f = fArr[i];
            float f2 = fArr[i + 1];
            if (!this.mViewPortHandler.isInBoundsRight(f)) {
                return;
            }
            if (this.mViewPortHandler.isInBoundsLeft(f) && this.mViewPortHandler.isInBoundsY(f2)) {
                int i2 = i / 2;
                int i3 = i2 + this.mXBounds.min;
                Entry entryForIndex = iLineDataSet.getEntryForIndex(i3);
                Object data = entryForIndex.getData();
                String str = data != null ? (String) data : "";
                if (iLineDataSet.isDrawValuesEnabled()) {
                    if (!"LINE_PATH_BOLD_START".equals(str)) {
                        if ("DASH_PATH_END".equals(str)) {
                            iLineDataSet.setValueTextSize(10.0f);
                            applyValueTextStyle(iLineDataSet);
                            drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, f, f2 + Utils.convertDpToPixel(15.0f), Color.parseColor(Constants.CHOOSE_TEXT_COLOR));
                        } else if ("DASH_PATH".equals(str)) {
                            iLineDataSet.setValueTextSize(8.0f);
                            applyValueTextStyle(iLineDataSet);
                            drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, f, f2 + Utils.convertDpToPixel(13.0f), iLineDataSet.getValueTextColor(i2));
                        } else {
                            drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i3, f + nqe.b(fArr, i, entryForIndex.getY()), (f2 - Utils.convertDpToPixel(8.0f)) + nqe.a(fArr, i, entryForIndex.getY()), iLineDataSet.getValueTextColor(i2));
                        }
                    }
                }
                if (entryForIndex.getIcon() != null && iLineDataSet.isDrawIconsEnabled()) {
                    Drawable icon = entryForIndex.getIcon();
                    Utils.drawImage(canvas, icon, (int) (f + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.LineChartRenderer
    public void drawCircles(Canvas canvas) {
        d dVar;
        this.mRenderPaint.setStyle(Paint.Style.FILL);
        float[] fArr = this.e;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        List<T> dataSets = this.mChart.getLineData().getDataSets();
        for (int i = 0; i < dataSets.size(); i++) {
            ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i);
            if (iLineDataSet.isVisible() && iLineDataSet.isDrawCirclesEnabled() && iLineDataSet.getEntryCount() != 0) {
                this.mCirclePaintInner.setColor(iLineDataSet.getCircleHoleColor());
                Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                this.mXBounds.set(this.mChart, iLineDataSet);
                float circleRadius = iLineDataSet.getCircleRadius();
                float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
                boolean z = iLineDataSet.isDrawCircleHoleEnabled() && circleHoleRadius < circleRadius && circleHoleRadius > 0.0f;
                boolean z2 = z && iLineDataSet.getCircleHoleColor() == 1122867;
                if (this.b.containsKey(iLineDataSet)) {
                    dVar = this.b.get(iLineDataSet);
                } else {
                    dVar = new d();
                    this.b.put(iLineDataSet, dVar);
                }
                d dVar2 = dVar;
                if (dVar2.a(iLineDataSet)) {
                    dVar2.d(iLineDataSet, z, z2);
                }
                cFb_(canvas, transformer, iLineDataSet, dVar2, circleRadius);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.github.mikephil.charting.data.Entry] */
    private void cFb_(Canvas canvas, Transformer transformer, ILineDataSet iLineDataSet, d dVar, float f) {
        ?? entryForIndex;
        Bitmap cFe_;
        float phaseY = this.mAnimator.getPhaseY();
        int i = this.mXBounds.range;
        int i2 = this.mXBounds.min;
        for (int i3 = this.mXBounds.min; i3 <= i + i2 && (entryForIndex = iLineDataSet.getEntryForIndex(i3)) != 0; i3++) {
            this.e[0] = entryForIndex.getX();
            this.e[1] = entryForIndex.getY() * phaseY;
            transformer.pointValuesToPixel(this.e);
            Object data = entryForIndex.getData();
            if ("LINE_PATH".equals(data != null ? (String) data : "")) {
                Paint paint = new Paint();
                paint.setColor(BaseApplication.getContext().getResources().getColor(R$color.colorButtonNormal));
                paint.setStrokeWidth(1.0f);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                Path path = new Path();
                float[] fArr = this.e;
                path.moveTo(fArr[0], fArr[1]);
                path.lineTo(this.e[0], this.mViewPortHandler.contentBottom());
                canvas.drawPath(path, paint);
                path.reset();
            }
            if (!this.mViewPortHandler.isInBoundsRight(this.e[0])) {
                return;
            }
            if (this.mViewPortHandler.isInBoundsLeft(this.e[0]) && this.mViewPortHandler.isInBoundsY(this.e[1]) && (cFe_ = dVar.cFe_(i3)) != null) {
                float[] fArr2 = this.e;
                canvas.drawBitmap(cFe_, fArr2[0] - f, fArr2[1] - f, (Paint) null);
            }
        }
    }

    class d {
        private Bitmap[] b;
        private Path c;

        private d() {
            this.c = new Path();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(ILineDataSet iLineDataSet) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            Bitmap[] bitmapArr = this.b;
            if (bitmapArr == null) {
                this.b = new Bitmap[circleColorCount];
            } else {
                if (bitmapArr.length == circleColorCount) {
                    return false;
                }
                this.b = new Bitmap[circleColorCount];
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(ILineDataSet iLineDataSet, boolean z, boolean z2) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            float circleRadius = iLineDataSet.getCircleRadius();
            float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
            for (int i = 0; i < circleColorCount; i++) {
                int i2 = (int) (circleRadius * 2.1d);
                Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(createBitmap);
                this.b[i] = createBitmap;
                nqi.this.mRenderPaint.setColor(iLineDataSet.getCircleColor(i));
                if (!z2) {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, nqi.this.mRenderPaint);
                    if (z) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, nqi.this.mCirclePaintInner);
                    }
                } else {
                    this.c.reset();
                    this.c.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
                    this.c.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
                    canvas.drawPath(this.c, nqi.this.mRenderPaint);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap cFe_(int i) {
            Bitmap[] bitmapArr = this.b;
            return bitmapArr[i % bitmapArr.length];
        }
    }
}

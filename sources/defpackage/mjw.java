package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class mjw extends LineChartRenderer {

    /* renamed from: a, reason: collision with root package name */
    private float f15028a;
    private HashMap<IDataSet, c> b;
    private float d;
    private float[] e;

    public mjw(LineDataProvider lineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(lineDataProvider, chartAnimator, viewPortHandler);
        this.d = 0.0f;
        this.f15028a = 0.0f;
        this.e = new float[2];
        this.b = new HashMap<>(0);
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
                    this.mXBounds.set(this.mChart, iLineDataSet);
                    float[] generateTransformedValuesLine = transformer.generateTransformedValuesLine(iLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    MPPointF mPPointF = MPPointF.getInstance(iLineDataSet.getIconsOffset());
                    mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                    mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                    for (int i2 = 0; i2 < generateTransformedValuesLine.length && this.mViewPortHandler.isInBoundsRight(generateTransformedValuesLine[i2]); i2 += 2) {
                        cjo_(new int[]{i, i2}, generateTransformedValuesLine, iLineDataSet, mPPointF, canvas);
                    }
                    MPPointF.recycleInstance(mPPointF);
                }
            }
        }
    }

    private void cjo_(int[] iArr, float[] fArr, ILineDataSet iLineDataSet, MPPointF mPPointF, Canvas canvas) {
        int i = iArr[1];
        float f = fArr[i];
        int i2 = i + 1;
        float f2 = fArr[i2];
        if (i == 0) {
            this.d = f;
        } else if (i2 == fArr.length - 1) {
            this.f15028a = f;
        } else {
            LogUtil.c("HwHealthAchieveReportLineRender", "dealLineLoop positionIndex is normal");
        }
        if (this.mViewPortHandler.isInBoundsLeft(f) && this.mViewPortHandler.isInBoundsY(f2)) {
            int i3 = i / 2;
            int i4 = i3 + this.mXBounds.min;
            Entry entryForIndex = iLineDataSet.getEntryForIndex(i4);
            Object data = entryForIndex.getData();
            String str = data instanceof String ? (String) data : "";
            if (iLineDataSet.isDrawValuesEnabled()) {
                if ("LINE_PATH_BOLD_START".equals(str)) {
                    return;
                }
                if ("DASH_PATH_END".equals(str)) {
                    iLineDataSet.setValueTextSize(10.0f);
                    applyValueTextStyle(iLineDataSet);
                    drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i4, f, f2 + Utils.convertDpToPixel(15.0f), Color.parseColor(Constants.CHOOSE_TEXT_COLOR));
                } else if ("DASH_PATH".equals(str)) {
                    iLineDataSet.setValueTextSize(8.0f);
                    applyValueTextStyle(iLineDataSet);
                    drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i4, f, f2 + Utils.convertDpToPixel(13.0f), iLineDataSet.getValueTextColor(i3));
                } else {
                    IValueFormatter valueFormatter = iLineDataSet.getValueFormatter();
                    float y = entryForIndex.getY();
                    float b = nqe.b(fArr, i, entryForIndex.getY());
                    float convertDpToPixel = Utils.convertDpToPixel(8.0f);
                    drawValue(canvas, valueFormatter, y, entryForIndex, i4, b + f, (f2 - convertDpToPixel) + nqe.a(fArr, i, entryForIndex.getY()), iLineDataSet.getValueTextColor(i3));
                }
            }
            if (entryForIndex.getIcon() == null || !iLineDataSet.isDrawIconsEnabled()) {
                return;
            }
            Drawable icon = entryForIndex.getIcon();
            Utils.drawImage(canvas, icon, (int) (f + mPPointF.x), (int) (f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }
    }

    @Override // com.github.mikephil.charting.renderer.LineChartRenderer
    public void drawCircles(Canvas canvas) {
        c cVar;
        this.mRenderPaint.setStyle(Paint.Style.FILL);
        float phaseY = this.mAnimator.getPhaseY();
        float[] fArr = this.e;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        for (T t : this.mChart.getLineData().getDataSets()) {
            if (t.isVisible() && t.isDrawCirclesEnabled() && t.getEntryCount() != 0) {
                this.mCirclePaintInner.setColor(t.getCircleHoleColor());
                Transformer transformer = this.mChart.getTransformer(t.getAxisDependency());
                this.mXBounds.set(this.mChart, t);
                float circleRadius = t.getCircleRadius();
                float circleHoleRadius = t.getCircleHoleRadius();
                boolean z = t.isDrawCircleHoleEnabled() && circleHoleRadius < circleRadius && circleHoleRadius > 0.0f;
                boolean z2 = z && t.getCircleHoleColor() == 1122867;
                if (this.b.containsKey(t)) {
                    cVar = this.b.get(t);
                } else {
                    cVar = new c();
                    this.b.put(t, cVar);
                }
                c cVar2 = cVar;
                if (cVar2.a(t)) {
                    cVar2.d(t, z, z2);
                }
                int i = this.mXBounds.min;
                for (int i2 = this.mXBounds.range + this.mXBounds.min; i <= i2; i2 = i2) {
                    Entry entryForIndex = t.getEntryForIndex(i);
                    if (entryForIndex == null) {
                        break;
                    }
                    this.e[0] = entryForIndex.getX();
                    this.e[1] = entryForIndex.getY() * phaseY;
                    transformer.pointValuesToPixel(this.e);
                    cjp_(canvas, entryForIndex, i, circleRadius, cVar2);
                    i++;
                }
            }
        }
    }

    private void cjp_(Canvas canvas, Entry entry, int i, float f, c cVar) {
        Bitmap cjr_;
        Object data = entry.getData();
        if ("LINE_PATH".equals(data instanceof String ? (String) data : "")) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#1A000000"));
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
        if (this.mViewPortHandler.isInBoundsRight(this.e[0]) && this.mViewPortHandler.isInBoundsLeft(this.e[0]) && this.mViewPortHandler.isInBoundsY(this.e[1]) && (cjr_ = cVar.cjr_(i)) != null) {
            float[] fArr2 = this.e;
            canvas.drawBitmap(cjr_, fArr2[0] - f, fArr2[1] - f, (Paint) null);
        }
    }

    @Override // com.github.mikephil.charting.renderer.LineChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        LineData lineData = this.mChart.getLineData();
        for (Highlight highlight : highlightArr) {
            ILineDataSet iLineDataSet = (ILineDataSet) lineData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineDataSet != null && iLineDataSet.isHighlightEnabled()) {
                Entry entryForXValue = iLineDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iLineDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iLineDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                }
            }
        }
    }

    class c {
        private Bitmap[] c;
        private Path d;

        private c() {
            this.d = new Path();
        }

        protected boolean a(ILineDataSet iLineDataSet) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            Bitmap[] bitmapArr = this.c;
            if (bitmapArr == null) {
                this.c = new Bitmap[circleColorCount];
            } else {
                if (bitmapArr.length == circleColorCount) {
                    return false;
                }
                this.c = new Bitmap[circleColorCount];
            }
            return true;
        }

        protected void d(ILineDataSet iLineDataSet, boolean z, boolean z2) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            float circleRadius = iLineDataSet.getCircleRadius();
            float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
            int i = (int) (circleRadius * 2.1d);
            for (int i2 = 0; i2 < circleColorCount; i2++) {
                Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(createBitmap);
                this.c[i2] = createBitmap;
                mjw.this.mRenderPaint.setColor(iLineDataSet.getCircleColor(i2));
                if (!z2) {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, mjw.this.mRenderPaint);
                    if (z) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, mjw.this.mCirclePaintInner);
                    }
                } else {
                    this.d.reset();
                    this.d.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
                    this.d.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
                    canvas.drawPath(this.d, mjw.this.mRenderPaint);
                }
            }
        }

        protected Bitmap cjr_(int i) {
            Bitmap[] bitmapArr = this.c;
            return bitmapArr[i % bitmapArr.length];
        }
    }

    public float b() {
        return this.d;
    }

    public float d() {
        return this.f15028a;
    }

    @Override // com.github.mikephil.charting.renderer.LineRadarRenderer
    public void drawFilledPath(Canvas canvas, Path path, Drawable drawable) {
        int save = canvas.save();
        canvas.clipPath(path);
        drawable.setBounds((int) this.mViewPortHandler.contentLeft(), (int) this.mViewPortHandler.contentTop(), (int) this.mViewPortHandler.contentRight(), (int) this.mViewPortHandler.contentBottom());
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.github.mikephil.charting.data.Entry] */
    private void cjq_(ILineDataSet iLineDataSet, int i, int i2, Path path, Transformer transformer) {
        float contentBottom = this.mViewPortHandler.contentBottom();
        float phaseY = this.mAnimator.getPhaseY();
        boolean z = iLineDataSet.getMode() == LineDataSet.Mode.STEPPED;
        path.reset();
        ?? entryForIndex = iLineDataSet.getEntryForIndex(i);
        float[] fArr = {entryForIndex.getX(), entryForIndex.getY()};
        transformer.pointValuesToPixel(fArr);
        path.moveTo(fArr[0], contentBottom);
        path.lineTo(fArr[0], fArr[1] * phaseY);
        int i3 = i + 1;
        Object obj = null;
        while (i3 <= i2) {
            ?? entryForIndex2 = iLineDataSet.getEntryForIndex(i3);
            fArr[0] = entryForIndex2.getX();
            fArr[1] = entryForIndex2.getY();
            transformer.pointValuesToPixel(fArr);
            if (z && obj != null) {
                path.lineTo(fArr[0], fArr[1] * phaseY);
            }
            path.lineTo(fArr[0], fArr[1] * phaseY);
            i3++;
            obj = entryForIndex2;
        }
        if (obj != null) {
            path.lineTo(fArr[0], contentBottom);
        }
        path.close();
    }

    @Override // com.github.mikephil.charting.renderer.LineChartRenderer
    public void drawLinearFill(Canvas canvas, ILineDataSet iLineDataSet, Transformer transformer, BarLineScatterCandleBubbleRenderer.XBounds xBounds) {
        int i;
        int i2;
        Path path = this.mGenerateFilledPathBuffer;
        int i3 = xBounds.min;
        int i4 = xBounds.min + xBounds.range;
        int i5 = 0;
        do {
            i = i3 + (i5 * 128);
            int i6 = i + 128;
            i2 = i6 > i4 ? i4 : i6;
            if (i <= i2) {
                cjq_(iLineDataSet, i, i2, path, transformer);
                Drawable fillDrawable = iLineDataSet.getFillDrawable();
                if (fillDrawable != null) {
                    drawFilledPath(canvas, path, fillDrawable);
                } else {
                    drawFilledPath(canvas, path, iLineDataSet.getFillColor(), iLineDataSet.getFillAlpha());
                }
            }
            i5++;
        } while (i <= i2);
    }
}

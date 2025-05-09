package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes9.dex */
public class mjr extends Transformer {

    /* renamed from: a, reason: collision with root package name */
    protected Matrix f15024a;
    private LineChart e;

    public mjr(ViewPortHandler viewPortHandler, LineChart lineChart) {
        super(viewPortHandler);
        this.f15024a = new Matrix();
        this.mViewPortHandler = viewPortHandler;
        this.e = lineChart;
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void prepareMatrixValuePx(float f, float f2, float f3, float f4) {
        float contentWidth = f2 > 0.0f ? this.mViewPortHandler.contentWidth() / f2 : 0.0f;
        float contentHeight = f3 > 0.0f ? this.mViewPortHandler.contentHeight() / f3 : 0.0f;
        if (Float.isInfinite(contentWidth)) {
            contentWidth = 0.0f;
        }
        if (Float.isInfinite(contentHeight)) {
            contentHeight = 0.0f;
        }
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-f, -f4);
        this.mMatrixValueToPx.postScale(contentWidth, -contentHeight);
        this.f15024a.reset();
        this.f15024a.postScale(1.0f, -1.0f);
        this.f15024a.postTranslate(0.0f, -this.e.getViewPortHandler().getContentRect().height());
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.f15024a);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pointValuesToPixel(float[] fArr) {
        this.mMatrixValueToPx.mapPoints(fArr);
        this.f15024a.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().mapPoints(fArr);
        this.mMatrixOffset.mapPoints(fArr);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixel(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.f15024a.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectToPixelPhase(RectF rectF, float f) {
        rectF.top *= f;
        rectF.bottom *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.f15024a.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectToPixelPhaseHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.f15024a.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixelHorizontal(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.f15024a.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixelHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.f15024a.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.f15024a.invert(matrix);
        matrix.mapPoints(fArr);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(fArr);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public Matrix getValueToPixelMatrix() {
        Matrix matrix = new Matrix();
        matrix.set(this.mMatrixValueToPx);
        matrix.postConcat(this.f15024a);
        matrix.postConcat(this.mViewPortHandler.getMatrixTouch());
        matrix.postConcat(this.mMatrixOffset);
        return matrix;
    }
}

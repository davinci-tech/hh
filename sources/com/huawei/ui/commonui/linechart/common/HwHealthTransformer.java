package com.huawei.ui.commonui.linechart.common;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import defpackage.nng;

/* loaded from: classes6.dex */
public class HwHealthTransformer extends Transformer {

    /* renamed from: a, reason: collision with root package name */
    private BusinessMatrixGenerator f8876a;
    private Context b;
    private Matrix c;
    private Matrix d;
    protected Matrix e;

    public interface BusinessMatrixGenerator {
        Matrix generateBusinessMatrix(Matrix matrix);
    }

    public HwHealthTransformer(Context context, ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
        this.e = new Matrix();
        this.f8876a = null;
        this.b = null;
        this.d = new Matrix();
        this.c = new Matrix();
        this.b = context;
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void prepareMatrixValuePx(float f, float f2, float f3, float f4) {
        super.prepareMatrixValuePx(f, f2, f3, f4);
        this.e.reset();
        d(f, f2, f3, f4);
        this.d.reset();
        if (nng.d(this.b)) {
            this.d.postScale(-1.0f, 1.0f);
            this.d.postTranslate(this.mViewPortHandler.getContentRect().width(), 0.0f);
        }
    }

    protected void d(float f, float f2, float f3, float f4) {
        BusinessMatrixGenerator businessMatrixGenerator = this.f8876a;
        if (businessMatrixGenerator != null) {
            Matrix matrix = this.e;
            matrix.set(businessMatrixGenerator.generateBusinessMatrix(matrix));
        }
    }

    public void a(BusinessMatrixGenerator businessMatrixGenerator) {
        this.f8876a = businessMatrixGenerator;
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pointValuesToPixel(float[] fArr) {
        this.mMatrixValueToPx.mapPoints(fArr);
        this.e.mapPoints(fArr);
        this.d.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().mapPoints(fArr);
        this.mMatrixOffset.mapPoints(fArr);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pathValueToPixel(Path path) {
        if (path == null) {
            LogUtil.b("HwHealthTransformer", "pathValueToPixel path == null");
            return;
        }
        path.transform(this.mMatrixValueToPx);
        path.transform(this.e);
        path.transform(this.d);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixel(RectF rectF) {
        if (rectF == null) {
            LogUtil.b("HwHealthTransformer", "rectValueToPixel rectF == null");
            return;
        }
        this.mMatrixValueToPx.mapRect(rectF);
        this.e.mapRect(rectF);
        this.d.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectToPixelPhase(RectF rectF, float f) {
        if (rectF == null) {
            LogUtil.b("HwHealthTransformer", "rectToPixelPhase rectF == null");
            return;
        }
        rectF.top *= f;
        rectF.bottom *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.e.mapRect(rectF);
        this.d.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectToPixelPhaseHorizontal(RectF rectF, float f) {
        cBB_(rectF, f);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixelHorizontal(RectF rectF) {
        if (rectF == null) {
            LogUtil.b("HwHealthTransformer", "rectValueToPixelHorizontal rectF == null");
            return;
        }
        this.mMatrixValueToPx.mapRect(rectF);
        this.e.mapRect(rectF);
        this.d.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void rectValueToPixelHorizontal(RectF rectF, float f) {
        cBB_(rectF, f);
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public Matrix getValueToPixelMatrix() {
        this.c.set(this.mMatrixValueToPx);
        this.c.postConcat(this.e);
        this.c.postConcat(this.d);
        this.c.postConcat(this.mViewPortHandler.getMatrixTouch());
        this.c.postConcat(this.mMatrixOffset);
        return this.c;
    }

    @Override // com.github.mikephil.charting.utils.Transformer
    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.d.invert(matrix);
        matrix.mapPoints(fArr);
        this.e.invert(matrix);
        matrix.mapPoints(fArr);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(fArr);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.github.mikephil.charting.data.Entry] */
    public float[] e(IHwHealthLineDataSet iHwHealthLineDataSet, float f, float f2, int i, int i2) {
        int i3 = (((int) ((i2 - i) * f)) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesLine.length != i3) {
            this.valuePointsForGenerateTransformedValuesLine = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesLine;
        if (iHwHealthLineDataSet == null) {
            LogUtil.b("HwHealthTransformer", "generateTransformedValuesLine data == null");
            return fArr;
        }
        int entryCount = iHwHealthLineDataSet.getEntryCount();
        for (int i4 = 0; i4 < i3; i4 += 2) {
            int i5 = (i4 / 2) + i;
            if (i5 >= entryCount) {
                break;
            }
            ?? entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i5);
            if (entryForIndex != 0) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public void cBB_(RectF rectF, float f) {
        if (rectF == null) {
            LogUtil.b("HwHealthTransformer", "rectValueToPixelHorizontal rectF == null");
            return;
        }
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.e.mapRect(rectF);
        this.d.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }
}

package defpackage;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;

/* loaded from: classes6.dex */
public class nly {

    /* renamed from: a, reason: collision with root package name */
    private float f15378a;
    private float c;
    private float d;
    private float e;
    private float f;
    private float h;
    private float i;
    private float j;
    private Matrix b = new Matrix();
    private int o = 40;
    private int g = HeartRateThresholdConfig.HEART_RATE_LIMIT;

    public void d(float f, float f2, float f3, float f4) {
        this.c = f;
        this.f15378a = f2;
        this.d = f3;
        this.e = f4;
    }

    public void c(float f, float f2, float f3, float f4) {
        this.i = f;
        this.h = f2;
        this.f = f3;
        this.j = f4;
    }

    public void d(int i, int i2) {
        this.o = i;
        this.g = i2;
    }

    public void c() {
        this.b.reset();
        float[] fArr = {0.0f, this.g, 501.0f, this.o};
        this.b.postTranslate(-this.c, -this.d);
        this.b.mapPoints(fArr);
        Log.i("ChartContentTransform", "matrix anchor(postTranslate -mDataAreaMinX,-mDataAreaMinY):" + fArr[0] + " " + fArr[1] + " " + fArr[2] + " " + fArr[3]);
        this.b.postScale(1.0f, -1.0f);
        this.b.mapPoints(fArr);
        Log.i("ChartContentTransform", "matrix anchor(postScale 1 -1):" + fArr[0] + " " + fArr[1] + " " + fArr[2] + " " + fArr[3]);
        float f = this.e - this.d;
        this.b.postTranslate(0.0f, f);
        this.b.mapPoints(fArr);
        Log.i("ChartContentTransform", "matrix anchor(postTranslate 0 dataAreaHeight):" + fArr[0] + " " + fArr[1] + " " + fArr[2] + " " + fArr[3]);
        this.b.postScale((this.h - this.i) / (this.f15378a - this.c), (this.j - this.f) / f);
        this.b.mapPoints(fArr);
        Log.i("ChartContentTransform", "matrix anchor(postScale drawingAreaWidth/dataAreaWidth drawingAreaHeight/dataAreaHeight):" + fArr[0] + " " + fArr[1] + " " + fArr[2] + " " + fArr[3]);
    }

    public void b(float[] fArr) {
        this.b.mapPoints(fArr);
    }

    public void cAf_(RectF rectF) {
        this.b.mapRect(rectF);
    }
}

package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kas;
import java.lang.reflect.Array;

/* loaded from: classes8.dex */
public class kav {
    public static Bitmap bMJ_(kas kasVar, Bitmap bitmap) {
        int i = a(kasVar)[0];
        int i2 = a(kasVar)[1];
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paint.setColor(Color.parseColor("#80868686"));
        bMM_(canvas, paint, new b(i, i2, 1), kasVar);
        Bitmap bML_ = bML_(bitmap, kasVar.j(), kasVar.d(), kasVar);
        paint.reset();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bML_, (i - kasVar.j()) / 2.0f, (i2 - kasVar.d()) / 2.0f, paint);
        return createBitmap;
    }

    private static int[] a(kas kasVar) {
        return new int[]{kasVar.j() + 6, kasVar.d() + 6};
    }

    private static void bMM_(Canvas canvas, Paint paint, b bVar, kas kasVar) {
        if (kasVar.g() == 1) {
            bMN_(canvas, paint, bVar, kasVar);
        } else if (kasVar.g() == 2) {
            bMO_(canvas, paint, bVar, kasVar);
        } else {
            LogUtil.b("CropImageUtil", "roundedCutType is not supported in drawRoundRec");
        }
    }

    private static void bMN_(Canvas canvas, Paint paint, b bVar, kas kasVar) {
        kas.e c = kasVar.c();
        if (c == null) {
            LogUtil.b("CropImageUtil", "bezierCurvePoints is null");
            return;
        }
        float[][] d = bVar.e == 2 ? c.d() : c.a();
        if (d != null && d.length >= 4) {
            float[] fArr = d[0];
            if (fArr.length >= 2) {
                PointF pointF = new PointF(fArr[0], fArr[1]);
                float[] fArr2 = d[3];
                PointF pointF2 = new PointF(fArr2[0], fArr2[1]);
                if (bVar.e == 1) {
                    pointF.x += 1.5f;
                    pointF2.y += 1.5f;
                }
                float[] fArr3 = d[1];
                PointF pointF3 = new PointF(fArr3[0], fArr3[1]);
                float[] fArr4 = d[2];
                PointF[] pointFArr = {pointF, pointF3, new PointF(fArr4[0], fArr4[1]), pointF2};
                PointF[][] pointFArr2 = (PointF[][]) Array.newInstance((Class<?>) PointF.class, 4, 4);
                pointFArr2[0] = pointFArr;
                pointFArr2[1] = bMP_(1, bVar.b, bVar.f14240a, pointFArr);
                pointFArr2[2] = bMP_(2, bVar.b, bVar.f14240a, pointFArr);
                pointFArr2[3] = bMP_(3, bVar.b, bVar.f14240a, pointFArr);
                Path path = new Path();
                path.moveTo(pointFArr2[0][0].x, pointFArr2[0][0].y);
                for (int i = 0; i < pointFArr2.length; i++) {
                    path.cubicTo(pointFArr2[i][1].x, pointFArr2[i][1].y, pointFArr2[i][2].x, pointFArr2[i][2].y, pointFArr2[i][3].x, pointFArr2[i][3].y);
                    if (i != pointFArr2.length - 1) {
                        int i2 = i + 1;
                        path.lineTo(pointFArr2[i2][0].x, pointFArr2[i2][0].y);
                    }
                }
                path.close();
                canvas.drawPath(path, paint);
                return;
            }
        }
        LogUtil.b("CropImageUtil", "bezierCurvePoint size is incorrect");
    }

    private static PointF[] bMP_(int i, int i2, int i3, PointF[] pointFArr) {
        PointF[] pointFArr2 = new PointF[4];
        if (i == 1) {
            float f = i2;
            pointFArr2[0] = new PointF(f - pointFArr[3].x, pointFArr[3].y);
            pointFArr2[1] = new PointF(f - pointFArr[2].x, pointFArr[2].y);
            pointFArr2[2] = new PointF(f - pointFArr[1].x, pointFArr[1].y);
            pointFArr2[3] = new PointF(f - pointFArr[0].x, pointFArr[0].y);
        }
        if (i == 2) {
            float f2 = i2;
            float f3 = i3;
            pointFArr2[0] = new PointF(f2 - pointFArr[0].x, f3 - pointFArr[0].y);
            pointFArr2[1] = new PointF(f2 - pointFArr[1].x, f3 - pointFArr[1].y);
            pointFArr2[2] = new PointF(f2 - pointFArr[2].x, f3 - pointFArr[2].y);
            pointFArr2[3] = new PointF(f2 - pointFArr[3].x, f3 - pointFArr[3].y);
        }
        if (i == 3) {
            float f4 = i3;
            pointFArr2[0] = new PointF(pointFArr[3].x, f4 - pointFArr[3].y);
            pointFArr2[1] = new PointF(pointFArr[2].x, f4 - pointFArr[2].y);
            pointFArr2[2] = new PointF(pointFArr[1].x, f4 - pointFArr[1].y);
            pointFArr2[3] = new PointF(pointFArr[0].x, f4 - pointFArr[0].y);
        }
        return pointFArr2;
    }

    private static void bMO_(Canvas canvas, Paint paint, b bVar, kas kasVar) {
        float[] i = kasVar.i();
        if (i == null || i.length < 2) {
            LogUtil.b("CropImageUtil", "radians size is incorrect");
        } else {
            canvas.drawRoundRect(new RectF(1.5f, 1.5f, bVar.b - 1.5f, bVar.f14240a - 1.5f), i[0] + 3.0f, i[1] + 3.0f, paint);
        }
    }

    private static Bitmap bML_(Bitmap bitmap, int i, int i2, kas kasVar) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        if (kasVar.g() == 1) {
            bMN_(canvas, paint, new b(i, i2, 2), kasVar);
        } else if (kasVar.g() == 2) {
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, i, i2), kasVar.i()[0], kasVar.i()[1], paint);
        } else {
            LogUtil.b("CropImageUtil", "roundedCutType is not supported cropSquareImage");
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        int e = kasVar.e();
        canvas.drawBitmap(bitmap, kasVar.f() - e, kasVar.f() - e, paint);
        return createBitmap;
    }

    public static Bitmap bMI_(kas kasVar, Bitmap bitmap) {
        int i = a(kasVar)[0];
        int i2 = a(kasVar)[1];
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paint.setColor(Color.parseColor("#80868686"));
        canvas.drawCircle(i / 2.0f, i2 / 2.0f, Math.min(i - 3, i2 - 3) / 2.0f, paint);
        Bitmap bMK_ = bMK_(kasVar, bitmap);
        paint.reset();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bMK_, (i - kasVar.j()) / 2.0f, (i2 - kasVar.d()) / 2.0f, paint);
        return createBitmap;
    }

    private static Bitmap bMK_(kas kasVar, Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        int j = kasVar.j();
        int d = kasVar.d();
        Bitmap createBitmap = Bitmap.createBitmap(j, d, bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle(j / 2.0f, d / 2.0f, Math.min(j, d) / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        int e = kasVar.e();
        canvas.drawBitmap(bitmap, kasVar.f() - e, kasVar.h() - e, paint);
        return createBitmap;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f14240a;
        private int b;
        private int e;

        b(int i, int i2, int i3) {
            this.b = i;
            this.f14240a = i2;
            this.e = i3;
        }
    }
}

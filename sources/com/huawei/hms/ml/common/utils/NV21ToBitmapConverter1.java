package com.huawei.hms.ml.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import java.util.Locale;

/* loaded from: classes9.dex */
public class NV21ToBitmapConverter1 {
    private Context applicationContext;
    private Allocation in;
    private Allocation out;
    private RenderScript renderScript;
    private Type.Builder rgbaType;
    private ScriptIntrinsicYuvToRGB yuvToRgbIntrinsic;
    private Type.Builder yuvType;
    private int width = -1;
    private int height = -1;
    private int length = -1;

    public NV21ToBitmapConverter1(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            this.applicationContext = context;
        } else {
            this.applicationContext = applicationContext;
        }
        RenderScript create = RenderScript.create(this.applicationContext);
        this.renderScript = create;
        if (create != null) {
            this.yuvToRgbIntrinsic = ScriptIntrinsicYuvToRGB.create(create, Element.U8_4(create));
        }
    }

    public Context getApplicationContext() {
        Context context = this.applicationContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("initial must be called first");
    }

    public Bitmap convertYUVtoRGB(byte[] bArr, int i, int i2) {
        if (this.yuvType == null) {
            RenderScript renderScript = this.renderScript;
            Type.Builder x = new Type.Builder(renderScript, Element.U8(renderScript)).setX(bArr.length);
            this.yuvType = x;
            this.in = Allocation.createTyped(this.renderScript, x.create(), 1);
            RenderScript renderScript2 = this.renderScript;
            Type.Builder y = new Type.Builder(renderScript2, Element.RGBA_8888(renderScript2)).setX(i).setY(i2);
            this.rgbaType = y;
            this.out = Allocation.createTyped(this.renderScript, y.create(), 1);
        }
        this.in.copyFrom(bArr);
        ScriptIntrinsicYuvToRGB scriptIntrinsicYuvToRGB = this.yuvToRgbIntrinsic;
        if (scriptIntrinsicYuvToRGB != null) {
            scriptIntrinsicYuvToRGB.setInput(this.in);
            this.yuvToRgbIntrinsic.forEach(this.out);
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.out.copyTo(createBitmap);
        return createBitmap;
    }

    public Matrix getTransformationMatrix(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3) {
        Matrix matrix = new Matrix();
        if (i5 != 0) {
            if (i5 % 90 != 0) {
                throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Rotation of %d", Integer.valueOf(i5)));
            }
            matrix.postTranslate((-i) / 2.0f, (-i2) / 2.0f);
            matrix.postRotate(i5);
        }
        boolean z4 = (Math.abs(i5) + 90) % 180 == 0;
        int i6 = z4 ? i2 : i;
        if (!z4) {
            i = i2;
        }
        int i7 = z ? -1 : 1;
        int i8 = z2 ? -1 : 1;
        if (i6 != i3 || i != i4) {
            float f = (i7 * i3) / i6;
            float f2 = (i8 * i4) / i;
            if (z3) {
                float max = Math.max(Math.abs(f), Math.abs(f2));
                matrix.postScale(max, max);
            } else {
                matrix.postScale(f, f2);
            }
        }
        if (i5 != 0) {
            float f3 = i3 / 2.0f;
            float f4 = i4 / 2.0f;
            matrix.postTranslate(f3, f4);
            matrix.postScale(i7, i8, f3, f4);
        }
        return matrix;
    }

    public Bitmap convert(byte[] bArr, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3) {
        recreateIfNeed(bArr, i, i2, i5);
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(convertYUVtoRGB(bArr, i, i2), getTransformationMatrix(i, i2, i3, i4, i5, z, z2, z3), null);
        return createBitmap;
    }

    public Bitmap convert(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        return convert(bArr, i, i2, i3, i4, i5, false, false, false);
    }

    private void recreateIfNeed(byte[] bArr, int i, int i2, int i3) {
        if (this.width == i && this.height == i2 && this.length == bArr.length) {
            return;
        }
        this.width = i;
        this.height = i2;
        this.length = bArr.length;
        this.yuvType = null;
        this.rgbaType = null;
    }
}

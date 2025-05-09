package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import java.security.MessageDigest;

/* loaded from: classes6.dex */
public class nrp extends CenterCrop {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15463a = true;
    private float b;
    private float c;
    private float d;
    private float e;
    private float i;

    @Override // com.bumptech.glide.load.resource.bitmap.CenterCrop, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public nrp(Context context, int i) {
        this.e = i;
    }

    public nrp(float f, float f2, float f3, float f4) {
        this.d = f;
        this.i = f2;
        this.c = f3;
        this.b = f4;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.CenterCrop, com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        if (this.f15463a) {
            return TransformationUtils.roundedCorners(bitmapPool, bitmap, this.d, this.i, this.b, this.c);
        }
        return cKc_(bitmapPool, super.transform(bitmapPool, bitmap, i, i2));
    }

    private Bitmap cKc_(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null || bitmapPool == null) {
            return null;
        }
        Bitmap bitmap2 = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        float f = this.e;
        canvas.drawRoundRect(rectF, f, f, paint);
        return bitmap2;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.CenterCrop, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.bumptech.glide.load.resource.bitmap.CenterCrop, com.bumptech.glide.load.Key
    public int hashCode() {
        return super.hashCode();
    }
}

package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class nrz {
    public static BitmapDrawable cKn_(Context context, int i) {
        Resources resources;
        Bitmap decodeResource;
        if (context == null || (resources = context.getResources()) == null || (decodeResource = BitmapFactory.decodeResource(resources, i)) == null) {
            return null;
        }
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), decodeResource.getConfig());
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(createBitmap);
        matrix.setScale(-1.0f, 1.0f);
        matrix.postTranslate(decodeResource.getWidth(), 0.0f);
        canvas.drawBitmap(decodeResource, matrix, paint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, createBitmap);
        decodeResource.recycle();
        return bitmapDrawable;
    }

    public static BitmapDrawable cKm_(Context context, Drawable drawable) {
        BitmapDrawable bitmapDrawable;
        if (drawable == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(new Canvas(createBitmap));
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        matrix.postTranslate(createBitmap.getWidth(), 0.0f);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), createBitmap.getConfig());
        new Canvas(createBitmap2).drawBitmap(createBitmap, matrix, paint);
        if (context == null) {
            bitmapDrawable = new BitmapDrawable(BaseApplication.getContext().getResources(), createBitmap2);
        } else {
            bitmapDrawable = new BitmapDrawable(context.getResources(), createBitmap2);
        }
        createBitmap.recycle();
        return bitmapDrawable;
    }

    public static Drawable cKl_(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        return LanguageUtil.bc(context) ? cKm_(context, drawable) : drawable;
    }
}

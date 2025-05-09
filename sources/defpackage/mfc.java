package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes8.dex */
public class mfc {
    public static Bitmap cgv_(Context context, String str) {
        String c;
        if (context == null || !GeneralUtil.isSafePath(str) || (c = CommonUtil.c(str)) == null || !new File(c).exists()) {
            return null;
        }
        Bitmap cgx_ = cgx_(c);
        return cgx_ != null ? cgw_(cgx_) : cgx_;
    }

    private static Bitmap cgx_(String str) {
        File file = new File(str);
        Bitmap bitmap = null;
        if (!file.exists()) {
            return null;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(canonicalPath, options);
            options.inJustDecodeBounds = false;
            if (options.outWidth >= 200 || options.outHeight >= 200) {
                options.inSampleSize = 2;
            } else {
                options.inSampleSize = 1;
            }
            if (decodeFile == null || decodeFile.isRecycled()) {
                bitmap = decodeFile;
            } else {
                decodeFile.recycle();
            }
            try {
                return BitmapFactory.decodeFile(canonicalPath, options);
            } catch (OutOfMemoryError e) {
                LogUtil.b("AchieveImageLoader", "getThumbnailImage OutOfMemoryError = ", e.getMessage());
                return bitmap;
            }
        } catch (IOException e2) {
            LogUtil.b("file error", e2.getMessage());
            return null;
        }
    }

    public static Bitmap cgw_(Bitmap bitmap) {
        float f;
        float f2;
        float f3;
        float f4;
        if (bitmap == null) {
            LogUtil.a("AchieveImageLoader", "bitmap is null");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f4 = width;
            f = f4;
            f2 = f4 / 2.0f;
            f3 = 0.0f;
        } else {
            f = height;
            f2 = f / 2.0f;
            f3 = (width - height) / 2.0f;
            f4 = width - f3;
        }
        int i = (int) 0.0f;
        int i2 = (int) f;
        Rect rect = new Rect((int) f3, i, (int) f4, i2);
        Rect rect2 = new Rect(i, i, i2, i2);
        return cgu_(bitmap, new RectF(rect2), f2, rect, rect2);
    }

    private static Bitmap cgu_(Bitmap bitmap, RectF rectF, float f, Rect rect, Rect rect2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            width = height;
        }
        Paint paint = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        paint.setAntiAlias(true);
        paint.setColor(-12434878);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }
}

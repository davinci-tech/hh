package defpackage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import java.io.ByteArrayInputStream;
import java.io.Closeable;

/* loaded from: classes7.dex */
public class mg {
    public static Drawable aY_(String str, Context context) {
        return aZ_(str, context, 480);
    }

    public static Drawable aZ_(String str, Context context, int i) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(ku.d(str));
        } catch (Throwable unused) {
            byteArrayInputStream = null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (i <= 0) {
                i = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            }
            options.inDensity = i;
            options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(byteArrayInputStream, null, options));
            e(byteArrayInputStream);
            return bitmapDrawable;
        } catch (Throwable unused2) {
            e(byteArrayInputStream);
            return null;
        }
    }

    public static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}

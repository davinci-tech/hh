package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import java.io.File;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes8.dex */
public class aeq {
    public static VectorDrawableCompat e(String str, aen aenVar) throws abv {
        try {
            VectorDrawableCompat d = VectorDrawableCompat.d(abo.d() + File.separator + str + WatchFaceConstant.XML_SUFFIX);
            d.gs_(gH_(aenVar, d.getIntrinsicWidth(), d.getIntrinsicHeight()));
            return d;
        } catch (IOException | XmlPullParserException unused) {
            Log.e("VectorDrawableUtils", "load vector drawable failed, file name: " + str);
            throw new abv("load vector drawable failed, file name: " + str);
        }
    }

    public static VectorDrawableCompat a(String str, aen aenVar, int i, int i2) throws abv {
        try {
            VectorDrawableCompat d = VectorDrawableCompat.d(abo.d() + File.separator + str + WatchFaceConstant.XML_SUFFIX);
            d.gs_(gH_(aenVar, i, i2));
            return d;
        } catch (IOException | XmlPullParserException unused) {
            Log.e("VectorDrawableUtils", "load vector drawable failed, file name: " + str);
            throw new abv("load vector drawable failed, file name: " + str);
        }
    }

    private static Matrix gH_(aen aenVar, float f, float f2) {
        if (aenVar == null) {
            return null;
        }
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        Matrix matrix = new Matrix();
        matrix.postScale(aenVar.a(), aenVar.a(), f3, f4);
        matrix.postRotate(aenVar.b(), f3, f4);
        matrix.postTranslate(aenVar.d() * f, aenVar.c() * f2);
        return matrix;
    }

    public static Bitmap gI_(VectorDrawableCompat vectorDrawableCompat) {
        return gJ_(vectorDrawableCompat, vectorDrawableCompat.getMinimumWidth(), vectorDrawableCompat.getMinimumHeight());
    }

    public static Bitmap gJ_(VectorDrawableCompat vectorDrawableCompat, int i, int i2) {
        vectorDrawableCompat.c(false);
        vectorDrawableCompat.setBounds(0, 0, i, i2);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        vectorDrawableCompat.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}

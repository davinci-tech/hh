package defpackage;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/* loaded from: classes9.dex */
public class tre {
    public static byte[] ffd_(Bitmap bitmap) {
        if (bitmap == null) {
            tos.e("BitmapUtil", "getBytesByBitmap bitmap == null");
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)) {
            tos.e("BitmapUtil", "The bitmap can't be reconstructed by passing a corresponding inputstream to BitmapFactory.");
            return new byte[0];
        }
        return byteArrayOutputStream.toByteArray();
    }
}

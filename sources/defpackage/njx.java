package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class njx {
    public static Bitmap cwt_(Context context, String str) {
        return BitmapFactory.decodeStream(d(context, str));
    }

    public static InputStream d(Context context, String str) {
        njw.c("AssetsHelper", njw.b());
        try {
            return context.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            njw.c("AssetsHelper", njw.b() + " e=" + e.getMessage());
            return null;
        }
    }
}

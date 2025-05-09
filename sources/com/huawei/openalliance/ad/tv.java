package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

/* loaded from: classes5.dex */
public final class tv {
    public static byte[] a(Context context, ts tsVar, int i) {
        byte[] bArr = new byte[0];
        if (tsVar == null) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Bitmap a2 = a(context, tsVar);
            if (a2 == null) {
                return bArr;
            }
            Bitmap a3 = a(a2);
            int i2 = 100;
            a3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            while (byteArrayOutputStream.toByteArray().length > i) {
                byteArrayOutputStream.reset();
                a3.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                i2 -= 5;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                ho.c("ShareUtil", "get bitmap arr failed, cause: %s", th.getClass().getSimpleName());
                return bArr;
            } finally {
                com.huawei.openalliance.ad.utils.cy.a(byteArrayOutputStream);
            }
        }
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            ho.c("ShareUtil", "class not fount %s", str);
            return false;
        }
    }

    public static String a(String str, int i) {
        return (TextUtils.isEmpty(str) || str.length() <= i) ? str : str.substring(0, i);
    }

    private static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        int min = Math.min(width, height);
        return Bitmap.createBitmap(bitmap, (width - min) / 2, (height - min) / 2, min, min);
    }

    public static Bitmap a(Context context, String str) {
        rt rtVar = new rt();
        rtVar.c(str);
        rtVar.b(false);
        return (Bitmap) com.huawei.openalliance.ad.utils.dc.a(new tp(new rr(context, rtVar)), 2000L, null);
    }

    private static Bitmap a(Context context, ts tsVar) {
        Bitmap a2 = a(context, tsVar.a());
        return a2 == null ? BitmapFactory.decodeStream(context.getResources().openRawResource(tsVar.e())) : a2;
    }
}

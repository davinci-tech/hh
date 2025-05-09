package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.BaseObj;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class ax {
    private static void a(BaseObj baseObj) {
        if (baseObj != null) {
            baseObj.destroy();
        }
    }

    public static Drawable a(Context context, Drawable drawable, float f, float f2) {
        String str;
        Drawable drawable2 = null;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            drawable2 = a(context, a(context, az.a(drawable), f, f2));
            ho.a("BlurUtil", "blurDrawable: duration %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return drawable2;
        } catch (OutOfMemoryError unused) {
            str = "OOM blur image";
            ho.c("BlurUtil", str);
            return drawable2;
        } catch (Throwable th) {
            str = "blur drawable exception " + th.getClass().getSimpleName();
            ho.c("BlurUtil", str);
            return drawable2;
        }
    }

    private static Drawable a(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009a A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap a(android.content.Context r5, android.graphics.Bitmap r6, float r7, float r8) {
        /*
            r0 = 0
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 <= 0) goto Lae
            r0 = 1103626240(0x41c80000, float:25.0)
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 > 0) goto Lae
            r0 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 < 0) goto Lae
            r0 = 0
            int r1 = r6.getWidth()     // Catch: java.lang.Throwable -> L6e
            float r1 = (float) r1     // Catch: java.lang.Throwable -> L6e
            float r1 = r1 / r8
            int r1 = java.lang.Math.round(r1)     // Catch: java.lang.Throwable -> L6e
            int r2 = r6.getHeight()     // Catch: java.lang.Throwable -> L6e
            float r2 = (float) r2     // Catch: java.lang.Throwable -> L6e
            float r2 = r2 / r8
            int r8 = java.lang.Math.round(r2)     // Catch: java.lang.Throwable -> L6e
            r2 = 0
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createScaledBitmap(r6, r1, r8, r2)     // Catch: java.lang.Throwable -> L6e
            android.renderscript.RenderScript r5 = android.renderscript.RenderScript.create(r5)     // Catch: java.lang.Throwable -> L6b
            android.renderscript.Element r8 = android.renderscript.Element.U8_4(r5)     // Catch: java.lang.Throwable -> L65
            android.renderscript.ScriptIntrinsicBlur r8 = android.renderscript.ScriptIntrinsicBlur.create(r5, r8)     // Catch: java.lang.Throwable -> L65
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createFromBitmap(r5, r6)     // Catch: java.lang.Throwable -> L63
            android.renderscript.Type r2 = r1.getType()     // Catch: java.lang.Throwable -> L5e
            android.renderscript.Allocation r0 = android.renderscript.Allocation.createTyped(r5, r2)     // Catch: java.lang.Throwable -> L5e
            r8.setRadius(r7)     // Catch: java.lang.Throwable -> L5e
            r8.setInput(r1)     // Catch: java.lang.Throwable -> L5e
            r8.forEach(r0)     // Catch: java.lang.Throwable -> L5e
            r0.copyTo(r6)     // Catch: java.lang.Throwable -> L5e
            a(r1)
            a(r0)
            a(r8)
            if (r5 == 0) goto L5d
            r5.destroy()
        L5d:
            return r6
        L5e:
            r7 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L74
        L63:
            r7 = move-exception
            goto L67
        L65:
            r7 = move-exception
            r8 = r0
        L67:
            r1 = r0
            r0 = r5
            r5 = r1
            goto L74
        L6b:
            r5 = move-exception
            r7 = r5
            goto L71
        L6e:
            r5 = move-exception
            r7 = r5
            r6 = r0
        L71:
            r5 = r0
            r8 = r5
            r1 = r8
        L74:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9e
            java.lang.String r3 = "blur drawable exception"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r3 = "BlurUtil"
            java.lang.Class r7 = r7.getClass()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r7 = r7.getSimpleName()     // Catch: java.lang.Throwable -> L9e
            r2.append(r7)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L9e
            com.huawei.openalliance.ad.ho.c(r3, r7)     // Catch: java.lang.Throwable -> L9e
            a(r1)
            a(r5)
            a(r8)
            if (r0 == 0) goto L9d
            r0.destroy()
        L9d:
            return r6
        L9e:
            r6 = move-exception
            a(r1)
            a(r5)
            a(r8)
            if (r0 == 0) goto Lad
            r0.destroy()
        Lad:
            throw r6
        Lae:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "ensure blurRadius in (0, 25] and scaleRatio >= 1"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.ax.a(android.content.Context, android.graphics.Bitmap, float, float):android.graphics.Bitmap");
    }
}

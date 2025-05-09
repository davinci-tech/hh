package com.huawei.hms.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* loaded from: classes9.dex */
public class mah extends maa {

    /* renamed from: a, reason: collision with root package name */
    private int f5042a;
    private Bitmap b;

    @Override // com.huawei.hms.maps.utils.maa
    public Bitmap a(Context context) {
        Bitmap a2 = a(context, context.getResources().getDrawable(this.f5042a));
        this.b = a2;
        return a2;
    }

    public static Bitmap a(Context context, Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return mad.f5037a.b(context);
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public mah(int i) {
        this.f5042a = i;
    }
}

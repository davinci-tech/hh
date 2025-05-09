package com.huawei.hms.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* loaded from: classes9.dex */
public class mag extends maa {

    /* renamed from: a, reason: collision with root package name */
    private String f5041a;
    private Bitmap b;

    @Override // com.huawei.hms.maps.utils.maa
    public Bitmap a(Context context) {
        Bitmap a2 = a(this.f5041a);
        this.b = a2;
        return a2;
    }

    private Bitmap a(String str) {
        LogM.d("PathBitmapDescriptor", "getBitmapFromPath: ");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = 5;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            LogM.e("PathBitmapDescriptor", "generateBitmap OutOfMemoryError: ");
            return null;
        }
    }

    public mag(String str) {
        this.f5041a = str;
    }
}

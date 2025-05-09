package com.huawei.hms.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;

/* loaded from: classes9.dex */
public class mab extends maa {

    /* renamed from: a, reason: collision with root package name */
    private String f5035a;

    @Override // com.huawei.hms.maps.utils.maa
    public Bitmap a(Context context) {
        String str;
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(this.f5035a));
        } catch (IOException e) {
            str = "generateBitmap IOException: " + e.toString();
            LogM.e("AssetBitmapDescriptor", str);
            return null;
        } catch (OutOfMemoryError unused) {
            str = "generateBitmap OutOfMemoryError: ";
            LogM.e("AssetBitmapDescriptor", str);
            return null;
        }
    }

    public mab(String str) {
        LogM.d("AssetBitmapDescriptor", "AssetBitmapDescriptor assetName: " + str);
        this.f5035a = str;
    }
}

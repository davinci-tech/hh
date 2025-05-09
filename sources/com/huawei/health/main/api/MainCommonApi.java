package com.huawei.health.main.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public interface MainCommonApi {
    String getPersonalPrivacySettingValue(int i);

    Bitmap getQuickAppIcon(Context context, String str);

    int getSportIconBackgroundColor(Context context, int i);

    Drawable getSportIconDrawable(Context context, int i);

    String getSportName(Context context, int i);
}

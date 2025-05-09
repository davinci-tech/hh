package com.huawei.hms.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;

/* loaded from: classes9.dex */
public class mad extends maa {

    /* renamed from: a, reason: collision with root package name */
    public static final mad f5037a = new mad(0.0f);
    private final float b;

    @Override // com.huawei.hms.maps.utils.maa
    public Bitmap a(Context context) {
        return null;
    }

    public mad(float f) {
        this.b = f > 180.0f ? f - 360.0f : f;
    }
}

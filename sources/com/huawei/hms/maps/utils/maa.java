package com.huawei.hms.maps.utils;

import android.content.Context;
import android.graphics.Bitmap;

/* loaded from: classes4.dex */
public abstract class maa {

    /* renamed from: a, reason: collision with root package name */
    private volatile Bitmap f5034a;

    public abstract Bitmap a(Context context);

    public Bitmap b(Context context) {
        if (this.f5034a == null) {
            synchronized (this) {
                if (this.f5034a == null) {
                    this.f5034a = a(context);
                }
            }
        }
        return this.f5034a;
    }
}

package com.huawei.health.h5pro.core;

import android.content.Context;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class H5ProConfigGuardian {
    public static final H5ProConfigGuardian b = new H5ProConfigGuardian();
    public static volatile OnConfigGuardianListener d;

    public interface OnConfigGuardianListener {
        void onConfigGuardian(Context context);
    }

    public void onConfigGuardian(Context context) {
        if (d == null) {
            return;
        }
        d.onConfigGuardian((Context) new WeakReference(context).get());
    }

    public void addOnConfigGuardianListener(OnConfigGuardianListener onConfigGuardianListener) {
        synchronized (this) {
            if (d == null) {
                d = onConfigGuardianListener;
            }
        }
    }
}

package com.huawei.hihealth.api;

import android.content.Context;

/* loaded from: classes.dex */
public class HiHealthManager {
    private HiHealthManager() {
    }

    public static HiHealthApi d(Context context) {
        return HiHealthNativeApi.a(context);
    }
}

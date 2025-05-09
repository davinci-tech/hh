package com.huawei.hmf.services;

import android.content.Context;
import com.huawei.hmf.services.internal.ApplicationContext;

/* loaded from: classes9.dex */
public final class HmfConfig {
    private static final int HookSystemClassloaderForDexLoaderFlag = 2;
    private static final int UiModuleExposedToDexLoaderFlag = 1;
    private static long features;

    public static void init(Context context) {
        ApplicationContext.setContext(context);
    }

    public static void setUiModuleExposedToDexLoader(boolean z) {
        if (z) {
            features |= 1;
        } else {
            features &= -2;
        }
    }

    public static boolean isUiModuleExposedToDexLoader() {
        return (features & 1) == 1;
    }

    public static void setHookSystemClassloaderForDexLoader(boolean z) {
        if (z) {
            features |= 2;
        } else {
            features &= -3;
        }
    }

    public static boolean isHookSystemClassloaderForDexLoader() {
        return (features & 2) == 2;
    }
}

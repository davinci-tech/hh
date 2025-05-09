package com.huawei.secure.android.common.detect;

import android.os.Build;
import com.huawei.secure.android.common.detect.b.d;

/* loaded from: classes9.dex */
public class DeviceDetect {
    private static boolean a() {
        return d.a("hw_sc.product.useBrandCust", false);
    }

    public static boolean isHonorDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("HONOR");
    }

    public static boolean isHuaweiDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI") || a();
    }

    public static boolean isHuaweiOrHonorDevice() {
        return isHonorDevice() || isHuaweiDevice();
    }
}

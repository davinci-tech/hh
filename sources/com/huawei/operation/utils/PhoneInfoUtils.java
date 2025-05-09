package com.huawei.operation.utils;

import android.os.Build;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class PhoneInfoUtils {
    private static final String MESSAGE_HW_PHONE = "HW";
    private static final String MESSAGE_OTHER_PHONE = "3RD";

    private PhoneInfoUtils() {
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getPhoneType() {
        return "huawei".equalsIgnoreCase(Build.MANUFACTURER) ? MESSAGE_HW_PHONE : MESSAGE_OTHER_PHONE;
    }

    public static String getHuaweiManufaturerOrEmui() {
        return ("huawei".equalsIgnoreCase(Build.MANUFACTURER) || CommonUtil.bh()) ? MESSAGE_HW_PHONE : MESSAGE_OTHER_PHONE;
    }
}

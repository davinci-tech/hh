package com.huawei.operation.utils;

import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes5.dex */
public class AppTypeUtils {
    static final int HUAWEI_WEAR = 2;
    static final int SPORT_AND_HEALTH = 1;

    private AppTypeUtils() {
    }

    public static int getAppType() {
        return "com.huawei.bone".equals(BaseApplication.getAppPackage()) ? 2 : 1;
    }
}

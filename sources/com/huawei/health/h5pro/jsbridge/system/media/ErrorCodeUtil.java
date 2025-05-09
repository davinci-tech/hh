package com.huawei.health.h5pro.jsbridge.system.media;

import com.huawei.openalliance.ad.constant.IntentFailError;

/* loaded from: classes8.dex */
public class ErrorCodeUtil {
    public static int getErrorCode(String str) {
        if (IntentFailError.NO_PERMISSION.equals(str)) {
            return 1001;
        }
        return "cancel operate".equals(str) ? 1002 : -1;
    }
}

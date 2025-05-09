package com.huawei.hianalytics.util;

import android.content.Context;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.z0;

/* loaded from: classes.dex */
public class HiAnalyticTools {
    public static void enableLog(Context context, int i) {
        HiLog.setLogAdapter(new z0());
        HiLog.init(i, "FormalHASDK");
    }

    public static void enableLog(Context context) {
        enableLog(context, 3);
    }
}

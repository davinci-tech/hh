package com.huawei.hms.push;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class g {
    public static int a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return activeNetworkInfo.getType();
        }
        HMSLog.i("NetworkUtil", "no connected network");
        return -1;
    }
}

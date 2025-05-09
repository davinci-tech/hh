package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class ov {
    public static boolean a() {
        try {
            Class.forName("com.huawei.hms.pps.HwPPS");
            Class.forName("com.huawei.hmf.tasks.Task");
            return true;
        } catch (Throwable unused) {
            ho.c("HMSConnectProcessor", "check hms sdk available error");
            return false;
        }
    }
}

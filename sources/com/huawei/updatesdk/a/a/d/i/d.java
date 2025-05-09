package com.huawei.updatesdk.a.a.d.i;

import com.huawei.hms.mlsdk.asr.MLAsrConstants;

/* loaded from: classes7.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static int f10810a = -1;

    public static int a() {
        if (f10810a == -1) {
            f10810a = (MLAsrConstants.LAN_ZH.equals(c.a("ro.product.locale.language", "")) && "CN".equals(c.a("ro.product.locale.region", ""))) ? 0 : 1;
        }
        return f10810a;
    }
}

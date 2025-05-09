package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class ma {
    public static boolean a(String str) {
        try {
            Class.forName(str);
            ho.b("OMSDKCheckUtil", str);
            return true;
        } catch (ClassNotFoundException unused) {
            ho.b("OMSDKCheckUtil", str + " is not avaliable");
            return false;
        }
    }
}

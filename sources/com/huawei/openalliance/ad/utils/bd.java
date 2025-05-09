package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;

/* loaded from: classes5.dex */
public class bd {
    public static boolean a(int i) {
        return i == 1;
    }

    public static boolean b(int i) {
        return 3 == i || 9 == i || 8 == i || 13 == i;
    }

    public static boolean a(Integer num) {
        return num != null && num.intValue() == 3;
    }

    public static boolean a(AdSlotParam adSlotParam) {
        return adSlotParam.A() && a(adSlotParam.C());
    }
}

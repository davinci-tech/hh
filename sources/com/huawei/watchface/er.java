package com.huawei.watchface;

import android.util.SparseArray;

/* loaded from: classes7.dex */
public class er {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<String> f11017a;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f11017a = sparseArray;
        sparseArray.put(20001, "File info check fail");
        sparseArray.put(20002, "getDeviceInfoByBt() failed");
        sparseArray.put(20003, "Send watchface info to device failed");
        sparseArray.put(20004, "getSupportedClockCapability() failed -- exception msg");
        sparseArray.put(20005, "loadProperties() failed -- exception msg");
        sparseArray.put(20006, "updateWearStructListByXmlRes() failed -- exception msg");
        sparseArray.put(20007, "setBackgroundOptionalConfig() failed -- exception msg");
        sparseArray.put(20008, "createRoundedPngRes() failed -- exception msg");
    }

    public static String a(int i) {
        return f11017a.get(i);
    }
}

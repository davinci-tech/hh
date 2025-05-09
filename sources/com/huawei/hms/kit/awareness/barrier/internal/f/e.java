package com.huawei.hms.kit.awareness.barrier.internal.f;

import android.util.SparseArray;

/* loaded from: classes9.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4885a = "unknown";
    private static final SparseArray<String> b;

    public static String a(int i) {
        return b.get(i, "unknown");
    }

    private e() {
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        b = sparseArray;
        sparseArray.put(11, "SCREEN");
        sparseArray.put(0, "HEADSET");
        sparseArray.put(1, "LOCATION");
        sparseArray.put(3, "BEHAVIOR");
        sparseArray.put(4, "TIME");
        sparseArray.put(5, "AMBIENT");
        sparseArray.put(7, "BEACON");
        sparseArray.put(9, "BLUETOOTH");
        sparseArray.put(10, "ZEN");
        sparseArray.put(12, "WIFI");
        sparseArray.put(14, "APPLICATION");
        sparseArray.put(50, "COMPLEX");
        sparseArray.put(-1, "unknown");
    }
}

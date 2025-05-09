package com.huawei.hms.kit.awareness.b;

import com.huawei.hms.kit.awareness.AwarenessStatusCodes;

/* loaded from: classes4.dex */
public class HHG extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    private static final long f4817a = 7982983303014510118L;

    public HHG(String str) {
        super(str);
    }

    public HHG(int i, String str) {
        super("Error Code: " + i + " Desc: " + str);
    }

    public HHG(int i) {
        super("Error Code: " + i + " Desc: " + AwarenessStatusCodes.getMessage(i));
    }
}

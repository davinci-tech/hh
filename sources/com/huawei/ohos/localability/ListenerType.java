package com.huawei.ohos.localability;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public enum ListenerType {
    VISIBLE(0),
    INVISIBLE(1);

    private static final Map<Integer, ListenerType> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private int f6547a;

    public int getValue() {
        return this.f6547a;
    }

    public static ListenerType intToEnum(int i) {
        ListenerType listenerType = b.get(Integer.valueOf(i));
        return listenerType == null ? VISIBLE : listenerType;
    }

    ListenerType(int i) {
        this.f6547a = i;
    }

    static {
        for (ListenerType listenerType : values()) {
            b.put(Integer.valueOf(listenerType.getValue()), listenerType);
        }
    }
}

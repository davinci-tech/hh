package com.huawei.hms.common;

/* loaded from: classes.dex */
public enum HmsCheckedState {
    UNCHECKED(0),
    NOT_NEED_UPDATE(1),
    NEED_UPDATE(2);


    /* renamed from: a, reason: collision with root package name */
    private final int f4428a;

    HmsCheckedState(int i) {
        this.f4428a = i;
    }

    public int getState() {
        return this.f4428a;
    }
}

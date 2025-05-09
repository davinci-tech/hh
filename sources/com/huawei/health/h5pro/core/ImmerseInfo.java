package com.huawei.health.h5pro.core;

/* loaded from: classes3.dex */
public class ImmerseInfo {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2381a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;

    public boolean isStatusBarTextBlack() {
        return this.f2381a;
    }

    public boolean isStartAtBottomOfStatusBar() {
        return this.e;
    }

    public boolean isShowStatusBar() {
        return this.d;
    }

    public boolean isImmerse() {
        return this.b;
    }

    public boolean isHideBottomVirtualKeys() {
        return this.c;
    }

    public ImmerseInfo(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.b = z;
        this.d = z2;
        this.f2381a = z3;
        this.e = z4;
        this.c = z5;
    }
}

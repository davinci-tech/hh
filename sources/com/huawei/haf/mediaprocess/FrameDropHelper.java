package com.huawei.haf.mediaprocess;

import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class FrameDropHelper {

    /* renamed from: a, reason: collision with root package name */
    private int f2130a;
    private int b;
    private int c;
    private int d;
    private boolean e;

    public FrameDropHelper(int i, int i2) {
        this.e = true;
        this.d = i;
        this.b = i2;
        if (i <= i2) {
            LogUtil.e("FrameDropHelper", "srcFrameRate:", Integer.valueOf(i), " dstFrameRate:", Integer.valueOf(i2), "，not support frame interpolation");
            this.e = false;
        }
    }

    public boolean d(int i) {
        if (!this.e) {
            return false;
        }
        if (i == 0) {
            this.c++;
            return false;
        }
        float f = (r7 - this.b) / this.d;
        int i2 = this.f2130a;
        int i3 = this.c + i2;
        if (i3 != 0) {
            float f2 = i3;
            r1 = Math.abs((((float) (i2 + 1)) / f2) - f) < Math.abs((((float) i2) / ((float) (i3 + 1))) - f);
            if (r1) {
                this.f2130a++;
            } else {
                this.c++;
            }
            LogUtil.c("FrameDropHelper", "current frame drop rate:", Float.valueOf(this.f2130a / f2), " target frame drop rate:", Float.valueOf(f));
        }
        return r1;
    }
}

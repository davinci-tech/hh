package com.huawei.openalliance.ad;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes9.dex */
public class ef {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f6835a;
    private static final float[] b;
    private static final FloatBuffer c;
    private static final FloatBuffer d;
    private final FloatBuffer e = c;
    private final FloatBuffer f = d;
    private final int g = 2;
    private final int h = f6835a.length / 2;

    int d() {
        return 8;
    }

    int e() {
        return 8;
    }

    int f() {
        return 2;
    }

    int c() {
        return this.h;
    }

    FloatBuffer b() {
        return this.f;
    }

    FloatBuffer a() {
        return this.e;
    }

    private static FloatBuffer a(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    static {
        float[] fArr = {-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f};
        f6835a = fArr;
        float[] fArr2 = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        b = fArr2;
        c = a(fArr);
        d = a(fArr2);
    }
}

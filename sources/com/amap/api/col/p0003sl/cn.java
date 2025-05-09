package com.amap.api.col.p0003sl;

import android.opengl.GLES20;

/* loaded from: classes2.dex */
public class cn {

    /* renamed from: a, reason: collision with root package name */
    public int f943a;
    public int b;
    public int c;
    private boolean d;

    public final void a() {
        int i = this.f943a;
        if (i >= 0) {
            GLES20.glDeleteProgram(i);
        }
        int i2 = this.b;
        if (i2 >= 0) {
            GLES20.glDeleteShader(i2);
        }
        int i3 = this.c;
        if (i3 >= 0) {
            GLES20.glDeleteShader(i3);
        }
        this.d = true;
    }
}

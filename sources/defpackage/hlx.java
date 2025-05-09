package defpackage;

import android.content.Context;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes4.dex */
public class hlx {
    private Context b;
    protected int c;
    protected String d;
    protected int e;
    protected int f;
    protected final FloatBuffer g;
    protected int h;
    protected final FloatBuffer i;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    protected String n;
    protected int o;
    protected int r;
    protected int s;

    protected void e() {
    }

    public hlx(Context context, int i, int i2) {
        this.b = context;
        this.o = i;
        this.e = i2;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.i = asFloatBuffer;
        asFloatBuffer.clear();
        asFloatBuffer.put(new float[]{-1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f});
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.g = asFloatBuffer2;
        asFloatBuffer2.clear();
        asFloatBuffer2.put(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
        e(context);
    }

    private void e(Context context) {
        this.n = hlz.a(context, this.o);
        String a2 = hlz.a(context, this.e);
        this.d = a2;
        int a3 = hlz.a(this.n, a2);
        this.k = a3;
        this.l = GLES20.glGetAttribLocation(a3, "vPosition");
        this.c = GLES20.glGetAttribLocation(this.k, "vCoord");
        this.j = GLES20.glGetUniformLocation(this.k, "vMatrix");
        this.m = GLES20.glGetUniformLocation(this.k, "vTexture");
        e();
    }

    public void e(int i, int i2, int i3, int i4) {
        this.f = i;
        this.h = i2;
        this.s = i3;
        this.r = i4;
    }

    public int d(int i) {
        GLES20.glViewport(this.s, this.r, this.f, this.h);
        GLES20.glUseProgram(this.k);
        this.i.position(0);
        GLES20.glVertexAttribPointer(this.l, 2, 5126, false, 0, (Buffer) this.i);
        GLES20.glEnableVertexAttribArray(this.l);
        this.g.position(0);
        GLES20.glVertexAttribPointer(this.c, 2, 5126, false, 0, (Buffer) this.g);
        GLES20.glEnableVertexAttribArray(this.c);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(this.m, 0);
        GLES20.glDrawArrays(5, 0, 4);
        return i;
    }

    public void d() {
        GLES20.glDeleteProgram(this.k);
    }
}

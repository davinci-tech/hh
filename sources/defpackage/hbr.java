package defpackage;

import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* loaded from: classes4.dex */
public class hbr {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f13069a;
    private static final FloatBuffer c;
    private static final float[] d;
    private static final FloatBuffer e;
    private float[] b;
    private float[] f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int m;
    private int n;

    static {
        float[] fArr = {-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        d = fArr;
        float[] fArr2 = {0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
        f13069a = fArr2;
        c = hbs.a(fArr);
        e = hbs.a(fArr2);
    }

    public hbr(int i, int i2) {
        this();
    }

    public hbr() {
        this.b = new float[16];
        float[] fArr = new float[16];
        this.f = fArr;
        this.i = -12345;
        Matrix.setIdentityM(fArr, 0);
    }

    public int d() {
        return this.i;
    }

    public void a() {
        int e2 = hbs.e("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec4 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = uSTMatrix * aTextureCoord;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec4 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord.xy/vTextureCoord.z);}\n");
        this.j = e2;
        if (e2 == 0) {
            throw new hbm("failed creating program");
        }
        this.h = GLES20.glGetAttribLocation(e2, "aPosition");
        this.g = GLES20.glGetAttribLocation(this.j, "aTextureCoord");
        this.n = GLES20.glGetUniformLocation(this.j, "uMVPMatrix");
        this.m = GLES20.glGetUniformLocation(this.j, "uSTMatrix");
        this.i = b();
    }

    public static int b() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GLES20.glTexParameteri(36197, 10241, 9728);
        GLES20.glTexParameteri(36197, 10240, 9728);
        return iArr[0];
    }

    public void c() {
        GLES20.glUseProgram(this.j);
        GLES20.glEnableVertexAttribArray(this.h);
        GLES20.glVertexAttribPointer(this.h, 3, 5126, false, 12, (Buffer) c);
        GLES20.glEnableVertexAttribArray(this.g);
        GLES20.glVertexAttribPointer(this.g, 4, 5126, false, 16, (Buffer) e);
        Matrix.setIdentityM(this.b, 0);
        GLES20.glUniformMatrix4fv(this.n, 1, false, this.b, 0);
        GLES20.glUniformMatrix4fv(this.m, 1, false, this.f, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.h);
        GLES20.glDisableVertexAttribArray(this.g);
        GLES20.glUseProgram(0);
    }
}

package com.huawei.haf.mediaprocess;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes.dex */
public class TextureRenderer {
    private static final float[] c = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    private int e;
    private int f;
    private int g;
    private int h;
    private FloatBuffer i;
    private int j;
    private int n;
    private float[] b = new float[16];

    /* renamed from: a, reason: collision with root package name */
    private float[] f2134a = new float[16];
    private int d = -12345;

    public TextureRenderer(int i) {
        this.n = i;
        float[] fArr = c;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.i = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        Matrix.setIdentityM(this.f2134a, 0);
    }

    public int e() {
        return this.d;
    }

    public void ze_(SurfaceTexture surfaceTexture, boolean z) throws IOException {
        c("onDrawFrame start");
        surfaceTexture.getTransformMatrix(this.f2134a);
        if (z) {
            float[] fArr = this.f2134a;
            fArr[5] = -fArr[5];
            fArr[13] = 1.0f - fArr[13];
        }
        GLES20.glUseProgram(this.e);
        c("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.d);
        this.i.position(0);
        GLES20.glVertexAttribPointer(this.h, 3, 5126, false, 20, (Buffer) this.i);
        c("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.h);
        c("glEnableVertexAttribArray maPositionHandle");
        this.i.position(3);
        GLES20.glVertexAttribPointer(this.f, 2, 5126, false, 20, (Buffer) this.i);
        c("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.f);
        c("glEnableVertexAttribArray maTextureHandle");
        GLES20.glUniformMatrix4fv(this.g, 1, false, this.f2134a, 0);
        GLES20.glUniformMatrix4fv(this.j, 1, false, this.b, 0);
        GLES20.glDrawArrays(5, 0, 4);
        c("glDrawArrays");
        GLES20.glFinish();
    }

    public void c() throws IOException {
        int d = d("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        this.e = d;
        if (d == 0) {
            throw new IOException("failed creating program");
        }
        this.h = GLES20.glGetAttribLocation(d, "aPosition");
        c("glGetAttribLocation aPosition");
        if (this.h == -1) {
            throw new IOException("Could not get attrib location for aPosition");
        }
        this.f = GLES20.glGetAttribLocation(this.e, "aTextureCoord");
        c("glGetAttribLocation aTextureCoord");
        if (this.f == -1) {
            throw new IOException("Could not get attrib location for aTextureCoord");
        }
        this.j = GLES20.glGetUniformLocation(this.e, "uMVPMatrix");
        c("glGetUniformLocation uMVPMatrix");
        if (this.j == -1) {
            throw new IOException("Could not get attrib location for uMVPMatrix");
        }
        this.g = GLES20.glGetUniformLocation(this.e, "uSTMatrix");
        c("glGetUniformLocation uSTMatrix");
        if (this.g == -1) {
            throw new IOException("Could not get attrib location for uSTMatrix");
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        this.d = i;
        GLES20.glBindTexture(36197, i);
        c("glBindTexture mTextureId");
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        c("glTexParameter");
        Matrix.setIdentityM(this.b, 0);
        int i2 = this.n;
        if (i2 != 0) {
            Matrix.rotateM(this.b, 0, i2, 0.0f, 0.0f, 1.0f);
        }
    }

    private int b(int i, String str) throws IOException {
        int glCreateShader = GLES20.glCreateShader(i);
        c("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    private int d(String str, String str2) throws IOException {
        int b;
        int b2 = b(35633, str);
        if (b2 == 0 || (b = b(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        c("glCreateProgram");
        if (glCreateProgram == 0) {
            return 0;
        }
        GLES20.glAttachShader(glCreateProgram, b2);
        c("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, b);
        c("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    public void c(String str) throws IOException {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        LogUtil.e("TextureRenderer", str, ": glError ", Integer.valueOf(glGetError));
        throw new IOException(str + ": glError " + glGetError);
    }
}

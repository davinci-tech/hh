package com.huawei.openalliance.ad;

import android.opengl.GLES20;
import java.nio.Buffer;

/* loaded from: classes9.dex */
public class eh {

    /* renamed from: a, reason: collision with root package name */
    private int f6837a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i = 36197;
    private final float[] j = new float[9];
    private float[] k = {-0.00390625f, -0.00390625f, 0.0f, -0.00390625f, 0.00390625f, -0.00390625f, -0.00390625f, 0.0f, 0.0f, 0.0f, 0.00390625f, 0.0f, -0.00390625f, 0.00390625f, 0.0f, 0.00390625f, 0.00390625f, 0.00390625f};
    private float l;

    public int b() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        a("fail to generate texture");
        int i = iArr[0];
        GLES20.glBindTexture(this.i, i);
        a("fail to bind texture ");
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        a("fail to create texture");
        return i;
    }

    void a(ei eiVar) {
        a("render - 1");
        b(eiVar);
        c(eiVar);
        f();
        d(eiVar);
    }

    public void a() {
        try {
            GLES20.glDeleteProgram(this.f6837a);
            this.f6837a = -1;
        } catch (Throwable th) {
            ho.a(5, "TexProgram", "release", th);
        }
    }

    private void f() {
        int i = this.d;
        if (i >= 0) {
            GLES20.glUniform1fv(i, 9, this.j, 0);
            GLES20.glUniform2fv(this.e, 9, this.k, 0);
            GLES20.glUniform1f(this.f, this.l);
        }
    }

    private void e() {
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.f6837a, "positionLocation");
        this.g = glGetAttribLocation;
        a(glGetAttribLocation, "positionLocation");
        int glGetAttribLocation2 = GLES20.glGetAttribLocation(this.f6837a, "textureCoordLocation");
        this.h = glGetAttribLocation2;
        a(glGetAttribLocation2, "textureCoordLocation");
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.f6837a, "matrixLocation");
        this.b = glGetUniformLocation;
        a(glGetUniformLocation, "matrixLocation");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.f6837a, "texMatrixLocation");
        this.c = glGetUniformLocation2;
        a(glGetUniformLocation2, "texMatrixLocation");
        this.d = GLES20.glGetUniformLocation(this.f6837a, "coreLocation");
    }

    private void d(ei eiVar) {
        GLES20.glDrawArrays(5, eiVar.c(), eiVar.d());
        a("pr4 - 1");
        GLES20.glDisableVertexAttribArray(this.g);
        GLES20.glDisableVertexAttribArray(this.h);
        GLES20.glBindTexture(this.i, 0);
        GLES20.glUseProgram(0);
    }

    private void d() {
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.f6837a, "textureOffsetLocation");
        this.e = glGetUniformLocation;
        a(glGetUniformLocation, "textureOffsetLocation");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.f6837a, "colorAdjustLocation");
        this.f = glGetUniformLocation2;
        a(glGetUniformLocation2, "colorAdjustLocation");
        System.arraycopy(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f}, 0, this.j, 0, 9);
        this.l = 0.0f;
    }

    private void c(ei eiVar) {
        GLES20.glEnableVertexAttribArray(this.g);
        a("rv - 1");
        GLES20.glVertexAttribPointer(this.g, eiVar.e(), 5126, false, eiVar.f(), (Buffer) eiVar.b());
        a("rv - 2");
        GLES20.glEnableVertexAttribArray(this.h);
        a("rv - 3");
        GLES20.glVertexAttribPointer(this.h, 2, 5126, false, eiVar.j(), (Buffer) eiVar.h());
        a("rv - 4");
    }

    private static int c() {
        int b;
        int b2 = b(35633, "uniform mat4 matrixLocation; uniform mat4 texMatrixLocation; attribute vec4 positionLocation; attribute vec4 textureCoordLocation; varying vec2 textureCoordination; void main() { gl_Position = matrixLocation * positionLocation; textureCoordination = (texMatrixLocation * textureCoordLocation).xy;}");
        if (b2 == 0 || (b = b(35632, "#extension GL_OES_EGL_image_external : require\n precision mediump float; varying vec2 textureCoordination; uniform samplerExternalOES sTexture; void main() {gl_FragColor = texture2D(sTexture, textureCoordination);}")) == 0) {
            return 0;
        }
        return a(b2, b);
    }

    private void b(ei eiVar) {
        GLES20.glUseProgram(this.f6837a);
        a("rtm - 1");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.i, eiVar.i());
        GLES20.glUniformMatrix4fv(this.b, 1, false, eiVar.a(), 0);
        a("rtm - 2");
        GLES20.glUniformMatrix4fv(this.c, 1, false, eiVar.g(), 0);
        a("rtm - 3");
    }

    private static int b(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        a("create shader " + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        ho.c("TexProgram", "fail to compile shader: " + i + " " + GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    static void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        String str2 = str + " error: " + Integer.toHexString(glGetError);
        ho.c("TexProgram", str2);
        throw new IllegalStateException(str2);
    }

    static void a(int i, String str) {
        if (i >= 0) {
            return;
        }
        throw new IllegalStateException("program fail to find " + str);
    }

    private static int a(int i, int i2) {
        int glCreateProgram = GLES20.glCreateProgram();
        a("create program");
        if (glCreateProgram == 0) {
            ho.c("TexProgram", "fail not create program");
        }
        GLES20.glAttachShader(glCreateProgram, i);
        a("attach shader");
        GLES20.glAttachShader(glCreateProgram, i2);
        a("attach shader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        ho.c("TexProgram", "fail to link");
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    public eh() {
        int c = c();
        this.f6837a = c;
        if (c == 0) {
            throw new IllegalStateException("fail to create program");
        }
        e();
        if (this.d >= 0) {
            d();
            return;
        }
        this.d = -1;
        this.e = -1;
        this.f = -1;
    }
}

package defpackage;

import android.content.Context;
import android.opengl.GLES20;

/* loaded from: classes9.dex */
public class nkb {

    /* renamed from: a, reason: collision with root package name */
    private static final String f15339a = "ShaderHelper";

    public static int e(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader == 0) {
            String str2 = f15339a;
            njw.c(str2, "getShader Error creating vertex shader.");
            throw new RuntimeException(str2 + njw.b() + " Error creating vertex shader.");
        }
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder sb = new StringBuilder();
        String str3 = f15339a;
        sb.append(str3);
        sb.append("_ES20_ERROR");
        njw.c(sb.toString(), "Could not compile shader " + i + ":");
        njw.c(str3 + "_ES20_ERROR", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    public static int e(String str) {
        return e(35633, str);
    }

    public static int b(String str) {
        return e(35632, str);
    }

    public static int b(Context context, String str, String str2) {
        int c;
        synchronized (nkb.class) {
            c = c(njz.a(context, str), njz.a(context, str2));
        }
        return c;
    }

    public static int c(String str, String str2) {
        return b(e(str), b(str2));
    }

    public static int b(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram == 0) {
            njw.c(f15339a, "Error creating program..");
            throw new RuntimeException("Error creating program.");
        }
        GLES20.glAttachShader(glCreateProgram, i);
        njv.d("glAttachShader vertexShader");
        GLES20.glAttachShader(glCreateProgram, i2);
        njv.d("glAttachShader fragShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            StringBuilder sb = new StringBuilder();
            String str = f15339a;
            sb.append(str);
            sb.append("_ES20_ERROR");
            njw.c(sb.toString(), njw.b() + "Could not link program: ");
            njw.c(str + "_ES20_ERROR", njw.b() + GLES20.glGetProgramInfoLog(glCreateProgram));
            GLES20.glDeleteProgram(glCreateProgram);
            throw new RuntimeException("Error creating program.");
        }
        njw.c(f15339a, njw.b() + "program ID=" + glCreateProgram);
        GLES20.glDeleteShader(i);
        GLES20.glDeleteShader(i2);
        return glCreateProgram;
    }
}

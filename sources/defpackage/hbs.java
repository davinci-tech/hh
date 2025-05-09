package defpackage;

import android.opengl.GLES20;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes4.dex */
public class hbs {
    public static int e(String str, String str2) {
        int d;
        int d2 = d(35633, str);
        if (d2 == 0 || (d = d(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        b("glCreateProgram");
        if (glCreateProgram == 0) {
            LogUtil.b("Track_EncodeDecodeSurface", "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, d2);
        b("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, d);
        b("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        LogUtil.b("Track_EncodeDecodeSurface", "Could not link program: ");
        LogUtil.b("Track_EncodeDecodeSurface", GLES20.glGetProgramInfoLog(glCreateProgram));
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    public static int d(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        b("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        LogUtil.b("Track_EncodeDecodeSurface", "Could not compile shader ", Integer.valueOf(i), ":");
        LogUtil.b("Track_EncodeDecodeSurface", " ", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    public static void b(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        String str2 = str + ": glError 0x" + Integer.toHexString(glGetError);
        LogUtil.b("Track_EncodeDecodeSurface", str2);
        throw new hbm(str2);
    }

    public static FloatBuffer a(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }
}

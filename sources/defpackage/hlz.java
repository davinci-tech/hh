package defpackage;

import android.content.Context;
import android.opengl.GLES20;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public class hlz {
    public static String a(Context context, int i) {
        if (context == null) {
            return "";
        }
        InputStream openRawResource = context.getResources().openRawResource(i);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, StandardCharsets.UTF_8));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine).append(System.lineSeparator());
                } catch (IOException e) {
                    LogUtil.a("Track_OpenGlUtils", "readTemporaryMotionPath ", LogAnonymous.b((Throwable) e));
                }
            } catch (Throwable th) {
                IoUtils.e(openRawResource);
                IoUtils.e(bufferedReader);
                throw th;
            }
        }
        IoUtils.e(openRawResource);
        IoUtils.e(bufferedReader);
        return stringBuffer.toString();
    }

    public static int a(String str, String str2) {
        int glCreateShader = GLES20.glCreateShader(35633);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 1) {
            throw new IllegalStateException("load vertex raw error :" + GLES20.glGetShaderInfoLog(glCreateShader));
        }
        int glCreateShader2 = GLES20.glCreateShader(35632);
        GLES20.glShaderSource(glCreateShader2, str2);
        GLES20.glCompileShader(glCreateShader2);
        GLES20.glGetShaderiv(glCreateShader2, 36346, iArr, 0);
        if (iArr[0] != 1) {
            throw new IllegalStateException("load fragment raw error :" + GLES20.glGetShaderInfoLog(glCreateShader2));
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, glCreateShader);
        GLES20.glAttachShader(glCreateProgram, glCreateShader2);
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            throw new IllegalStateException("link program:" + GLES20.glGetProgramInfoLog(glCreateProgram));
        }
        GLES20.glDeleteShader(glCreateShader);
        GLES20.glDeleteShader(glCreateShader2);
        return glCreateProgram;
    }

    public static void d(int[] iArr) {
        if (iArr == null) {
            return;
        }
        GLES20.glGenTextures(iArr.length, iArr, 0);
        for (int i : iArr) {
            GLES20.glBindTexture(3553, i);
            GLES20.glTexParameteri(3553, 10240, 9728);
            GLES20.glTexParameteri(3553, 10241, 9728);
            GLES20.glTexParameteri(3553, 10242, 10497);
            GLES20.glTexParameteri(3553, 10243, 10497);
            GLES20.glBindTexture(3553, 0);
        }
    }
}

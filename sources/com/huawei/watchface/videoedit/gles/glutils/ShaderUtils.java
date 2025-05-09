package com.huawei.watchface.videoedit.gles.glutils;

import android.content.Context;
import android.opengl.GLES20;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* loaded from: classes9.dex */
public class ShaderUtils {
    private static final String TAG = "ShaderUtil";

    public static String readFromAssets(Context context, String str) {
        if (context == null) {
            HwLog.e(TAG, "context is null");
            return "";
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            return readFromInputStream(inputStream);
        } catch (IOException unused) {
            HwLog.e(TAG, "read from assets failed!");
            return "";
        } finally {
            Utils.closeSilently(inputStream);
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder("");
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
                sb.append(System.lineSeparator());
            } else {
                Utils.closeSilently(bufferedReader);
                Utils.closeSilently(inputStreamReader);
                Utils.closeSilently(inputStream);
                return sb.toString();
            }
        }
    }

    public static int loadShader(Context context, int i, String str) {
        String readFromAssets = readFromAssets(context, str);
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader == 0) {
            HwLog.d(TAG, "create shader failed! type: " + i);
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, readFromAssets);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateShader;
        }
        HwLog.d(TAG, "shader compile error! type: " + i);
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    public static int createProgram(int i, int i2) {
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram == 0) {
            HwLog.d(TAG, "create program error!");
            return 0;
        }
        GLES20.glAttachShader(glCreateProgram, i);
        GLES20.glAttachShader(glCreateProgram, i2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        HwLog.d(TAG, "link program error");
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }
}

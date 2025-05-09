package com.huawei.hihealth.util;

import android.content.Context;
import health.compact.a.util.LogUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class HiFileUtil {
    public static boolean c(Context context, String str, String str2) {
        if (str2 == null || str == null || context == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = context.openFileOutput(str2, 0);
                    fileOutputStream.write(str.getBytes("UTF-8"));
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            LogUtil.e("Debug_HiFileUtil", "writeDataToFile fout close error = ", e.getMessage());
                        }
                    }
                    return true;
                } catch (Throwable th) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            LogUtil.e("Debug_HiFileUtil", "writeDataToFile fout close error = ", e2.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                LogUtil.c("Debug_HiFileUtil", "writeDataToFile no file: ", e3.getMessage());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        LogUtil.e("Debug_HiFileUtil", "writeDataToFile fout close error = ", e4.getMessage());
                    }
                }
                return false;
            }
        } catch (UnsupportedEncodingException e5) {
            LogUtil.c("Debug_HiFileUtil", "decode not support ", e5.getMessage());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    LogUtil.e("Debug_HiFileUtil", "writeDataToFile fout close error = ", e6.getMessage());
                }
            }
            return false;
        } catch (IOException unused) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e7) {
                    LogUtil.e("Debug_HiFileUtil", "writeDataToFile fout close error = ", e7.getMessage());
                }
            }
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        health.compact.a.util.LogUtil.c("Debug_HiFileUtil", "read too match ", java.lang.Integer.valueOf(r11));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(android.content.Context r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "readFiletoData final IOException = "
            java.lang.String r1 = "Debug_HiFileUtil"
            if (r11 == 0) goto L95
            if (r10 != 0) goto Lb
            goto L95
        Lb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = 1
            r4 = 2
            r5 = 0
            r6 = 0
            java.io.FileInputStream r5 = r10.openFileInput(r11)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r11 = r6
        L1d:
            int r7 = r5.read(r10)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r8 = -1
            if (r7 == r8) goto L45
            int r11 = r11 + r7
            r8 = 52428800(0x3200000, float:4.7019774E-37)
            if (r11 < r8) goto L3a
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            java.lang.String r7 = "read too match "
            r10[r6] = r7     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r10[r3] = r11     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            health.compact.a.util.LogUtil.c(r1, r10)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            goto L45
        L3a:
            java.lang.String r8 = new java.lang.String     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            java.lang.String r9 = "UTF-8"
            r8.<init>(r10, r6, r7, r9)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r2.append(r8)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            goto L1d
        L45:
            if (r5 == 0) goto L7d
            r5.close()     // Catch: java.io.IOException -> L4b
            goto L7d
        L4b:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            java.lang.Object[] r10 = new java.lang.Object[]{r0, r10}
            health.compact.a.util.LogUtil.e(r1, r10)
            goto L7d
        L58:
            r10 = move-exception
            goto L82
        L5a:
            r10 = move-exception
            java.lang.Object[] r11 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L58
            java.lang.String r4 = "readFiletoData IOException = "
            r11[r6] = r4     // Catch: java.lang.Throwable -> L58
            java.lang.String r10 = r10.getMessage()     // Catch: java.lang.Throwable -> L58
            r11[r3] = r10     // Catch: java.lang.Throwable -> L58
            health.compact.a.util.LogUtil.e(r1, r11)     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L7d
            r5.close()     // Catch: java.io.IOException -> L71
            goto L7d
        L71:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            java.lang.Object[] r10 = new java.lang.Object[]{r0, r10}
            health.compact.a.util.LogUtil.e(r1, r10)
        L7d:
            java.lang.String r10 = r2.toString()
            return r10
        L82:
            if (r5 == 0) goto L94
            r5.close()     // Catch: java.io.IOException -> L88
            goto L94
        L88:
            r11 = move-exception
            java.lang.String r11 = r11.getMessage()
            java.lang.Object[] r11 = new java.lang.Object[]{r0, r11}
            health.compact.a.util.LogUtil.e(r1, r11)
        L94:
            throw r10
        L95:
            java.lang.String r10 = ""
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.util.HiFileUtil.d(android.content.Context, java.lang.String):java.lang.String");
    }
}

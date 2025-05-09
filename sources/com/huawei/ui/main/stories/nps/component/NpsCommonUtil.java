package com.huawei.ui.main.stories.nps.component;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.agconnect.apms.Agent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.SystemProperties;

/* loaded from: classes9.dex */
public class NpsCommonUtil {
    private static final String DEVICE_NAME_FILE_NAME = "/data/product.bin";
    private static final int MAX_READ_LINE = 5242880;
    private static final String TAG = "NpsCommonUtil";

    private NpsCommonUtil() {
    }

    public static String getClientVersionCode() {
        return CommonUtil.d(BaseApplication.getContext()) + "";
    }

    public static String getCountryCode() {
        return BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
    }

    public static String getFirmRomVersion() {
        return Build.DISPLAY;
    }

    public static String getEmuiVerion() {
        return CommonUtil.az() ? HarmonyBuild.b : EmuiBuild.c;
    }

    public static String getInternalModel() {
        String convertFileToString = convertFileToString(getDeviceNameFileName());
        if (!TextUtils.isEmpty(convertFileToString)) {
            return convertFileToString;
        }
        String b = SystemProperties.b("ro.product.model");
        return TextUtils.isEmpty(b) ? Build.MODEL : b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String convertFileToString(java.lang.String r10) {
        /*
            java.lang.String r0 = "convertFileToString occurred IOException3"
            java.lang.String r1 = "convertFileToString occurred IOException2"
            java.lang.String r2 = "NpsCommonUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 0
            r5 = 1
            r6 = 0
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            r8.<init>(r10)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.lang.String r10 = "UTF-8"
            r7.<init>(r8, r10)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L87
            r10.<init>(r7)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L87
            r6 = r4
        L20:
            java.lang.String r8 = r10.readLine()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            if (r8 == 0) goto L3e
            r3.append(r8)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            int r6 = r6 + r5
            r8 = 5242880(0x500000, float:7.34684E-39)
            if (r6 <= r8) goto L20
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            java.lang.String r9 = "read lines exceed max: "
            r6[r4] = r9     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            r6[r5] = r8     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            health.compact.a.util.LogUtil.e(r2, r6)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
        L3e:
            r7.close()     // Catch: java.io.IOException -> L42
            goto L49
        L42:
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r2, r1)
        L49:
            r10.close()     // Catch: java.io.IOException -> L4d
            goto L82
        L4d:
            java.lang.Object[] r10 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.e(r2, r10)
            goto L82
        L55:
            r3 = move-exception
            r6 = r10
            goto L89
        L58:
            r6 = r10
            goto L5f
        L5a:
            r10 = move-exception
            r3 = r10
            r10 = r6
            goto L8b
        L5e:
            r7 = r6
        L5f:
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L87
            java.lang.String r5 = "convertFileToString occurred IOException"
            r10[r4] = r5     // Catch: java.lang.Throwable -> L87
            health.compact.a.util.LogUtil.e(r2, r10)     // Catch: java.lang.Throwable -> L87
            if (r7 == 0) goto L75
            r7.close()     // Catch: java.io.IOException -> L6e
            goto L75
        L6e:
            java.lang.Object[] r10 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r2, r10)
        L75:
            if (r6 == 0) goto L82
            r6.close()     // Catch: java.io.IOException -> L7b
            goto L82
        L7b:
            java.lang.Object[] r10 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.e(r2, r10)
        L82:
            java.lang.String r10 = r3.toString()
            return r10
        L87:
            r10 = move-exception
            r3 = r10
        L89:
            r10 = r6
            r6 = r7
        L8b:
            if (r6 == 0) goto L98
            r6.close()     // Catch: java.io.IOException -> L91
            goto L98
        L91:
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r2, r1)
        L98:
            if (r10 == 0) goto La5
            r10.close()     // Catch: java.io.IOException -> L9e
            goto La5
        L9e:
            java.lang.Object[] r10 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.e(r2, r10)
        La5:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.component.NpsCommonUtil.convertFileToString(java.lang.String):java.lang.String");
    }

    public static String getAndroidVersion() {
        return Agent.OS_NAME + Build.VERSION.RELEASE;
    }

    private static String getDeviceNameFileName() {
        return DEVICE_NAME_FILE_NAME;
    }
}

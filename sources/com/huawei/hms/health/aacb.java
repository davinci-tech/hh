package com.huawei.hms.health;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
public class aacb {
    private static final boolean aab;
    private static final String aaba;
    private static final String aabb;
    private static final String aabc;

    private static boolean aab(String str) {
        boolean z = !TextUtils.isEmpty(str) && str.contains("EmotionUI");
        StringBuilder aab2 = aab.aab("checkEmuiSystem, isEmuiBuildEx=");
        aab2.append(aab);
        aab2.append(", isEmui=");
        aab2.append(z);
        aab2.toString();
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    static {
        /*
            java.lang.String r0 = "com.huawei.android.os.BuildEx"
            boolean r0 = aaba(r0)
            com.huawei.hms.health.aacb.aab = r0
            java.lang.String r1 = ""
            if (r0 != 0) goto Le
            r2 = r1
            goto L14
        Le:
            java.lang.String r2 = "ro.build.version.emui"
            java.lang.String r2 = aab(r2, r1)
        L14:
            com.huawei.hms.health.aacb.aaba = r2
            aab(r2)
            java.lang.String r2 = "EnvironmentInfo"
            if (r0 != 0) goto L1e
            goto L35
        L1e:
            java.lang.String r0 = "ro.build.hw_emui_api_level"
            java.lang.String r0 = aab(r0, r1)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L2b
            goto L35
        L2b:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L30
            goto L36
        L30:
            java.lang.String r0 = "getEmuiVersionCodeExImpl: catch a NumberFormatException"
            com.huawei.hms.health.aabz.aab(r2, r0)
        L35:
            r0 = 0
        L36:
            java.lang.String r3 = "initEmuiVersionCodeEx, isEmuiBuildEx="
            java.lang.StringBuilder r3 = com.huawei.hms.health.aab.aab(r3)
            boolean r4 = com.huawei.hms.health.aacb.aab
            r3.append(r4)
            java.lang.String r5 = ", emuiVersionCode="
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.huawei.hms.health.aabz.aabb(r2, r0)
            java.lang.String r0 = "hw_sc.build.platform.version"
            java.lang.String r0 = aab(r0, r1)
            com.huawei.hms.health.aacb.aabb = r0
            if (r4 != 0) goto L5b
            goto L61
        L5b:
            java.lang.String r0 = "ro.build.version.magic"
            java.lang.String r1 = aab(r0, r1)
        L61:
            com.huawei.hms.health.aacb.aabc = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aacb.<clinit>():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void aab() throws java.lang.SecurityException {
        /*
            com.huawei.hms.hihealth.HiHealthKitClient r0 = com.huawei.hms.hihealth.HiHealthKitClient.getInstance()
            android.content.Context r0 = r0.getContext()
            if (r0 != 0) goto Lb
            return
        Lb:
            java.lang.String r1 = com.huawei.hms.utils.Util.getAppId(r0)
            java.lang.String r2 = "10414141"
            boolean r2 = r2.equals(r1)
            java.lang.String r3 = "EnvironmentInfo"
            if (r2 != 0) goto L96
            java.lang.String r2 = "102162151"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L23
            goto L96
        L23:
            java.lang.String r1 = com.huawei.hms.health.aacb.aabb
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r4 = 2
            java.lang.String r5 = "\\."
            r6 = 0
            if (r2 == 0) goto L30
            goto L4d
        L30:
            java.lang.String[] r1 = r1.split(r5)
            int r2 = r1.length
            if (r2 <= 0) goto L4d
            r2 = r1[r6]
            boolean r2 = android.text.TextUtils.isDigitsOnly(r2)
            if (r2 == 0) goto L4d
            r1 = r1[r6]
            int r1 = java.lang.Integer.parseInt(r1)
            if (r1 < r4) goto L4d
            java.lang.String r1 = "System OS is Harmony."
            com.huawei.hms.health.aabz.aabb(r3, r1)
            goto L82
        L4d:
            java.lang.String r1 = com.huawei.hms.health.aacb.aabc
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L56
            goto L70
        L56:
            java.lang.String[] r1 = r1.split(r5)
            int r2 = r1.length
            if (r2 <= 0) goto L70
            r2 = r1[r6]
            boolean r2 = android.text.TextUtils.isDigitsOnly(r2)
            if (r2 == 0) goto L70
            r1 = r1[r6]
            int r1 = java.lang.Integer.parseInt(r1)
            if (r1 < r4) goto L70
            java.lang.String r1 = "System OS is Magic New Ui."
            goto L7f
        L70:
            java.lang.String r1 = "Android SDK Version is "
            java.lang.StringBuilder r1 = com.huawei.hms.health.aab.aab(r1)
            int r2 = android.os.Build.VERSION.SDK_INT
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L7f:
            com.huawei.hms.health.aabz.aabb(r3, r1)
        L82:
            boolean r0 = com.huawei.hms.health.aaca.aaba(r0)
            if (r0 == 0) goto L89
            return
        L89:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            r1 = 50060(0xc38c, float:7.0149E-41)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r0.<init>(r1)
            throw r0
        L96:
            java.lang.String r0 = "skip checkSystemVersion"
            com.huawei.hms.health.aabz.aabb(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aacb.aab():void");
    }

    private static boolean aaba(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            aabz.aab("EnvironmentInfo", "isSupportEmuiBuildEx ClassNotFoundException");
            return false;
        }
    }

    static String aab(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class).invoke(null, str, str2);
            return invoke instanceof String ? (String) invoke : str2;
        } catch (ClassNotFoundException unused) {
            str3 = "getSystemProperties ClassNotFoundException";
            aabz.aab("EnvironmentInfo", str3);
            return str2;
        } catch (IllegalAccessException unused2) {
            str3 = "getSystemProperties IllegalAccessException";
            aabz.aab("EnvironmentInfo", str3);
            return str2;
        } catch (NoSuchMethodException unused3) {
            str3 = "getSystemProperties NoSuchMethodException";
            aabz.aab("EnvironmentInfo", str3);
            return str2;
        } catch (InvocationTargetException unused4) {
            str3 = "getSystemProperties InvocationTargetException";
            aabz.aab("EnvironmentInfo", str3);
            return str2;
        }
    }
}

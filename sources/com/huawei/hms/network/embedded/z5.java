package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public class z5 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5592a = "PingNet";
    public static final String b = "\n";

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e3, code lost:
    
        if (r9 != 0) goto L55;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f5  */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.StringBuffer] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.ProcessBuilder] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.network.embedded.a6 a(java.lang.String r9, int r10, int r11, java.lang.StringBuffer r12) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.z5.a(java.lang.String, int, int, java.lang.StringBuffer):com.huawei.hms.network.embedded.a6");
    }

    public static String[] a(a6 a6Var) {
        return new String[]{r3.r, "-c", String.valueOf(a6Var.d()), "-w", String.valueOf(a6Var.f()), a6Var.b()};
    }

    public static void a(StringBuffer stringBuffer, String str) {
        if (stringBuffer != null) {
            stringBuffer.append(str).append("\n");
        }
    }

    public static String a(String str) {
        String str2 = null;
        for (String str3 : str.split("\n")) {
            if (str3.contains("time=")) {
                str2 = str3.substring(str3.indexOf("time=") + 5);
                Logger.v(f5592a, "the ping time is : " + str2);
            }
        }
        return str2;
    }

    public static a6 a(String str, a6 a6Var) {
        String a2 = a(str);
        if (a2 != null) {
            a6Var.d(a2);
            Logger.v(f5592a, "ping time:" + a2);
        }
        if (str.contains("avg")) {
            int indexOf = str.indexOf("/", 20);
            int indexOf2 = str.indexOf(".", indexOf);
            StringBuilder sb = new StringBuilder("ping avg time:");
            int i = indexOf + 1;
            sb.append(str.substring(i, indexOf2));
            sb.append("ms");
            Logger.v(f5592a, sb.toString());
            a6Var.a(str.substring(i, indexOf2) + "ms");
        }
        if (str.contains("packet loss")) {
            int indexOf3 = str.indexOf("%");
            StringBuilder sb2 = new StringBuilder("packet loss rate:");
            int i2 = indexOf3 - 2;
            sb2.append(str.substring(i2, indexOf3));
            Logger.v(f5592a, sb2.toString());
            a6Var.c(str.substring(i2, indexOf3));
        }
        return a6Var;
    }
}

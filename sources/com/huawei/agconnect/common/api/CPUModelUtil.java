package com.huawei.agconnect.common.api;

/* loaded from: classes2.dex */
public class CPUModelUtil {
    private static final String FILE_KEY = "Hardware";
    private static final String FILE_PATH = "/proc/cpuinfo";
    private static final String SPLIT_KEY = ":";
    private static final String TAG = "CPUModelUtil";

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
    
        r0 = r3.split(":")[1].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0046, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0047, code lost:
    
        com.huawei.agconnect.common.api.Logger.e(com.huawei.agconnect.common.api.CPUModelUtil.TAG, "close cpuinfo io error :" + r3.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005b, code lost:
    
        r4.close();
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCpuModel() {
        /*
            java.lang.String r0 = ":"
            java.lang.String r1 = "close cpuinfo io error :"
            java.lang.String r2 = "CPUModelUtil"
            r3 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
            java.lang.String r7 = "/proc/cpuinfo"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
            java.lang.String r7 = "UTF-8"
            r5.<init>(r6, r7)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L95
        L1a:
            java.lang.String r3 = r4.readLine()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            if (r3 == 0) goto L5b
            java.lang.String r5 = "Hardware"
            boolean r5 = r3.contains(r5)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            if (r5 == 0) goto L1a
            java.lang.String[] r5 = r3.split(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            int r5 = r5.length     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            r6 = 1
            if (r5 <= r6) goto L1a
            java.lang.String[] r5 = r3.split(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            r5 = r5[r6]     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            if (r5 == 0) goto L1a
            java.lang.String[] r0 = r3.split(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            r0 = r0[r6]     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68 java.io.FileNotFoundException -> L6b
            r4.close()     // Catch: java.io.IOException -> L46
            goto L5a
        L46:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r1 = r3.getMessage()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.huawei.agconnect.common.api.Logger.e(r2, r1)
        L5a:
            return r0
        L5b:
            r4.close()     // Catch: java.io.IOException -> L5f
            goto Lb4
        L5f:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            goto La6
        L66:
            r0 = move-exception
            goto Lb8
        L68:
            r0 = move-exception
            r3 = r4
            goto L70
        L6b:
            r3 = r4
            goto L95
        L6d:
            r0 = move-exception
            goto Lb7
        L6f:
            r0 = move-exception
        L70:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6d
            r4.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = "read cpuinfo file error :"
            r4.append(r5)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L6d
            r4.append(r0)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L6d
            com.huawei.agconnect.common.api.Logger.e(r2, r0)     // Catch: java.lang.Throwable -> L6d
            if (r3 == 0) goto Lb4
            r3.close()     // Catch: java.io.IOException -> L8e
            goto Lb4
        L8e:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            goto La6
        L95:
            java.lang.String r0 = "can not find cpuinfo file"
            com.huawei.agconnect.common.api.Logger.e(r2, r0)     // Catch: java.lang.Throwable -> L6d
            if (r3 == 0) goto Lb4
            r3.close()     // Catch: java.io.IOException -> La0
            goto Lb4
        La0:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
        La6:
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.huawei.agconnect.common.api.Logger.e(r2, r0)
        Lb4:
            java.lang.String r0 = ""
            return r0
        Lb7:
            r4 = r3
        Lb8:
            if (r4 == 0) goto Ld2
            r4.close()     // Catch: java.io.IOException -> Lbe
            goto Ld2
        Lbe:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r1 = r3.getMessage()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.huawei.agconnect.common.api.Logger.e(r2, r1)
        Ld2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.common.api.CPUModelUtil.getCpuModel():java.lang.String");
    }
}

package com.huawei.health.h5pro.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class LogPrintOperate {
    public static final Pattern b = Pattern.compile("\\d*[a-z|A-Z]*[一-龥]*");
    public static volatile LogDecodePrintInterceptor c;

    public interface LogDecodePrintInterceptor {
        boolean isDecodePrint(String str);
    }

    public static void setLogDecodePrintInterceptor(LogDecodePrintInterceptor logDecodePrintInterceptor) {
        synchronized (LogPrintOperate.class) {
            c = logDecodePrintInterceptor;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0016, code lost:
    
        if (com.huawei.health.h5pro.utils.LogPrintOperate.c.isDecodePrint(r3) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isDecodePrint(java.lang.String r3) {
        /*
            java.lang.Class<com.huawei.health.h5pro.utils.LogPrintOperate> r0 = com.huawei.health.h5pro.utils.LogPrintOperate.class
            monitor-enter(r0)
            boolean r1 = com.huawei.health.h5pro.utils.LogUtil.isPrint()     // Catch: java.lang.Throwable -> L1c
            r2 = 1
            if (r1 == 0) goto Lc
            monitor-exit(r0)
            return r2
        Lc:
            com.huawei.health.h5pro.utils.LogPrintOperate$LogDecodePrintInterceptor r1 = com.huawei.health.h5pro.utils.LogPrintOperate.c     // Catch: java.lang.Throwable -> L1c
            if (r1 == 0) goto L19
            com.huawei.health.h5pro.utils.LogPrintOperate$LogDecodePrintInterceptor r1 = com.huawei.health.h5pro.utils.LogPrintOperate.c     // Catch: java.lang.Throwable -> L1c
            boolean r3 = r1.isDecodePrint(r3)     // Catch: java.lang.Throwable -> L1c
            if (r3 == 0) goto L19
            goto L1a
        L19:
            r2 = 0
        L1a:
            monitor-exit(r0)
            return r2
        L1c:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.utils.LogPrintOperate.isDecodePrint(java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0033, code lost:
    
        if (com.huawei.health.h5pro.utils.LogPrintOperate.c.isDecodePrint(r4) != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isDecodePrint(com.huawei.health.h5pro.vengine.H5ProInstance r6) {
        /*
            java.lang.Class<com.huawei.health.h5pro.utils.LogPrintOperate> r0 = com.huawei.health.h5pro.utils.LogPrintOperate.class
            monitor-enter(r0)
            boolean r1 = com.huawei.health.h5pro.utils.LogUtil.isPrint()     // Catch: java.lang.Throwable -> L39
            r2 = 1
            if (r1 == 0) goto Lc
            monitor-exit(r0)
            return r2
        Lc:
            r1 = 0
            if (r6 != 0) goto L11
            monitor-exit(r0)
            return r1
        L11:
            com.huawei.health.h5pro.vengine.H5ProAppInfo r3 = r6.getAppInfo()     // Catch: java.lang.Throwable -> L39
            if (r3 != 0) goto L19
            monitor-exit(r0)
            return r1
        L19:
            java.lang.String r4 = r3.getPkgName()     // Catch: java.lang.Throwable -> L39
            com.huawei.health.h5pro.vengine.H5ProAppInfo$H5ProAppType r3 = r3.getH5ProAppType()     // Catch: java.lang.Throwable -> L39
            com.huawei.health.h5pro.vengine.H5ProAppInfo$H5ProAppType r5 = com.huawei.health.h5pro.vengine.H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM     // Catch: java.lang.Throwable -> L39
            if (r3 == r5) goto L29
            java.lang.String r4 = r6.getUrl()     // Catch: java.lang.Throwable -> L39
        L29:
            com.huawei.health.h5pro.utils.LogPrintOperate$LogDecodePrintInterceptor r6 = com.huawei.health.h5pro.utils.LogPrintOperate.c     // Catch: java.lang.Throwable -> L39
            if (r6 == 0) goto L36
            com.huawei.health.h5pro.utils.LogPrintOperate$LogDecodePrintInterceptor r6 = com.huawei.health.h5pro.utils.LogPrintOperate.c     // Catch: java.lang.Throwable -> L39
            boolean r6 = r6.isDecodePrint(r4)     // Catch: java.lang.Throwable -> L39
            if (r6 == 0) goto L36
            goto L37
        L36:
            r2 = r1
        L37:
            monitor-exit(r0)
            return r2
        L39:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.utils.LogPrintOperate.isDecodePrint(com.huawei.health.h5pro.vengine.H5ProInstance):boolean");
    }

    public static String encodingLog(String str) {
        synchronized (LogPrintOperate.class) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            int length = str.length();
            int i = 1;
            if (1 == length) {
                return str;
            }
            StringBuilder sb = new StringBuilder(length);
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (b.matcher(String.valueOf(charAt)).matches()) {
                    if (i % 2 == 0) {
                        charAt = '*';
                    }
                    i++;
                }
                sb.append(charAt);
            }
            return sb.toString();
        }
    }
}

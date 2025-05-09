package com.huawei.health.h5pro.load.rule;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.h5pro.utils.LogUtil;
import java.util.Date;
import java.util.regex.Pattern;

@Deprecated
/* loaded from: classes8.dex */
public class SimpleTimeRuleParser {
    public static long parseTime(String str) {
        if (!Pattern.matches("-?\\d+:[DH(MI)S]:M?S:\\d+", str)) {
            LogUtil.w("H5PRO_SimpleTimeRule", "parseTime, not match: " + str);
            return 0L;
        }
        String[] split = str.split(":");
        String str2 = split[2];
        long parseLong = Long.parseLong(split[3]);
        if (parseLong <= 0) {
            parseLong = getCurrentTime(str2);
        }
        return getTargetTime(parseLong, str2, Long.parseLong(split[0]), split[1]);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTargetTime(long r4, java.lang.String r6, long r7, java.lang.String r9) {
        /*
            r9.hashCode()
            int r0 = r9.hashCode()
            r1 = 68
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2c
            r1 = 72
            if (r0 == r1) goto L21
            r1 = 2460(0x99c, float:3.447E-42)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "MI"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L1f
            goto L34
        L1f:
            r9 = r2
            goto L37
        L21:
            java.lang.String r0 = "H"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L2a
            goto L34
        L2a:
            r9 = r3
            goto L37
        L2c:
            java.lang.String r0 = "D"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L36
        L34:
            r9 = -1
            goto L37
        L36:
            r9 = 0
        L37:
            r0 = 1000(0x3e8, double:4.94E-321)
            if (r9 == 0) goto L49
            if (r9 == r3) goto L45
            if (r9 == r2) goto L41
            long r7 = r7 * r0
            goto L4d
        L41:
            r2 = 60000(0xea60, double:2.9644E-319)
            goto L4c
        L45:
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            goto L4c
        L49:
            r2 = 86400000(0x5265c00, double:4.2687272E-316)
        L4c:
            long r7 = r7 * r2
        L4d:
            java.lang.String r9 = "S"
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L58
            long r7 = r7 / r0
            long r4 = r4 + r7
            return r4
        L58:
            long r4 = r4 + r7
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.load.rule.SimpleTimeRuleParser.getTargetTime(long, java.lang.String, long, java.lang.String):long");
    }

    public static long getCurrentTime(String str) {
        return ExifInterface.LATITUDE_SOUTH.equals(str) ? new Date().getTime() / 1000 : new Date().getTime();
    }
}

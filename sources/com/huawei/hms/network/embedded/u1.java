package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.wearengine.sensor.DataResult;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class u1 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5512a = "HttpHeadersUtils";
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
    public static final ThreadLocal<DateFormat> b = new a();
    public static final ThreadLocal<DateFormat> c = new b();

    public static long stringToLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            Logger.w(f5512a, "String to Long catch NumberFormatException.", e);
            return j;
        }
    }

    public static int stringToInteger(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            Logger.w(f5512a, "String to Integer catch NumberFormatException.", e);
            return i;
        }
    }

    public static int skipWhitespace(String str, int i) {
        for (int i2 = i; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ' && charAt != '\t') {
                return i2;
            }
        }
        return i;
    }

    public static int skipUntil(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0011, code lost:
    
        if (r3 < 0) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parseSeconds(java.lang.String r3, int r4) {
        /*
            long r3 = java.lang.Long.parseLong(r3)     // Catch: java.lang.NumberFormatException -> L16
            r0 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r2 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r2 <= 0) goto Ld
        Lb:
            r3 = r0
            goto L14
        Ld:
            r0 = 0
            int r2 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r2 >= 0) goto L14
            goto Lb
        L14:
            int r3 = (int) r3
            return r3
        L16:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.u1.parseSeconds(java.lang.String, int):int");
    }

    public class a extends ThreadLocal<DateFormat> {
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(u1.UTC);
            return simpleDateFormat;
        }
    }

    public class b extends ThreadLocal<DateFormat> {
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
            return simpleDateFormat;
        }
    }

    public static String parseCurrentUTCTime() {
        return c.get().format(new Date());
    }

    public static Date parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        DateFormat dateFormat = b.get();
        if (dateFormat == null) {
            Logger.w(f5512a, "dateFormat == null");
            return null;
        }
        Date parse = dateFormat.parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        return null;
    }
}

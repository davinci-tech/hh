package com.huawei.hms.network.embedded;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes9.dex */
public final class h9 {

    /* renamed from: a, reason: collision with root package name */
    public static final long f5293a = 253402300799999L;
    public static final ThreadLocal<DateFormat> b = new a();
    public static final String[] c = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    public static final DateFormat[] d = new DateFormat[15];

    public class a extends ThreadLocal<DateFormat> {
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(f8.i);
            return simpleDateFormat;
        }
    }

    public static Date a(String str) {
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = b.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        String[] strArr = c;
        synchronized (strArr) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                DateFormat[] dateFormatArr = d;
                DateFormat dateFormat = dateFormatArr[i];
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(c[i], Locale.US);
                    dateFormat.setTimeZone(f8.i);
                    dateFormatArr[i] = dateFormat;
                }
                parsePosition.setIndex(0);
                Date parse2 = dateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return parse2;
                }
            }
            return null;
        }
    }

    public static String a(Date date) {
        return b.get().format(date);
    }
}

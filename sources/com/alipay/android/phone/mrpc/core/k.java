package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import com.huawei.wearengine.sensor.DataResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public static final Pattern f825a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    public static final Pattern b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    public static a e(String str) {
        int charAt = str.charAt(0) - '0';
        int i = 1;
        if (str.charAt(1) != ':') {
            charAt = (charAt * 10) + (str.charAt(1) - '0');
            i = 2;
        }
        return new a(charAt, ((str.charAt(i + 1) - '0') * 10) + (str.charAt(i + 2) - '0'), ((str.charAt(i + 4) - '0') * 10) + (str.charAt(i + 5) - '0'));
    }

    public static int d(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return charAt >= 70 ? charAt + 1900 : charAt + 2000;
        }
        if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        }
        if (str.length() == 4) {
            return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
        }
        return 1970;
    }

    public static int c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase == 9) {
            return 11;
        }
        if (lowerCase == 10) {
            return 1;
        }
        if (lowerCase == 22) {
            return 0;
        }
        if (lowerCase == 26) {
            return 7;
        }
        if (lowerCase == 29) {
            return 2;
        }
        if (lowerCase == 32) {
            return 3;
        }
        if (lowerCase == 40) {
            return 6;
        }
        if (lowerCase == 42) {
            return 5;
        }
        if (lowerCase == 48) {
            return 10;
        }
        switch (lowerCase) {
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public int f826a;
        public int b;
        public int c;

        public a(int i, int i2, int i3) {
            this.f826a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public static long a(String str) {
        int c;
        int d;
        int i;
        a aVar;
        int i2;
        int i3;
        int i4;
        Matcher matcher = f825a.matcher(str);
        if (matcher.find()) {
            i = b(matcher.group(1));
            c = c(matcher.group(2));
            d = d(matcher.group(3));
            aVar = e(matcher.group(4));
        } else {
            Matcher matcher2 = b.matcher(str);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            c = c(matcher2.group(1));
            int b2 = b(matcher2.group(2));
            a e = e(matcher2.group(3));
            d = d(matcher2.group(4));
            i = b2;
            aVar = e;
        }
        if (d >= 2038) {
            i3 = 0;
            i4 = 2038;
            i2 = 1;
        } else {
            i2 = i;
            i3 = c;
            i4 = d;
        }
        Time time = new Time(DataResult.UTC);
        time.set(aVar.c, aVar.b, aVar.f826a, i2, i3, i4);
        return time.toMillis(false);
    }
}

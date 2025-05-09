package com.huawei.secure.android.common.anonymization;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes6.dex */
public class Anonymizer {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8571a = "Anonymizer";

    public static String maskAccountId(String str) {
        return (str == null || str.length() <= 0) ? "" : str.length() <= 1 ? "*" : str.length() < 8 ? maskCommonString(str, 0, 1) : maskCommonString(str, 0, 4);
    }

    public static String maskBankAccount(String str) {
        return (str == null || str.length() <= 0) ? "" : str.length() <= 2 ? a.a(str, '*') : (str.length() < 11 || str.length() >= 20) ? maskCommonString(str, 6, 4) : maskCommonString(str, 4, 4);
    }

    public static String maskBirthday(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 4) {
            return a.a(str, '*');
        }
        String[] a2 = a.a(str, 4);
        return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), "0123456789", "*"));
    }

    public static String maskCommonString(String str, int i, int i2) {
        int length;
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (str.length() <= i + i2) {
            length = str.length() - 1;
            i = 1;
        } else {
            length = str.length() - i2;
        }
        String[] a2 = a.a(str, i, length);
        return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), '*'), a.a(a2, 2));
    }

    public static String maskEmail(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 1) {
            return "*";
        }
        int indexOf = str.indexOf(64);
        if (indexOf < 0) {
            String[] a2 = a.a(str, 1);
            return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), '*'));
        }
        String[] a3 = a.a(str, indexOf, indexOf + 1, str.lastIndexOf(46));
        return a.a("", a.a(a.a(a3, 0), '*'), a.a(a3, 1), a.a(a.a(a3, 2), '*'), a.a(a3, 3));
    }

    public static String maskId(String str) {
        return (str == null || str.length() <= 0) ? "" : maskCommonString(str, str.length() - 12, 0);
    }

    public static String maskImeiImsi(String str) {
        return (str == null || str.length() <= 0) ? "" : maskCommonString(str, str.length() - 4, 0);
    }

    public static String maskIpV4(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return a.a(str, '*');
        }
        String[] a2 = a.a(str, lastIndexOf + 1);
        return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), '*'));
    }

    public static String maskIpV6(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        String[] b = a.b(str, ':');
        if (b.length <= 1) {
            return a.a(str, '*');
        }
        if (b.length != 8) {
            String[] a2 = a.a(str, str.indexOf(58) + 1);
            return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), "0123456789ABCDEFabcdef", "*"));
        }
        b[2] = maskLower8Bit(b[2]);
        for (int i = 3; i < b.length; i++) {
            b[i] = a.a(b[i], '*');
        }
        return a.a(":", b);
    }

    public static String maskLower8Bit(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f8571a, "maskLower8Bit: s is null");
            return "";
        }
        if (str.length() <= 2) {
            return a.a(str, '*');
        }
        String[] a2 = a.a(str, str.length() - 2);
        return a.a("", a.a(a2, 0), a.a(a.a(a2, 1), '*'));
    }

    public static String maskMac(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        int a2 = a.a(str, '-', 2);
        if (a2 < 0) {
            return a.a(str, "0123456789ABCDEFabcdef", "*");
        }
        String[] a3 = a.a(str, a2);
        return a.a("", a.a(a3, 0), a.a(a.a(a3, 1), "0123456789ABCDEFabcdef", "*"));
    }

    public static String maskName(String str) {
        return (str == null || str.length() <= 0) ? "" : str.length() <= 1 ? "*" : maskCommonString(str, 1, 0);
    }

    public static String maskPhone(String str) {
        return (str == null || str.length() <= 0) ? "" : str.length() <= 2 ? a.a(str, '*') : (str.length() < 8 || str.length() >= 11) ? maskCommonString(str, 3, 4) : maskCommonString(str, 2, 2);
    }
}

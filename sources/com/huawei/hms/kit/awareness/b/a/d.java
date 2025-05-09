package com.huawei.hms.kit.awareness.b.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4827a = "^[a-zA-Z0-9]+-dr([a-zA-Z]+)\\.dt\\.(hicloud.com|dbankcloud.cn)";
    private static final Pattern b = Pattern.compile(f4827a);

    public static String b(String str) {
        Matcher a2 = a(str);
        return a2.find() ? a2.group(1) : "";
    }

    public static boolean a(String str, String str2) {
        String str3 = str == null ? str2 : str;
        if (str3 == null) {
            return true;
        }
        return str3.equals(str2) && str3.equals(str);
    }

    public static Matcher a(String str) {
        return b.matcher(str);
    }
}

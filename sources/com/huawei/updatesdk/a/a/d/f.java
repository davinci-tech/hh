package com.huawei.updatesdk.a.a.d;

import java.util.List;

/* loaded from: classes7.dex */
public class f {
    public static String a(String[] strArr, String str, String str2, String str3) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        String a2 = a(str);
        String a3 = a(str2);
        String a4 = a(str3);
        StringBuilder sb = new StringBuilder();
        sb.append(a3);
        sb.append(strArr[0]);
        sb.append(a4);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(a2);
            sb.append(a3);
            sb.append(strArr[i]);
            sb.append(a4);
        }
        return sb.toString();
    }

    public static String a(String[] strArr, String str) {
        return a(strArr, str, (String) null, (String) null);
    }

    public static String a(List<String> list, String str, String str2, String str3) {
        return (list == null || list.isEmpty()) ? "" : a((String[]) list.toArray(new String[0]), str, str2, str3);
    }

    public static String a(List<String> list, String str) {
        return a(list, str, (String) null, (String) null);
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }
}

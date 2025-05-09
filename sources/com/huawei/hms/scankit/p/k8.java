package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class k8 extends t6 {
    private static final Pattern b = Pattern.compile("WIFI:[^:]", 2);
    static final String[] c = new String[0];

    static String[] a(String str, String str2, char c2, boolean z) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i = 0;
        while (i < length) {
            int indexOf = str2.indexOf(str, i);
            if (indexOf < 0) {
                break;
            }
            int length2 = indexOf + str.length();
            boolean z2 = true;
            ArrayList arrayList2 = arrayList;
            int i2 = length2;
            while (z2) {
                int indexOf2 = str2.indexOf(c2, i2);
                if (indexOf2 < 0) {
                    i2 = str2.length();
                } else if (a(str2, indexOf2) % 2 != 0) {
                    i2 = indexOf2 + 1;
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(3);
                    }
                    String b2 = t6.b(str2.substring(length2, indexOf2));
                    if (z) {
                        b2 = b2.trim();
                    }
                    arrayList2.add(b2);
                    i2 = indexOf2 + 1;
                }
                z2 = false;
            }
            i = i2;
            arrayList = arrayList2;
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(c);
    }

    private static int c(String str) {
        if (str == null) {
            return 0;
        }
        if (str.equalsIgnoreCase("WEP")) {
            return 2;
        }
        if ((str.equalsIgnoreCase("WPA") | str.equalsIgnoreCase("WPA2") | str.equalsIgnoreCase("WPA/WPA2")) || str.equalsIgnoreCase("WPA2/WPA")) {
            return 1;
        }
        return str.equalsIgnoreCase("SAE") ? 3 : 0;
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String str;
        String a2 = t6.a(s6Var);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        Matcher matcher = b.matcher(a2);
        if (matcher.find() && matcher.start() == 0) {
            String substring = a2.substring(5);
            if (!substring.endsWith(";")) {
                substring = substring + ";";
            }
            String b2 = b("S:", substring, ';', false);
            if (b2 != null && !b2.isEmpty()) {
                String b3 = b("P:", substring, ';', false);
                String b4 = b("T:", substring, ';', false);
                StringBuilder sb = new StringBuilder();
                sb.append(b2);
                if (b3 == null || b3.isEmpty()) {
                    str = "";
                } else {
                    str = " " + b3;
                }
                sb.append(str);
                return new HmsScan(s6Var.k(), t6.a(s6Var.c()), sb.toString(), HmsScan.WIFI_CONNECT_INFO_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.WiFiConnectionInfo(b2, b3, c(b4))));
            }
        }
        return null;
    }

    private String b(String str, String str2, char c2, boolean z) {
        String str3;
        String[] a2 = a(str, str2, c2, z);
        return (a2 == null || a2.length == 0 || (str3 = a2[0]) == null) ? "" : str3;
    }

    private static int a(CharSequence charSequence, int i) {
        int i2 = 0;
        for (int i3 = i - 1; i3 >= 0 && charSequence.charAt(i3) == '\\'; i3--) {
            i2++;
        }
        return i2;
    }
}

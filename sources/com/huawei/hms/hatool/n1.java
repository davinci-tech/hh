package com.huawei.hms.hatool;

import android.util.Pair;
import com.huawei.openalliance.ad.constant.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class n1 {
    public static Set<String> a(Set<String> set) {
        if (set == null || set.size() == 0) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if ("_default_config_tag".equals(str)) {
                hashSet.add("_default_config_tag");
            } else {
                String str2 = str + "-oper";
                String str3 = str + "-maint";
                hashSet.add(str2);
                hashSet.add(str3);
                hashSet.add(str + "-diffprivacy");
            }
        }
        return hashSet;
    }

    public static String a(String str, String str2, String str3) {
        StringBuilder sb;
        if ("_default_config_tag".equals(str)) {
            sb = new StringBuilder("_default_config_tag#");
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(Constants.LINK);
            sb2.append(str2);
            sb2.append("#");
            sb = sb2;
        }
        sb.append(str3);
        return sb.toString();
    }

    public static String a(String str, String str2) {
        if ("_default_config_tag".equals(str)) {
            return str;
        }
        return str + Constants.LINK + str2;
    }

    public static String a(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "alltype" : "diffprivacy" : "preins" : "maint" : "oper";
    }

    public static Pair<String, String> a(String str) {
        String str2;
        String str3;
        if ("_default_config_tag".equals(str)) {
            return new Pair<>(str, "");
        }
        String[] split = str.split(Constants.LINK);
        if (split.length > 2) {
            str3 = split[split.length - 1];
            str2 = str.substring(0, (str.length() - str3.length()) - 1);
        } else {
            str2 = split[0];
            str3 = split[1];
        }
        return new Pair<>(str2, str3);
    }

    public static long a(String str, long j) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.getDefault());
            return simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(j))).getTime();
        } catch (ParseException unused) {
            v.f("hmsSdk/stringUtil", "getMillisOfDate(): Time conversion Exception !");
            return 0L;
        }
    }
}

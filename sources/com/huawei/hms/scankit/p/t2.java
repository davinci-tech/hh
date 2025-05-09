package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class t2 extends t6 {
    private static final Pattern b = Pattern.compile("(?:MATMSG:TO:|mailto:|SMTP:)([\\s\\S]+)", 2);
    private static final Pattern c = Pattern.compile("mailto:([\\s\\S]+)\\?subject=([\\s\\S]+)&body=([\\s\\S]+)", 2);
    private static final Pattern d = Pattern.compile("MATMSG:TO:([\\s\\S]+);SUB:([\\s\\S]+);BODY:([\\s\\S]+)", 2);
    private static final Pattern e = Pattern.compile("SMTP:([\\s\\S]+):([\\s\\S]+):([\\s\\S]+)", 2);

    static String c(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        Matcher matcher;
        Matcher matcher2;
        Matcher matcher3;
        String group;
        String str;
        String group2;
        String a2 = t6.a(s6Var);
        if (TextUtils.isEmpty(a2) || !b.matcher(a2).matches()) {
            return null;
        }
        try {
            matcher = c.matcher(a2);
            matcher2 = d.matcher(a2);
            matcher3 = e.matcher(a2);
        } catch (RuntimeException | Exception unused) {
        }
        if (matcher.matches()) {
            String group3 = matcher.group(1);
            group = matcher.group(2);
            group2 = matcher.group(3);
            str = group3;
        } else {
            if (!matcher2.matches()) {
                if (matcher3.matches()) {
                    String group4 = matcher3.group(1);
                    group = matcher3.group(2);
                    str = group4;
                    group2 = matcher3.group(3);
                }
                return null;
            }
            String group5 = matcher2.group(1);
            String group6 = matcher2.group(2);
            String group7 = matcher2.group(3);
            str = group5;
            group = group6;
            group2 = group7;
        }
        return new HmsScan(s6Var.k(), t6.a(s6Var.c()), str, HmsScan.EMAIL_CONTENT_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.EmailContent(str, c(group), c(group2), HmsScan.EmailContent.OTHER_USE_TYPE)));
    }
}

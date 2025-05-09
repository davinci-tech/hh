package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class t3 extends t6 {
    private static final Pattern b = Pattern.compile("(?:http:|http//|https://)([\\s\\S]+)", 2);
    private static final Pattern c = Pattern.compile("(?:http:/?(?!/)|http//)([\\s\\S]+)", 2);

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String a2 = t6.a(s6Var);
        if (!b.matcher(a2).matches()) {
            return null;
        }
        Matcher matcher = c.matcher(a2);
        if (matcher.matches()) {
            a2 = a2.substring(0, 4) + "://" + matcher.group(1);
        }
        String a3 = t6.a(a2);
        if (a3.length() == 7) {
            return null;
        }
        return new HmsScan(s6Var.k(), t6.a(s6Var.c()), a3, HmsScan.URL_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.LinkUrl("", a3)));
    }
}

package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class n4 extends t6 {
    private static final Pattern b = Pattern.compile("geo:([\\s\\-0-9.]+),([\\s\\-0-9.]+)(?:[,?].*)?", 2);

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String a2 = t6.a(s6Var);
        Matcher matcher = b.matcher(a2);
        if (!matcher.matches()) {
            return null;
        }
        try {
            return new HmsScan(s6Var.k(), t6.a(s6Var.c()), a2, HmsScan.LOCATION_COORDINATE_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.LocationCoordinate(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}

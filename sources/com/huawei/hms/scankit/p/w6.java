package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class w6 extends t6 {
    private static final Pattern b = Pattern.compile("(?:mmsto|smsto):([\\s\\S]+)", 2);

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String str;
        String str2;
        String str3;
        String a2 = t6.a(s6Var);
        if (TextUtils.isEmpty(a2) || !b.matcher(a2).matches()) {
            return null;
        }
        String substring = a2.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            str = substring.substring(0, indexOf);
            str2 = substring.substring(indexOf + 1);
        } else {
            str = substring;
            str2 = "";
        }
        if (str2.isEmpty()) {
            str3 = str;
        } else {
            str3 = str + "\n" + str2;
        }
        return new HmsScan(s6Var.k(), t6.a(s6Var.c()), str3, HmsScan.SMS_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.SmsContent(str2, str)));
    }
}

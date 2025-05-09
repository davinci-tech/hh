package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class n2 extends t6 {
    private static final List<String> c = new a();
    private String b = "";

    class a extends ArrayList<String> {
        a() {
            add("");
            add("DCT");
            add("DAD");
            add("DCS");
            add("DBC");
            add("DBB");
            add("DAG");
            add("DAI");
            add("DAJ");
            add("DAK");
            add("DAQ");
            add("DCG");
            add("DBD");
            add("DBA");
        }
    }

    public HmsScan.DriverInfo a(String[] strArr, String str) {
        String[] strArr2 = {"", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        strArr2[0] = str;
        boolean z = false;
        for (String str2 : strArr) {
            if (str2.length() <= 3) {
                return null;
            }
            int indexOf = c.indexOf(str2.substring(0, 3));
            if (indexOf != -1) {
                strArr2[indexOf] = str2.substring(3).trim();
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        this.b = strArr2[0] + " " + strArr2[3] + " " + strArr2[1];
        return new HmsScan.DriverInfo(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4], strArr2[5], strArr2[6], strArr2[7], strArr2[8], strArr2[9], strArr2[10], strArr2[11], strArr2[12], strArr2[13], null, null, null, null);
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String a2 = t6.a(s6Var);
        if (!TextUtils.isEmpty(a2) && a2.startsWith("@") && a2.length() > 34 && a2.substring(4, 8).equals("ANSI")) {
            char charAt = a2.charAt(1);
            char charAt2 = a2.charAt(3);
            String substring = a2.substring(21, 23);
            HmsScan.DriverInfo a3 = a(a2.substring(a2.indexOf(substring, 23) + 2).split(String.valueOf(charAt2))[0].split(String.valueOf(charAt)), substring);
            if (a3 != null) {
                return new HmsScan(s6Var.k(), t6.a(s6Var.c()), this.b, HmsScan.DRIVER_INFO_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(a3));
            }
        }
        return null;
    }
}

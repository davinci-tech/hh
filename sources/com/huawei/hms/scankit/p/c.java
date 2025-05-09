package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class c extends t6 {
    private static final Pattern b = Pattern.compile("(?:MECARD:)([\\s\\S]+)", 2);
    static final String[] c = new String[0];

    private static HmsScan.PeopleName a(String str, String str2) {
        HmsScan.PeopleName peopleName = new HmsScan.PeopleName("", "", "", "", "", "", "");
        peopleName.spelling = str2;
        int indexOf = str.indexOf(",");
        if (indexOf < 0) {
            peopleName.fullName = str;
            String[] split = str.split(" ");
            if (split.length > 0) {
                peopleName.givenName = split[0];
            }
            if (split.length > 1) {
                peopleName.familyName = split[1];
            }
        } else {
            peopleName.familyName = str.substring(0, indexOf);
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(",", i);
            if (indexOf2 < 0) {
                peopleName.givenName = str.substring(i);
            } else {
                peopleName.givenName = str.substring(i, indexOf2);
            }
            peopleName.fullName = peopleName.givenName + " " + peopleName.familyName;
        }
        return peopleName;
    }

    private static HmsScan.TelPhoneNumber[] c(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new HmsScan.TelPhoneNumber[0];
        }
        HmsScan.TelPhoneNumber[] telPhoneNumberArr = new HmsScan.TelPhoneNumber[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            telPhoneNumberArr[i] = new HmsScan.TelPhoneNumber(HmsScan.TelPhoneNumber.OTHER_USE_TYPE, strArr[i]);
        }
        return telPhoneNumberArr;
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String a2 = t6.a(s6Var);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        Matcher matcher = b.matcher(a2);
        if (!matcher.matches()) {
            return null;
        }
        String[] split = matcher.group(1).split("(?<=(?<!\\\\)(?:\\\\\\\\){0,100});");
        String a3 = a(split, "N:");
        if (a3.length() == 0) {
            return null;
        }
        String[] b2 = b(split, "TEL:");
        String[] b3 = b(split, "EMAIL:");
        String[] b4 = b(split, "ADR:");
        String[] b5 = b(split, "URL:");
        String a4 = a(split, "SOUND:");
        HmsScan.ContactDetail contactDetail = new HmsScan.ContactDetail(a(a3, a4), "", a(split, "ORG:"), c(b2), b(b3), a(b4), b5, a(split, "NOTE:"));
        return new HmsScan(s6Var.k(), t6.a(s6Var.c()), contactDetail.peopleName.fullName, HmsScan.CONTACT_DETAIL_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(contactDetail));
    }

    private static HmsScan.AddressInfo[] a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new HmsScan.AddressInfo[0];
        }
        HmsScan.AddressInfo[] addressInfoArr = new HmsScan.AddressInfo[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            addressInfoArr[i] = new HmsScan.AddressInfo(new String[]{strArr[i]}, HmsScan.AddressInfo.OTHER_USE_TYPE);
        }
        return addressInfoArr;
    }

    private static String a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.startsWith(str)) {
                return t6.b(str2.substring(str.length()));
            }
        }
        return "";
    }

    private static HmsScan.EmailContent[] b(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new HmsScan.EmailContent[0];
        }
        HmsScan.EmailContent[] emailContentArr = new HmsScan.EmailContent[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            emailContentArr[i] = new HmsScan.EmailContent(strArr[i], "", "", HmsScan.EmailContent.OTHER_USE_TYPE);
        }
        return emailContentArr;
    }

    private static String[] b(String[] strArr, String str) {
        ArrayList arrayList = new ArrayList(5);
        for (String str2 : strArr) {
            if (str2.startsWith(str)) {
                arrayList.add(t6.b(str2.substring(str.length())));
            }
        }
        return (String[]) arrayList.toArray(c);
    }
}

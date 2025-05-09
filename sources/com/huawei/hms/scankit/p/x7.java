package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.utils.Constants;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class x7 extends t6 {
    private static final Pattern b = Pattern.compile("\r?\n[ \t]");
    private static final Pattern c = Pattern.compile("=");
    private static final Pattern d = Pattern.compile(";");
    private static final Pattern e = Pattern.compile("(?<!\\\\);+");
    static final String[] f = new String[0];

    protected static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    static int a(int i, String str, boolean z) {
        int indexOf;
        while (true) {
            indexOf = str.indexOf(10, i);
            if (indexOf >= 0) {
                if (indexOf < str.length() - 1) {
                    int i2 = indexOf + 1;
                    if (str.charAt(i2) == ' ' || str.charAt(i2) == '\t') {
                        i = indexOf + 2;
                    }
                }
                if (!z || (!a(indexOf, 1, str) && !a(indexOf, 2, str))) {
                    break;
                }
                i = indexOf + 1;
            } else {
                break;
            }
        }
        return indexOf;
    }

    private static HmsScan.TelPhoneNumber[] c(String[] strArr, String[] strArr2) {
        if (strArr.length != strArr2.length) {
            return new HmsScan.TelPhoneNumber[0];
        }
        HmsScan.TelPhoneNumber[] telPhoneNumberArr = new HmsScan.TelPhoneNumber[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            int i2 = HmsScan.TelPhoneNumber.OTHER_USE_TYPE;
            HmsScan.TelPhoneNumber telPhoneNumber = new HmsScan.TelPhoneNumber(i2, strArr2[i]);
            String str = strArr[i];
            if (str != null) {
                if (str.equals("WORK")) {
                    telPhoneNumber.useType = HmsScan.TelPhoneNumber.OFFICE_USE_TYPE;
                } else if (strArr[i].equals(Constants.HOME)) {
                    telPhoneNumber.useType = HmsScan.TelPhoneNumber.RESIDENTIAL_USE_TYPE;
                } else if (strArr[i].equals("CELL")) {
                    telPhoneNumber.useType = HmsScan.TelPhoneNumber.CELLPHONE_NUMBER_USE_TYPE;
                } else if (strArr[i].equals("FAX")) {
                    telPhoneNumber.useType = HmsScan.TelPhoneNumber.FAX_USE_TYPE;
                } else {
                    telPhoneNumber.useType = i2;
                }
            }
            telPhoneNumberArr[i] = telPhoneNumber;
        }
        return telPhoneNumberArr;
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        String a2 = t6.a(s6Var);
        if (!a2.startsWith("BEGIN:VCARD")) {
            return null;
        }
        String str = a2 + "\n";
        String a3 = a("N", str, true, false);
        if (a3 == null || a3.isEmpty() || a3.split(";").length == 0) {
            return null;
        }
        String a4 = a("FN", str, true, false);
        if (a4 == null || a4.isEmpty()) {
            a4 = c(a3);
        }
        String str2 = a4;
        List<List<String>> b2 = b("TEL", str, true, false);
        List<List<String>> b3 = b(CommonConstant.RETKEY.EMAIL, str, true, false);
        List<List<String>> b4 = b("ADR", str, true, true);
        return new HmsScan(s6Var.k(), t6.a(s6Var.c()), str2, HmsScan.CONTACT_DETAIL_FORM, s6Var.i(), t6.a(s6Var.j()), null, new z6(new HmsScan.ContactDetail(a(a3, str2), a("TITLE", str, true, false), a("ORG", str, true, true), c(b(b2), a(b2)), b(b(b3), a(b3)), a(b(b4), a(b4)), a(b("URL", str, true, false)), null)));
    }

    static void a(String str, boolean z, boolean z2, String str2, boolean z3, List<String> list, List<List<String>> list2) {
        String replaceAll;
        if (z) {
            str = str.trim();
        }
        if (z2) {
            replaceAll = a((CharSequence) str, str2);
            if (z3) {
                replaceAll = e.matcher(replaceAll).replaceAll(" ").trim();
            }
        } else {
            if (z3) {
                str = e.matcher(str).replaceAll(" ").trim();
            }
            replaceAll = b.matcher(str).replaceAll("");
        }
        if (list == null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(replaceAll);
            list2.add(arrayList);
        } else {
            list.add(0, replaceAll);
            list2.add(list);
        }
    }

    private static String c(String str) {
        int indexOf;
        if (str == null || str.isEmpty()) {
            return null;
        }
        String[] strArr = new String[5];
        int i = 0;
        int i2 = 0;
        while (i < 4 && (indexOf = str.indexOf(59, i2)) >= 0) {
            strArr[i] = str.substring(i2, indexOf);
            i++;
            i2 = indexOf + 1;
        }
        strArr[i] = str.substring(i2);
        StringBuilder sb = new StringBuilder(100);
        a(strArr, 3, sb);
        a(strArr, 1, sb);
        a(strArr, 2, sb);
        a(strArr, 0, sb);
        a(strArr, 4, sb);
        return sb.toString().trim();
    }

    private static String a(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (charAt2 != '\n' && charAt2 != '\r') {
                if (charAt2 != '=') {
                    a(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (i < length - 2 && (charAt = charSequence.charAt(i + 1)) != '\r' && charAt != '\n') {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int a2 = a(charAt);
                    int a3 = a(charAt3);
                    if (a2 >= 0 && a3 >= 0) {
                        byteArrayOutputStream.write((a2 << 4) + a3);
                    }
                }
            }
            i++;
        }
        a(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    static List<List<String>> b(CharSequence charSequence, String str, boolean z, boolean z2) {
        boolean z3;
        String str2;
        ArrayList arrayList;
        int length = str.length();
        Pattern compile = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:\n(?![ |\t])]*))?:");
        int i = 0;
        ArrayList arrayList2 = null;
        while (i < length) {
            Matcher matcher = compile.matcher(str);
            if (i > 0) {
                i--;
            }
            if (!matcher.find(i)) {
                break;
            }
            int end = matcher.end(0);
            String group = matcher.group(1);
            if (group != null) {
                z3 = false;
                ArrayList arrayList3 = null;
                String str3 = null;
                for (String str4 : d.split(group)) {
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList(1);
                    }
                    arrayList3.add(str4);
                    String[] split = c.split(str4, 2);
                    if (split.length > 1) {
                        if (MLAsrConstants.WAVE_PAINE_ENCODING.equalsIgnoreCase(split[0]) && "QUOTED-PRINTABLE".equalsIgnoreCase(split[1])) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(split[0])) {
                            str3 = split[1];
                        }
                    }
                }
                arrayList = arrayList3;
                str2 = str3;
            } else {
                z3 = false;
                str2 = null;
                arrayList = null;
            }
            int a2 = a(end, str, z3);
            if (a2 < 0) {
                a2 = length;
            } else if (a2 > end) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                }
                if (a2 >= 1) {
                    int i2 = a2 - 1;
                    if (str.charAt(i2) == '\r') {
                        a2 = i2;
                    }
                }
                a(str.substring(end, a2), z, z3, str2, z2, arrayList, arrayList2);
            }
            i = a2 + 1;
        }
        return arrayList2;
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String a(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> b2 = b(charSequence, str, z, z2);
        String str2 = "";
        if (b2 != null && !b2.isEmpty()) {
            for (List<String> list : b2) {
                if (list.get(0) != null && !list.get(0).isEmpty()) {
                    str2 = list.get(0);
                }
            }
        }
        return str2;
    }

    private static String[] b(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str2 = list.get(0);
            if (str2 != null && !str2.isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= list.size()) {
                        str = null;
                        break;
                    }
                    str = list.get(i);
                    int indexOf = str.indexOf(61);
                    if (indexOf < 0) {
                        break;
                    }
                    if ("TYPE".equals(str.substring(0, indexOf))) {
                        str = str.substring(indexOf + 1);
                        break;
                    }
                    i++;
                }
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(f);
    }

    private static String[] a(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<List<String>> it = collection.iterator();
        while (it.hasNext()) {
            String str = it.next().get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(f);
    }

    private static HmsScan.PeopleName a(String str, String str2) {
        HmsScan.PeopleName peopleName = new HmsScan.PeopleName("", "", "", "", "", "", "");
        if (str != null) {
            String[] split = str.split(";");
            if (split.length > 0) {
                peopleName.familyName = split[0];
            }
            if (split.length > 1) {
                peopleName.givenName = split[1];
            }
            if (split.length > 2) {
                peopleName.middleName = split[2];
            }
            if (split.length > 3) {
                peopleName.namePrefix = split[3];
            }
            if (split.length > 4) {
                peopleName.nameSuffix = split[4];
            }
        }
        if (str2 != null) {
            peopleName.fullName = str2;
        }
        return peopleName;
    }

    private static HmsScan.EmailContent[] b(String[] strArr, String[] strArr2) {
        if (strArr.length != strArr2.length) {
            return new HmsScan.EmailContent[0];
        }
        HmsScan.EmailContent[] emailContentArr = new HmsScan.EmailContent[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            HmsScan.EmailContent emailContent = new HmsScan.EmailContent(strArr2[i], "", "", HmsScan.EmailContent.OTHER_USE_TYPE);
            String str = strArr[i];
            if (str != null) {
                if (str.equals("WORK")) {
                    emailContent.addressType = HmsScan.EmailContent.OFFICE_USE_TYPE;
                } else if (strArr[i].equals(Constants.HOME)) {
                    emailContent.addressType = HmsScan.TelPhoneNumber.RESIDENTIAL_USE_TYPE;
                }
            }
            emailContentArr[i] = emailContent;
        }
        return emailContentArr;
    }

    private static HmsScan.AddressInfo[] a(String[] strArr, String[] strArr2) {
        if (strArr.length != strArr2.length) {
            return new HmsScan.AddressInfo[0];
        }
        HmsScan.AddressInfo[] addressInfoArr = new HmsScan.AddressInfo[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            HmsScan.AddressInfo addressInfo = new HmsScan.AddressInfo(new String[]{strArr2[i]}, HmsScan.AddressInfo.OTHER_USE_TYPE);
            String str = strArr[i];
            if (str != null) {
                if (str.equals("WORK")) {
                    addressInfo.addressType = HmsScan.AddressInfo.OFFICE_TYPE;
                } else if (strArr[i].equals(Constants.HOME)) {
                    addressInfo.addressType = HmsScan.AddressInfo.RESIDENTIAL_USE_TYPE;
                }
            }
            addressInfoArr[i] = addressInfo;
        }
        return addressInfoArr;
    }

    private static void a(String[] strArr, int i, StringBuilder sb) {
        String str = strArr[i];
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i]);
    }

    private static boolean a(int i, int i2, String str) {
        return i >= i2 && str.charAt(i - i2) == '=';
    }
}

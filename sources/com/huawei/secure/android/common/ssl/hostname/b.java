package com.huawei.secure.android.common.ssl.hostname;

import com.amap.api.col.p0003sl.ed;
import com.huawei.hms.network.embedded.v6;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.secure.android.common.ssl.util.d;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.SSLException;

/* loaded from: classes6.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f8630a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final String[] b;

    static {
        String[] strArr = {"ac", "co", "com", ed.f989a, "edu", "go", "gouv", "gov", CloudParamKeys.INFO, "lg", "ne", "net", "or", "org"};
        b = strArr;
        Arrays.sort(strArr);
    }

    public static final void a(String str, X509Certificate x509Certificate, boolean z) throws SSLException {
        String[] a2 = a(x509Certificate);
        String[] b2 = b(x509Certificate);
        d.a("", "cn is : " + Arrays.toString(a2));
        d.a("", "san is : " + Arrays.toString(b2));
        a(str, a2, b2, z);
    }

    public static String[] b(X509Certificate x509Certificate) {
        Collection<List<?>> collection;
        LinkedList linkedList = new LinkedList();
        try {
            collection = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            d.a("", "Error parsing certificate.", e);
            collection = null;
        }
        if (collection != null) {
            for (List<?> list : collection) {
                if (((Integer) list.get(0)).intValue() == 2) {
                    linkedList.add((String) list.get(1));
                }
            }
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    private static boolean c(String str) {
        return f8630a.matcher(str).matches();
    }

    public static final void a(String str, String[] strArr, String[] strArr2, boolean z) throws SSLException {
        String str2;
        LinkedList linkedList = new LinkedList();
        if (strArr != null && strArr.length > 0 && (str2 = strArr[0]) != null) {
            linkedList.add(str2);
        }
        if (strArr2 != null) {
            for (String str3 : strArr2) {
                if (str3 != null) {
                    linkedList.add(str3);
                }
            }
        }
        if (!linkedList.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();
            String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
            Iterator it = linkedList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                String lowerCase2 = ((String) it.next()).toLowerCase(Locale.ENGLISH);
                stringBuffer.append(" <");
                stringBuffer.append(lowerCase2);
                stringBuffer.append('>');
                if (it.hasNext()) {
                    stringBuffer.append(" OR");
                }
                if (lowerCase2.startsWith(v6.b.e) && lowerCase2.indexOf(46, 2) != -1 && a(lowerCase2) && !c(str)) {
                    boolean endsWith = lowerCase.endsWith(lowerCase2.substring(1));
                    if (endsWith && z) {
                        z2 = b(lowerCase) == b(lowerCase2);
                    } else {
                        z2 = endsWith;
                    }
                } else {
                    z2 = lowerCase.equals(lowerCase2);
                }
                if (z2) {
                    break;
                }
            }
            if (z2) {
                return;
            }
            throw new SSLException("hostname in certificate didn't match: <" + str + "> !=" + ((Object) stringBuffer));
        }
        throw new SSLException("Certificate for <" + str + "> doesn't contain CN or DNS subjectAlt");
    }

    public static int b(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        return i;
    }

    public static boolean a(String str) {
        int length = str.length();
        if (length >= 7 && length <= 9) {
            int i = length - 3;
            if (str.charAt(i) == '.') {
                if (Arrays.binarySearch(b, str.substring(2, i)) >= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String[] a(X509Certificate x509Certificate) {
        List<String> b2 = new a(x509Certificate.getSubjectX500Principal()).b("cn");
        if (b2.isEmpty()) {
            return null;
        }
        String[] strArr = new String[b2.size()];
        b2.toArray(strArr);
        return strArr;
    }
}

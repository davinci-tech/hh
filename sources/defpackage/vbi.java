package defpackage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vbi {
    private static final Logger e = vha.a((Class<?>) vbi.class);

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f17643a = Pattern.compile("\\s{2,}");

    public static boolean b(X509Certificate x509Certificate, boolean z) {
        if (x509Certificate.getKeyUsage() != null && !x509Certificate.getKeyUsage()[0]) {
            e.debug("certificate: {}, not for signing!", x509Certificate.getSubjectX500Principal());
            return false;
        }
        try {
            List<String> extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
            if (extendedKeyUsage != null && !extendedKeyUsage.isEmpty()) {
                e.trace("certificate: {}", x509Certificate.getSubjectX500Principal());
                String str = z ? "1.3.6.1.5.5.7.3.2" : "1.3.6.1.5.5.7.3.1";
                boolean z2 = false;
                for (String str2 : extendedKeyUsage) {
                    e.trace("   extkeyusage {}", str2);
                    if (str.equals(str2)) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    e.debug("certificate: {}, not for {}!", x509Certificate.getSubjectX500Principal(), z ? "client" : "server");
                    return false;
                }
            } else {
                e.debug("certificate: {}, no extkeyusage!", x509Certificate.getSubjectX500Principal());
            }
        } catch (CertificateParsingException e2) {
            e.warn("x509 certificate:", (Throwable) e2);
        }
        return true;
    }

    public static CertPath a(List<X509Certificate> list, int i) {
        if (list == null) {
            throw new NullPointerException("Certificate chain must not be null!");
        }
        if (i > list.size()) {
            throw new IllegalArgumentException("size must not be larger then certificate chain!");
        }
        try {
            if (!list.isEmpty()) {
                int size = list.size() - 1;
                X500Principal x500Principal = null;
                for (int i2 = 0; i2 <= size; i2++) {
                    X509Certificate x509Certificate = list.get(i2);
                    Logger logger = e;
                    logger.debug("Current Subject DN: {}", x509Certificate.getSubjectX500Principal().getName());
                    if (x500Principal != null && !x500Principal.equals(x509Certificate.getSubjectX500Principal())) {
                        logger.debug("Actual Issuer DN: {}", x509Certificate.getSubjectX500Principal().getName());
                        throw new IllegalArgumentException("Given certificates do not form a chain");
                    }
                    x500Principal = x509Certificate.getIssuerX500Principal();
                    logger.debug("Expected Issuer DN: {}", x500Principal.getName());
                    if (x500Principal.equals(x509Certificate.getSubjectX500Principal()) && i2 != size) {
                        throw new IllegalArgumentException("Given certificates do not form a chain, root is not the last!");
                    }
                }
                if (i < list.size()) {
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < i; i3++) {
                        arrayList.add(list.get(i3));
                    }
                    list = arrayList;
                }
            }
            return CertificateFactory.getInstance("X.509").generateCertPath(list);
        } catch (CertificateException e2) {
            throw new IllegalArgumentException("could not create X.509 certificate factory", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        if (r6.getIssuerX500Principal().equals(r6.getSubjectX500Principal()) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.security.cert.CertPath d(java.util.List<java.security.cert.X509Certificate> r5, java.util.List<javax.security.auth.x500.X500Principal> r6) {
        /*
            if (r5 == 0) goto L4d
            int r0 = r5.size()
            if (r0 <= 0) goto L48
            r1 = 1
            if (r6 == 0) goto L2b
            boolean r2 = r6.isEmpty()
            if (r2 != 0) goto L2b
            r2 = 0
            r3 = r2
        L13:
            if (r3 >= r0) goto L2c
            java.lang.Object r4 = r5.get(r3)
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            javax.security.auth.x500.X500Principal r4 = r4.getIssuerX500Principal()
            boolean r4 = r6.contains(r4)
            if (r4 == 0) goto L28
            int r2 = r3 + 1
            goto L2c
        L28:
            int r3 = r3 + 1
            goto L13
        L2b:
            r2 = r0
        L2c:
            if (r0 <= r1) goto L47
            if (r2 != r0) goto L47
            int r0 = r0 + (-1)
            java.lang.Object r6 = r5.get(r0)
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6
            javax.security.auth.x500.X500Principal r1 = r6.getIssuerX500Principal()
            javax.security.auth.x500.X500Principal r6 = r6.getSubjectX500Principal()
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L47
            goto L48
        L47:
            r0 = r2
        L48:
            java.security.cert.CertPath r5 = a(r5, r0)
            return r5
        L4d:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Certificate chain must not be null!"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vbi.d(java.util.List, java.util.List):java.security.cert.CertPath");
    }

    public static CertPath b(boolean z, CertPath certPath, X509Certificate[] x509CertificateArr) throws GeneralSecurityException {
        String str;
        boolean z2;
        String str2;
        boolean z3;
        boolean z4;
        X509Certificate x509Certificate;
        boolean z5;
        int i;
        boolean z6;
        if (x509CertificateArr == null) {
            throw new CertPathValidatorException("certificates are not trusted!");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        if (certificates.isEmpty()) {
            return certPath;
        }
        List<X509Certificate> a2 = a(certificates);
        int size = a2.size();
        int i2 = size - 1;
        X509Certificate x509Certificate2 = (X509Certificate) certificates.get(i2);
        X509Certificate x509Certificate3 = null;
        if (x509CertificateArr.length == 0) {
            if (i2 == 0) {
                if (!x509Certificate2.getIssuerX500Principal().equals(x509Certificate2.getSubjectX500Principal())) {
                    e.debug("   trust all- single certificate {}", x509Certificate2.getSubjectX500Principal());
                    return certPath;
                }
                i2 = size;
            }
            str2 = "last";
            z4 = false;
            z3 = false;
        } else if (!z) {
            X509Certificate a3 = a(x509Certificate2, x509CertificateArr);
            if (a3 == null && b(x509Certificate2, x509CertificateArr)) {
                str = "last's subject";
                z2 = false;
            } else {
                str = "last's issuer";
                z2 = !x509Certificate2.equals(a3);
                x509Certificate2 = a3;
            }
            str2 = str;
            z3 = false;
            z4 = z2;
            i2 = size;
        } else {
            int i3 = 1;
            while (true) {
                if (i3 >= size) {
                    x509Certificate = null;
                    break;
                }
                x509Certificate = a2.get(i3);
                if (!b(x509Certificate, x509CertificateArr)) {
                    i3++;
                } else if (i2 > i3) {
                    i2 = i3;
                    z5 = true;
                }
            }
            z5 = false;
            if (x509Certificate == null) {
                X509Certificate a4 = a(x509Certificate2, x509CertificateArr);
                i = size;
                z6 = a4 != null ? !x509Certificate2.equals(a4) : z5;
                x509Certificate2 = a4;
            } else {
                x509Certificate2 = x509Certificate;
                i = i2;
                z6 = z5;
            }
            if (x509Certificate2 == null) {
                X509Certificate x509Certificate4 = a2.get(0);
                if (b(x509Certificate4, x509CertificateArr)) {
                    if (size > 1) {
                        x509Certificate2 = a2.get(1);
                        str2 = "node's issuer";
                        z3 = true;
                        z4 = z6;
                        i2 = 1;
                    } else {
                        e.debug("   trust node - single certificate {}", x509Certificate4.getSubjectX500Principal());
                        return certPath;
                    }
                }
            }
            str2 = "anchor";
            boolean z7 = z5;
            z4 = z6;
            i2 = i;
            z3 = z7;
        }
        CertPath a5 = a(a2, i2);
        HashSet hashSet = new HashSet();
        if (x509Certificate2 == null) {
            x509Certificate2 = x509CertificateArr[0];
        }
        hashSet.add(new TrustAnchor(x509Certificate2, null));
        Logger logger = e;
        if (logger.isDebugEnabled()) {
            List<X509Certificate> a6 = a(a5.getCertificates());
            logger.debug("verify: certificate path {} (orig. {})", Integer.valueOf(i2), Integer.valueOf(size));
            Iterator<X509Certificate> it = a6.iterator();
            while (it.hasNext()) {
                x509Certificate3 = it.next();
                e.debug("   cert : {}", x509Certificate3.getSubjectX500Principal());
            }
            if (x509Certificate3 != null) {
                e.debug("   sign : {}", x509Certificate3.getIssuerX500Principal());
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                e.debug("   trust: {}, {}", str2, ((TrustAnchor) it2.next()).getTrustedCert().getSubjectX500Principal());
            }
        }
        CertPathValidator certPathValidator = CertPathValidator.getInstance(CertPathValidator.getDefaultType());
        PKIXParameters pKIXParameters = new PKIXParameters(hashSet);
        pKIXParameters.setRevocationEnabled(false);
        certPathValidator.validate(a5, pKIXParameters);
        if (vbu.b()) {
            vbm.a(a2, x509Certificate2, i2);
        }
        if (!z3 && !z4) {
            return certPath;
        }
        if (!z4) {
            return a5;
        }
        if (!z3) {
            a2.add(x509Certificate2);
        }
        return a(a2, i2 + 1);
    }

    public static List<X509Certificate> a(List<? extends Certificate> list) {
        if (list == null) {
            throw new NullPointerException("Certificates list must not be null!");
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Certificate certificate : list) {
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Given certificate is not X.509!" + certificate.getClass());
            }
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }

    public static List<X500Principal> c(List<X509Certificate> list) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<X509Certificate> it = list.iterator();
            while (it.hasNext()) {
                X500Principal subjectX500Principal = it.next().getSubjectX500Principal();
                if (!arrayList.contains(subjectX500Principal)) {
                    arrayList.add(subjectX500Principal);
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public static String e(X509Certificate x509Certificate) {
        return vbm.b(x509Certificate.getSubjectX500Principal().getEncoded());
    }

    public static boolean e(X509Certificate x509Certificate, String str) {
        if (x509Certificate == null) {
            throw new NullPointerException("Certificate must not be null!");
        }
        if (str == null) {
            throw new NullPointerException("Destination must not be null!");
        }
        if (!vcb.b(str)) {
            throw new IllegalArgumentException("Destination " + str + " is no literal IP!");
        }
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames != null) {
                for (List<?> list : subjectAlternativeNames) {
                    if (((Integer) list.get(0)).intValue() == 7) {
                        String str2 = (String) list.get(1);
                        if (vcb.b(str2) && b(str2, str)) {
                            return true;
                        }
                    }
                }
            }
        } catch (ClassCastException | IllegalArgumentException | CertificateParsingException unused) {
        }
        return false;
    }

    public static boolean b(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Subject must not be null!");
        }
        if (str2 == null) {
            throw new NullPointerException("Destination must nit be null!");
        }
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        try {
            return InetAddress.getByName(str).equals(InetAddress.getByName(str2));
        } catch (SecurityException | UnknownHostException unused) {
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
    
        if (r4 == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.security.cert.X509Certificate r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "match"
            if (r8 == 0) goto L77
            if (r9 == 0) goto L6f
            r1 = 0
            java.util.Collection r2 = r8.getSubjectAlternativeNames()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            r3 = 1
            if (r2 == 0) goto L3d
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            r4 = r1
        L13:
            boolean r5 = r2.hasNext()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            if (r5 == 0) goto L3b
            java.lang.Object r5 = r2.next()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.lang.Object r6 = r5.get(r1)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            int r6 = r6.intValue()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            r7 = 2
            if (r6 != r7) goto L13
            java.lang.Object r4 = r5.get(r3)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            boolean r4 = r9.equalsIgnoreCase(r4)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            if (r4 == 0) goto L39
            return r3
        L39:
            r4 = r3
            goto L13
        L3b:
            if (r4 != 0) goto L6e
        L3d:
            java.lang.String r8 = e(r8)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            if (r8 == 0) goto L6e
            java.util.regex.Pattern r2 = defpackage.vbi.f17643a     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.lang.String r8 = r8.trim()     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.util.regex.Matcher r8 = r2.matcher(r8)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            java.lang.String r2 = " "
            java.lang.String r8 = r8.replaceAll(r2)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            boolean r8 = r9.equalsIgnoreCase(r8)     // Catch: java.lang.IllegalArgumentException -> L5a java.security.cert.CertificateParsingException -> L61 java.lang.ClassCastException -> L68
            if (r8 == 0) goto L6e
            return r3
        L5a:
            r8 = move-exception
            org.slf4j.Logger r9 = defpackage.vbi.e
            r9.debug(r0, r8)
            goto L6e
        L61:
            r8 = move-exception
            org.slf4j.Logger r9 = defpackage.vbi.e
            r9.debug(r0, r8)
            goto L6e
        L68:
            r8 = move-exception
            org.slf4j.Logger r9 = defpackage.vbi.e
            r9.debug(r0, r8)
        L6e:
            return r1
        L6f:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "Destination must not be null!"
            r8.<init>(r9)
            throw r8
        L77:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "Certificate must not be null!"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vbi.c(java.security.cert.X509Certificate, java.lang.String):boolean");
    }

    private static X509Certificate a(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr) {
        X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
        X509Certificate x509Certificate2 = null;
        for (X509Certificate x509Certificate3 : x509CertificateArr) {
            if (x509Certificate3 != null && issuerX500Principal.equals(x509Certificate3.getSubjectX500Principal())) {
                if (x509Certificate2 != null && b(x509Certificate, x509Certificate2)) {
                    return x509Certificate2;
                }
                x509Certificate2 = x509Certificate3;
            }
        }
        return x509Certificate2;
    }

    private static boolean b(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        try {
            x509Certificate2.checkValidity();
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    private static boolean b(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr) throws CertificateEncodingException {
        for (X509Certificate x509Certificate2 : x509CertificateArr) {
            if (x509Certificate.equals(x509Certificate2)) {
                return true;
            }
        }
        return false;
    }
}

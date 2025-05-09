package defpackage;

import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;

/* loaded from: classes7.dex */
public class twj {
    public static boolean a(Certificate[] certificateArr) throws twc {
        String str;
        if (certificateArr == null || certificateArr.length <= 0) {
            throw new twc(1022L, "isRootCertSupport params certificates is null!");
        }
        Certificate certificate = certificateArr[certificateArr.length - 1];
        if (certificate == null) {
            throw new twc(1022L, "params certificate is null!");
        }
        try {
            int i = 0;
            String[] split = twq.b(twe.a(certificate.getEncoded(), 0)).getSubjectDN().getName().split(",");
            int length = split.length;
            while (true) {
                if (i >= length) {
                    str = null;
                    break;
                }
                String str2 = split[i];
                if (str2.startsWith("CN=")) {
                    str = str2.substring(3);
                    break;
                }
                i++;
            }
            return "Android Keystore Software Attestation Root".equals(str);
        } catch (CertificateEncodingException e) {
            StringBuilder e2 = twf.e("get certificate param fail: ");
            e2.append(e.getMessage());
            throw new twc(1022L, e2.toString());
        }
    }
}

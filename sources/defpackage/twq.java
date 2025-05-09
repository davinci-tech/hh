package defpackage;

import android.content.Context;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes7.dex */
public class twq {
    public static volatile X509Certificate c;

    public static void d(Context context, txj txjVar) throws twc {
        int i;
        if (c == null) {
            synchronized (twq.class) {
                if (c == null) {
                    c = a(context, "cbg_root.cer");
                }
            }
        }
        String[] strArr = txjVar.e.d;
        if (strArr == null || strArr.length == 0) {
            throw new twc(1012L, "verify cert chain failed , certs is empty..");
        }
        int length = strArr.length;
        X509Certificate[] x509CertificateArr = new X509Certificate[length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            x509CertificateArr[i2] = b(strArr[i2]);
        }
        StringBuilder e = twf.e("Start verify cert chain using root ca: ");
        e.append(c.getSubjectDN().getName());
        twb.a("CertVerifier", e.toString(), new Object[0]);
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            try {
                twb.a("CertVerifier", "verify cert " + x509CertificateArr[i3].getSubjectDN().getName(), new Object[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("using ");
                int i4 = i3 + 1;
                sb.append(x509CertificateArr[i4].getSubjectDN().getName());
                twb.a("CertVerifier", sb.toString(), new Object[0]);
                x509CertificateArr[i3].checkValidity();
                x509CertificateArr[i3].verify(x509CertificateArr[i4].getPublicKey());
                i3 = i4;
            } catch (RuntimeException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException e2) {
                StringBuilder e3 = twf.e("verify cert chain failed , exception ");
                e3.append(e2.getMessage());
                String sb2 = e3.toString();
                throw two.e("CertVerifier", sb2, new Object[0], 1012L, sb2);
            }
            StringBuilder e32 = twf.e("verify cert chain failed , exception ");
            e32.append(e2.getMessage());
            String sb22 = e32.toString();
            throw two.e("CertVerifier", sb22, new Object[0], 1012L, sb22);
        }
        x509CertificateArr[i].verify(c.getPublicKey());
        for (String str : x509CertificateArr[0].getSubjectDN().getName().split(",")) {
            if (str.startsWith("OU=") && "Huawei CBG Cloud Security Signer".equals(str.substring(3))) {
                X509Certificate x509Certificate = x509CertificateArr[0];
                try {
                    Signature signature = Signature.getInstance(CommonPickerConstant.IdTokenSupportAlg.RS_256.equals(txjVar.e.e) ? "SHA256WithRSA" : "SHA256WithRSA/PSS");
                    signature.initVerify(x509Certificate.getPublicKey());
                    signature.update(txjVar.c.getBytes(StandardCharsets.UTF_8));
                    if (signature.verify(txjVar.b)) {
                        return;
                    } else {
                        throw new twc(1012L, "signature not verify");
                    }
                } catch (RuntimeException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e4) {
                    StringBuilder e5 = twf.e("verify signature of c1 failed, exception ");
                    e5.append(e4.getMessage());
                    String sb3 = e5.toString();
                    throw two.e("CertVerifier", sb3, new Object[0], 1012L, sb3);
                }
            }
        }
        throw new twc(1012L, "Subject OU not verify");
    }

    public static X509Certificate b(String str) throws twc {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(twe.c(str, 0));
            try {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
                byteArrayInputStream.close();
                return x509Certificate;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | CertificateException e) {
            throw new twc(1012L, e.getMessage());
        }
    }

    public static X509Certificate a(Context context, String str) throws twc {
        try {
            InputStream open = context.getAssets().open(str);
            try {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(open);
                if (open != null) {
                    open.close();
                }
                return x509Certificate;
            } finally {
            }
        } catch (IOException | CertificateException e) {
            StringBuilder e2 = twf.e("Read root cert error ");
            e2.append(e.getMessage());
            String sb = e2.toString();
            throw two.e("CertVerifier", sb, new Object[0], 1012L, sb);
        }
    }
}

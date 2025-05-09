package defpackage;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class ahg {
    private static List<String> b(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 1) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (JSONException unused) {
            ahi.e.c("X509CertUtil", "Failed to getCertChain: ");
            return Collections.emptyList();
        }
    }

    public static boolean c(X509Certificate x509Certificate, String str) {
        return d(x509Certificate, "OU", str);
    }

    private static List<X509Certificate> e(List<String> list) {
        if (list == null) {
            ahi.e.d("X509CertUtil", "base64 CertChain is null.");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            X509Certificate e = e(it.next());
            if (e == null) {
                ahi.e.c("X509CertUtil", "Failed to get cert from CertChain");
            } else {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static List<X509Certificate> a(String str) {
        return e(b(str));
    }

    private static boolean a(List<X509Certificate> list) {
        for (int i = 1; i < list.size(); i++) {
            if (!b(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(X509Certificate x509Certificate, byte[] bArr, byte[] bArr2) {
        if (x509Certificate == null || bArr == null || bArr2 == null || bArr2.length == 0) {
            ahi.e.d("X509CertUtil", "checkSignature parameter is null");
            return false;
        }
        try {
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            signature.initVerify(x509Certificate.getPublicKey());
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            ahi.e.d("X509CertUtil", "failed checkSignature,Exception:", e);
            return false;
        }
    }

    public static boolean b(X509Certificate x509Certificate, List<X509Certificate> list) {
        if (list != null && list.size() != 0) {
            if (x509Certificate == null) {
                ahi.e.c("X509CertUtil", "rootCert is null,verify failed ");
                return false;
            }
            try {
                x509Certificate.checkValidity();
                PublicKey publicKey = x509Certificate.getPublicKey();
                for (int size = list.size() - 1; size >= 0; size--) {
                    X509Certificate x509Certificate2 = list.get(size);
                    if (x509Certificate2 != null) {
                        try {
                            x509Certificate2.verify(publicKey);
                            x509Certificate2.checkValidity();
                            publicKey = x509Certificate2.getPublicKey();
                        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException e) {
                            ahi.e.c("X509CertUtil", "verify failed " + e.getMessage());
                        }
                    }
                    return false;
                }
                return a(list);
            } catch (CertificateExpiredException | CertificateNotYetValidException e2) {
                ahi.e.c("X509CertUtil", "verifyCertChain Exception:" + e2.getMessage());
            }
        }
        return false;
    }

    private static boolean d(X509Certificate x509Certificate, String str, String str2) {
        if (x509Certificate == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(b(x509Certificate.getSubjectDN().getName(), str));
    }

    public static boolean e(X509Certificate x509Certificate, String str) {
        return d(x509Certificate, "CN", str);
    }

    private static boolean b(X509Certificate x509Certificate) {
        if (x509Certificate == null || x509Certificate.getBasicConstraints() == -1) {
            return false;
        }
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (keyUsage.length <= 5) {
            return false;
        }
        return keyUsage[5];
    }

    private static X509Certificate b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            if (generateCertificate instanceof X509Certificate) {
                return (X509Certificate) generateCertificate;
            }
            return null;
        } catch (CertificateException e) {
            ahi.e.c("X509CertUtil", "Failed to get cert: " + e.getMessage());
            return null;
        }
    }

    private static X509Certificate e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return b(ahd.a(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.security.cert.X509Certificate e(android.content.Context r4) {
        /*
            java.lang.String r0 = "X509CertUtil"
            android.content.pm.PackageManager r1 = r4.getPackageManager()
            r2 = 0
            java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Exception -> L12 android.content.pm.PackageManager.NameNotFoundException -> L18
            r3 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r4 = r1.getApplicationInfo(r4, r3)     // Catch: java.lang.Exception -> L12 android.content.pm.PackageManager.NameNotFoundException -> L18
            goto L21
        L12:
            r4 = move-exception
            ahi r1 = defpackage.ahi.e
            java.lang.String r3 = "PackageInfo with Exception:"
            goto L1d
        L18:
            r4 = move-exception
            ahi r1 = defpackage.ahi.e
            java.lang.String r3 = "PackageInfo with NameNotFoundException:"
        L1d:
            r1.d(r0, r3, r4)
            r4 = r2
        L21:
            if (r4 == 0) goto L42
            android.os.Bundle r4 = r4.metaData
            if (r4 != 0) goto L2c
            ahi r4 = defpackage.ahi.e
            java.lang.String r1 = "failed getCBGRootCA metaData is null"
            goto L46
        L2c:
            java.lang.String r1 = "componentverify_ag_cbg_root"
            java.lang.String r4 = r4.getString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L3d
            ahi r4 = defpackage.ahi.e
            java.lang.String r1 = "failed getCBGRootCA sdkCbgRoot is null"
            goto L46
        L3d:
            java.security.cert.X509Certificate r4 = e(r4)
            return r4
        L42:
            ahi r4 = defpackage.ahi.e
            java.lang.String r1 = "failed getCBGRootCA packageInfo is null"
        L46:
            r4.c(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ahg.e(android.content.Context):java.security.cert.X509Certificate");
    }

    private static String b(String str, String str2) {
        int indexOf = str.toUpperCase(Locale.getDefault()).indexOf(str2 + "=");
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(",", indexOf);
        int length = indexOf + str2.length() + 1;
        return indexOf2 != -1 ? str.substring(length, indexOf2) : str.substring(length);
    }
}

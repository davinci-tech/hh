package com.huawei.hms.device;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.support.log.common.Base64;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class a {
    public static boolean a(X509Certificate x509Certificate, List<X509Certificate> list) {
        if (list == null || list.size() == 0) {
            HMSLog.e("X509CertUtil", "certChain is null,verify failed ");
            return false;
        }
        if (x509Certificate == null) {
            HMSLog.e("X509CertUtil", "rootCert is null,verify failed ");
            return false;
        }
        PublicKey publicKey = x509Certificate.getPublicKey();
        for (X509Certificate x509Certificate2 : list) {
            if (x509Certificate2 != null) {
                try {
                    x509Certificate2.checkValidity();
                    x509Certificate2.verify(publicKey);
                    publicKey = x509Certificate2.getPublicKey();
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException e) {
                    HMSLog.e("X509CertUtil", "verify failed " + e.getMessage());
                }
            }
            return false;
        }
        return a(list);
    }

    public static List<X509Certificate> b(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            X509Certificate a2 = a(it.next());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static List<String> c(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 1) {
                return Collections.emptyList();
            }
            int length = jSONArray.length();
            if (jSONArray.length() > 3) {
                length = 3;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (JSONException e) {
            HMSLog.e("X509CertUtil", "Failed to getCertChain: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<X509Certificate> b(String str) {
        return b(c(str));
    }

    public static boolean b(X509Certificate x509Certificate, String str, String str2) {
        if (x509Certificate == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(a(x509Certificate.getSubjectDN().getName(), str));
    }

    public static boolean b(X509Certificate x509Certificate, String str) {
        return b(x509Certificate, "OU", str);
    }

    public static X509Certificate a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return a(Base64.decode(str));
        } catch (IllegalArgumentException e) {
            HMSLog.e("X509CertUtil", "getCert failed : " + e.getMessage());
            return null;
        }
    }

    public static X509Certificate a(byte[] bArr) {
        Certificate generateCertificate;
        if (bArr == null) {
            HMSLog.e("X509CertUtil", "Failed to get cert, params is invalid.");
            return null;
        }
        try {
            generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            HMSLog.e("X509CertUtil", "Failed to get cert: " + e.getMessage());
        }
        if (generateCertificate instanceof X509Certificate) {
            return (X509Certificate) generateCertificate;
        }
        HMSLog.e("X509CertUtil", "Failed to get cert, certificate is invalid.");
        return null;
    }

    private static String a(String str, String str2) {
        int indexOf = str.toUpperCase(Locale.getDefault()).indexOf(str2 + "=");
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(",", indexOf);
        if (indexOf2 != -1) {
            return str.substring(indexOf + str2.length() + 1, indexOf2);
        }
        return str.substring(indexOf + str2.length() + 1);
    }

    public static boolean a(X509Certificate x509Certificate) {
        if (x509Certificate == null || x509Certificate.getBasicConstraints() == -1) {
            return false;
        }
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (5 < keyUsage.length) {
            return keyUsage[5];
        }
        return false;
    }

    public static boolean a(List<X509Certificate> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (!a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(X509Certificate x509Certificate, String str) {
        return b(x509Certificate, "CN", str);
    }

    public static boolean a(X509Certificate x509Certificate, String str, String str2) {
        try {
            return a(x509Certificate, str.getBytes("UTF-8"), Base64.decode(str2));
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            HMSLog.e("X509CertUtil", " plainText exception: " + e.getMessage());
            return false;
        }
    }

    public static boolean a(X509Certificate x509Certificate, byte[] bArr, byte[] bArr2) {
        try {
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            signature.initVerify(x509Certificate.getPublicKey());
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            HMSLog.e("X509CertUtil", "failed checkSignature : " + e.getMessage());
            return false;
        }
    }

    public static X509Certificate a(Context context) {
        byte[] readAssetsFile = IOUtils.readAssetsFile(context.getAssets(), "base_hms_app_root.cer");
        if (readAssetsFile != null && readAssetsFile.length != 0) {
            return a(readAssetsFile);
        }
        HMSLog.e("X509CertUtil", "Get root cert is invalid.");
        HMSPackageManager.getInstance(context).setUseOldCertificate(true);
        return null;
    }
}

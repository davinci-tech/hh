package com.huawei.hwauthutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixn;
import defpackage.ixo;
import defpackage.ixq;
import defpackage.ixv;
import health.compact.a.IoUtils;
import health.compact.a.Sha256;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: classes.dex */
public final class HsfSignValidator {
    private HsfSignValidator() {
    }

    public static boolean e(String str) {
        ixo.a(str, "packageName must not be empty.");
        String d = d(str);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("HsfSignValidator", "kitVerify", "kitSign is null");
            return false;
        }
        String e = e(BaseApplication.e(), str);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("HsfSignValidator", "kitVerify", "fingerprint is null");
            return false;
        }
        PublicKey b = b();
        if (b == null) {
            LogUtil.h("HsfSignValidator", "kitVerify", "hsfPublicKey is null");
            return false;
        }
        try {
            Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initVerify(b);
            signature.update(str.getBytes(Charset.defaultCharset()));
            signature.update(e.getBytes(Charset.defaultCharset()));
            return signature.verify(Base64.decode(d, 0));
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
            LogUtil.b("HsfSignValidator", "kitVerify", "Failed to verify application Kit-signature. ", e2.getMessage());
            LogUtil.h("HsfSignValidator", "kitVerify", "Failed to verify application Kit-signature.");
            return false;
        }
    }

    private static String d(String str) {
        try {
            ApplicationInfo applicationInfo = BaseApplication.e().getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                LogUtil.a("HsfSignValidator", "getKitPackageSignature", "appInfo className ", applicationInfo.className, " processName ", applicationInfo.processName, " appInfo.metaData ", applicationInfo.metaData, " packageHsfSignature ", applicationInfo.metaData.getString("com.huawei.hihealthkit.signature"));
                return applicationInfo.metaData.getString("com.huawei.hihealthkit.signature");
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("HsfSignValidator", "getKitPackageSignature", "Failed to get the application HSF signature. ", e.getMessage());
        }
        return null;
    }

    public static boolean c(String str) {
        PublicKey b;
        ixo.a(str, "packageName must not be empty.");
        String a2 = a(str);
        if (TextUtils.isEmpty(a2) || ixn.c(a2)) {
            return false;
        }
        String e = e(BaseApplication.e(), str);
        if (TextUtils.isEmpty(e) || (b = b()) == null) {
            return false;
        }
        try {
            Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initVerify(b);
            signature.update(str.getBytes(Charset.defaultCharset()));
            signature.update(e.getBytes(Charset.defaultCharset()));
            return signature.verify(Base64.decode(a2, 0));
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
            LogUtil.b("HsfSignValidator", "verify", "Failed to verify application HSF-signature. ", e2.getMessage());
            LogUtil.h("HsfSignValidator", "verify", "Failed to verify application HSF-signature.");
            return false;
        }
    }

    private static PublicKey b() {
        return new ixv().a("publickey.txt");
    }

    private static String a(String str) {
        try {
            ApplicationInfo applicationInfo = BaseApplication.e().getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                LogUtil.a("HsfSignValidator", "getPackageHsfSignature", "appInfo className ", applicationInfo.className, " processName ", applicationInfo.processName, " appInfo.metaData ", applicationInfo.metaData, " packageHsfSignature ", applicationInfo.metaData.getString("health-signature"));
                return applicationInfo.metaData.getString("health-signature");
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("HsfSignValidator", "getPackageHsfSignature", "Failed to get the application HSF signature. ", e.getMessage());
        }
        return null;
    }

    public static String e(Context context, String str) {
        Certificate b = b(str);
        if (b != null) {
            try {
                LogUtil.c("HsfSignValidator", "getPackageFingerprint", "cert.getEncoded() ", ixq.c(Sha256.e(b.getEncoded()), true));
                return ixq.c(Sha256.e(b.getEncoded()), true);
            } catch (CertificateEncodingException unused) {
                LogUtil.b("HsfSignValidator", "getPackageFingerprint", "Failed to get application signature certificate fingerprint.");
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v2 */
    private static Certificate b(String str) {
        Exception e;
        InputStream inputStream;
        Certificate certificate;
        ?? r5 = 0;
        InputStream inputStream2 = null;
        Certificate certificate2 = null;
        try {
            try {
                PackageInfo packageInfo = BaseApplication.e().getPackageManager().getPackageInfo(str, 64);
                if (packageInfo == null || packageInfo.signatures.length <= 0) {
                    certificate = null;
                } else {
                    inputStream = IoUtils.e(packageInfo.signatures[0].toByteArray());
                    try {
                        certificate = CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
                        inputStream2 = inputStream;
                    } catch (PackageManager.NameNotFoundException e2) {
                        e = e2;
                        LogUtil.b("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate. ", e.getMessage());
                        IoUtils.e(inputStream);
                        LogUtil.h("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate.");
                        return certificate2;
                    } catch (CertificateException e3) {
                        e = e3;
                        LogUtil.b("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate. ", e.getMessage());
                        IoUtils.e(inputStream);
                        LogUtil.h("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate.");
                        return certificate2;
                    }
                }
                IoUtils.e(inputStream2);
                certificate2 = certificate;
            } catch (Throwable th) {
                r5 = str;
                th = th;
                IoUtils.e((Closeable) r5);
                throw th;
            }
        } catch (PackageManager.NameNotFoundException e4) {
            e = e4;
            e = e;
            inputStream = null;
            LogUtil.b("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate. ", e.getMessage());
            IoUtils.e(inputStream);
            LogUtil.h("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate.");
            return certificate2;
        } catch (CertificateException e5) {
            e = e5;
            e = e;
            inputStream = null;
            LogUtil.b("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate. ", e.getMessage());
            IoUtils.e(inputStream);
            LogUtil.h("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate.");
            return certificate2;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e((Closeable) r5);
            throw th;
        }
        LogUtil.h("HsfSignValidator", "getPackageCertificate", "Failed to get application signature certificate.");
        return certificate2;
    }
}

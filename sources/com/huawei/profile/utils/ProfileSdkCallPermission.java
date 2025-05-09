package com.huawei.profile.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public final class ProfileSdkCallPermission {
    private static final int LEN_BYTE_TO_HEX = 2;
    private static final String PROFILE_PACKAGE_NAME = "com.huawei.profile";
    private static final String TAG = "ProfileSdkCallPermission";
    private static final List<String> TRUST_CERT_LIST = new ArrayList<String>() { // from class: com.huawei.profile.utils.ProfileSdkCallPermission.1
        private static final long serialVersionUID = -6226401099808150780L;

        {
            add("1E:3E:EE:2A:88:A6:DF:75:FB:4A:F5:6A:DC:83:73:BB:81:8F:3C:B9:0A:49:35:C7:82:15:82:B8:CE:BB:69:4C");
            add("35:17:26:22:15:D8:D3:00:8C:BF:88:87:50:B6:41:8E:DC:4D:56:2A:C3:3E:D6:87:4E:0D:73:AB:A6:67:BC:3C");
            add("60:3A:C6:A5:7E:20:23:E0:0C:9C:93:BB:53:9C:A6:53:DF:30:03:EB:A4:E9:2E:A1:90:4B:A4:AA:A5:D9:38:F0");
        }
    };

    private ProfileSdkCallPermission() {
    }

    public static boolean checkAppTrust(List<String> list, Context context) {
        if (context == null) {
            Log.e(TAG, "checkAppTrust failed, reason is context is null");
            return false;
        }
        if (!checkIfExistProfilePkgName(list)) {
            Log.e(TAG, "checkAppTrust failed, reason is that profile is not running");
            return false;
        }
        if (checkSignMatch(context)) {
            return true;
        }
        String certificateSha256Fingerprint = getCertificateSha256Fingerprint(context, "com.huawei.profile");
        if (TextUtils.isEmpty(certificateSha256Fingerprint)) {
            Log.e(TAG, "checkAppTrust failed, reason is Sha256Fingerprint not exist");
            return false;
        }
        return TRUST_CERT_LIST.contains(certificateSha256Fingerprint);
    }

    private static boolean checkIfExistProfilePkgName(List<String> list) {
        return list != null && list.contains("com.huawei.profile");
    }

    private static String getCertificateSha256Fingerprint(Context context, String str) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getRawSignature: not the super application");
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, AMapEngineUtils.HALF_MAX_P20_WIDTH);
                if (packageInfo == null) {
                    Log.e(TAG, "getRawSignature: get package info failed.");
                    return null;
                }
                Signature[] apkContentsSigners = packageInfo.signingInfo.getApkContentsSigners();
                if (apkContentsSigners != null && apkContentsSigners.length > 0) {
                    return getSha256FingerData(apkContentsSigners[0].toByteArray());
                }
                return null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "getRawSignature: package name is not found.");
        }
        return null;
    }

    private static String getSha256FingerData(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                Certificate generateCertificate = CertificateFactory.getInstance("X509").generateCertificate(byteArrayInputStream);
                if (generateCertificate instanceof X509Certificate) {
                    String byte2HexFormatted = byte2HexFormatted(MessageDigest.getInstance("SHA256").digest(((X509Certificate) generateCertificate).getEncoded()));
                    byteArrayInputStream.close();
                    return byte2HexFormatted;
                }
                byteArrayInputStream.close();
                return null;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            Log.e(TAG, "getSha256FingerData: IOException");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            Log.e(TAG, "getSha256FingerData: NoSuchAlgorithmException");
            return null;
        } catch (CertificateException unused3) {
            Log.e(TAG, "getSha256FingerData: CertificateException");
            return null;
        }
    }

    private static String byte2HexFormatted(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            } else if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase(Locale.ENGLISH));
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    private static boolean checkSignMatch(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        if (packageManager.checkSignatures(Binder.getCallingUid(), Process.myUid()) == 0) {
            Log.i(TAG, "the application has platform signature");
            return true;
        }
        Log.e(TAG, "the application not has platform signature");
        return false;
    }
}

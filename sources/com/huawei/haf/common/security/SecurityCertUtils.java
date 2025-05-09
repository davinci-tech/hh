package com.huawei.haf.common.security;

import android.content.Context;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;

/* loaded from: classes.dex */
public class SecurityCertUtils {
    private SecurityCertUtils() {
    }

    public static boolean e() {
        LogUtil.c("SecurityCertUtils", "initProvider() init");
        if ((!EmuiBuild.d && !HarmonyBuild.d) || 21 > EmuiBuild.f13113a) {
            LogUtil.d("SecurityCertUtils", "initKeyStoreProvider(), not support huks");
            return false;
        }
        try {
            ReflectionUtils.b(ReflectionUtils.d("com.huawei.security.keystore.HwUniversalKeyStoreProvider"), JsbMapKeyNames.H5_TEXT_DOWNLOAD_INSTALL, new Class[0]).invoke(null, new Object[0]);
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("SecurityCertUtils", "initKeyStoreProvider() exception = ", LogUtil.a(e));
            return false;
        }
    }

    public static X509Certificate[] c(Context context, int[] iArr, byte[] bArr) {
        Throwable e;
        Object invoke;
        X509Certificate[] x509CertificateArr = null;
        if ((!EmuiBuild.d && !HarmonyBuild.d) || 21 > EmuiBuild.f13113a) {
            LogUtil.d("SecurityCertUtils", "getCertificateChains(),  not support huks");
            return null;
        }
        try {
            Method b = ReflectionUtils.b(ReflectionUtils.d("com.huawei.security.keystore.HwAttestationUtils"), "attestDeviceIds", Context.class, int[].class, byte[].class);
            if (b == null || (invoke = b.invoke(null, context, iArr, bArr)) == null || !(invoke instanceof X509Certificate[])) {
                return null;
            }
            X509Certificate[] x509CertificateArr2 = (X509Certificate[]) invoke;
            try {
                LogUtil.d("SecurityCertUtils", "certs is", Integer.valueOf(x509CertificateArr2.length));
                return x509CertificateArr2;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                e = e2;
                x509CertificateArr = x509CertificateArr2;
                LogUtil.e("SecurityCertUtils", "getCertificateChain() exception = ", LogUtil.a(e));
                return x509CertificateArr;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e3) {
            e = e3;
        }
    }
}

package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.BksUtil;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import com.huawei.secure.android.common.ssl.util.d;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

/* loaded from: classes6.dex */
public class SSFSecureX509SingleInstance {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8616a = "SSFSecureX509SingleInstance";
    private static volatile SecureX509TrustManager b;

    private SSFSecureX509SingleInstance() {
    }

    public static SecureX509TrustManager getInstance(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        ContextUtil.setContext(context);
        if (b == null) {
            synchronized (SSFSecureX509SingleInstance.class) {
                if (b == null) {
                    InputStream filesBksIS = BksUtil.getFilesBksIS(context);
                    if (filesBksIS == null) {
                        d.c(f8616a, "get assets bks");
                        filesBksIS = context.getAssets().open("hmsrootcas.bks");
                    } else {
                        d.c(f8616a, "get files bks");
                    }
                    b = new SecureX509TrustManager(filesBksIS, "", true);
                }
            }
        }
        return b;
    }

    @Deprecated
    public static void updateBks(InputStream inputStream) {
        String str = f8616a;
        d.c(str, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && b != null) {
            b = new SecureX509TrustManager(inputStream, "", true);
            d.a(str, "updateBks: new SecureX509TrustManager cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            SSFCompatiableSystemCA.a(b);
            SASFCompatiableSystemCA.a(b);
        }
        d.a(str, "update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    public static void updateBks(InputStream inputStream, SecureRandom secureRandom) {
        String str = f8616a;
        d.c(str, "update bks");
        long currentTimeMillis = System.currentTimeMillis();
        if (inputStream != null && b != null) {
            b = new SecureX509TrustManager(inputStream, "", true);
            d.a(str, "updateBks: new SecureX509TrustManager cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            SSFCompatiableSystemCA.a(b, secureRandom);
            SASFCompatiableSystemCA.a(b, secureRandom);
        }
        d.a(str, "update bks cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }
}

package com.huawei.hms.framework.network.grs.h.g;

import android.content.Context;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final HostnameVerifier f4556a = new StrictHostnameVerifier();

    public static SSLSocketFactory a(Context context) {
        try {
            return new SecureSSLSocketFactoryNew(new SecureX509TrustManager(context.getAssets().open(GrsApp.getInstance().getBrand("/") + "grs_sp.bks"), ""), EncryptUtil.genSecureRandom());
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static HostnameVerifier a() {
        return f4556a;
    }
}

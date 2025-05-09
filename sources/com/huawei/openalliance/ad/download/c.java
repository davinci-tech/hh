package com.huawei.openalliance.ad.download;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.ho;
import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes5.dex */
public abstract class c implements Closeable {
    public abstract InputStream a();

    public abstract String a(String str);

    public abstract int b();

    public abstract int c();

    public abstract HttpConnection d();

    public static c a(Context context, String str, long j) {
        try {
            ho.b("DownloadNetworkConnection", "create OkHttpNetworkConnection");
            return new j(context, str, j);
        } catch (Throwable unused) {
            ho.b("DownloadNetworkConnection", "create HttpUrlNetworkConnection");
            return new i(str, j);
        }
    }
}

package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.util.Log;
import com.huawei.openalliance.ad.ld;
import com.huawei.openalliance.ad.le;
import com.huawei.openalliance.ad.net.http.HttpsConfig;
import java.io.IOException;

/* loaded from: classes5.dex */
public class cr {
    public static void a(Context context) {
        String str;
        try {
            Context applicationContext = context.getApplicationContext();
            HttpsConfig.a(ld.a(applicationContext));
            HttpsConfig.a(new le(applicationContext));
        } catch (IOException unused) {
            str = "SecureSSLSocketFactory create fail io";
            Log.w("SecurityHttpsConfig", str);
        } catch (Exception unused2) {
            str = "SecureSSLSocketFactory create fail";
            Log.w("SecurityHttpsConfig", str);
        }
    }
}

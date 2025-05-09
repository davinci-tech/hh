package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ay extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(final Context context, final String str, final RemoteCallResultCallback<String> remoteCallResultCallback) {
        final com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        final int[] iArr = {-1};
        boolean optBoolean = new JSONObject(str).optBoolean(JsbMapKeyNames.ALLOWED_NON_WIFI_NETWORK, false);
        if (d == null) {
            a(remoteCallResultCallback, this.f7108a, 1000, Integer.valueOf(iArr[0]), true);
            return;
        }
        if (d.getAppInfo() != null) {
            d.getAppInfo().setAllowedNonWifiNetwork(optBoolean);
        }
        com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ay.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    iArr[0] = ay.this.e(context, str).b(context, d);
                } catch (Throwable unused) {
                }
                j.a(remoteCallResultCallback, ay.this.f7108a, 1000, Integer.valueOf(iArr[0]), true);
            }
        });
    }

    public ay() {
        super("pps.download.resume");
    }
}

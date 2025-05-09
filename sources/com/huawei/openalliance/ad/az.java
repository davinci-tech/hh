package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class az extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(final Context context, final String str, final RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        int i;
        ContentRecord b = b(context, str);
        if (b != null) {
            boolean optBoolean = new JSONObject(str).optBoolean(JsbMapKeyNames.ALLOWED_NON_WIFI_NETWORK, false);
            if (!a(context, b)) {
                str2 = this.f7108a;
                i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
                a(remoteCallResultCallback, str2, i, null, true);
            } else {
                final int[] iArr = {-1};
                final com.huawei.openalliance.ad.inter.data.i a2 = a(b);
                if (a2 != null) {
                    if (a2.getAppInfo() != null) {
                        a2.getAppInfo().setAllowedNonWifiNetwork(optBoolean);
                    }
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.az.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                iArr[0] = az.this.e(context, str).a(context, a2);
                            } catch (Throwable unused) {
                            }
                            j.a(remoteCallResultCallback, az.this.f7108a, 1000, Integer.valueOf(iArr[0]), true);
                        }
                    });
                    return;
                }
            }
        }
        str2 = this.f7108a;
        i = 3002;
        a(remoteCallResultCallback, str2, i, null, true);
    }

    public az() {
        super("pps.download.start");
    }
}

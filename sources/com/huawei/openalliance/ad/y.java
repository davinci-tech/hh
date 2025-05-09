package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.hms.ads.jsb.inner.data.DeviceInfo;
import com.huawei.openalliance.ad.R;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.Map;

/* loaded from: classes5.dex */
public class y extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.a(bz.a(context).d());
        deviceInfo.a(com.huawei.openalliance.ad.utils.d.a());
        deviceInfo.b(b(context));
        a(remoteCallResultCallback, this.f7108a, 1000, com.huawei.openalliance.ad.utils.be.a(deviceInfo), true);
    }

    private int c(String str) {
        try {
            return R.string.class.getField(str).getInt(null);
        } catch (Throwable th) {
            ho.c("JsbReqSettings", "getResourceId err, " + th.getClass().getSimpleName());
            return 0;
        }
    }

    private String b(Context context) {
        Resources resources = context.getResources();
        Map<String, String> a2 = com.huawei.openalliance.ad.utils.be.a(resources.getString(com.huawei.health.R.string._2130851102_res_0x7f02351e));
        if (a2 == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : a2.entrySet()) {
            int c = c(entry.getValue());
            if (c > 0) {
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(resources.getString(c));
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public y() {
        super("pps.settings");
    }
}

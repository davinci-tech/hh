package com.huawei.maps.offlinedata.service.network;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;

/* loaded from: classes5.dex */
public class a extends SafeBroadcastReceiver {
    @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
    public void onReceiveMsg(Context context, Intent intent) {
        int networkType = NetworkUtil.getNetworkType(context);
        g.a("NetTypeReceiver", "NetTypeReceiver onReceiveMsg: " + networkType);
        com.huawei.maps.offlinedata.jsbridge.b.a().b("configHolder.network", String.valueOf(networkType));
        if (networkType == 1) {
            com.huawei.maps.offlinedata.service.download.b.a().b().set(false);
        }
        if (com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        if (networkType == 1) {
            com.huawei.maps.offlinedata.service.a.a().d();
        } else {
            com.huawei.maps.offlinedata.service.a.a().e();
        }
    }
}

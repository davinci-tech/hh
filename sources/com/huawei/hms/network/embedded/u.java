package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.inner.api.NetworkReceiver;
import com.huawei.operation.utils.Constants;

/* loaded from: classes9.dex */
public class u implements NetworkReceiver {
    public static final String b = "DNSNetworkReceiver";

    /* renamed from: a, reason: collision with root package name */
    public NetworkInfo f5508a;

    @Override // com.huawei.hms.network.inner.api.NetworkReceiver
    public void onReceiveMsg(Context context, Intent intent) {
        NetworkInfo networkInfo = NetworkUtil.getNetworkInfo(context);
        if (networkInfo == null) {
            Logger.v(b, "Get NetworkInfo failed");
            return;
        }
        Logger.v(b, "networkInfo: %s", networkInfo);
        if (a(this.f5508a, networkInfo)) {
            a0.a(networkInfo);
            this.f5508a = networkInfo;
        }
    }

    private boolean a(NetworkInfo networkInfo, NetworkInfo networkInfo2) {
        return NetworkUtil.isChangeToConnected(networkInfo, networkInfo2) || NetworkUtil.isConnectTypeChange(networkInfo, networkInfo2);
    }

    public u() {
        NetworkInfo networkInfo = NetworkUtil.getNetworkInfo(ContextHolder.getAppContext());
        this.f5508a = networkInfo;
        Object[] objArr = new Object[1];
        objArr[0] = networkInfo == null ? Constants.NULL : networkInfo.toString();
        Logger.v(b, "lastActivityNetInfo: %s", objArr);
    }
}

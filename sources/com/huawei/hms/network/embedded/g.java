package com.huawei.hms.network.embedded;

import android.content.Context;
import com.huawei.hms.network.ComposedNetworkKit;
import com.huawei.hms.network.NetworkKit;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class g extends NetworkKit {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5254a = "NetworkKitImpl";
    public static final NetworkKit b = new g();

    @Override // com.huawei.hms.network.NetworkKit
    public void setOptions(String str) {
        ComposedNetworkKit.getInstance().setOptions(str);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void initKit(Context context, String str) {
        ComposedNetworkKit.getInstance().initKit(context, str);
    }

    public void initKit(Context context) {
        ComposedNetworkKit.getInstance().initKit(context, "");
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void initConnectionPool(int i, long j, TimeUnit timeUnit) {
        ComposedNetworkKit.getInstance().initConnectionPool(i, j, timeUnit);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public String getOption(String str) {
        return ComposedNetworkKit.getInstance().getOption("", str);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void evictAllConnections() {
        ComposedNetworkKit.getInstance().evictAllConnections();
    }

    public boolean checkConnectivity() {
        return ComposedNetworkKit.getInstance().checkConnectivity();
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void addQuicHint(boolean z, String... strArr) {
        ComposedNetworkKit.getInstance().addQuicHint(z, strArr);
    }

    public static NetworkKit getInstance() {
        return b;
    }
}

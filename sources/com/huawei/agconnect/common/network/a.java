package com.huawei.agconnect.common.network;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.huawei.agconnect.common.api.HaConnector;
import com.huawei.agconnect.common.appinfo.SafeAppInfo;
import com.huawei.agconnect.common.network.AccessNetworkManager;
import com.huawei.agconnect.credential.obs.al;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class a extends AccessNetworkManager {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1699a = "com.huawei.agconnect.AccessNetwork";
    private final List<AccessNetworkManager.AccessNetworkCallback> b = new CopyOnWriteArrayList();
    private Context c = al.a().b();

    @Override // com.huawei.agconnect.common.network.AccessNetworkManager
    public void setAccessNetwork(boolean z) {
        a(z);
        HaConnector.getInstance().setAnalyticsEnabled(z);
        if (z) {
            a();
        }
    }

    @Override // com.huawei.agconnect.common.network.AccessNetworkManager
    public boolean canAccessNetwork() {
        ApplicationInfo safeGetApplicationInfo = SafeAppInfo.safeGetApplicationInfo(this.c.getPackageManager(), this.c.getPackageName(), 128);
        return safeGetApplicationInfo.metaData == null || !"false".equalsIgnoreCase(String.valueOf(safeGetApplicationInfo.metaData.get(f1699a))) || b.a().b();
    }

    @Override // com.huawei.agconnect.common.network.AccessNetworkManager
    public void addCallback(AccessNetworkManager.AccessNetworkCallback accessNetworkCallback) {
        if (accessNetworkCallback != null) {
            this.b.add(accessNetworkCallback);
            a(accessNetworkCallback);
        }
    }

    private void a(boolean z) {
        b.a().a(z);
    }

    private void a(AccessNetworkManager.AccessNetworkCallback accessNetworkCallback) {
        if (canAccessNetwork()) {
            accessNetworkCallback.onNetWorkReady();
        }
    }

    private void a() {
        Iterator<AccessNetworkManager.AccessNetworkCallback> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onNetWorkReady();
        }
    }
}

package com.huawei.hmf.orb.aidl;

import android.content.Intent;
import android.os.IBinder;
import com.huawei.hmf.orb.aidl.communicate.AIDLInvoke;
import java.util.Set;

@Deprecated
/* loaded from: classes9.dex */
public abstract class RemoteModuleService extends RemoteDiscoveryService {
    private IBinder aidlInvoke = new AIDLInvoke();

    @Override // com.huawei.hmf.orb.aidl.RemoteDiscoveryService
    protected abstract Set<String> exportRemoteModule();

    @Override // com.huawei.hmf.orb.aidl.RemoteDiscoveryService, android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.aidlInvoke;
    }
}

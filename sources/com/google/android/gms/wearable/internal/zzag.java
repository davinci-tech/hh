package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityClient;

/* loaded from: classes8.dex */
final class zzag extends UnregisterListenerMethod<zzhg, CapabilityClient.OnCapabilityChangedListener> {
    private final CapabilityClient.OnCapabilityChangedListener zzby;

    private zzag(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, ListenerHolder.ListenerKey<CapabilityClient.OnCapabilityChangedListener> listenerKey) {
        super(listenerKey);
        this.zzby = onCapabilityChangedListener;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgg(taskCompletionSource), this.zzby);
    }
}

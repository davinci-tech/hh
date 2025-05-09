package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityClient;

/* loaded from: classes8.dex */
final class zzaf extends RegisterListenerMethod<zzhg, CapabilityClient.OnCapabilityChangedListener> {
    private final IntentFilter[] zzba;
    private final CapabilityClient.OnCapabilityChangedListener zzby;
    private final ListenerHolder<CapabilityApi.CapabilityListener> zzbz;

    private zzaf(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, IntentFilter[] intentFilterArr, ListenerHolder<CapabilityClient.OnCapabilityChangedListener> listenerHolder) {
        super(listenerHolder);
        this.zzby = onCapabilityChangedListener;
        this.zzba = intentFilterArr;
        this.zzbz = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final /* synthetic */ void registerListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgh(taskCompletionSource), this.zzby, this.zzbz, this.zzba);
    }
}

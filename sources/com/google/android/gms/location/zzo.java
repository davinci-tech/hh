package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzo extends UnregisterListenerMethod<zzaz, LocationCallback> {
    private final /* synthetic */ FusedLocationProviderClient zzaa;

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzaz zzazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        com.google.android.gms.internal.location.zzaj zza;
        zzaz zzazVar2 = zzazVar;
        zza = this.zzaa.zza(taskCompletionSource);
        try {
            zzazVar2.zzb(getListenerKey(), zza);
        } catch (RuntimeException e) {
            taskCompletionSource.trySetException(e);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzo(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zzaa = fusedLocationProviderClient;
    }
}

package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzbh;
import com.google.android.gms.internal.fitness.zzbt;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzc extends UnregisterListenerMethod<com.google.android.gms.internal.fitness.zzp, BleScanCallback> {
    private final /* synthetic */ ListenerHolder zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzc(BleClient bleClient, ListenerHolder.ListenerKey listenerKey, ListenerHolder listenerHolder) {
        super(listenerKey);
        this.zzg = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(com.google.android.gms.internal.fitness.zzp zzpVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        com.google.android.gms.internal.fitness.zzp zzpVar2 = zzpVar;
        com.google.android.gms.fitness.request.zza zzb = com.google.android.gms.fitness.request.zzd.zzt().zzb(this.zzg);
        if (zzb == null) {
            taskCompletionSource.setResult(false);
        } else {
            ((zzbt) zzpVar2.getService()).zza(new zzbh((zzae) zzb, (zzcq) zzen.zzb(taskCompletionSource)));
        }
    }
}

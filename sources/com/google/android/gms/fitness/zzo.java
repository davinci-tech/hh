package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.zzal;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzar;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzcd;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzo extends UnregisterListenerMethod<zzas, OnDataPointListener> {
    private final /* synthetic */ ListenerHolder zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzo(SensorsClient sensorsClient, ListenerHolder.ListenerKey listenerKey, ListenerHolder listenerHolder) {
        super(listenerKey);
        this.zzg = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzas zzasVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzas zzasVar2 = zzasVar;
        zzal zzd = zzan.zzw().zzd(this.zzg);
        if (zzd == null) {
            taskCompletionSource.setResult(false);
        } else {
            ((zzcd) zzasVar2.getService()).zza(new zzar((zzt) zzd, (PendingIntent) null, (zzcq) zzen.zzb(taskCompletionSource)));
        }
    }
}

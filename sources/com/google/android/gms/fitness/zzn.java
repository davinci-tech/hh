package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzao;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzcd;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzn extends RegisterListenerMethod<zzas, OnDataPointListener> {
    private final /* synthetic */ ListenerHolder zzg;
    private final /* synthetic */ SensorRequest zzy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzn(SensorsClient sensorsClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, SensorRequest sensorRequest) {
        super(listenerHolder);
        this.zzg = listenerHolder2;
        this.zzy = sensorRequest;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final /* synthetic */ void registerListener(zzas zzasVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzcd) zzasVar.getService()).zza(new zzao(this.zzy, zzan.zzw().zzc(this.zzg), null, zzen.zza(taskCompletionSource)));
    }
}

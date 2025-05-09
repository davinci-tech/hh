package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SensorRequest;

/* loaded from: classes8.dex */
final class zzec extends zzax {
    private final /* synthetic */ com.google.android.gms.fitness.data.zzt zzfu;
    private final /* synthetic */ PendingIntent zzfv;
    private final /* synthetic */ SensorRequest zzy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzec(zzea zzeaVar, GoogleApiClient googleApiClient, SensorRequest sensorRequest, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzy = sensorRequest;
        this.zzfu = zztVar;
        this.zzfv = pendingIntent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.fitness.zzax
    /* renamed from: zza */
    public final Status createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzas zzasVar) throws RemoteException {
        ((zzcd) zzasVar.getService()).zza(new com.google.android.gms.fitness.request.zzao(this.zzy, this.zzfu, this.zzfv, new zzen(this)));
    }

    @Override // com.google.android.gms.internal.fitness.zzax, com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }
}

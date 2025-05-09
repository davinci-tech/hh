package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

/* loaded from: classes8.dex */
final class zzdh extends zzae<GoalsResult> {
    private final /* synthetic */ GoalsReadRequest zzfd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdh(zzdg zzdgVar, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.zzfd = goalsReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzab zzabVar) throws RemoteException {
        ((zzbx) zzabVar.getService()).zza(new GoalsReadRequest(this.zzfd, new zzdi(this)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }
}

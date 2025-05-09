package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzm extends TaskApiCall<zzaz, LocationAvailability> {
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* synthetic */ void doExecute(zzaz zzazVar, TaskCompletionSource<LocationAvailability> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzazVar.zza());
    }

    zzm(FusedLocationProviderClient fusedLocationProviderClient) {
    }
}

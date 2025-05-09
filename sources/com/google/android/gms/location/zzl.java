package com.google.android.gms.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzl extends TaskApiCall<zzaz, Location> {
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* synthetic */ void doExecute(zzaz zzazVar, TaskCompletionSource<Location> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzazVar.getLastLocation());
    }

    zzl(FusedLocationProviderClient fusedLocationProviderClient) {
    }
}

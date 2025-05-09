package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes8.dex */
final class zzb<T> extends zzn<Status> {
    private T zzaw;
    private ListenerHolder<T> zzax;
    private zzc<T> zzay;

    static <T> PendingResult<Status> zza(GoogleApiClient googleApiClient, zzc<T> zzcVar, T t) {
        return googleApiClient.enqueue(new zzb(googleApiClient, t, googleApiClient.registerListener(t), zzcVar));
    }

    private zzb(GoogleApiClient googleApiClient, T t, ListenerHolder<T> listenerHolder, zzc<T> zzcVar) {
        super(googleApiClient);
        this.zzaw = (T) Preconditions.checkNotNull(t);
        this.zzax = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
        this.zzay = (zzc) Preconditions.checkNotNull(zzcVar);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        this.zzay.zza(zzhgVar, this, this.zzaw, this.zzax);
        this.zzaw = null;
        this.zzax = null;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        this.zzaw = null;
        this.zzax = null;
        return status;
    }
}

package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.ChannelApi;
import javax.annotation.Nullable;

/* loaded from: classes8.dex */
final class zzan extends zzn<Status> {
    private final String zzce;
    private ChannelApi.ChannelListener zzcf;

    zzan(GoogleApiClient googleApiClient, ChannelApi.ChannelListener channelListener, @Nullable String str) {
        super(googleApiClient);
        this.zzcf = (ChannelApi.ChannelListener) Preconditions.checkNotNull(channelListener);
        this.zzce = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        zzhgVar.zza(this, this.zzcf, this.zzce);
        this.zzcf = null;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        this.zzcf = null;
        return status;
    }
}

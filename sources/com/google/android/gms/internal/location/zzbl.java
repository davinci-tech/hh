package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

/* loaded from: classes8.dex */
final class zzbl extends LocationServices.zza<LocationSettingsResult> {
    private final /* synthetic */ LocationSettingsRequest zzdp;
    private final /* synthetic */ String zzdq = null;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzdp, this, this.zzdq);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new LocationSettingsResult(status);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbl(zzbk zzbkVar, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        this.zzdp = locationSettingsRequest;
    }
}

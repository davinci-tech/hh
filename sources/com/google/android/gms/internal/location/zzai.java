package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;

/* loaded from: classes8.dex */
abstract class zzai extends LocationServices.zza<Status> {
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    public zzai(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
}

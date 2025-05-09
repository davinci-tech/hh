package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
abstract class zzal extends zzaj<Status> {
    zzal(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Result createFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess());
        return status;
    }
}

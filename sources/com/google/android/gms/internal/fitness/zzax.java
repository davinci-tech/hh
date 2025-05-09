package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes8.dex */
abstract class zzax extends zzav<Status> {
    zzax(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public Status createFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess());
        return status;
    }
}

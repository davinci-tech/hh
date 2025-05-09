package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes8.dex */
abstract class zzs<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzp> {
    public zzs(GoogleApiClient googleApiClient) {
        super(zzp.API, googleApiClient);
    }
}

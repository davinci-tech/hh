package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes8.dex */
abstract class zzae<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzab> {
    public zzae(GoogleApiClient googleApiClient) {
        super(zzab.API, googleApiClient);
    }
}

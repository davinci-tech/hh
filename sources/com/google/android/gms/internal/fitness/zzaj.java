package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
abstract class zzaj<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzag> {
    public zzaj(GoogleApiClient googleApiClient) {
        super(zzag.API, googleApiClient);
    }
}

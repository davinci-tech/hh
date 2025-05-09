package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* loaded from: classes8.dex */
final class zzdz extends zzci {
    private final BaseImplementation.ResultHolder<ListSubscriptionsResult> zzev;

    private zzdz(BaseImplementation.ResultHolder<ListSubscriptionsResult> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzch
    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.zzev.setResult(listSubscriptionsResult);
    }

    /* synthetic */ zzdz(BaseImplementation.ResultHolder resultHolder, zzdu zzduVar) {
        this(resultHolder);
    }
}

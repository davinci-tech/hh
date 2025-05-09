package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* loaded from: classes8.dex */
public final class zzo extends zzbl {
    private final BaseImplementation.ResultHolder<DataSourcesResult> zzev;

    public zzo(BaseImplementation.ResultHolder<DataSourcesResult> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzbk
    public final void zza(DataSourcesResult dataSourcesResult) {
        this.zzev.setResult(dataSourcesResult);
    }
}

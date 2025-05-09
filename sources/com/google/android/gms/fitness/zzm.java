package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zzm implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzm();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((DataSourcesResult) result).getDataSources();
    }

    private zzm() {
    }
}

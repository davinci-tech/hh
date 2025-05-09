package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DataTypeResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zze implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zze();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((DataTypeResult) result).getDataType();
    }

    private zze() {
    }
}

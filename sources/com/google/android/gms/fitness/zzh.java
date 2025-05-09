package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.GoalsResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zzh implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzh();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((GoalsResult) result).getGoals();
    }

    private zzh() {
    }
}

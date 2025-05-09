package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zzj implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzj();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((DailyTotalResult) result).getTotal();
    }

    private zzj() {
    }
}

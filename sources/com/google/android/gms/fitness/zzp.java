package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.SessionStopResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zzp implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzp();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((SessionStopResult) result).getSessions();
    }

    private zzp() {
    }
}

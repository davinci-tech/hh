package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* loaded from: classes8.dex */
final /* synthetic */ class zzl implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzf = new zzl();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((ListSubscriptionsResult) result).getSubscriptions();
    }

    private zzl() {
    }
}

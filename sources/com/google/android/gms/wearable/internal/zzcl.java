package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes8.dex */
final /* synthetic */ class zzcl implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzbx = new zzcl();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((DataApi.DataItemResult) result).getDataItem();
    }

    private zzcl() {
    }
}

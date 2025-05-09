package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.NodeApi;

/* loaded from: classes2.dex */
final /* synthetic */ class zzfn implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzbx = new zzfn();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((NodeApi.GetConnectedNodesResult) result).getNodes();
    }

    private zzfn() {
    }
}

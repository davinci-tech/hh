package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final /* synthetic */ class zzac implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzbx = new zzac();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        return ((CapabilityApi.GetAllCapabilitiesResult) result).getAllCapabilities();
    }

    private zzac() {
    }
}

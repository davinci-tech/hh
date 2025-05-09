package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes8.dex */
final /* synthetic */ class zzap implements PendingResultUtil.ResultConverter {
    static final PendingResultUtil.ResultConverter zzbx = new zzap();

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result result) {
        ChannelClient.Channel zza;
        zza = zzao.zza(((ChannelApi.OpenChannelResult) result).getChannel());
        return zza;
    }

    private zzap() {
    }
}

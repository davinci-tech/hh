package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.ChannelApi;

/* loaded from: classes8.dex */
final class zzha extends zzgm<ChannelApi.OpenChannelResult> {
    public zzha(BaseImplementation.ResultHolder<ChannelApi.OpenChannelResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzfq zzfqVar) {
        zza((zzha) new zzam(zzgd.zzb(zzfqVar.statusCode), zzfqVar.zzck));
    }
}

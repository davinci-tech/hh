package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final class zzgr extends zzgm<CapabilityApi.GetCapabilityResult> {
    public zzgr(BaseImplementation.ResultHolder<CapabilityApi.GetCapabilityResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzdk zzdkVar) {
        zza((zzgr) new zzy(zzgd.zzb(zzdkVar.statusCode), zzdkVar.zzdq == null ? null : new zzw(zzdkVar.zzdq)));
    }
}

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final class zzhd extends zzgm<CapabilityApi.RemoveLocalCapabilityResult> {
    public zzhd(BaseImplementation.ResultHolder<CapabilityApi.RemoveLocalCapabilityResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzfy zzfyVar) {
        zza((zzhd) new zzu(zzgd.zzb(zzfyVar.statusCode)));
    }
}
